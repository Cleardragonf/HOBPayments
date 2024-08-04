package com.cleardragonf.hobpayments.economy;

import java.util.HashMap;
import java.util.Map;

public class EconomyManager {
    private final Map<String, Double> playerBalances = new HashMap<>();

    public void setBalance(String playerName, double amount) {
        playerBalances.put(playerName, amount);
    }

    public double getBalance(String playerName) {
        return playerBalances.getOrDefault(playerName, 0.0);
    }

    public void addBalance(String playerName, double amount) {
        double currentBalance = getBalance(playerName);
        setBalance(playerName, currentBalance + amount);
    }

    public void subtractBalance(String playerName, double amount) {
        double currentBalance = getBalance(playerName);
        setBalance(playerName, currentBalance - amount);
    }
}
