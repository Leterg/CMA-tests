package ru.dvgubochkin.test2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import ru.dvgubochkin.test2.Main;
import ru.dvgubochkin.test2.model.Student;

public class StudentOverviewController {
    public StudentOverviewController() {
    }
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> studygroupColumb;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> uuidNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label patronymicLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label studyGroupLabel;
    @FXML
    private Label uuidLabel;
    @FXML
    private Button deleteButton;

    private Main main;
    @FXML
    private void initialize() {
        // Инициализация таблицы студентов с двумя столбцами.
        studygroupColumb.setCellValueFactory(cellData -> cellData.getValue().studyGroupProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        uuidNameColumn.setCellValueFactory(cellData -> cellData.getValue().uuidProperty());

        showStudentDetails(null);

        studentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStudentDetails(newValue));
    }

    public void setMain(Main main) {
        this.main = main;

        // Добавление в таблицу данных из  списка
        studentTable.setItems(main.getStudentData());

    }
    private void showStudentDetails(Student student) {
        if (student != null) {
            // Заполняем метки информацией из объекта person.
            firstNameLabel.setText(student.getFirstName());
            lastNameLabel.setText(student.getLastName());
            patronymicLabel.setText(student.getPatronymic());
            birthdayLabel.setText(student.getBirthday());
            studyGroupLabel.setText(student.getStudyGroup());
            uuidLabel.setText(student.getUuid());

            deleteButton.setDisable(false);

        } else {
            // Если Person = null, то убираем весь текст.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            patronymicLabel.setText("");
            birthdayLabel.setText("");
            studyGroupLabel.setText("");
            uuidLabel.setText("");

            deleteButton.setDisable(true);
        }
    }
    @FXML
    private void handleDeleteStudent() {
        // Удаляем студента из базы
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        main.db.deleteStudent(selectedStudent.getUuid());
        main.updateUI();
    }
    @FXML
    private void handleAddStudent() {
        // Добавляем студента
        Student tempStudent = new Student();
        boolean okClicked = main.showStudentAddDialog(tempStudent);
        if (okClicked) {
            main.db.insertStudent(tempStudent);
            main.updateUI();
        }
    }
}
