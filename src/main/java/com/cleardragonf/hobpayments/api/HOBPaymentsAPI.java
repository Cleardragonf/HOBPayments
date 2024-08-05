// HOBPaymentsAPI.java
package com.cleardragonf.hobpayments.api;

import com.cleardragonf.hobpayments.HOBPayments;
import com.cleardragonf.hobpayments.economy.EconomyManager;

public class HOBPaymentsAPI {
    private static final EconomyManager economyManager = HOBPayments.economyManager;

    public static void setBalance(String playerName, double amount) {
        economyManager.setBalance(playerName, amount);
    }

    public static double getBalance(String playerName) {
        return economyManager.getBalance(playerName);
    }

    public static void addBalance(String playerName, double amount) {
        economyManager.addBalance(playerName, amount);
    }

    public static void subtractBalance(String playerName, double amount) {
        economyManager.subtractBalance(playerName, amount);
    }
}
