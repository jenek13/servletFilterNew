package javaguides.usermanagement.dao;

import javaguides.usermanagement.exception.DBException;
import javaguides.usermanagement.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDAO<User>{

        private Connection connection;

        public UserDaoJDBCimpl(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void insertDAO(User user) throws SQLException, DBException {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO newuser (name, age) VALUES (?, ?);")) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setString(3, user.getRole());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e2) {
                    throw new DBException( e2.getMessage() +  "Произошло sql сообщение");
                }
                } finally {
                    try {
                         connection.setAutoCommit(true);
                    }catch (SQLException e2) {
                         throw new DBException(e2.toString());
                    }
                }
        }

        @Override
        public void updateDAO(User user) throws SQLException {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("UPDATE newuser SET name = ?, age= ? WHERE id = ?;");) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.setInt(3, user.getId());
                statement.setString(4, user.getRole());
                connection.commit();
                } catch (DBException e) {
                try {
                    connection.rollback();
                } catch (DBException db) {
                    System.err.println(db.getMessage());
                }
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (DBException db) {
                    System.err.println(db.getMessage());
                }
            }

        }

        @Override
        public boolean deleteDAO(int id) throws SQLException {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM newuser WHERE id = ?;");) {
            statement.setInt(1, id);//в делит запрос подставлем айдишку того юзера котрого надо удалить
            }
            return false;
        }

        @Override
        public User selectUser(int id) throws SQLException {
            User user = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,age FROM newuser WHERE id =?");) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String role = rs.getString("role");
                    user = new User(id, name, age, role);
                }
            }catch (SQLException e) {
            printSQLException(e);
            }
            return user;
        }

        @Override
        public String getRoleByLoginPassword(String login, String password) throws SQLException {
            String role = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM newuser WHERE login =? and password =?");) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    role = rs.getString("role");
                }
            }catch (SQLException e) {
                printSQLException(e);
            }
                return role;
        }

        @Override
        public User selectUserByLoginPassword(String login, String password) throws SQLException {
            User user = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT login, password, name, age, role  FROM newuser WHERE login =?");) {
                preparedStatement.setString(1, login);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String role = rs.getString("role");
                    user = new User(login, password,name, age, role);
                }
            }catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }

        @Override
        public List<User> selectAllUsers() {
            List<User> users = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM newuser");) {
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String role = rs.getString("role");
                    users.add(new User(id, name, age, role));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return users;
        }

        private void printSQLException(SQLException ex) {
            for (Throwable e : ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }