package com.andersen.sadwyn.databinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andersen.sadwyn.databinding.R;
import com.andersen.sadwyn.databinding.databinding.ActivityMainBinding;
import com.andersen.sadwyn.databinding.viewmodel.MainActivityVM;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by Sadwyn on 26.04.2017.
 */

public class MainActivity extends AppCompatActivity implements Observer{

    private ActivityMainBinding activityMainBinding;
    private MainActivityVM mainActivityVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupRecyclerView(activityMainBinding.recycler);
        setupObserver(mainActivityVM);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainActivityVM.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("onSaveInstanceState", "Save");
        mainActivityVM.onSaveInstanceState(outState);
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityVM = new MainActivityVM();
        activityMainBinding.setMainActivityVM(mainActivityVM);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MainActivityVM) {
            MemesAdapter peopleAdapter = (MemesAdapter) activityMainBinding.recycler.getAdapter();
            MainActivityVM peopleViewModel = (MainActivityVM) o;
            peopleAdapter.setMemList(peopleViewModel.getMemes());
        }
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        MemesAdapter adapter = new MemesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }
}