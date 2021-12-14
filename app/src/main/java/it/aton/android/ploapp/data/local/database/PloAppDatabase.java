package it.aton.android.ploapp.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import it.aton.android.ploapp.data.local.converters.Converters;
import it.aton.android.ploapp.data.local.dao.PooDao;
import it.aton.android.ploapp.data.local.dao.UserDao;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.data.local.model.User;

@Database(entities = {Poo.class, User.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PloAppDatabase extends RoomDatabase {

    private static final String DB_NAME = "ploapp_database.db";
    private static PloAppDatabase instance;

    public PloAppDatabase() {
    }

    ;

    //SINGLETON IMPLEMENTATION:
    public static PloAppDatabase getInstance(Context context) {
        if (instance == null)
            instance = create(context);
        return instance;
    }

    private static PloAppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                PloAppDatabase.class,
                DB_NAME
        )
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract PooDao pooDao();

    public abstract UserDao userDao();
}
