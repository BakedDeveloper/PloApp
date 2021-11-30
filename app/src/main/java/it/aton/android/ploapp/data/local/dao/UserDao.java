package it.aton.android.ploapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import it.aton.android.ploapp.data.local.model.User;

@Dao
public interface UserDao {

    @Insert
    public Completable insertUser(User user);

    @Update
    public Completable updateUser(User user);

    @Delete
    public Completable deleteUser(User user);

    @Query("SELECT * FROM user where first_name=:name")
    public Single<User> selectUserWithName(String name);

    @Query("SELECT * FROM user")
    public Single<List<User>> selectAllUsers();

}
