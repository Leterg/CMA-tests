package ru.dvgubochkin.test1.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.nio.file.Path;

public class TextFile {
    private StringProperty name;
    private StringProperty parent;
    private ObjectProperty<Path> path;

    public TextFile(Path path){
        this.path = new SimpleObjectProperty<Path>(path);
        name = new SimpleStringProperty(path.getFileName().toString());
        parent = new SimpleStringProperty(path.getParent().toString());

    }
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getParent() {
        return parent.get();
    }

    public void setParent(String parent) {
        this.parent.set(parent);
    }

    public StringProperty parentProperty() {
        return parent;
    }

    public Path getPath() {
        return path.get();
    }

    public void setPath(Path path) {
        this.path.set(path);
    }

    public ObjectProperty<Path> pathProperty() {
        return path;
    }
}
