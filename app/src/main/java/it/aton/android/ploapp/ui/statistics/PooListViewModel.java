package it.aton.android.ploapp.ui.statistics;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;


import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.data.local.repositories.PooRepository;

public class PooListViewModel extends AndroidViewModel {

    private PooRepository pooRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<Poo>> userPoos = new MutableLiveData<>();


    public PooListViewModel(@NonNull Application application) {
        super(application);
        pooRepository = new PooRepository(application);
        loadPoos();
    }


    private void loadPoos() {
        compositeDisposable.add(pooRepository.getAllPoos()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        poos -> {
                            if(poos.isEmpty()){
                                Log.i("READ","Il DB è vuoto");
                            }

                            this.userPoos.postValue(poos);
                        },

                        throwable -> {
                            Log.e("DATABASE", "Error in loading the poos for user: " + userPoos+ throwable.getMessage());
                        }

                ));
    }


    public LiveData<List<Poo>> getUserPoos() {
        return this.userPoos;
    }
}
