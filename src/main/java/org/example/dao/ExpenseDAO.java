package org.example.dao;

import org.example.model.Expense;
import org.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // Add a new expense/income record
    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO transactions (type, category, amount, note, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, expense.getType());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getNote());
            ps.setDate(5, Date.valueOf(expense.getDate()));
            ps.executeUpdate();
        }
    }

    // Retrieve all expenses/incomes
    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Expense e = new Expense();
                e.setId(rs.getInt("id"));
                e.setType(rs.getString("type"));
                e.setCategory(rs.getString("category"));
                e.setAmount(rs.getDouble("amount"));
                e.setNote(rs.getString("note"));
                e.setDate(rs.getDate("date").toLocalDate());
                list.add(e);
            }
        }
        return list;
    }

    // ✅ Delete a transaction by ID
    public void deleteExpense(int id) throws SQLException {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
