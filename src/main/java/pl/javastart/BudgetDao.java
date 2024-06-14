package pl.javastart;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BudgetDao {
    private Connection connection;

    public BudgetDao() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget?serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void add(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (type, description, amount, date) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, transaction.getType());
        preparedStatement.setString(2, transaction.getDescription());
        preparedStatement.setBigDecimal(3, transaction.getAmount());
        preparedStatement.setDate(4, (Date) transaction.getDate());
        preparedStatement.executeUpdate();
    }

    boolean modify(Transaction transaction) throws SQLException {
        String sql = "UPDATE transaction SET type = ?, description = ?, amount = ?, date = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, transaction.getType());
        preparedStatement.setString(2, transaction.getDescription());
        preparedStatement.setBigDecimal(3, transaction.getAmount());
        preparedStatement.setDate(4, (Date) transaction.getDate());
        preparedStatement.setLong(5, transaction.getId());
        int updatedRows = preparedStatement.executeUpdate();
        return updatedRows != 0;
    }

    List<Transaction> show(String transactionType) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE (type = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, transactionType);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Long transactionId = result.getLong("id");
            String type = result.getString("type");
            String description = result.getString("description");
            BigDecimal amount = result.getBigDecimal("amount");
            Date date = result.getDate("date");
            Transaction transaction = new Transaction(transactionId, type, description, amount, date);
            transactions.add(transaction);
        }
        return transactions;
    }

    boolean delete(long transactionId) throws SQLException {
        String sql = "DELETE FROM transaction WHERE (id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, transactionId);
        int updatedRows = preparedStatement.executeUpdate();
        return updatedRows != 0;
    }

    void close() throws SQLException {
        connection.close();
    }
}
