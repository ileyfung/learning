package util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Data 2020/6/1 0001
 */
public class DButil {
    private static BasicDataSource basicDataSource;
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static int initial;
    private static int max;
    private static int idle;

    static {
        basicDataSource = new BasicDataSource(); //创建数据库连接池
        Properties cfg = new Properties(); //创建Properties对象
        try {
            InputStream inputStream = DButil.class.getClassLoader().getResourceAsStream("db.properties"); //读取数据库配置文件
            cfg.load(inputStream); //加载文件
            inputStream.close(); //关闭输入流
            //获取对应参数
            driver = cfg.getProperty("driver");
            url = cfg.getProperty("url");
            username = cfg.getProperty("username");
            password = cfg.getProperty("password");
            initial = Integer.parseInt(cfg.getProperty("initial"));
            max = Integer.parseInt(cfg.getProperty("max"));
            idle = Integer.parseInt(cfg.getProperty("idle"));
            //将参数设置到 连接池
            basicDataSource.setDriverClassName(driver);
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            basicDataSource.setInitialSize(initial);
            basicDataSource.setMaxActive(max);
            basicDataSource.setMaxIdle(idle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static void close(Connection conn) {
        try {
            if (conn != null){
                conn.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接池
     */
    public static void  close() {
        try {
            basicDataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
