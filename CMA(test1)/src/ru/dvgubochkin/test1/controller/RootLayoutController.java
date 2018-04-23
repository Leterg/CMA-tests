package ru.dvgubochkin.test1.controller;

import javafx.stage.DirectoryChooser;
import ru.dvgubochkin.test1.Main;
import ru.dvgubochkin.test1.model.TextFileHelper;

import java.io.File;
import java.nio.charset.MalformedInputException;

public class RootLayoutController {

    private Main main;

    private File selectedDirectory;

    TextFileHelper textFileHelper;

    public void chooseDir(){
        // метод для menuItem
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите корневой каталог");

        selectedDirectory = directoryChooser.showDialog(main.getPrimaryStage());
        // если директория выбрана, выполнить основное задание
        if (selectedDirectory != null) {
            main.sendMergedText(null);
            main.getTextFileData().clear();
            textFileHelper = new TextFileHelper(selectedDirectory.toPath(), main.getTextFileData());
            // выведем на текстовую область полученный текст
            main.sendMergedText(textFileHelper.mergeTextFiles());
        }
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
