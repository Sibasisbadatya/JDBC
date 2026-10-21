package com.example.jdbc.repository;

import com.example.jdbc.model.Student;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class StudentRepository {

    private final DataSource dataSource;


    String url = "jdbc:h2:mem:testdb;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1";
    String username = "sa";
    String password = "";

    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createuser() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = "Insert INTO student(name,email,age)" + "VALUES('Sibasis','sibasis@gmail.com',24)";
            int res = statement.executeUpdate(sqlQuery);
            if (res == 1) {
                System.out.println("Creation Succesful");
            } else {
                System.out.println("Creation Failed");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void getUser() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT id,name,email,age from student where id=1";
            ResultSet result = statement.executeQuery(sqlQuery);
            result.next();
            Student student = new Student();
            student.setAge(result.getInt("age"));
            student.setEmail(result.getString("email"));
            student.setId(result.getLong("id"));
            student.setName(result.getString("name"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void createUserWithPreparedStatement(Student student) {
        String sqlQuery = "INSERT INTO student (name, email, age) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            // execute() returns:
            // true  -> if the first result is a ResultSet (e.g., SELECT)
            // false -> if the result is an update count or there are no results (e.g., INSERT, UPDATE, DELETE)

//            for ResultSet we have to close this also
            boolean hasResultSet = preparedStatement.execute();
            if (!hasResultSet) {
                int rowsAffected = preparedStatement.getUpdateCount();
                if (rowsAffected > 0) {
                    System.out.println("Creation Successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("Creation Failed. No rows affected.");
                }
            } else {
                System.out.println("Unexpected ResultSet returned.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
