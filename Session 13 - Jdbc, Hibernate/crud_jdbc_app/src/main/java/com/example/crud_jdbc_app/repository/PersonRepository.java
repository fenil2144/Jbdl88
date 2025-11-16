package com.example.crud_jdbc_app.repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crud_jdbc_app.model.Person;

@Slf4j
@Repository
public class PersonRepository {

    private Connection connection;

    public PersonRepository(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS PERSON(
                id int primary key auto_increment, first_name varchar(30),
                last_name varchar(25), age int, dob varchar(15))
                """;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String createPersonStatic(Person person) {
        Statement statement = null;
        try {
            statement = connection.createStatement();

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
            PreparedStatement preparedStatement = connection.prepareStatement("insert into person VALUES (?,?,?,?,?)");

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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person where id = ?");
            preparedStatement.setInt(1, personId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person = getPersonFromResultSet(resultSet);
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString(3))
                .age(resultSet.getInt("age"))
                .dob(resultSet.getString(5))
                .build();
    }

    public void updatePerson(Person updatedPerson) {

        try {
            String updateSql = "Update person set first_name = ?, last_name = ?, age = ?, dob = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, updatedPerson.getFirstName());
            preparedStatement.setString(2, updatedPerson.getLastName());
            preparedStatement.setInt(3, updatedPerson.getAge());
            preparedStatement.setString(4, updatedPerson.getDob());
            preparedStatement.setInt(5, updatedPerson.getId());

            int updatedRows = preparedStatement.executeUpdate();
            log.debug("Updated rows : {}", updatedRows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePerson(int personId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, personId);
            int rowsDeleted = preparedStatement.executeUpdate();
            log.debug("Rows Deleted :{}", rowsDeleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from person");
            while (resultSet.next()) {
                Person person = getPersonFromResultSet(resultSet);
                personList.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }
}
