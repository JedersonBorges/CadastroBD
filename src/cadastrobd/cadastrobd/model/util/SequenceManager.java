package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SequenceManager {
    public static int nextId(String tableName, String columnName) {
        int nextId = 1;
        try (Connection conn = ConectorBD.getConnection();
             Statement stmt = conn.createStatement()) {
            String query = "SELECT MAX(" + columnName + ") FROM " + tableName;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }
}
