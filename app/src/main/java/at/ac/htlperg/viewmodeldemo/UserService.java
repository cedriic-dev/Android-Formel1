package at.ac.htlperg.viewmodeldemo;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import at.ac.htlperg.viewmodeldemo.model.MRData;
public class UserService {
    private static final String TAG = "UserService";
    private static final String URL = "https://ergast.com/api/f1/drivers.json";

    public CompletableFuture<MRData> load() {
        try {
            var url = new URL(URL);
            var mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return CompletableFuture.supplyAsync(() -> {
                MRData data;
                try {
                    data = mapper.readValue(url, MRData.class);
                    Log.d(TAG, "Data loaded: " + data.toString());
                    return data;
                } catch (IOException e) {
                    Log.e(TAG, "Error parsing JSON", e);
                    throw new CompletionException(e);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error loading data", e);
            throw new CompletionException(e);
        }
    }
}
