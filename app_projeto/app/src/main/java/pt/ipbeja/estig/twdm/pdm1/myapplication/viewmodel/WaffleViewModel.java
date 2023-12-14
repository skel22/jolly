package pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartRepository;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Waffle;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.WaffleRepository;

public class WaffleViewModel extends AndroidViewModel {
    private WaffleRepository waffleRepository;

    public WaffleViewModel(@NonNull Application application){
        super(application);
        this.waffleRepository = new WaffleRepository(application.getApplicationContext());
    }

    public LiveData<List<Waffle>> getAll() { return waffleRepository.getAll(); }

    public LiveData<Waffle> getWaffleById(long waffleId) { return waffleRepository.getWaffleById(waffleId); }

}
