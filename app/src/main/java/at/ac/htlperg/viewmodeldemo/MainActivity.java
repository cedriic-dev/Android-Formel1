package at.ac.htlperg.viewmodeldemo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getData().observe(this, new Observer<Model>() {
            @Override
            public void onChanged(Model model) {
                drivers = model.getDrivers();
                listView.setAdapter(new UserAdapter());
            }
        });

        new UserService().load().thenAccept(mrData -> {
            drivers = mrData.getDriverTable().getDrivers();
            Model model = new Model(drivers);
            viewModel.getData().setValue(model);
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Driver selectedDriver = drivers.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("driverId", selectedDriver.getDriverId());
                intent.putExtra("givenName", selectedDriver.getGivenName());
                intent.putExtra("familyName", selectedDriver.getFamilyName());
                startActivity(intent);
            }
        });
    }

    class UserAdapter implements ListAdapter {
        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int i) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return drivers.size();
        }

        @Override
        public Object getItem(int i) {
            return drivers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.driver_cell_layout, null);
            }

            Driver driver = drivers.get(i);

            TextView driverIdView = view.findViewById(R.id.id);
            driverIdView.setText(driver.getDriverId());

            return view;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
