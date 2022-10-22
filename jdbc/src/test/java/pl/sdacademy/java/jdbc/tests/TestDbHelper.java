package pl.sdacademy.java.jdbc.tests;

import org.h2.tools.DeleteDbFiles;
import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class TestDbHelper {
    private TestDbHelper() {
        throw new UnsupportedOperationException();
    }

    private static final String H2_DB_NAME="sakila-jdbc-test";
    private static final String H2_DB_FILE="/sakila-h2.sql";

    /**
     * JDBC URL for test database. File-based H2 database was used to ensure its persistence
     * between connections.
     * <br><br>
     * <li>Set up DB with {@link #importSakilaDatabase()}</li>
     * <li>Remove DB file with {@link #cleanUp()}</li>
     */
    public static final String JDBC_URL = "jdbc:h2:~/" + H2_DB_NAME;

    public static void importSakilaDatabase() throws IOException, SQLException {
        try (
                final var worldInputStream = TestDbHelper.class.getResourceAsStream(H2_DB_FILE);
                final var connection = DriverManager.getConnection(JDBC_URL)
        ) {
            if (worldInputStream == null) {
                throw new IllegalStateException(String.format("Missing '%s' resource!", H2_DB_FILE));
            }

            RunScript.execute(connection, new InputStreamReader(worldInputStream));
        }
    }

    public static <T> Optional<T> query(String sql, Class<T> resultType, Object... params) {
        try (final var connection = DriverManager.getConnection(JDBC_URL)) {
            final var preparedStatement = connection.prepareStatement(sql);

            applyParams(preparedStatement, params);
            final var rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.ofNullable(rs.getObject(1)).map(resultType::cast);
        }
        catch(SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public static void execute(String sql, Object... params) {
        try (final var connection = DriverManager.getConnection(JDBC_URL)) {
            final var preparedStatement = connection.prepareStatement(sql);

            applyParams(preparedStatement, params);
            preparedStatement.execute();
        }
        catch(SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private static void applyParams(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        for (int paramIndex = 1; paramIndex <= params.length; paramIndex++) {
            preparedStatement.setObject(paramIndex, params[paramIndex-1]);
        }
    }

    public static void cleanUp() {
        DeleteDbFiles.execute(System.getProperty("user.home"), H2_DB_NAME, false);
    }
}
