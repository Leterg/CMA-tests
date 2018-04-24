package ru.dvgubochkin.test2.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> studyGroupBox;

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        // Инициализируем список групп
        String[] groupList = {"Учебная группа A01", "Учебная группа A02","Учебная группа A03",
                                "Учебная группа B01", "Учебная группа B02"};
        studyGroupBox.getItems().setAll(groupList);
        studyGroupBox.getSelectionModel().selectFirst();
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
        if (isInputValid()) {
            student.setFirstName(firstNameField.getText());
            student.setLastName(lastNameField.getText());
            student.setPatronymic(patronymicField.getText());
            student.setBirthday(birthdayField.getText());
            student.setStudyGroup(studyGroupBox.getSelectionModel().getSelectedItem());
            student.setUuid(UUID.randomUUID().toString());

            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Пустое поле: имя\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Пустое поле: фамилия\n";
        }
        if (patronymicField.getText() == null || patronymicField.getText().length() == 0) {
            errorMessage += "Пустое поле: отчество\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Пустое поле: дата рождения\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Пустые поля");
            alert.setHeaderText("Заполните все поля ввода");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
