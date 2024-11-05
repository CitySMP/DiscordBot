package de.citysmp.discordbot.listener;

import de.citysmp.discordbot.command.ReloadConfigCommand;
import de.citysmp.discordbot.command.WhitelistCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        switch (event.getCommandId().toLowerCase()) {
            case "reload-config" -> new ReloadConfigCommand().execute(event);
            case "whitelist" -> new WhitelistCommand().execute(event);
        }
    }
}
