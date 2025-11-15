package com.example.crud_jdbc_app.repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.example.crud_jdbc_app.model.Person;

@Slf4j
@Repository
public class PersonRepository {

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl88", "root", "root1234");
    }

    public PersonRepository() {
        createTable();
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS PERSON(
                id int primary key, first_name varchar(30),
                last_name varchar(25), age int, dob varchar(15))
                """;
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String createPersonStatic(Person person) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();

            String sql = "INSERT INTO PERSON (id, first_name, last_name, age, dob) values (1, 'John', 'Carls', 22, '2002-01-15')";

            int rows = statement.executeUpdate(sql);
            log.info("Number of rows inserted: {}", rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Person created successfully.";

    }

    public String savePerson(Person person) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("insert into person VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setString(5, person.getDob());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Person saved successfully.";
    }

    public Person getPersonById(int personId) {
        Person person = null;

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM person where id = ?");
            preparedStatement.setInt(1, personId);

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
