package pl.coderslab.mazi85;

import pl.coderslab.mazi85.dao.UserDao;
import pl.coderslab.mazi85.entity.User;

import java.sql.SQLException;

public class MainTestApp {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDao();
        User user = new User("oksy@oksy.pl", "paciulok85", "qwerty");
        User user1 = new User("hyzio@oksy.pl", "Hyzio", "12345");
        User user2 = new User("dyzio@oksy.pl", "Dyzio", "asdfg");
        User user3 = new User("zyzio@oksy.pl", "Zyzio", "zxcvb");

        userDao.create(user);
        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);
        user = userDao.read(user.getId());
        System.out.println(user);
        user.setUserName("paciulok90");
        userDao.update(user);
        user=userDao.read(user.getId());
        System.out.println(user);
        userDao.delete(user.getId());


        for (User u : userDao.findAll()) {
            System.out.println(u);
        }
        userDao.updatePassword(user3,"Abrakadabra");

        for (User u : userDao.findAll()) {
            System.out.println(u);
        }
    }
}
