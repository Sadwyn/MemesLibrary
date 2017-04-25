package com.andersen.sadwyn.databinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andersen.sadwyn.databinding.data.MemsData;
import com.andersen.sadwyn.databinding.views.adapters.MemesAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        App.getApi().getMemes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MemsData>() {
                    @Override
                    public void accept(MemsData memsData) throws Exception {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        MemesAdapter adapter = new MemesAdapter(memsData.getData().getMemes());
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
}
