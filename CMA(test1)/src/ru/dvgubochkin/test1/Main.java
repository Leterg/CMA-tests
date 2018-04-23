package ru.dvgubochkin.test1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.dvgubochkin.test1.model.TextFile;
import ru.dvgubochkin.test1.controller.RootLayoutController;
import ru.dvgubochkin.test1.controller.TextFileOverviewController;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    /* В резутате выполнения текстовый файл будет находится рядом с Jar*/
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<TextFile> textFileData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CMA(test1)");

        initRootLayout();

        showTextFileOverview();
    }
    RootLayoutController rootController;
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            rootController = loader.getController();
            rootController.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    TextFileOverviewController overviewController;
    public void showTextFileOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TextFileOverview.fxml"));
            AnchorPane textFileOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(textFileOverview);

            overviewController = loader.getController();
            overviewController.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<TextFile> getTextFileData() {
        return textFileData;
    }

    public void sendMergedText(List<String> stringList){
        overviewController.fillTextArea(stringList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
