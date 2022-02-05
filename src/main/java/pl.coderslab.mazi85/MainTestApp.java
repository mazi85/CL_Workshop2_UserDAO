package pl.coderslab.mazi85;

import pl.coderslab.mazi85.dao.UserDao;
import pl.coderslab.mazi85.entity.User;

import java.sql.SQLException;

public class MainTestApp {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDao();
        User user = new User("oksy@oksy.pl", "paciulok85", "qwerty");

        userDao.create(user);
        user = userDao.read(user.getId());
        System.out.println(user);
        user.setUserName("paciulok90");
        userDao.update(user);
        user=userDao.read(user.getId());
        System.out.println(user);

    }
}
