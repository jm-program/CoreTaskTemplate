package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface AdapterDB {
    Connection connect() throws ClassNotFoundException, SQLException;

    void disconnect() throws SQLException;
}
