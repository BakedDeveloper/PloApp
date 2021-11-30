package it.aton.android.ploapp.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "poo")
public class Poo {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "user_id")
    private int user_id;

    //Todo da implementare relazione one to many

    @ColumnInfo(name = "color")
    private int color;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    @ColumnInfo(name = "is_blood_present")
    private boolean isBloodPresent;

    @ColumnInfo(name = "is_painful")
    private boolean isPainful;

    @ColumnInfo(name = "session_time")
    private int sessionTime;

    @ColumnInfo(name = "is_enema_used")
    private boolean isEnemaUsed;

    //Todo da implementare posizione di cacca

    public Poo() {

    }

    @Ignore
    public Poo(
            int user_id,
            int color,
            int type,
            int quantity,
            String dateTime,
            boolean isBloodPresent,
            boolean isPainful,
            int sessionTime,
            boolean isEnemaUsed) {
        this.user_id = user_id;
        this.color = color;
        this.type = type;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.isBloodPresent = isBloodPresent;
        this.isPainful = isPainful;
        this.sessionTime = sessionTime;
        this.isEnemaUsed = isEnemaUsed;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getDateTime() {
        return dateTime;
    }


    public int getSessionTime() {
        return sessionTime;
    }


    public int getId() {
        return id;
    }


    public int getColor() {
        return color;
    }


    public int getType() {
        return type;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isBloodPresent() {
        return isBloodPresent;
    }

    public void setBloodPresent(boolean bloodPresent) {
        isBloodPresent = bloodPresent;
    }

    public boolean isPainful() {
        return isPainful;
    }

    public void setPainful(boolean painful) {
        isPainful = painful;
    }

    public void setSessionTime(int sessionTime) {
        this.sessionTime = sessionTime;
    }

    public boolean isEnemaUsed() {
        return isEnemaUsed;
    }

    public void setEnemaUsed(boolean enemaUsed) {
        isEnemaUsed = enemaUsed;
    }


}