package it.aton.android.ploapp.data.local.repositories;

import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import it.aton.android.ploapp.data.local.dao.UserDao;
import it.aton.android.ploapp.data.local.database.PloAppDatabase;
import it.aton.android.ploapp.data.local.model.User;

public class UserRepository {

    private final UserDao userDao;

    public UserRepository(Context context){
        PloAppDatabase ploAppDatabase= PloAppDatabase.getInstance(context);
        this.userDao=ploAppDatabase.userDao();
    }

    public Completable addUser(User user){
        return userDao.insertUser(user);
    }

    public Completable updateUser(User user){
       return userDao.updateUser(user);
    }

    public Completable removeUser(User user){
        return userDao.deleteUser(user);
    }

    public Single<User> selectUserWithName(String name){
        return userDao.selectUserWithName(name);
    }

    public Single<List<User>> selectAllUsers(){
        return userDao.selectAllUsers();
    }


}
