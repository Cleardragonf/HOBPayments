package com.cleardragonf.hobpayments.commands;

import net.minecraft.network.chat.Component;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import com.cleardragonf.hobpayments.economy.EconomyManager;

import java.util.function.Supplier;

public class EconomyCommands {
    private static final EconomyManager economyManager = new EconomyManager();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("balance")
                .executes(context -> {
                    String playerName = String.valueOf(context.getSource().getPlayerOrException().getName());
                    double balance = economyManager.getBalance(playerName);
                    Component message = Component.literal("Your balance is: " + balance);
                    context.getSource().sendSuccess((Supplier<Component>) message, false);
                    return Command.SINGLE_SUCCESS;
                }));

        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("pay")
                .then(Commands.argument("amount", DoubleArgumentType.doubleArg())
                        .executes(context -> {
                            double amount = DoubleArgumentType.getDouble(context, "amount");
                            String playerName = String.valueOf(context.getSource().getPlayerOrException().getName());
                            economyManager.addBalance(playerName, amount);
                            Component message = Component.literal("Paid: " + amount);
                            context.getSource().sendSuccess((Supplier<Component>) message, false);
                            return Command.SINGLE_SUCCESS;
                        })));

        // Command to set player balance, restricted to console only
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("set")
                .requires(source -> source.getEntity() == null) // Ensure the command is run from the console
                .then(Commands.argument("player", StringArgumentType.word())
                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg())
                                .executes(context -> {
                                    String playerName = StringArgumentType.getString(context, "player");
                                    double amount = DoubleArgumentType.getDouble(context, "amount");
                                    economyManager.setBalance(playerName, amount);
                                    Component message = Component.literal("Set balance of " + playerName + " to: " + amount);
                                    context.getSource().sendSuccess((Supplier<Component>) message, false);
                                    return Command.SINGLE_SUCCESS;
                                }))));
    }
}