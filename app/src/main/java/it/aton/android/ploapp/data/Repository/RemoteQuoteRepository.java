package it.aton.android.ploapp.data.Repository;


import io.reactivex.rxjava3.core.Single;
import it.aton.android.ploapp.data.remote.QuoteAPI;
import it.aton.android.ploapp.data.remote.QuoteAPIInterface;
import it.aton.android.ploapp.data.remote.model.Quote;

public class RemoteQuoteRepository {

    QuoteAPIInterface quoteAPIInterface= QuoteAPI.getClient();

    public RemoteQuoteRepository() {
    }

    public Single<Quote> getRandomQuote(){
        return quoteAPIInterface.getRandomQuote();
    }
}
