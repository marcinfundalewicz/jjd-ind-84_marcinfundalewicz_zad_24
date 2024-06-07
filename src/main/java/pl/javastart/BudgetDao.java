package pl.javastart;

import java.math.BigDecimal;
import java.sql.*;

public class BudgetDao {
    private Connection connection;

    public BudgetDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget?serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void add(Transaction transaction) {
        String sql = "INSERT INTO transaction (type, description, amount, date) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setDate(4, (Date) transaction.getDate());

            int updatedRows = preparedStatement.executeUpdate();

            System.out.println("Dodano nowe rekordy w liczbie: " + updatedRows);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie dodawania nowego rekordu do bazy danych" + e.getMessage());
        }
    }

    void modify(Transaction transaction) {
        String sql = "UPDATE transaction SET type = ?, description = ?, amount = ?, date = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setDate(4, (Date) transaction.getDate());
            preparedStatement.setLong(5, transaction.getId());

            int updatedRows = preparedStatement.executeUpdate();

            System.out.println("Zaktualizowano rekordy w liczbie: " + updatedRows);
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie modyfikowania rekordu w bazie danych" + e.getMessage());
        }
    }

    void show(String transactionType) {
        String sql = "SELECT * FROM transaction WHERE (type = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transactionType);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String type = resultSet.getString("type");
                String description = resultSet.getString("description");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                Date date = resultSet.getDate("date");
                System.out.println("Lista transakcji: " + transactionType);
                System.out.println("id: " + id + " , description: " + description + " , amount: " + amount + " ,date: " + date);
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie wyświetlania rekordów z bazy danych" + e.getMessage());
        }
    }

    void delete(long transactionId) {
        String sql = "DELETE FROM transaction WHERE (id = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, transactionId);

            int updatedRows = preparedStatement.executeUpdate();
            System.out.println("Usunięto rekordy w liczbie: " + updatedRows);

        } catch (SQLException e) {
            System.out.println("Wystąpił błąd w trakcie usuwania rekordu z bazy danych" + e.getMessage());
        }
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
