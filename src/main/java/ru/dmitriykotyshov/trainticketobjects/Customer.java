package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Date;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class Customer {

    private int customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private String gender;
    private Document document;
    private String docNumber;
    private String email;
    private String telephone;

    public Customer(int customerId, String firstName, String middleName, String lastName, Date birthday, String gender, Document document, String docNumber, String email, String telephone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.document = document;
        this.docNumber = docNumber;
        this.email = email;
        this.telephone = telephone;
    }

    public Customer(int customerId, String firstName, String middleName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Customer(int customerId){
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
