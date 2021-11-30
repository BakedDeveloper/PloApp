package it.aton.android.ploapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import it.aton.android.ploapp.R;
import it.aton.android.ploapp.data.local.database.PloAppDatabase;
import it.aton.android.ploapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    PloAppDatabase ploAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ploAppDatabase= PloAppDatabase.getInstance(getApplication());
    }

}