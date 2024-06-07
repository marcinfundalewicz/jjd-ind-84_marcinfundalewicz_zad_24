package pl.javastart;

import java.util.Scanner;

public class BudgetDeleteTransaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID transakcji, którą chcesz usunąć");
        long id = scanner.nextLong();
        BudgetDao budgetDao = new BudgetDao();
        budgetDao.delete(id);
    }
}

