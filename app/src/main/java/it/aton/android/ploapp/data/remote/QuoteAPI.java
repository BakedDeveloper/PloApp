package it.aton.android.ploapp.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteAPI {
    private static QuoteAPIInterface quoteAPIInterface = null;
    private static final  String BASE_URL="https://api.fisenko.net/v1/quotes/en/";

    public static QuoteAPIInterface getClient() {

        OkHttpClient client = new OkHttpClient.Builder().build();
        quoteAPIInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(QuoteAPIInterface.class);
        return quoteAPIInterface;
    }

   public  static QuoteAPIInterface returnClient(){
        if (quoteAPIInterface==null){
            return getClient();
        }
        return quoteAPIInterface;
    }
}
