package javaguides.usermanagement.dao;

import javaguides.usermanagement.util.DBHelper;
import javaguides.usermanagement.util.PropertyReader;
import org.hibernate.SessionFactory;
import java.sql.Connection;

public class UserDaoFactory {

    public static UserDAO getUserDAO(){

        if (PropertyReader.getValue("technology", "currentTechnology.properties").equalsIgnoreCase("hibernate")){
            SessionFactory sessionFactory = DBHelper.getSessionFactory();
            return new UserDaoHibernateImpl(sessionFactory);
        }else{
            Connection connection = DBHelper.getDbConnection();
            return new UserDaoJDBCimpl(connection);
        }
    }
}
