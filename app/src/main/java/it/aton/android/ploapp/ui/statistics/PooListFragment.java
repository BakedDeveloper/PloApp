package it.aton.android.ploapp.ui.statistics;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.aton.android.ploapp.R;
import it.aton.android.ploapp.databinding.FragmentNewPooBinding;
import it.aton.android.ploapp.databinding.FragmentPooListBinding;
import it.aton.android.ploapp.placeholder.PlaceholderContent;
import it.aton.android.ploapp.ui.newpoo.NewPooViewModel;

/**
 * A fragment representing a list of Items.
 */
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

        binding.list.setAdapter(pooAdapter);

        viewModel.getUserPoos().observe(getViewLifecycleOwner(), poos -> {
            pooAdapter.updateData(poos);
//            binding.list.setAdapter(pooAdapter);
        });

        return binding.getRoot();
    }
}