package pl.javastart;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BudgetAddTransaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj typ transakcji, którą chcesz dodać (przychód / wydatek)");
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

        Transaction transaction = new Transaction(type, description, amount, date);
        BudgetDao budgetDao = new BudgetDao();
        budgetDao.add(transaction);
    }
}
