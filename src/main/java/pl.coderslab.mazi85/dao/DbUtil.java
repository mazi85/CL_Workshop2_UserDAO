package pl.coderslab.mazi85.dao;
import java.sql.*;

public class DbUtil {

    private static final String DB_URL="jdbc:mysql://localhost:3306";
    private static final String DB_USER="root";
    private static final String DB_PASS="coderslab";
    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";


    public static Connection connect() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        return connection;
    }

    public static Connection connect(String dbName) throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL + "/" + dbName,DB_USER,DB_PASS);
        return connection;
    }

// Wszystkie update bez parametryzacji INSERTY, CREATE, DELETE, ALTER TABLE
    public static void execute(Connection conn, String query) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // INSERTY z parametryzacją
    public static void insert(Connection conn, String query, String... params) {
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
                printFromResult(resultSet,columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT parametryzowane (i nie kiedy nie podamy params) -> ResultSet
    public static ResultSet resultFromQuery(Connection conn, String query, String... params) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(query);
        int i=0;
        for (String param : params) {
            statement.setString(++i,param);
        }
        return statement.executeQuery();
    }

    // drukowanie z ResultSet
    public static void printFromResult(ResultSet resultSet, String... columnNames) throws SQLException {

        StringBuilder sb = new StringBuilder();
        while (resultSet.next()) {
            for (String columnName : columnNames) {
                sb.append(columnName).append(": ")
                        .append(resultSet.getString(columnName)).append("| ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    // DELETE WHERE id=
    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName))) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
