package de.citysmp.discordbot;

import de.citysmp.discordbot.database.DatabaseProcessor;
import de.citysmp.discordbot.listener.GuildListener;
import de.eztxm.ezlib.api.database.SQLConnection;
import de.eztxm.ezlib.config.JsonConfig;
import de.eztxm.ezlib.database.SQLiteConnection;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class DiscordBot {
    @Getter
    @Setter
    private static JsonConfig config;
    @Getter
    private static DatabaseProcessor database;
    @Getter
    private static JDA jda;

    public static void main(String[] args) {
        config = new JsonConfig("data", "config.json");
        if (config.get("Database") == null) {
            System.out.println("Database not set!");
            return;
        }
        var databaseObject = config.get("Database").asJsonObject();
        var sqliteObject = databaseObject.get("SQLite").asJsonObject();
        SQLConnection connection = new SQLiteConnection(
                sqliteObject.get("Path").asString(),
                sqliteObject.get("Filename").asString()
        );
        database = new DatabaseProcessor(connection);
        if (config.get("Token") == null) {
            System.out.println("Token not set!");
            return;
        }
        jda = JDABuilder.createDefault(config.get("Token").asString())
                .addEventListeners(new GuildListener())
                .build();
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(
                Commands.slash("reload-config", "Reloads the configuration file."),
                Commands.slash("whitelist", "Manage the whitelist.")
                        .addSubcommands(new SubcommandData("add", "Add a member to the whitelist.")
                                .addOption(OptionType.USER, "user", "The member.")
                                .addOption(OptionType.STRING, "username", "The minecraft username of the member.")
                        )
                        .addSubcommands(new SubcommandData("remove", "Add a member to the whitelist.")
                                .addOption(OptionType.USER, "user", "The member.")
                        )
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.PRIORITY_SPEAKER))
        ).queue();
    }
}
