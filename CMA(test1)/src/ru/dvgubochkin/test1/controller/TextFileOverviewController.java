package ru.dvgubochkin.test1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import ru.dvgubochkin.test1.Main;
import ru.dvgubochkin.test1.model.TextFile;

import java.util.List;

public class TextFileOverviewController {
    @FXML
    private TableView<TextFile> fileTable;
    @FXML
    private TableColumn<TextFile, String> nameColumn;
    @FXML
    private TableColumn<TextFile, String> parentColumn;
    @FXML
    private TextArea textArea;


    private Main main;

    public TextFileOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        parentColumn.setCellValueFactory(cellData -> cellData.getValue().parentProperty());
    }

    public void setMain(Main main) {
        this.main = main;

        fileTable.setItems(main.getTextFileData());
    }
    public void fillTextArea(List<String> stringList){
        if (stringList == null)
            textArea.clear();
        else
            for (String st:stringList)
                textArea.appendText(st+"\n");
    }
}
