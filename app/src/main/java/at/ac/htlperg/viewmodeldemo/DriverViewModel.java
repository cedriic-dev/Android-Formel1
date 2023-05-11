package at.ac.htlperg.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class DriverViewModel extends ViewModel {
    private MutableLiveData<Model> data;

    public MutableLiveData<Model> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    public void updateData(List<Driver> drivers) {
        if (data != null) {
            Model model = new Model(drivers);
            data.setValue(model);
        }
    }
}
