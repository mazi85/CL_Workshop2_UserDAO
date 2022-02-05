package pl.coderslab.mazi85;

import pl.coderslab.mazi85.dao.UserDao;
import pl.coderslab.mazi85.entity.User;

import java.sql.SQLException;

public class MainTestApp {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDao();
//        User marcin80 = new User("psiarz@alibaba.pl", "marcin80", "54321");
//
//        try {
//            marcin80 = userDao.create(marcin80);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(marcin80.getId());

        System.out.println(userDao.read(7));


    }
}
