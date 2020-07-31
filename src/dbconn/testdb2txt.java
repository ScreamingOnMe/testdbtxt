package dbconn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


public class testdb2txt {
    public static void main(String[] args) {
        try {
            getTxt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getTxt() throws IOException, SQLException {
        Connection con = null;
        String sql;
        File file = null;
        FileWriter fw = null;
        file = new File("/tmp/tongcai_file.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://47.95.235.127:3306/zabbix", "zabbix", "password");
            Statement stat = con.createStatement();
            sql = "select * from hosts";
            ResultSet result = stat.executeQuery(sql);
            fw.write("hostid name" + "\r\n");
            while (result.next()) {
                fw.write(result.getString("hostid") + "  " + result.getString("name") + "\r\n");
                fw.flush();
            }
            System.out.println("完成查询插入txt功能");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert con != null;
            con.close();
            fw.close();
        }
    }
}