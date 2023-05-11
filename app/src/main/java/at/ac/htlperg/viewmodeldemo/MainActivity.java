package at.ac.htlperg.viewmodeldemo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

        UserService userService = new UserService();

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

            ImageView driverImageView = convertView.findViewById(R.id.driverImage);
            Picasso.get().load(driver.getImage()).into(driverImageView);

            return convertView;
        }
    }
}
