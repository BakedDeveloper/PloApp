package it.aton.android.ploapp.ui.start;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import java.text.MessageFormat;

import io.reactivex.rxjava3.schedulers.Schedulers;
import it.aton.android.ploapp.R;
import it.aton.android.ploapp.data.remote.QuoteAPI;
import it.aton.android.ploapp.databinding.FragmentStartBinding;
import it.aton.android.ploapp.ui.newpoo.NewPooViewModel;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private StartViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(StartViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getQuote().observe(getViewLifecycleOwner(), quote -> {
            binding.startQuoteAuthor.setText(quote.getAuthor().getName());
            binding.startQuote.setText(MessageFormat.format("\"{0}\"", quote.getText()));
        });

        binding.startQuote.setOnClickListener(v -> {
            viewModel.loadRandomQuote();
        });

        binding.mainNewPooButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_startFragment_to_pooFragment);
        });

        binding.mainStatistictsButton.setOnClickListener(v ->{
            Navigation.findNavController(v).navigate(R.id.action_startFragment_to_pooListFragment);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadRandomQuote();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}