package de.citysmp.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class WhitelistCommand {

    public void execute(SlashCommandInteractionEvent event) {
        assert event.getSubcommandName() != null;
        switch (event.getSubcommandName().toLowerCase()) {
            case "add" -> {

            }
            case "remove" -> {

            }
        }
    }
}
