package com.example.shayanmoradi.tamrin6firsttry.MainView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.shayanmoradi.tamrin6firsttry.R;

public class MainActivity extends SingleFragment {
    private static final String EXTRA_CRIME_ID = "com.example.amin.criminalintent.crime_id";
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private  ViewPager viewPager;


//    public static Intent newIntent(Context context, UUID crimeId) {
//        Intent intent = new Intent(context, CrimeDetailActivity.class);
//        intent.putExtra(EXTRA_CRIME_ID, crimeId);
//        return intent;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllTasksFragment(), "All tasks");
        adapter.addFragment(new AllTasksFragment(), "Done Tasks");
        adapter.addFragment(new AllTasksFragment(), "un Done Task ");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());


//
//            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
             //   Toast.makeText(MainActivity.this,"reusme",Toast.LENGTH_LONG).show();

            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                // Check if this is the page you want.
            }
        });


        viewPager.setOffscreenPageLimit(3);
    }


    @Override
    public Fragment createFragment() {
        return new DoneTaskFragment();

    }
}
