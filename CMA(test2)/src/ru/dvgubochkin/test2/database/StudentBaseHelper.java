package ru.dvgubochkin.test2.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.dvgubochkin.test2.model.Student;

import java.sql.*;

public class StudentBaseHelper  {


    private String url;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    ObservableList<Student> studentData = FXCollections.observableArrayList();

    public void createNewDatabase(){
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	firstname NOT NULL,\n"
                + "	lastname NOT NULL,\n"
                + "	patronymic NOT NULL,\n"
                + "	birthday NOT NULL,\n"
                + "	studуgroup NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO students(firstname, lastname, patronymic, birthday, studуgroup) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getPatronymic());
            pstmt.setString(4, student.getBirthday());
            pstmt.setString(5, student.getStudyGroup());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Student> selectAll(){
        String sql = "SELECT id, firstname, lastname, patronymic, birthday, studуgroup  FROM students";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                studentData.add( new Student(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("patronymic"),
                        rs.getString("birthday"),
                        rs.getString("studуgroup"),
                        rs.getInt("id"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentData;
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
