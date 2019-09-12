package javaguides.usermanagement.service;

import javaguides.usermanagement.exception.DBException;

import java.sql.SQLException;
import java.util.List;

public interface UserService<T> {

    void insertUser(String name, int age, String role) throws SQLException, DBException;
    void updateUser(int id, String name, int age, String role) throws SQLException;;
    void deleteUser(int id) throws SQLException;
    List<T> listUser();
}
