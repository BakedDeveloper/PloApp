package it.aton.android.ploapp.data.local.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import it.aton.android.ploapp.data.local.model.Poo;
@Dao
public interface PooDao {

//    @Insert
//    Long insertPoo(Poo poo);

    @Insert
    Completable insertPoo(Poo poo);

    @Update
    Completable updatePoo(Poo poo);

    @Delete
    Completable deletePoo(Poo poo);

    @Query("SELECT * FROM poo")
    Single<List<Poo>> getAllPoos();

    @Query("SELECT * FROM poo WHERE user_id = :userId")
    public Single<List<Poo>> getAllUserPoos(int userId);

    @Query("SELECT * FROM poo")
    public  Single<List<Poo>> getALlPoos();




}
