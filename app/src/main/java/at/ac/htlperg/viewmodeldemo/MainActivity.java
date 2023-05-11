package at.ac.htlperg.viewmodeldemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.driverList);

        DriverService userService = new DriverService();

        userService.load().thenAccept(driverList -> {
            drivers = driverList;
            runOnUiThread(() -> listView.setAdapter(new DriverAdapter()));
        }).exceptionally(e -> {
            Log.e("MainActivity", "Error loading data", e);
            return null;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Driver selectedDriver = drivers.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", selectedDriver.getDriverId());
            intent.putExtra("givenName", selectedDriver.getGivenName());
            intent.putExtra("familyName", selectedDriver.getFamilyName());
            startActivity(intent);
        });
    }

    class DriverAdapter extends ArrayAdapter<Driver> {

        DriverAdapter() {
            super(MainActivity.this, R.layout.driver_cell_layout, drivers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.driver_cell_layout, parent, false);
            }

            Driver driver = drivers.get(position);

            TextView givenNameView = convertView.findViewById(R.id.givenName);
            givenNameView.setText(driver.getGivenName());

            TextView familyNameView = convertView.findViewById(R.id.familyName);
            familyNameView.setText(driver.getFamilyName());

            TextView teamNameView = convertView.findViewById(R.id.teamName);
            teamNameView.setText(driver.getTeam());

            // Set the color of the teamNameView based on the team name
            switch (driver.getTeam()) {
                case "Red Bull Racing":
                    teamNameView.setTextColor(getResources().getColor(R.color.RedBull));
                    break;
                case "Ferrari":
                    teamNameView.setTextColor(getResources().getColor(R.color.Ferrari));
                    break;
                case "Mercedes":
                    teamNameView.setTextColor(getResources().getColor(R.color.Mercedes));
                    break;
                case "Alpine":
                    teamNameView.setTextColor(getResources().getColor(R.color.Alpine));
                    break;
                case "McLaren":
                    teamNameView.setTextColor(getResources().getColor(R.color.McLaren));
                    break;
                case "Alfa Romeo":
                    teamNameView.setTextColor(getResources().getColor(R.color.AlfaRomeo));
                    break;
                case "Aston Martin":
                    teamNameView.setTextColor(getResources().getColor(R.color.AstonMartin));
                    break;
                case "Haas":
                    teamNameView.setTextColor(getResources().getColor(R.color.Haas));
                    break;
                case "Alpha Tauri":
                    teamNameView.setTextColor(getResources().getColor(R.color.AlphaTauri));
                    break;
                case "Williams":
                    teamNameView.setTextColor(getResources().getColor(R.color.Williams));
                    break;
                default:
                    // Use the default text color if the team name is not recognized
                    teamNameView.setTextColor(getResources().getColor(android.R.color.black));
                    break;
            }

            ImageView driverImageView = convertView.findViewById(R.id.driverImage);
            Picasso.get().load(driver.getImage()).into(driverImageView);

            return convertView;
        }
    }
}
