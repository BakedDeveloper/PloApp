package it.aton.android.ploapp.data.local.converters;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {

    @TypeConverter
    public static String fromDateToString(LocalDateTime date){

        if(date!= null){
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return date.format(formatter);
        }
      return null;
    }

    @TypeConverter
    public static LocalDateTime fromStringToDate(String date){
        if(date!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }


}
