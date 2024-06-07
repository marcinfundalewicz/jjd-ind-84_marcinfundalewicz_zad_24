package pl.javastart;

import java.util.Scanner;

public class BudgetApp {

    private static final String EXIT = "0";
    private static final String ADD_TRANSACTION = "1";
    private static final String MODIFY_TRANSACTION = "2";
    private static final String SHOW_TRANSACTIONS = "3";
    private static final String DELETE_TRANSACTION = "4";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
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
                    return;
                }
                case ADD_TRANSACTION -> BudgetAddTransaction.main(new String[0]);
                case MODIFY_TRANSACTION -> BudgetModifyTransaction.main(new String[0]);
                case SHOW_TRANSACTIONS -> BudgetShowTransaction.main(new String[0]);
                case DELETE_TRANSACTION -> BudgetDeleteTransaction.main(new String[0]);
                default -> System.out.println("Nieprawidłowa opcja");
            }
        }
    }
}
