package it.aton.android.ploapp.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

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
        });

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                int from= viewHolder.getLayoutPosition();
                int to = target.getLayoutPosition();
                pooAdapter.swapItems(from, to);
                pooAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pooAdapter.removePooFromDb(viewHolder);
            }
        });

        mItemTouchHelper.attachToRecyclerView(binding.list);

        return binding.getRoot();
    }



}