package de.citysmp.discordbot.listener;

import de.citysmp.discordbot.DiscordBot;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Role guest = event.getGuild().getRoleById(DiscordBot.getConfig().get("Roles").asJsonObject().get("Guest").asString());
    }
}