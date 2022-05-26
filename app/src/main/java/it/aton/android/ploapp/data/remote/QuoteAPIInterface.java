package it.aton.android.ploapp.data.remote;


import io.reactivex.rxjava3.core.Single;
import it.aton.android.ploapp.data.remote.model.Quote;
import retrofit2.http.GET;

public interface QuoteAPIInterface {
    @GET("random")
    public Single<Quote> getRandomQuote();
}
