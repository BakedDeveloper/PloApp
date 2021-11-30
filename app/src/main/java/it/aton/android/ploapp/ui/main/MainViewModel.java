package it.aton.android.ploapp.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import it.aton.android.ploapp.data.local.repositories.UserRepository;

public class MainViewModel extends AndroidViewModel {

    UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository= new UserRepository(application);
    }
}
