package de.citysmp.discordbot.database;

import de.eztxm.ezlib.api.database.SQLConnection;
import lombok.Getter;

@Getter
public record DatabaseProcessor(SQLConnection connection) {

    /***
     * Insert values into a table.
     * @param table - Name of the table.
     * @param columns - Seperated columns by ,
     * @param values - Seperated values by ,
     */
    public void insert(String table, String columns, String values) {
        this.connection.putAsync("INSERT INTO ?(?) VALUES (?)", table, columns, values).join();
    }

    public void update(String table, String key, String value) {
        this.connection.putAsync("UPDATE ? SET ?=?", table, key, value).join();
    }

    public void update(String table, String key, String value, String condition) {
        this.connection.putAsync("UPDATE ? SET ?=? WHERE ?", table, key, value, condition).join();
    }

    public void update(String table, String keysEqualsValues) {
        this.connection.putAsync("UPDATE ? SET ?", table, keysEqualsValues).join();
    }

    public void update(String table, String condition, String... keysEqualsValues) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keysEqualsValues.length; i++) {
            if (i == keysEqualsValues.length - 1) {
                builder.append(keysEqualsValues[i]);
                return;
            }
            builder.append(keysEqualsValues[i]).append(",");
        }
        this.connection.putAsync("UPDATE ? SET ? WHERE ?", table, builder.toString(), condition).join();
    }
}
