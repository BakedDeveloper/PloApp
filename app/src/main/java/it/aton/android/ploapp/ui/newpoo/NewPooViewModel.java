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
import io.reactivex.schedulers.Schedulers;
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
    private final MutableLiveData<Boolean> isBloodPresent = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isPainful = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isEnemaUsed = new MutableLiveData<>(false);
    private final MutableLiveData<String> sessionTime = new MutableLiveData<>("20");


    public NewPooViewModel(@NonNull Application application) {
        super(application);
        pooRepository = new PooRepository(application);
    }

    public void savePooInDb(){
        Poo poo= new Poo(
                1,
                pooColorId.getValue(),
                pooTypeImageId.getValue(),
                quantity.getValue(),
                Converters.fromDateToString(dateTime.getValue()),
                isBloodPresent.getValue(),
                isPainful.getValue(),
                Integer.parseInt(sessionTime.getValue()),
                isEnemaUsed.getValue());

        pooRepository.addPoo(poo);
    }


    //TODO AGGIUNGI CONDIZIONI A TUTTI I SET

    public void setPooQuantity(int pooQuantity){
        this.quantity.setValue(pooQuantity);
    }

    public void setPooDateTime(LocalDateTime pooDateTime){
        if(!this.dateTime.getValue().equals(pooDateTime)){
            this.dateTime.setValue(pooDateTime);
        }
    }

    public void setIsBloodPresent(boolean isBloodPresent){
        if(!this.isBloodPresent.getValue().equals(isBloodPresent)){
            this.isBloodPresent.setValue(isBloodPresent);
        }
    }

    public void setIsPainful(boolean isPainful){
        if(!this.isPainful.getValue().equals(isPainful)) {
            this.isPainful.setValue(isPainful);
        }
    }

    public void setIsEnemaUsed(boolean isEnemaUsed){
        if(!this.isEnemaUsed.getValue().equals(isEnemaUsed)){
            this.isEnemaUsed.setValue(isEnemaUsed);
        }
    }

    public void setSessionTime(String pooTime){
        if(!this.sessionTime.getValue().equals(pooTime)){
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

    public MutableLiveData<Boolean> getIsBloodPresent() {
        return isBloodPresent;
    }

    public MutableLiveData<Boolean> getIsPainful() {
        return isPainful;
    }

    public MutableLiveData<Boolean> getIsEnemaUsed() {
        return isEnemaUsed;
    }

    public MutableLiveData<String> getSessionTime() {
        return sessionTime;
    }



    @SuppressLint("CheckResult")
    public void insertMockPoo() {
        Poo mockPoo = new Poo(1, 1, 1, 3, "data", false, false, 20, false);
        pooRepository.addPoo(mockPoo)
                .subscribeOn(Schedulers.computation())
                .subscribe(() -> Log.i("[DATABASE]", "Mock Poo Added to database"));
    }


}
