package javaguides.usermanagement.dao;

import javaguides.usermanagement.exception.DBException;
import javaguides.usermanagement.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO<Entity> {

          void insertDAO(Entity model) throws SQLException, DBException;

          void updateDAO(User user) throws SQLException;

          boolean deleteDAO(int id) throws SQLException;

          Entity selectUser(int id) throws SQLException;

          String getRoleByLoginPassword(String login, String password) throws SQLException;

          Entity selectUserByLoginPassword(String login, String password) throws SQLException;

          List<Entity> selectAllUsers();

          //boolean userIsExist(String login, String password) throws SQLException;







}
