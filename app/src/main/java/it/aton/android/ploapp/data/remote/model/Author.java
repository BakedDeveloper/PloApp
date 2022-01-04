package it.aton.android.ploapp.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public Author() {
    }

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Author setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }
}
