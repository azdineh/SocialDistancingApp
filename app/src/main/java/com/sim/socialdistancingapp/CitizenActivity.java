package com.sim.socialdistancingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CitizenActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager,viewPager2;
    ViewPagerAdapter viewPagerAdapter,viewPagerAdapter2;
    Fragment citizenRequestFragment,getCitizenQRCodeFramgment;

    public static CitizenRequest citizenRequest;
    public static ArrayList<Destination> destinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        tabLayout=findViewById(R.id.tab_layout);

        viewPager=findViewById(R.id.view_pager);
        viewPager2=findViewById(R.id.view_pager2);

        tabLayout.setupWithViewPager(viewPager);

        citizenRequestFragment=CitizenRequestFragment.newInstance("A","B",citizenRequest);
        getCitizenQRCodeFramgment=CitizenQRCFragment.newInstance("A","B");

        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter2=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(citizenRequestFragment,"Request");
        viewPagerAdapter.addFragment(getCitizenQRCodeFramgment,"QR Code");


        viewPager.setAdapter(viewPagerAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                Intent loginIntent=new Intent(this, MainActivity.class);
                startActivity(loginIntent);
                finish();
                return true;

            case R.id.new_request:
                viewPager2.setVisibility(View.INVISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setupWithViewPager(viewPager);
                toolbar.setTitle("Social Distancing");
                viewPagerAdapter2.clear();
                viewPager.setCurrentItem(0);
                return true;

            case R.id.recommendations:
                viewPager.setVisibility(View.INVISIBLE);
                viewPager2.setVisibility(View.VISIBLE);
                toolbar.setTitle("Recommendations");
                tabLayout.setupWithViewPager(viewPager2);
                viewPagerAdapter2.addFragment(RecTimesFragment.newInstance("A","B"),"Times");
                viewPagerAdapter2.addFragment(RecPlacesFragment.newInstance("A","B"),"Places");
                viewPager2.setAdapter(viewPagerAdapter2);
                return true;

            case R.id.menu_aboutus:
                Intent aboutusIntent=new Intent(this,AboutusActivity.class);
                startActivity(aboutusIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments=new ArrayList<>();
        List<String> fragmentstitles=new ArrayList<>();


        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            fragmentstitles.add(title);
        }

        void clear(){
            fragments.clear();
            fragmentstitles.clear();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentstitles.get(position);
        }


    }






}