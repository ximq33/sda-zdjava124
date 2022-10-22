package pl.sdacademy.java.jdbc.workshop4;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.jdbc.tests.TestDbHelper;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.catchThrowable;

class Workshop4Test {

    @BeforeEach
    void setUp() throws Exception {
        TestDbHelper.importSakilaDatabase();
    }

    @AfterEach
    void tearDown() {
        TestDbHelper.cleanUp();
    }

    @Test
    void shouldCreateRentalAndPayment() throws RentalException {
        //GIVEN
        final var rentalsCount = TestDbHelper.query("select count(*) from rental;", Long.class).orElseThrow();
        final var paymentsCount = TestDbHelper.query("select count(*) from payment;", Long.class).orElseThrow();

        //WHEN
        final var rentalId = Workshop4.rentDvd(TestDbHelper.JDBC_URL, 10, 3,1);

        //THEN
        final var paymentId = TestDbHelper.query("select payment_id from payment where rental_id = ?;", Integer.class, rentalId.orElse(-1));
        final var paymentAmount = TestDbHelper.query("select amount from payment where payment_id = ?", BigDecimal.class, paymentId.orElse(-1));
        final var newRentalsCount = TestDbHelper.query("select count(*) from rental;", Long.class).orElseThrow();
        final var newPaymentsCount = TestDbHelper.query("select count(*) from payment;", Long.class).orElseThrow();

        final var softly = new SoftAssertions();
        softly.assertThat(rentalId).isNotEmpty();
        softly.assertThat(paymentId).isNotEmpty();
        softly.assertThat(paymentAmount).isNotEmpty();
        softly.assertThat(paymentAmount.get()).isEqualByComparingTo("4.99");
        softly.assertThat(newRentalsCount - rentalsCount).isEqualTo(1);
        softly.assertThat(newPaymentsCount - paymentsCount).isEqualTo(1);
        softly.assertAll();
    }

    @Test
    void shouldNotCreateRentalOrPaymentIfMissingRentalRate() {
        //GIVEN
        final var rentalsCount = TestDbHelper.query("select count(*) from rental;", Long.class).orElseThrow();
        final var paymentsCount = TestDbHelper.query("select count(*) from payment;", Long.class).orElseThrow();
        TestDbHelper.execute("update film set rental_rate = 0 where film_id = 2");

        //WHEN
        final var thrown = catchThrowable( () -> Workshop4.rentDvd(TestDbHelper.JDBC_URL, 10, 3,1));

        //THEN
        final var newRentalsCount = TestDbHelper.query("select count(*) from rental;", Long.class).orElseThrow();
        final var newPaymentsCount = TestDbHelper.query("select count(*) from payment;", Long.class).orElseThrow();

        final var softly = new SoftAssertions();
        softly.assertThat(thrown)
                .isNotNull()
                .isInstanceOf(RentalException.class)
                .hasMessage("Missing rental rate for inventoryId: 10");


        softly.assertThat(newRentalsCount).isEqualTo(rentalsCount);
        softly.assertThat(newPaymentsCount).isEqualTo(paymentsCount);
        softly.assertAll();
    }

}
