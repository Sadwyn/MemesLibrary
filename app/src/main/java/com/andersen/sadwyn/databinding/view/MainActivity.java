package com.andersen.sadwyn.databinding.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andersen.sadwyn.databinding.R;
import com.andersen.sadwyn.databinding.model.pojo.MemsData;
import com.andersen.sadwyn.databinding.model.Model;
import com.andersen.sadwyn.databinding.model.ModelImpl;
import com.andersen.sadwyn.databinding.view.adapters.MemesAdapter;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        Model model = ModelImpl.getInstance();
        model.getImages().doOnNext(new Consumer<MemsData>() {
            @Override
            public void accept(MemsData memsData) throws Exception {
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                MemesAdapter adapter = new MemesAdapter(memsData.getData().getMemes());
                recyclerView.setAdapter(adapter);
            }
        }).subscribe();
    }
}
