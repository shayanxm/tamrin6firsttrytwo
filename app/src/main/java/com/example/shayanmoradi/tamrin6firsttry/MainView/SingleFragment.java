package com.example.shayanmoradi.tamrin6firsttry.MainView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.shayanmoradi.tamrin6firsttry.R;

public abstract class SingleFragment extends AppCompatActivity {
public abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.fragment_container_id)==null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_id,createFragment())
                    .commit();
        }
    }
}
