package it.aton.android.ploapp.ui.start;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.schedulers.Schedulers;
import it.aton.android.ploapp.data.Repository.RemoteQuoteRepository;
import it.aton.android.ploapp.data.remote.model.Quote;

public class StartViewModel extends ViewModel {

    private final RemoteQuoteRepository quoteRepository= new RemoteQuoteRepository();

    private MutableLiveData<Quote> quoteData = new MutableLiveData<>();

    public StartViewModel() {
    }

    @SuppressLint("CheckResult")
    public void loadRandomQuote(){
        quoteRepository.getRandomQuote()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        quote->{
                            quoteData.postValue(quote);
                        },
                        throwable ->{
                            Log.e("REMOTE-QUOTE", "[ Error while downloading the quote: "+ throwable.getMessage()+" ]");
        } );
    }

    public LiveData<Quote> getQuote(){
        return quoteData;
    }
}
