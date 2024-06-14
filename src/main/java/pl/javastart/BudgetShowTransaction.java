package pl.javastart;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BudgetShowTransaction {
    private static final String SHOW_INCOMES = "1";
    private static final String SHOW_EXPENSES = "2";

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        BudgetDao budgetDao = new BudgetDao();
        System.out.println("Jakie transakcje chcesz wyświetlić?");
        System.out.println(SHOW_INCOMES + " - przychody");
        System.out.println(SHOW_EXPENSES + " - wydatki");
        String choice = scanner.nextLine();
        try {
            List<Transaction> transactions = budgetDao.show(choice);
            for (Transaction transaction : transactions) {
                System.out.println("Lista transakcji: " + transaction.getType());
                System.out.println("id: " + transaction.getId() + " , description: " + transaction.getDescription() + " , amount: " + transaction.getAmount() + " ,date: " + transaction.getDate());
            }
        } catch (
                SQLException e) {
            System.out.println("Wystąpił błąd w trakcie wyświetlania rekordów z bazy danych" + e.getMessage());
        }
    }
}
