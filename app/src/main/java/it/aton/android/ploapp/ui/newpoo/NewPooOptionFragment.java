package it.aton.android.ploapp.ui.newpoo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.aton.android.ploapp.R;
import it.aton.android.ploapp.databinding.FragmentNewPooOptionBinding;

public class NewPooOptionFragment extends Fragment {

    private FragmentNewPooOptionBinding binding;
    private NewPooViewModel viewModel;
    private float uncheckedButton = 0.5F;
    private float checkedButton = 1F;

    private boolean cramps = false;
    private boolean nausea = false;
    private boolean swelling = false;


    public NewPooOptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(NewPooViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNewPooOptionBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.poooptionsBack.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        binding.imageBloodDrop.setOnClickListener(v -> {
            viewModel.setIsBloodPresent();
        });

        binding.imageNoBlood.setOnClickListener(v -> {
            viewModel.setIsBloodPresent();
        });

        binding.imageEnema.setOnClickListener(v -> {
            viewModel.setIsEnemaUsed();
        });

        binding.imageNoEnema.setOnClickListener(v -> {
            viewModel.setIsEnemaUsed();
        });

        binding.imagePeriod.setOnClickListener(v -> {
            viewModel.setPeriod();
        });

        binding.imageNoPeriod.setOnClickListener(v -> {
            viewModel.setPeriod();
        });

        binding.imageCramps.setOnClickListener(v -> {
            viewModel.setCramps();
        });

        binding.imageNausea.setOnClickListener(v -> {
            viewModel.setNausea();
        });

        binding.imageSwelling.setOnClickListener(v -> {
            viewModel.setSwelling();
        });

        binding.imageNoPain.setOnClickListener(v -> {
            viewModel.setNoPain();
        });

        observeDataFromViewModel();
    }

    private void observeDataFromViewModel() {

        viewModel.getIsBloodPresent().observe(getViewLifecycleOwner(), blood -> {
            if (blood) {
                binding.optionsBloodDrop.setAlpha(checkedButton);
                binding.optionsBloodOk.setAlpha(uncheckedButton);
            } else {
                binding.optionsBloodDrop.setAlpha(uncheckedButton);
                binding.optionsBloodOk.setAlpha(checkedButton);
            }
        });

        viewModel.getIsEnemaUsed().observe(getViewLifecycleOwner(), enema -> {
            if (enema) {
                binding.optionsEnema.setAlpha(checkedButton);
                binding.optionsEnemaOk.setAlpha(uncheckedButton);
            } else {
                binding.optionsEnema.setAlpha(uncheckedButton);
                binding.optionsEnemaOk.setAlpha(checkedButton);
            }
        });

        viewModel.getPeriod().observe(getViewLifecycleOwner(), period -> {
            if (period) {
                binding.optionsCiclo.setAlpha(checkedButton);
                binding.optionsCicloOk.setAlpha(uncheckedButton);
            } else {
                binding.optionsCiclo.setAlpha(uncheckedButton);
                binding.optionsCicloOk.setAlpha(checkedButton);
            }
        });

        viewModel.getCramps().observe(getViewLifecycleOwner(), cramps -> {
            this.cramps = cramps;

            if (cramps) {
                binding.optionsCrampi.setAlpha(checkedButton);
            } else {
                binding.optionsCrampi.setAlpha(uncheckedButton);
            }

            setPainsOkButton();
        });

        viewModel.getNausea().observe(getViewLifecycleOwner(), nausea -> {
            this.nausea = nausea;

            if (nausea) {
                binding.optionsNausea.setAlpha(checkedButton);
            } else {
                binding.optionsNausea.setAlpha(uncheckedButton);
            }

            setPainsOkButton();
        });

        viewModel.getSwelling().observe(getViewLifecycleOwner(), swelling -> {
            this.swelling= swelling;

            if(swelling){
                binding.optionsGonfiore.setAlpha(checkedButton);
            } else {
                binding.optionsGonfiore.setAlpha(uncheckedButton);
            }

            setPainsOkButton();
        });

//        viewModel.getNoBlood().observe(getViewLifecycleOwner(),noBlood ->{
//            if(noBlood){
//                binding.optionsBloodOk.setAlpha(checkedButton);
//                binding.optionsBloodDrop.setAlpha(uncheckedButton);
//            } else{
//                binding.optionsBloodOk.setAlpha(uncheckedButton);
//                binding.optionsBloodDrop.setAlpha(checkedButton);
//            }
//        } );

    }

    private void setPainsOkButton(){
        if(!cramps && !swelling && !nausea){
            binding.optionsPainOk.setAlpha(checkedButton);
        } else {
            binding.optionsPainOk.setAlpha(uncheckedButton);
        }
    }

}