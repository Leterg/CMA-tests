package ru.dvgubochkin.test1.model;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextFileHelper {

    private Path rootPath;
    private ObservableList<TextFile> textFileData;

    public TextFileHelper(Path selectedDirectory, ObservableList<TextFile> textFileData){
        rootPath = selectedDirectory;
        this.textFileData = textFileData;
        findTextFiles();
        sortTextFilesList();
        mergeTextFiles();
    }

    private void findTextFiles() {
        Path start = rootPath;
        try {
            // воспользуемся методом, для обхождения всех файлов
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                // Переопределим один из методов, который выполняется при посещении файла
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    /* if(Files.probeContentType(file).equalsIgnoreCase("text/plain")){
                    * Будем выбирать по расширению:* */
                    System.out.println("\t"+file);
                        if (file.toString().endsWith(".txt")){
                        System.out.println(file);
                    textFileData.add(new TextFile(file));
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir,
                                                          IOException exc) {
                    System.out.format("Directory: %s%n", dir);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file,
                                                       IOException exc) {
                    System.err.println(exc);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sortTextFilesList(){
        textFileData.sort(Comparator.comparing(TextFile::getName));
    }
    public List<String> mergeTextFiles(){
        List<String> mergedText = new ArrayList<>();
        for (TextFile textFile:textFileData){
            try {
                mergedText.addAll(Files.readAllLines(textFile.getPath(), Charset.forName("windows-1251")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path outFile = Paths.get(System.getProperty("user.dir")+"/test.txt");
        try {
            Files. write(outFile, mergedText, Charset.forName("windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mergedText;
    }

}
