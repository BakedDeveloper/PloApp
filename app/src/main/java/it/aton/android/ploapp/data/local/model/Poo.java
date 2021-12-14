package it.aton.android.ploapp.data.local.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @ColumnInfo(name = "quantity_image")
    private int quantityImage;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    @ColumnInfo(name = "is_blood_present")
    private boolean isBloodPresent;

    @ColumnInfo(name = "cramps")
    private boolean cramps;

    @ColumnInfo(name = "nausea")
    private boolean nausea;

    @ColumnInfo(name = "swelling")
    private boolean swelling;

    @ColumnInfo(name = "period")
    private boolean period;

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
            boolean cramps,
            boolean nausea,
            boolean swelling,
            boolean period,
            int sessionTime,
            boolean isEnemaUsed,
            int quantityImage) {
        this.user_id = user_id;
        this.color = color;
        this.type = type;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.isBloodPresent = isBloodPresent;
        this.sessionTime = sessionTime;
        this.isEnemaUsed = isEnemaUsed;
        this.quantityImage = quantityImage;
        this.cramps = cramps;
        this.nausea = nausea;
        this.swelling = swelling;
        this.period = period;
    }

    public boolean isCramps() {
        return cramps;
    }

    public void setCramps(boolean cramps) {
        this.cramps = cramps;
    }

    public boolean isNausea() {
        return nausea;
    }

    public void setNausea(boolean nausea) {
        this.nausea = nausea;
    }

    public boolean isSwelling() {
        return swelling;
    }

    public void setSwelling(boolean swelling) {
        this.swelling = swelling;
    }

    public boolean isPeriod() {
        return period;
    }

    public void setPeriod(boolean period) {
        this.period = period;
    }

    public int getQuantityImage() {
        return quantityImage;
    }

    public void setQuantityImage(int quantityImage) {
        this.quantityImage = quantityImage;
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

    public void setSessionTime(int sessionTime) {
        this.sessionTime = sessionTime;
    }

    public boolean isEnemaUsed() {
        return isEnemaUsed;
    }

    public void setEnemaUsed(boolean enemaUsed) {
        isEnemaUsed = enemaUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poo poo = (Poo) o;
        return id == poo.id && user_id == poo.user_id && color == poo.color && type == poo.type && quantity == poo.quantity && quantityImage == poo.quantityImage && isBloodPresent == poo.isBloodPresent && cramps == poo.cramps && nausea == poo.nausea && swelling == poo.swelling && period == poo.period && sessionTime == poo.sessionTime && isEnemaUsed == poo.isEnemaUsed && Objects.equals(dateTime, poo.dateTime);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, color, type, quantity, quantityImage, dateTime, isBloodPresent, cramps, nausea, swelling, period, sessionTime, isEnemaUsed);
    }

    public boolean hasOptions() {
        if(!isBloodPresent()
                &&!isEnemaUsed()
                &&!isCramps()
                &&!isNausea()
                &&!isSwelling()
                &&!isPeriod())
        {
            return false;
        }
        return true;
    }
}
