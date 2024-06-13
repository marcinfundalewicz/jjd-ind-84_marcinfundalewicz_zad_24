package pl.javastart;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BudgetShowTransaction {
    private static final String SHOW_INCOMES = "1";
    private static final String SHOW_EXPENSES = "2";

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String userOption = null;
        BudgetDao budgetDao = new BudgetDao();
        System.out.println("Jakie transakcje chcesz wyświetlić?");
        System.out.println(SHOW_INCOMES + " - przychody");
        System.out.println(SHOW_EXPENSES + " - wydatki");
        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case SHOW_INCOMES -> {
                    userOption = "przychód";
                    ResultSet incomes = budgetDao.show(userOption);
                    while (incomes.next()) {
                        long id = incomes.getLong("id");
                        String type = incomes.getString("type");
                        String description = incomes.getString("description");
                        BigDecimal amount = incomes.getBigDecimal("amount");
                        Date date = incomes.getDate("date");
                        System.out.println("Lista transakcji: " + type);
                        System.out.println("id: " + id + " , description: " + description + " , amount: " + amount + " ,date: " + date);
                    }
                }
                case SHOW_EXPENSES -> {
                    userOption = "wydatek";
                    ResultSet expenses = budgetDao.show(userOption);
                    while (expenses.next()) {
                        long id = expenses.getLong("id");
                        String type = expenses.getString("type");
                        String description = expenses.getString("description");
                        BigDecimal amount = expenses.getBigDecimal("amount");
                        Date date = expenses.getDate("date");
                        System.out.println("Lista transakcji: " + type);
                        System.out.println("id: " + id + " , description: " + description + " , amount: " + amount + " ,date: " + date);
                    }
                }
                default -> System.out.println("Nieprawidłowa opcja");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie wyświetlania rekordów z bazy danych" + e.getMessage());
        }
    }
}
