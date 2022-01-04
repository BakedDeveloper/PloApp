package it.aton.android.ploapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("author")
    private Author author;

    public Quote(){}

    public Quote(String id, String text, Author author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public Quote setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Quote setText(String text) {
        this.text = text;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Quote setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
