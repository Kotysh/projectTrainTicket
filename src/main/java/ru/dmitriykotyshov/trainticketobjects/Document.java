package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class Document {

    int id;
    String Document;

    public Document(int id, String document) {
        this.id = id;
        Document = document;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }
}
