package pl.coderslab.mazi85.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.mazi85.entity.User;

import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (email, username, password)  VALUES (?,?,?)";

    private static final String READ_USER_QUERY =
            "SELECT * FROM users WHERE id=?";


    public User create(User user) throws SQLException {
        try (Connection connect = DbUtil.connect("workshop2")) {
            PreparedStatement psCreate = connect.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            psCreate.setString(1, user.getEmail());
            psCreate.setString(2, user.getUserName());
            psCreate.setString(3, hashPassword(user.getPassword()));
            psCreate.executeUpdate();
            ResultSet rsKeys = psCreate.getGeneratedKeys();
            if (rsKeys.next()) {
                user.setId(rsKeys.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            throw new SQLException();
        }

    }

    public User read(int userId) throws SQLException {


        try (Connection connect = DbUtil.connect("workshop2")) {
            PreparedStatement ps = connect.prepareStatement(READ_USER_QUERY);
            ps.setInt(1, userId);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setPassword(rs.getString(4));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }


    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

}
