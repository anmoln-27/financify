package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.scene.chart.PieChart;
import org.example.dao.ExpenseDAO;
import org.example.model.Expense;

import java.time.LocalDate;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML private ChoiceBox<String> typeChoice;
    @FXML private TextField categoryField;
    @FXML private TextField amountField;
    @FXML private TextField noteField;
    @FXML private TableView<Expense> table;
    @FXML private TableColumn<Expense, String> colType;
    @FXML private TableColumn<Expense, String> colCategory;
    @FXML private TableColumn<Expense, Double> colAmount;
    @FXML private TableColumn<Expense, String> colNote;
    @FXML private TableColumn<Expense, LocalDate> colDate;
    @FXML private PieChart expenseChart;
    @FXML private TableColumn<Expense, Void> colAction;

    private final ExpenseDAO dao = new ExpenseDAO();

    @FXML
    public void initialize() {
        // Setup dropdown
        typeChoice.getItems().addAll("INCOME", "EXPENSE");
        typeChoice.setValue("EXPENSE");

        // Setup table columns
        colType.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getType()));
        colCategory.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCategory()));
        colAmount.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getAmount()));
        colNote.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNote()));
        colDate.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDate()));

        // ✅ Add delete button in the "Actions" column
        colAction.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(e -> {
                    Expense selected = getTableView().getItems().get(getIndex());
                    handleDeleteExpense(selected);
                });
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        });

        // Load initial data and chart
        loadTable();
    }

    @FXML
    public void handleAddExpense() {
        try {
            String type = typeChoice.getValue();
            String category = categoryField.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());
            String note = noteField.getText().trim();

            Expense expense = new Expense(type, category, amount, note, LocalDate.now());
            dao.addExpense(expense);
            clearFields();
            loadTable();

        } catch (NumberFormatException e) {
            showAlert("Invalid amount value!");
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    private void handleDeleteExpense(Expense expense) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete this transaction?\n" + expense.getCategory() + " - " + expense.getAmount(),
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            try {
                dao.deleteExpense(expense.getId());
                loadTable();
            } catch (SQLException e) {
                showAlert("Error deleting: " + e.getMessage());
            }
        }
    }

    private void loadTable() {
        try {
            java.util.List<Expense> list = dao.getAllExpenses();
            ObservableList<Expense> data = FXCollections.observableArrayList(list);
            table.setItems(data);

            // 👇 Update the pie chart
            updateChart(list);

        } catch (SQLException e) {
            showAlert("Error loading data: " + e.getMessage());
        }
    }

    private void updateChart(java.util.List<Expense> list) {
        Map<String, Double> totals = new HashMap<>();

        // Group totals by category for EXPENSE type
        for (Expense e : list) {
            if (e.getType().equalsIgnoreCase("EXPENSE")) {
                totals.merge(e.getCategory(), e.getAmount(), Double::sum);
            }
        }

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        totals.forEach((cat, amt) -> pieData.add(new PieChart.Data(cat, amt)));

        expenseChart.setData(pieData);
        expenseChart.setLegendVisible(true);
        expenseChart.setLabelsVisible(true);
    }

    private void clearFields() {
        categoryField.clear();
        amountField.clear();
        noteField.clear();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
