package com.example.midiariodelecturas;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private Date dateRead;

    public Book(String title, String author, Date dateRead) {
        this.title = title;
        this.author = author;
        this.dateRead = dateRead;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDateRead() {
        return dateRead;
    }
}
