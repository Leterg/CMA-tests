package ru.dvgubochkin.test2.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty patronymic;
    private StringProperty birthday;
    private StringProperty studyGroup;
    private IntegerProperty id;

    public Student(){
        this(null,null,null,null,null,0);
    }

    public Student(String firstName, String lastName, String patronymic, String birthday, String studygroup, Integer id) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.patronymic = new SimpleStringProperty(patronymic);
    this.birthday = new SimpleStringProperty(birthday);
    this.studyGroup = new SimpleStringProperty(studygroup);
    this.id = new SimpleIntegerProperty(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName) ;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName) ;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic) ;
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday) ;
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public String getStudyGroup() {
        return studyGroup.get();
    }

    public void setStudyGroup(String studyGroup) {
        this.studyGroup.set(studyGroup) ;
    }

    public StringProperty studyGroupProperty() {
        return studyGroup;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id) ;
    }

    public IntegerProperty idProperty() {
        return id;
    }

}
