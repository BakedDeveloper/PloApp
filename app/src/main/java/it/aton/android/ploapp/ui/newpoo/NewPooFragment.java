package it.aton.android.ploapp.ui.newpoo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.time.LocalDateTime;
import java.util.Calendar;

import it.aton.android.ploapp.R;
import it.aton.android.ploapp.databinding.FragmentNewPooBinding;

public class NewPooFragment extends Fragment {

    private FragmentNewPooBinding binding;
    private NewPooViewModel viewModel;

    private AlertDialog optionDialog;
    private MaterialCheckBox painCheck;
    private MaterialCheckBox bloodCheck;
    private MaterialCheckBox enemaCheck;

    private LocalDateTime dateTime;

    public NewPooFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(NewPooViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("New Poo...");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewPooBinding.inflate(getLayoutInflater(), container, false);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.newpooOptionsButton.setOnClickListener(v -> {
            optionDialog.show();
        });

        binding.newpooDateTimeButton.setOnClickListener(v -> {
            createDateTimePicker();
        });

        binding.newPooDoneButton.setOnClickListener(v -> {
            viewModel.savePooInDb();
        });

        painCheck = new MaterialCheckBox(getContext());
        painCheck.setText(R.string.text_there_was_pain);
        painCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setIsPainful(isChecked);
        });

        bloodCheck = new MaterialCheckBox(getContext());
        bloodCheck.setText(R.string.text_poo_contains_blood);
        bloodCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setIsBloodPresent(isChecked);
        });

        enemaCheck = new MaterialCheckBox(getContext());
        enemaCheck.setText(R.string.text_i_used_an_enema);
        enemaCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setIsEnemaUsed(isChecked);
        });

        binding.newpooSessionTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    viewModel.setSessionTime(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

       binding.newPooSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               viewModel.setPooQuantity(progress);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {}

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {}
       });


        generateOptionsDialogViews();
        observeDataFromViewModel();


    }

    private void observeDataFromViewModel() {
        viewModel.getPooTypeImageId().observe(getViewLifecycleOwner(), typeImageId -> {
            binding.newPooTypeImage.setImageResource(typeImageId);
        });

        viewModel.getDateTime().observe(getViewLifecycleOwner(), dateTime -> {
            this.dateTime= dateTime;
        });

        viewModel.getPooColorId().observe(getViewLifecycleOwner(), colorId -> {
            binding.newpooColorCard.setCardBackgroundColor(getResources().getColor(colorId, getActivity().getTheme()));
        });

        viewModel.getQuantity().observe(getViewLifecycleOwner(), quantity -> {
            binding.newPooSeekBar.setProgress(quantity, true);
            //todo add animation
        });

        viewModel.getSessionTime().observe(getViewLifecycleOwner(), sessionTime -> {
            if(!binding.newpooSessionTime.getText().toString().equals(sessionTime)){
                binding.newpooSessionTime.setText(sessionTime);
            }
        });

        viewModel.getIsBloodPresent().observe(getViewLifecycleOwner(), aBoolean -> {
            bloodCheck.setChecked(aBoolean);
        });

        viewModel.getIsPainful().observe(getViewLifecycleOwner(), aBoolean -> {
            painCheck.setChecked(aBoolean);
        });

        viewModel.getIsEnemaUsed().observe(getViewLifecycleOwner(), aBoolean -> {
            enemaCheck.setChecked(aBoolean);
        });

    }

    private void generateOptionsDialogViews() {
        LinearLayoutCompat optionsLayout;
        optionsLayout = new LinearLayoutCompat(getContext());
        optionsLayout.setOrientation(LinearLayoutCompat.VERTICAL);
        optionsLayout.addView(painCheck);
        optionsLayout.addView(bloodCheck);
        optionsLayout.addView(enemaCheck);

        //new AlertDialog.Builder(new ContextThemeWrapper(this.getContext(), R.style.AlertDialogCustom))
        //TODO Creare custom AlertDialog
        optionDialog = new AlertDialog.Builder(this.getContext())
                .setTitle("OPTIONS")
                .setView(optionsLayout)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
    }

    public void createDateTimePicker() {

        //TODO creare custom dateTimePicker

        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                    viewModel.setPooDateTime(dateTime
                            .withYear(year)
                            .withMonth(monthOfYear+1)
                            .withDayOfMonth(dayOfMonth)
                            .withHour(hourOfDay)
                            .withMinute(minute));
                }, dateTime.getHour(), dateTime.getMinute(), true).show();

                //getButton(1).setBackgroundColor(getResources().getColor(R.color.black, getActivity().getTheme())
            }
        }, dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).show();

}
}