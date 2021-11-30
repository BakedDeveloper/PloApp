package it.aton.android.ploapp.data.local.repositories;


import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import it.aton.android.ploapp.data.local.dao.PooDao;
import it.aton.android.ploapp.data.local.database.PloAppDatabase;
import it.aton.android.ploapp.data.local.model.Poo;

public class PooRepository {

    private final PooDao pooDao;

    public PooRepository(Context context){
        PloAppDatabase ploAppDatabase= PloAppDatabase.getInstance(context);
        this.pooDao = ploAppDatabase.pooDao();
    }

//    public Long addPoo(Poo poo){
//
//       return pooDao.insertPoo(poo);
//    }
   public Completable addPoo(Poo poo){

       return pooDao.insertPoo(poo);
    }

    public Completable updatePoo(Poo poo){
       return pooDao.updatePoo(poo);
    }

    public Completable removePoo(Poo poo){
        return pooDao.deletePoo(poo);
    }

    public Single<List<Poo>> getAllUserPoos(int userId){
        return pooDao.getAllUserPoos(userId);
    }

    public Single<List<Poo>> getAllPoos(){
        return pooDao.getAllPoos();
    }





}
