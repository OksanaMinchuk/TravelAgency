package by.epam.javatr.minchuk.project.dao.util;

/**
 * Class {@code DBProperty} contains configuration properties for db connection.
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class DBProperty {

    public DBProperty() {
    }

    public static String JDBS_MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/travel";
    public static String DATABASE_USER = "root";
    public static String DATABASE_PASSWORD = "password";

    public static final int CONNECTION_COUNT = 10;


}
