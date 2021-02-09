package com.example.chess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setupFragments();
    }


    private void setupFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(StartGameFragment.newInstance());
        fragmentList.add(GamesViewFragment.newInstance());
        SlidePagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new FlipHorizontalPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initializeViews(){
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemChessGuide){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://Kvesss.github.io"));
            startActivity(browserIntent);
        }
        if(item.getItemId() == R.id.eng){
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration config = res.getConfiguration();
            config.setLocale(new Locale(""));
            res.updateConfiguration(config, dm);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        if (item.getItemId() == R.id.cro){
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration config = res.getConfiguration();
            config.setLocale(new Locale("hr"));
            res.updateConfiguration(config, dm);
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        return true;
    }
}