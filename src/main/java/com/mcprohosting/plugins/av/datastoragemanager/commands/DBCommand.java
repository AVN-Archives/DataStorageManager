package com.mcprohosting.plugins.av.datastoragemanager.commands;

import com.gmail.favorlock.commonutils.command.SubCommandHandler;
import com.gmail.favorlock.commonutils.text.FontFormat;
import com.mcprohosting.plugins.av.datastoragemanager.api.DataAPI;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DBCommand {

    @SubCommandHandler(parent = "db", name = "showcoins")
    public void showcoins(Player sender, String[] args) {
        NetworkUser user = DataAPI.getUser(sender.getUniqueId().toString());

        if (user == null) {
            sender.sendMessage(FontFormat.translateString("&cUnable to get your coin count! :("));
            return;
        }

        sender.sendMessage(FontFormat.translateString("&aYou have &6" + user.getCoins() + " &acoins!"));
    }

    @SubCommandHandler(parent = "db", name = "setcoins", permission = "antvenomdsm.setcoins")
    public void setCoins(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(FontFormat.translateString("&cInvalid number of parameters, check usage!"));
            return;
        }

        String name = args[0];
        int coins;

        Player player = Bukkit.getPlayer(name);
        NetworkUser user = player == null ? DataAPI.retrieveUserByName(name) : DataAPI.getUser(player.getUniqueId().toString());

        if (user == null) {
            sender.sendMessage(FontFormat.translateString("&cThat user does not exists on the network!"));
            return;
        }

        try {
            if (args[1].startsWith("-")) {
                coins = Integer.parseInt(args[1].substring(1, args[1].length()));
                user.setCoins(user.getCoins() - coins);
            } else if (args[1].startsWith("+")) {
                coins = Integer.parseInt(args[1].substring(1, args[1].length()));
                user.setCoins(user.getCoins() + coins);
            } else {
                coins = Integer.parseInt(args[1]);
                user.setCoins(coins);
            }

            DataAPI.saveUser(user);
            sender.sendMessage(FontFormat.translateString(FontFormat.translateString("&aUpdate &6") + name + "'s &acoins!"));
        } catch (NumberFormatException e) {
            sender.sendMessage(FontFormat.translateString("&cYou must specify a valid integer for coins!"));
            return;
        }
    }

    @SubCommandHandler(parent = "db", name = "setrank", permission = "antvenomdsm.setcoins")
    public void setRank(CommandSender sender, String[] args) {
        if (args.length > 2) {
            sender.sendMessage(FontFormat.translateString("&cInvalid number of parameters, check usage!"));
            return;
        }

        NetworkUser user;
        if (args.length == 1) {
            if (sender instanceof Player == false) {
                sender.sendMessage(FontFormat.translateString("&cYou must provide a player name to use this command from console!"));
                return;
            }

            user = DataAPI.getUser(((Player) sender).getUniqueId().toString());
        } else {
            user = DataAPI.retrieveUserByName(args[1]);
        }

        if (user == null) {
            sender.sendMessage(FontFormat.translateString("&cUnable to retrieve user from the database!"));
            return;
        }

        if (args.length == 1) {
            user.setRank(args[0]);
        } else {
            user.setRank(args[1]);
        }

        DataAPI.saveUser(user);
    }

}
