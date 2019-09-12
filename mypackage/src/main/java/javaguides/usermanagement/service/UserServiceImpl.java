package javaguides.usermanagement.service;

import javaguides.usermanagement.dao.UserDAO;
import javaguides.usermanagement.dao.UserDaoFactory;
import javaguides.usermanagement.exception.DBException;
import javaguides.usermanagement.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

        /*private static UserDAO userDAO;

        public UserServiceImpl() {
            userDAO = getInstance();
        }

        public static UserDAO getInstance() {
            if (userDAO == null) {
            userDAO = UserDaoFactory.getUserDAO();
            }
        return userDAO;
        }*/

        //private static UserDAO userDAO = UserDAO.getInstance();
        //1)нужно создать UserDAO с помощью гетинстанс т к у UserDao интерфейс нет гетинтсанс метода

    private static UserServiceImpl userServiceImpl;
    private static UserDAO userDAO;

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {

            if (userServiceImpl == null) {
                userDAO = UserDaoFactory.getUserDAO();
                userServiceImpl = new UserServiceImpl();
            }
            return userServiceImpl;
    }










    /*public boolean userIsExist(String login, String password)  {
        try {
            return userDAO.userIsExist(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/


        /*public String getRoleByLoginPassword(String login, String password)  {
            try {
            return  userDAO.getRoleByLoginPassword(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }return null;
        }*/


        public  List<User> listUser() {
            return userDAO.selectAllUsers();
        }

        public void deleteUser(int id) throws SQLException {
            userDAO.deleteDAO(id);
        }

        public User selectUser(int id) throws SQLException {
            return (User) userDAO.selectUser(id);
        }

        public User getUserbyLoginPassword(String login, String password) throws SQLException {
            return (User) userDAO.selectUserByLoginPassword(login, password);
        }

        public void insertUser(String name, int age, String role) throws SQLException {
            User newUser = new User(name, age, role);
            try {
                userDAO.insertDAO(newUser);
            } catch (DBException db) {
              for (Throwable e : db) {
                if (e instanceof DBException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((DBException) e).getSQLState());
                    System.err.println("Error Code: " + ((DBException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = db.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
            }
        }


        public void insertUserByLogin(String login, String password, String name, int age, String role) throws SQLException {
            User newUser = new User(login, password, name, age,role);
        try {
              userDAO.insertDAO(newUser);
        } catch (DBException db) {
            for (Throwable e : db) {
                if (e instanceof DBException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((DBException) e).getSQLState());
                    System.err.println("Error Code: " + ((DBException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = db.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
        }

        public void updateUser(int id, String name, int age, String role) {
            User newUser = new User(id, name, age, role);
                try {
                    userDAO.updateDAO(newUser);
                } catch (SQLException db) {
                    System.err.println(db.getMessage());//7 пункт
                }
            }
        }
