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

    public void update(String table, String key, String value, String conditions) {
        this.connection.putAsync("UPDATE ? SET ?=? WHERE ?", table, key, value, conditions).join();
    }

    public void update(String table, String keysEqualsValues) {
        this.connection.putAsync("UPDATE ? SET ?", table, keysEqualsValues).join();
    }

    public void update(String table, String conditions, String... keysEqualsValues) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keysEqualsValues.length; i++) {
            if (i == keysEqualsValues.length - 1) {
                builder.append(keysEqualsValues[i]);
                return;
            }
            builder.append(keysEqualsValues[i]).append(",");
        }
        this.connection.putAsync("UPDATE ? SET ? WHERE ?", table, builder.toString(), conditions).join();
    }

    public void select(String table, String columns) {
        this.connection.putAsync("SELECT ? FROM ?", columns, table).join();
    }

    public void select(String table, String columns, String conditions) {
        this.connection.putAsync("SELECT ? FROM ? WHERE ?", columns, table, conditions).join();
    }

    public void delete(String table, String conditions) {
        this.connection.putAsync("DELETE FROM ? WHERE ?", table, conditions).join();
    }
}
