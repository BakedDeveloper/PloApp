package it.aton.android.ploapp.ui.newpoo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


import io.reactivex.rxjava3.schedulers.Schedulers;
import it.aton.android.ploapp.data.local.converters.Converters;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.data.local.repositories.PooRepository;
import it.aton.android.ploapp.utils.ImagesRepository;

public class NewPooViewModel extends AndroidViewModel {

    private final ImagesRepository imagesRepository = new ImagesRepository();
    private final PooRepository pooRepository;

    private int pooTypeImagePosition = 0;
    private int pooColorPosition = 7;
    private final ArrayList<Integer> pooTypeImageIds = new ArrayList<>(Arrays.asList(imagesRepository.getPooTypeImages()));
    private final ArrayList<Integer> pooColorIds = new ArrayList<>(Arrays.asList(imagesRepository.getPooColors()));


    private final MutableLiveData<Integer> pooTypeImageId = new MutableLiveData<>(pooTypeImageIds.get(0));
    private final MutableLiveData<Integer> pooColorId = new MutableLiveData<>(pooColorIds.get(7));
    private final MutableLiveData<Integer> quantity = new MutableLiveData<>(50);
    private final MutableLiveData<LocalDateTime> dateTime = new MutableLiveData<>(LocalDateTime.now());
    private final MutableLiveData<String> sessionTime = new MutableLiveData<>("20");

    private final MutableLiveData<Boolean> cramps = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> nausea = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> swelling = new MutableLiveData<>(false);

    private final MutableLiveData<Boolean> isBloodPresent = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> period = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isEnemaUsed = new MutableLiveData<>(false);



    public NewPooViewModel(@NonNull Application application) {
        super(application);
        pooRepository = new PooRepository(application);
    }

    public void savePooInDb() {

        int quantityImageResource;
        if (quantity.getValue() <= 100) {
            quantityImageResource = imagesRepository.getPooQuantityImage()[0];
        } else if (quantity.getValue() > 100 && quantity.getValue() <= 200) {
            quantityImageResource = imagesRepository.getPooQuantityImage()[1];
        } else {
            quantityImageResource = imagesRepository.getPooQuantityImage()[2];
        }

        Poo poo = new Poo(
                1,
                pooColorId.getValue(),
                pooTypeImageId.getValue(),
                quantity.getValue(),
                Converters.fromDateToString(dateTime.getValue()),
                isBloodPresent.getValue(),
                cramps.getValue(),
                nausea.getValue(),
                swelling.getValue(),
                period.getValue(),
                Integer.parseInt(sessionTime.getValue()),
                isEnemaUsed.getValue(),
                quantityImageResource
                //TODO aggiungere Cramps, period, nausea, swelling
        );

        pooRepository.addPoo(poo)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> Log.i("INSERT", "A new poo was added: " + poo.toString()),

                        throwable -> Log.e("INSERT", throwable.getMessage()));
    }

    public void setPooQuantity(int pooQuantity) {
        this.quantity.setValue(pooQuantity);
    }

    public void setPooDateTime(LocalDateTime pooDateTime) {
        if (!this.dateTime.getValue().equals(pooDateTime)) {
            this.dateTime.setValue(pooDateTime);
        }
    }

    public void setIsBloodPresent() {
        this.isBloodPresent.setValue(!isBloodPresent.getValue());
    }

    public void setNoPain(){
        this.cramps.setValue(false);
        this.nausea.setValue(false);
        this.swelling.setValue(false);
    }

    public void setCramps() {
        this.cramps.setValue(!cramps.getValue());
    }

    public void setNausea() {
       this.nausea.setValue(!nausea.getValue());
    }

    public void setSwelling() {
      this.swelling.setValue(!swelling.getValue());
    }

    public void setPeriod() {
        this.period.setValue(!period.getValue());
    }

    public void setIsEnemaUsed() {
      this.isEnemaUsed.setValue(!isEnemaUsed.getValue());
    }

    public void setSessionTime(String pooTime) {
        if (!this.sessionTime.getValue().equals(pooTime)) {
            this.sessionTime.setValue(pooTime);
        }
    }


    public void nextPooTypeImage() {
        if (pooTypeImagePosition < pooTypeImageIds.size() - 1) {
            this.pooTypeImageId.setValue(pooTypeImageIds.get(this.pooTypeImagePosition += 1));
        } else {
            this.pooTypeImageId.setValue(pooTypeImageIds.get(this.pooTypeImagePosition = 0));
        }
    }

    public void previousPooTypeImage() {
        if (pooTypeImagePosition >= 1) {
            this.pooTypeImageId.setValue(pooTypeImageIds.get(this.pooTypeImagePosition -= 1));
        } else {
            this.pooTypeImageId.setValue(pooTypeImageIds.get(this.pooTypeImagePosition = pooTypeImageIds.size() - 1));
        }
    }

    public void nextPooColor() {
        if (pooColorPosition < pooColorIds.size() - 1) {
            this.pooColorId.setValue(pooColorIds.get(this.pooColorPosition += 1));
        } else {
            this.pooColorId.setValue(pooColorIds.get(this.pooColorPosition = 0));
        }
    }

    public void previousPooColor() {
        if (pooColorPosition >= 1) {
            this.pooColorId.setValue(pooColorIds.get(this.pooColorPosition -= 1));
        } else {
            this.pooColorId.setValue(pooColorIds.get(this.pooColorPosition = pooColorIds.size() - 1));
        }
    }

    public LiveData<Integer> getPooTypeImageId() {
        return pooTypeImageId;
    }

    public LiveData<Integer> getPooColorId() {
        return pooColorId;
    }

    public MutableLiveData<Integer> getQuantity() {
        return quantity;
    }

    public MutableLiveData<LocalDateTime> getDateTime() {
        return dateTime;
    }

    public MutableLiveData<String> getSessionTime(){
        return sessionTime;
    }

    public MutableLiveData<Boolean> getIsBloodPresent() {
        return isBloodPresent;
    }

    public MutableLiveData<Boolean> getCramps() {
        return cramps;
    }

    public MutableLiveData<Boolean> getNausea() {
        return nausea;
    }

    public MutableLiveData<Boolean> getSwelling() {
        return swelling;
    }

    public MutableLiveData<Boolean> getPeriod() {
        return period;
    }

    public MutableLiveData<Boolean> getIsEnemaUsed() {
        return isEnemaUsed;
    }






//    @SuppressLint("CheckResult")
//    public void insertMockPoo() {
//        Poo mockPoo = new Poo(1, 1, 1, 3, "data", false, false, 20, false);
//        pooRepository.addPoo(mockPoo)
//                .subscribeOn(Schedulers.computation())
//                .subscribe(() -> Log.i("[DATABASE]", "Mock Poo Added to database"));
//    }


}
