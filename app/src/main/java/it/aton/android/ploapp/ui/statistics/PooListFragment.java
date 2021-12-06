package it.aton.android.ploapp.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;

import java.util.ArrayList;

import it.aton.android.ploapp.databinding.FragmentPooListBinding;

public class PooListFragment extends Fragment {

    FragmentPooListBinding binding;
    PooListViewModel viewModel;

    MyPooRecyclerViewAdapter pooAdapter= new MyPooRecyclerViewAdapter(new ArrayList<>());

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


        viewModel.getUserPoos().observe(getViewLifecycleOwner(), poos -> {
            pooAdapter.updateData(poos);
//            binding.list.setAdapter(pooAdapter);

            SelectionTracker<Long> tracker = new SelectionTracker.Builder<>(
                    "mySelection",
                    binding.list,
                    new StableIdKeyProvider(binding.list),
                    new MyItemDetailsLookup(binding.list),
                    StorageStrategy.createLongStorage()
            ).withSelectionPredicate(
                    SelectionPredicates.createSelectAnything()
            ).build();

            pooAdapter.setTracker(tracker);
            binding.list.setAdapter(pooAdapter);
        });



        return binding.getRoot();
    }
}