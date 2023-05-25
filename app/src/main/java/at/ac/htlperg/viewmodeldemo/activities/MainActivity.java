package at.ac.htlperg.viewmodeldemo.activities;

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

import at.ac.htlperg.viewmodeldemo.services.DriverService;
import at.ac.htlperg.viewmodeldemo.R;
import at.ac.htlperg.viewmodeldemo.model.Driver;
import at.ac.htlperg.viewmodeldemo.utils.Team;

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
            Team team = Team.fromTeamName(driver.getTeam());
            if (team != null) {
                teamNameBox.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(team.getColorResourceId())));
                holder.driverNumberView.setTextColor(getResources().getColor(team.getColorResourceId()));
            } else {
                // Handle the case when the team name is not recognized
                holder.teamNameView.setBackgroundColor(getResources().getColor(android.R.color.white));
            }


            Picasso.get().load(driver.getImage()).into(holder.driverImageView);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", driver.getPermanentNumber());
                intent.putExtra("givenName", driver.getGivenName());
                intent.putExtra("familyName", driver.getFamilyName());
                intent.putExtra("team", driver.getTeam());
                intent.putExtra("nationality", driver.getNationality());
                intent.putExtra("highest_race_finish", driver.getHighest_race_finish());
                intent.putExtra("grands_prix", driver.getGrands_prix());
                intent.putExtra("points", driver.getPoints());
                intent.putExtra("image", driver.getImage());

                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return drivers.size();
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



