package pl.coderslab.mazi85.dao;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (email, username, password)  VALUES (?,?,?)";

}
