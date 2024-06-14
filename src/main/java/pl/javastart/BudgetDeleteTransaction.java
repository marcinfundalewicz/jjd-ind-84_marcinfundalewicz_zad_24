package pl.javastart;

import java.sql.SQLException;
import java.util.Scanner;

public class BudgetDeleteTransaction {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID transakcji, którą chcesz usunąć");
        long id = scanner.nextLong();
        BudgetDao budgetDao = new BudgetDao();
        try {
            boolean isEverythingOk = budgetDao.delete(id);
            if (isEverythingOk) {
                System.out.println("Udało się usunąć");
            } else {
                System.out.println("Nie udało się usunąć");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie usuwania rekordu z bazy danych" + e.getMessage());
        }
    }
}

