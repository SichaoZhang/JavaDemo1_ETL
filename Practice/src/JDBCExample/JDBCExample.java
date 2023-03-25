package JDBCExample;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCExample {
    public static void main(String[] args) throws SQLException, IOException {
        // 读取数据库配置文件
        Properties props = new Properties();
        try (InputStream in = JDBCExample.class.getClassLoader().getResourceAsStream("DBSetting.properties")) {
            props.load(in);
        }

        // 获取数据库连接
        Connection conn = DriverManager.getConnection(props.getProperty("db.url"),
                props.getProperty("db.username"), props.getProperty("db.password"));

        // 插入操作
        String insertSql = "INSERT INTO users (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, "John Doe");
            pstmt.setInt(2, 30);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows inserted.");
        }

        // 更新操作
        String updateSql = "UPDATE users SET age=? WHERE name=?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setInt(1, 35);
            pstmt.setString(2, "John Doe");
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows updated.");
        }

        // 删除操作
        String deleteSql = "DELETE FROM users WHERE name=?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
            pstmt.setString(1, "John Doe");
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows deleted.");
        }

        // 查询操作
        String selectSql = "SELECT * FROM users WHERE age=?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setInt(1, 30);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.println("id=" + id + ", name=" + name + ", age=" + age);
                }
            }// 在这里自动关闭 ResultSet 对象
        }// 在这里自动关闭 PreparedStatement 对象

        // 关闭数据库连接
        conn.close();
    }
}
