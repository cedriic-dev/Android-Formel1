package at.ac.htlperg.viewmodeldemo.services;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class DriverService {
    private static final String TAG = "UserService";
    private static final String URL = "https://planitup.eu/images/drivers.json";

    public CompletableFuture<List<Driver>> load() {
        try {
            URL url = new URL(URL);
            ObjectMapper mapper = new ObjectMapper();

            return CompletableFuture.supplyAsync(() -> {
                try {
                    Response response = mapper.readValue(url, Response.class);
                    List<Driver> drivers = response.getDrivers();
                    Log.d(TAG, "Data loaded: " + drivers.toString());
                    return drivers;
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

    private static class Response {
        private List<Driver> Drivers;

        public List<Driver> getDrivers() {
            return Drivers;
        }

        public void setDrivers(List<Driver> drivers) {
            Drivers = drivers;
        }
    }
}
