package pl.sdacademy.java.hibernate.workshop11;

import pl.sdacademy.java.hibernate.utils.ApplicationPropertiesProvider;

import java.util.Properties;
import java.util.Scanner;

/*
    Przygotuj metodę usuwającą kraj według podanego id, zwracającą true jeśli istniał i false jeśli nie (find(...) zwróci null).

    Użyj EntityManager.remove(...) na zarządzanym obiekcie.

    Obsłuż wyjątek PersistenceException – np. wycofanie transakcji i rzucenie wyjątku dalej.

    Przetestuj następujące scenariusze:
        * Kraj nie istnieje (zwróć false); dowolny id
        * Kraj dodany w poprzednim ćwiczeniu (usuń i zwróć true); Narnia, 110
        * Kraju nie da się usunąć, ponieważ jest używany jako FK (rzuć wyjątek); Canada, 20
 */
public class Workshop11 {
    public static void main(String[] args) {
        System.out.println("Podaj id kraju:");
        final long countryId = Long.parseLong(new Scanner(System.in).nextLine());

        final var result = removeCountry(ApplicationPropertiesProvider.getSakilaProperties(), countryId);
        System.out.println("Wynik: " + result);
    }

    public static boolean removeCountry(Properties properties, long countryId) {
        throw new UnsupportedOperationException("TODO");
    }
}
