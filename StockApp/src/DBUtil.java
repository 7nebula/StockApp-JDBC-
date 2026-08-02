import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private static final String USER = "system";
    private static final String PASSWORD = "0213";

 
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("[오류] JDBC 드라이버 로드 실패");
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
