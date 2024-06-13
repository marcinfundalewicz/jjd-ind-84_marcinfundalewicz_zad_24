package pl.javastart;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Set;

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
        preparedStatement.executeUpdate();
        return true;
    }

    ResultSet show(String transactionType) throws SQLException {
        String sql = "SELECT * FROM transaction WHERE (type = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, transactionType);
        return preparedStatement.executeQuery();
    }

    boolean delete(long transactionId) throws SQLException {
        String sql = "DELETE FROM transaction WHERE (id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, transactionId);
        preparedStatement.executeUpdate();
        return true;
    }

    void close() throws SQLException {
        connection.close();
    }
}
