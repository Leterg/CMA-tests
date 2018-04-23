package ru.dvgubochkin.test2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.dvgubochkin.test2.database.StudentBaseHelper;
import ru.dvgubochkin.test2.model.Student;
import ru.dvgubochkin.test2.controller.StudentAddDialogController;
import ru.dvgubochkin.test2.controller.StudentOverviewController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    public StudentBaseHelper db;

    public Main(){
        // развёртываем базу данных в директории с приложением
        db = new StudentBaseHelper();
        db.setUrl("jdbc:sqlite:" + System.getProperty("user.dir") + "/test.db");
        db.createNewDatabase();
        db.createNewTable();
        updateUI();
    }

    public void updateUI(){
        // Обновляем данные
        studentData.clear();
        studentData = db.selectAll();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/StudentOverview.fxml"));
        Parent root = loader.load();
        this.primaryStage.setTitle("Test2. База данных о студентах");
        this.primaryStage.setScene(
                new Scene(root));

        StudentOverviewController studentOverviewController = loader.getController();
        studentOverviewController.setMain(this);

        this.primaryStage.show();
    }

    public boolean showStudentAddDialog(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/StudentAddDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавьте новго студента");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StudentAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ObservableList<Student> getStudentData() {
        return studentData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

