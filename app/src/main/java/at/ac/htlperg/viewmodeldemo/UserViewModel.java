package at.ac.htlperg.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<Model> data;

    public MutableLiveData<Model> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    public void updateData(Model model) {
        if (data != null) {
            data.setValue(model);
        }
    }
}
