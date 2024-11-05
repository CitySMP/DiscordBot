package de.citysmp.discordbot.command;

import de.citysmp.discordbot.DiscordBot;
import de.eztxm.ezlib.config.JsonConfig;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ReloadConfigCommand {

    public void execute(SlashCommandInteractionEvent event) {
        DiscordBot.setConfig(new JsonConfig("data", "config.json"));
        event.reply("Configuration has been successfully reloaded.").setEphemeral(true).queue();
    }
}
