package com.example.shayanmoradi.tamrin6firsttry.MainView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.shayanmoradi.tamrin6firsttry.R;

public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID = "com.example.amin.criminalintent.crime_id";
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private  ViewPager viewPager;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllTasksFragment(), getString(R.string.all_task));
        adapter.addFragment(new AllTasksFragment(), "Done Tasks");
        adapter.addFragment(new AllTasksFragment(), "un Done Task ");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {


            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                // Check if this is the page you want.
            }
        });


        viewPager.setOffscreenPageLimit(3);
    }



}
