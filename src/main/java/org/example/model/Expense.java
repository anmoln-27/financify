package org.example.model;
import java.time.LocalDate;

public class Expense {
    private int id;
    private String type;      // "INCOME" or "EXPENSE"
    private String category;
    private double amount;
    private String note;
    private LocalDate date;

    public Expense() {} // empty constructor (needed for flexibility)

    public Expense(String type, String category, double amount, String note, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}