package it.aton.android.ploapp.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import it.aton.android.ploapp.databinding.FragmentPooListBinding;

public class PooListFragment extends Fragment {

    FragmentPooListBinding binding;
    PooListViewModel viewModel;

    MyPooRecyclerViewAdapter pooAdapter;

    public PooListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(PooListViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPooListBinding.inflate(getLayoutInflater(), container, false);

        pooAdapter= new MyPooRecyclerViewAdapter(new ArrayList<>(), getContext());

        binding.list.setAdapter(pooAdapter);

        viewModel.getUserPoos().observe(getViewLifecycleOwner(), poos -> {
            pooAdapter.updateData(poos);
//            binding.list.setAdapter(pooAdapter);
        });

        return binding.getRoot();
    }
}