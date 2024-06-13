package pl.javastart;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BudgetModifyTransaction {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID transakcji, którą chcesz zmodyfikować");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Podaj typ transakcji");
        String type = scanner.nextLine();
        System.out.println("Podaj krótki opis transakcji");
        String description = scanner.nextLine();
        System.out.println("Podaj kwotę transakcji");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Podaj datę transakcji w formacie yyyy-MM-dd");
        String userDate = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            java.util.Date parsedDate = dateFormat.parse(userDate);
            date = new Date(parsedDate.getTime());
        } catch (Exception e) {
            System.out.println("Nieprawidłowy format daty");
        }
        Transaction transaction = new Transaction(id, type, description, amount, date);
        BudgetDao budgetDao = new BudgetDao();
        try {
            boolean isEverythingOk = budgetDao.modify(transaction);
            if (isEverythingOk) {
                System.out.println("Zaktualizowano rekordy w bazie danych");
            }
            else {
                System.out.println("Nie udało się zaktualizować rekordu w bazie danych");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie modyfikowania rekordu w bazie danych" + e.getMessage());
        }
    }
}
