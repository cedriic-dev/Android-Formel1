package at.ac.htlperg.viewmodeldemo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Driver> drivers;
    private List<Driver> filteredDrivers;
    private EditText searchEditText;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.driverList);
        searchEditText = findViewById(R.id.searchEditText);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DriverService userService = new DriverService();
        userService.load().thenAccept(driverList -> {
            drivers = driverList;
            filteredDrivers = new ArrayList<>(drivers);
            runOnUiThread(() -> {
                recyclerView.setAdapter(new DriverAdapter());
                setupSearchListener();
            });
        }).exceptionally(e -> {
            Log.e("MainActivity", "Error loading data", e);
            return null;
        });
    }

    private void setupSearchListener() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().toLowerCase();
                filteredDrivers.clear();
                if (searchText.isEmpty()) {
                    filteredDrivers.addAll(drivers);
                } else {
                    for (Driver driver : drivers) {
                        if (driver.getFamilyName().toLowerCase().contains(searchText)) {
                            filteredDrivers.add(driver);
                        }
                    }
                }
                Log.d(TAG,filteredDrivers.toString());
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }

    class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

        @NonNull
        @Override
        public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Hier wird das Layout für die einzelnen Fahrer/Cells festgelegt
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_cell_layout, parent, false);
            return new DriverViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
            Driver driver = drivers.get(position);
            Chip teamNameBox = holder.itemView.findViewById(R.id.teamName);
            holder.givenNameView.setText(driver.getGivenName());
            holder.familyNameView.setText(driver.getFamilyName());
            holder.teamNameView.setText(driver.getTeam());
            holder.driverNumberView.setText(driver.getPermanentNumber());

            // Set the color of the teamNameView based on the team name
            switch (driver.getTeam()) {
                case "Red Bull Racing":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.RedBull)));
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.RedBull));
                    break;
                case "Ferrari":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.Ferrari));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Ferrari)));
                    break;
                case "Mercedes":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.Mercedes));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Mercedes)));
                    break;
                case "Alpine":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.Alpine));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Alpine)));
                    break;
                case "McLaren":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.McLaren));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.McLaren)));
                    break;
                case "Alfa Romeo":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.AlfaRomeo));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AlfaRomeo)));
                    break;
                case "Aston Martin":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.AstonMartin));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AstonMartin)));
                    break;
                case "Haas":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.Haas));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Haas)));
                    break;
                case "Alpha Tauri":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.AlphaTauri));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AlphaTauri)));
                    break;
                case "Williams":
                    holder.driverNumberView.setTextColor(getResources().getColor(R.color.Williams));
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Williams)));
                    break;
                default:
                    // Use the default text color if the team name is not recognized
                    holder.teamNameView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    break;
            }

            Picasso.get().load(driver.getImage()).into(holder.driverImageView);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", driver.getPermanentNumber());
                intent.putExtra("givenName", driver.getGivenName());
                intent.putExtra("familyName", driver.getFamilyName());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return filteredDrivers.size();
        }
        class DriverViewHolder extends RecyclerView.ViewHolder {
            TextView driverNumberView;
            TextView givenNameView;
            TextView familyNameView;
            TextView teamNameView;
            ImageView driverImageView;

            DriverViewHolder(@NonNull View itemView) {
                super(itemView);
                driverNumberView = itemView.findViewById(R.id.driverNumber);
                givenNameView = itemView.findViewById(R.id.givenName);
                familyNameView = itemView.findViewById(R.id.familyName);
                teamNameView = itemView.findViewById(R.id.teamName);
                driverImageView = itemView.findViewById(R.id.driverImage);
            }
        }
    }
}



