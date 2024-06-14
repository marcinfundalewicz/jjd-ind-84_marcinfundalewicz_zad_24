package pl.javastart;

import java.sql.SQLException;
import java.util.Scanner;

public class BudgetApp {

    private static final String EXIT = "0";
    private static final String ADD_TRANSACTION = "1";
    private static final String MODIFY_TRANSACTION = "2";
    private static final String SHOW_TRANSACTIONS = "3";
    private static final String DELETE_TRANSACTION = "4";

    public static void main(String[] args) {
        try {
            run();
        } catch (SQLException e) {
            System.out.println("Coś poszło nie tak");
        }
    }

    public static void run() throws SQLException {
        BudgetDao budgetDao = new BudgetDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w aplikacji bankowej BankApp");

        while (true) {
            System.out.println("Wybierz opcję:");
            System.out.println(EXIT + " - Koniec programu");
            System.out.println(ADD_TRANSACTION + " - Dodaj transakcję");
            System.out.println(MODIFY_TRANSACTION + " - Zmodyfikuj transakcję");
            System.out.println(SHOW_TRANSACTIONS + " - Wyświetl przychody / wydatki");
            System.out.println(DELETE_TRANSACTION + " - Usuń transakcję");

            String option = scanner.nextLine();
            switch (option) {
                case EXIT -> {
                    budgetDao.close();
                    return;
                }
                case ADD_TRANSACTION -> BudgetAddTransaction.run();
                case MODIFY_TRANSACTION -> BudgetModifyTransaction.run();
                case SHOW_TRANSACTIONS -> BudgetShowTransaction.run();
                case DELETE_TRANSACTION -> BudgetDeleteTransaction.run();
                default -> System.out.println("Nieprawidłowa opcja");
            }
        }
    }
}
