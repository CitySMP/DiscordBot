package de.citysmp.discordbot;

import de.eztxm.ezlib.api.database.SQLConnection;
import de.eztxm.ezlib.config.JsonConfig;
import de.eztxm.ezlib.database.SQLiteConnection;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    @Getter
    private static JsonConfig config;
    @Getter
    private static SQLConnection connection;
    @Getter
    private static JDA jda;

    public static void main(String[] args) {
        config = new JsonConfig("data", "config.json");
        if (config.get("Database") == null) {
            System.out.println("Database not set!");
            return;
        }
        var database = config.get("Database").asJsonObject();
        var sqlite = database.get("SQLite").asJsonObject();
        connection = new SQLiteConnection(
                sqlite.get("Path").asString(),
                sqlite.get("Filename").asString()
        );
        if (config.get("Token") == null) {
            System.out.println("Token not set!");
            return;
        }
        jda = JDABuilder.createDefault(config.get("Token").asString())

                .build();
    }
}