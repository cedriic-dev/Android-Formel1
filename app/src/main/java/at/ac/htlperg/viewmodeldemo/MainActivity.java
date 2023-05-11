package at.ac.htlperg.viewmodeldemo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.List;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.driverList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DriverService userService = new DriverService();

        userService.load().thenAccept(driverList -> {
            drivers = driverList;
            runOnUiThread(() -> recyclerView.setAdapter(new DriverAdapter()));
        }).exceptionally(e -> {
            Log.e("MainActivity", "Error loading data", e);
            return null;
        });
    }

    class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

        @NonNull
        @Override
        public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

            // Set the color of the teamNameView based on the team name
            switch (driver.getTeam()) {
                case "Red Bull Racing":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.RedBull)));
                    break;
                case "Ferrari":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Ferrari)));
                    break;
                case "Mercedes":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Mercedes)));
                    break;
                case "Alpine":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Alpine)));
                    break;
                case "McLaren":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.McLaren)));
                    break;
                case "Alfa Romeo":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AlfaRomeo)));
                    break;
                case "Aston Martin":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AstonMartin)));
                    break;
                case "Haas":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.Haas)));
                    break;
                case "Alpha Tauri":
                    teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.AlphaTauri)));
                    break;
                case "Williams":
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
                intent.putExtra("id", driver.getDriverId());
                intent.putExtra("givenName", driver.getGivenName());
                intent.putExtra("familyName", driver.getFamilyName());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return drivers.size();
        }
        class DriverViewHolder extends RecyclerView.ViewHolder {
            TextView givenNameView;
            TextView familyNameView;
            TextView teamNameView;
            ImageView driverImageView;

            DriverViewHolder(@NonNull View itemView) {
                super(itemView);
                givenNameView = itemView.findViewById(R.id.givenName);
                familyNameView = itemView.findViewById(R.id.familyName);
                teamNameView = itemView.findViewById(R.id.teamName);
                driverImageView = itemView.findViewById(R.id.driverImage);
            }
        }
    }
}



