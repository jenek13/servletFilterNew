package javaguides.usermanagement.util;

import javaguides.usermanagement.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    private static SessionFactory sessionFactory;

    private DBHelper() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.setProperty("hibernate.connection.url",PropertyReader.getValue("hibernate.connection.url", "hibernate.properties"));
                configuration.setProperty("hibernate.connection.username",PropertyReader.getValue("hibernate.connection.username", "hibernate.properties"));
                configuration.setProperty("hibernate.connection.password",PropertyReader.getValue("hibernate.connection.password", "hibernate.properties"));
                configuration.setProperty("hibernate.dialect",PropertyReader.getValue("hibernate.dialect", "hibernate.properties"));
                configuration.setProperty("hibernate.connection.driver_class",PropertyReader.getValue("hibernate.connection.driver_class", "hibernate.properties"));

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    private static Connection dbConnection;

    public static  Connection getDbConnection() {
        if (dbConnection != null) {
            return dbConnection;
        }
        return getConnection();
    }

    private static Connection getConnection() {//этот мтеод был другим тут была проверка на существующий конекшен который я вынесла в мтеод getDbConnection
        try {   String dbDriver = PropertyReader.getValue("dbDriver", "jdbc.properties");
            String connectionUrl = PropertyReader.getValue("connectionUrl", "jdbc.properties");
            String userName = PropertyReader.getValue("userName", "jdbc.properties");
            String password = PropertyReader.getValue("password", "jdbc.properties");
            Class.forName(dbDriver).newInstance();//инициализация драйвера
            dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
