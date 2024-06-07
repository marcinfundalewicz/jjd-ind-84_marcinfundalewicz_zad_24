package pl.javastart;

import java.util.Scanner;

public class BudgetShowTransaction {
    private static final String SHOW_INCOMES = "1";
    private static final String SHOW_EXPENSES = "2";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userOption = null;
        System.out.println("Jakie transakcje chcesz wyświetlić?");
        System.out.println(SHOW_INCOMES + " - przychody");
        System.out.println(SHOW_EXPENSES + " - wydatki");
        String choice = scanner.nextLine();
        switch (choice) {
            case SHOW_INCOMES -> {
                userOption = "przychód";
                BudgetDao budgetDao = new BudgetDao();
                budgetDao.show(userOption);
            }
            case SHOW_EXPENSES -> {
                userOption = "wydatek";
                BudgetDao budgetDao = new BudgetDao();
                budgetDao.show(userOption);
            }
            default -> System.out.println("Nieprawidłowa opcja");
        }
    }
}
