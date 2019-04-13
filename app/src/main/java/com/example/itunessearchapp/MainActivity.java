package com.example.itunessearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ITunesSearchApi iTunesSearchApi;
    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {

        final EditText searchInput = findViewById(R.id.search_input);
        Button searchButton = findViewById(R.id.search_button);
        final TextView requestText = findViewById(R.id.request_text_view);
        resultText = findViewById(R.id.response_text_view);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capturedMovie = searchInput.getText().toString();
                new Search(capturedMovie);
                String resultToDisplay = "You searched for: " + capturedMovie;
                requestText.setText(resultToDisplay);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://itunes.apple.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                iTunesSearchApi = retrofit.create(ITunesSearchApi.class);
                retrieveSearchResults(capturedMovie);
            }
        });

    }

    private void retrieveSearchResults(String searchTerm) {

        Call<SearchWrapper> call = iTunesSearchApi.getSearchResult(searchTerm, "us", "movie");

        call.enqueue(new Callback<SearchWrapper>() {
            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                if(!response.isSuccessful()){
                    resultText.setText("Response code: " + response.code() + "\n");
                    return;
                }

                List<Search> searchResults = response.body().searches();

                StringBuilder output = new StringBuilder();
                for(Search result : searchResults){
                    output.append("Artist Name " + result.getArtistName() + "\n");
                    output.append("Movie name " + result.getMovieName() + "\n");
                    output.append("Genre " + result.getPrimaryGenreName() + "\n");
                    output.append("Advisory rating " + result.getContentAdvisoryRating() + "\n");
                    output.append("Rental price " + result.getTrackRentalPrice() + "\n");
                    output.append("Buy price " + result.getTrackPrice() + "\n");
                    output.append("\n\n");
                    resultText.setText(output.toString());
                }
            }

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
                resultText.setText(t.getMessage());
            }
        });
    }


}
