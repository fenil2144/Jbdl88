package com.gfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableWIthMaven {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl88", "root", "root1234");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE maven_example(id INT Primary Key, name VARCHAR(20))");
        System.out.println("Table created successfully");
    }
}
