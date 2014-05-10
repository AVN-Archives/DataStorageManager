package com.mcprohosting.plugins.av.datastoragemanager.commands;

import com.gmail.favorlock.commonutils.command.SubCommandHandler;
import com.gmail.favorlock.commonutils.text.FontFormat;
import com.mcprohosting.plugins.av.datastoragemanager.api.DataAPI;
import com.mcprohosting.plugins.av.datastoragemanager.database.models.NetworkUser;
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

}
