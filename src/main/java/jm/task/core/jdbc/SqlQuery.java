package jm.task.core.jdbc;

public enum SqlQuery {
    CREATE("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT UNIQUE NOT NULL, name VARCHAR(45), lastName VARCHAR(45), age INT, PRIMARY KEY (id))"),
    DROP("DROP TABLE IF EXISTS users CASCADE"),
    SELECT("SELECT * FROM users"),
    SELECT_USER("SELECT * FROM users WHERE name = ? AND lastName = ? AND age = ?"),
    SELECT_BY_ID("SELECT * FROM users WHERE id = ?"),
    INSERT("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)"),
    UPDATE_USER("UPDATE users SET name = ?, lastName = ?, age = ? WHERE id = ?"),
    DELETE("DELETE FROM users WHERE id = ?"),
    TRUNCATE("TRUNCATE TABLE users");

    String query;

    SqlQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}