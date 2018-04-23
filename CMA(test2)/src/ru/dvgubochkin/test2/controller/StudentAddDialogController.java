package ru.dvgubochkin.test2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.dvgubochkin.test2.model.Student;

import java.util.UUID;

public class StudentAddDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField patronymicField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField studyGroupField;

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    @FXML
    private void handleOk() {
            // Присваиваем поля новому студенту
        student.setFirstName(firstNameField.getText());
        student.setLastName(lastNameField.getText());
        student.setPatronymic(patronymicField.getText());
        student.setBirthday(birthdayField.getText());
        student.setStudyGroup(studyGroupField.getText());
        student.setUuid(UUID.randomUUID().toString());

        okClicked = true;
        dialogStage.close();
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
