package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance;

    // все строки-константы храним в переменных
    private static final String DB_URL_KEY  = "db_url";
    private static final String DB_USER_KEY = "db_user";
    private static final String DB_PASS_KEY = "db_pass";

    private Connection connection;

    private ConnectionManager() {

    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    /**
     * Подключается к БД и возвращает объект соединения с БД
     * @return
     * @throws SQLException если установить подключение не удалось
     */
    public Connection getConnection() throws SQLException {
        // проверяем создан ли объект соединения с БД, а если создан, то закрыто ли соединение
        if (connection == null || connection.isClosed()) {
            AppProperties properties = AppProperties.getInstance();
            String url = properties.getProperty(DB_URL_KEY);
            String user = properties.getProperty(DB_USER_KEY);
            String pass = properties.getProperty(DB_PASS_KEY);

            connection = DriverManager.getConnection(url, user, pass);
        }

        return connection;
    }

    public void closeConnection() throws SQLException {
        // проверяем создан ли объект соединения с БД, а если создан, то открыто ли соединение
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}