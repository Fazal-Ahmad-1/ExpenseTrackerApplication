package com.FazalProject.ExpenseTracker.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStats {
    private int month;
    private int year;
    private double totalSpent;
    private double averageDailySpent;
    private double highestExpense;
}
