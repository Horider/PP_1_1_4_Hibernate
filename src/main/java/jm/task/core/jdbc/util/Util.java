package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String hostName = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private static final String userName = "root";
    private static final String password = "root";
    public static Connection connection;
    private static Util instance;
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties() {{
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.connection.url", hostName);
                setProperty("hibernate.connection.username", userName);
                setProperty("hibernate.connection.password", password);
                setProperty("hibernate.connection.driver_class", Driver);
                setProperty("show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
            }};

            sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            if (null == connection || connection.isClosed()) {
                connection = DriverManager.getConnection(hostName, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }
}
