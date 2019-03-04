package com.vivekraideveloper01vivekrai.cricdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public  ActionBarDrawerToggle toggle;
    private BottomNavigationView navigationView;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private HighlightsFragment highlightsFragment;
    private ConfabFragment confabFragment;
    private FrameLayout frameLayout;
    private int id;
    private String[] to = {"vivekraideveloper@gmail.com"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppRate.with(this).setInstallDays(1).setLaunchTimes(2).setRemindInterval(2).monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        frameLayout = findViewById(R.id.frame_layout);
        navigationView = findViewById(R.id.bottom_nav);
        homeFragment = new HomeFragment();
        highlightsFragment = new HighlightsFragment();
        newsFragment = new NewsFragment();
        confabFragment = new ConfabFragment();
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CricDroid");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, new HomeFragment());
        ft.addToBackStack("");
        ft.commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                id = item.getItemId();


                switch (id) {

                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_highlights:
                        setFragment(highlightsFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_news:
                        setFragment(newsFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_confab:
                        setFragment(confabFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;


                }
                return false;
            }
        });

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                drawerLayout.closeDrawers();
                break;

            case R.id.cricket_au:
                Intent intentAu = new Intent(MainActivity.this, WebActivity.class);
                intentAu.putExtra("url", "https://www.cricket.com.au/");
                startActivity(intentAu);
                break;

            case R.id.cricbuzz:
                Intent intentCric = new Intent(MainActivity.this, WebActivity.class);
                intentCric.putExtra("url", "https://www.cricbuzz.com/");
                startActivity(intentCric);
                break;

            case R.id.aboutUs:
                Intent aboutIntent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.help:
                final AlertDialog.Builder helpAlert = new AlertDialog.Builder(MainActivity.this);
                helpAlert.setIcon(R.drawable.official_logo);
                helpAlert.setTitle("Help");
                helpAlert.setMessage("CricDroid is your go to app for viewing all the cricketing actions going around the globe. Navigate to the Home, Highights, News and Confab section for an action packed journey. ");
                helpAlert.setPositiveButton("Go Ahead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        drawerLayout.closeDrawers();
                    }
                });
                helpAlert.setCancelable(false);
                helpAlert.show();
                break;
            case R.id.moreApps:
                Intent morIntent = new Intent(Intent.ACTION_VIEW);
                morIntent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=8604498569793359084"));
                if (morIntent != null) {
                    startActivity(morIntent);
                }
                break;

            case R.id.contact:
                Intent conatctIntent = new Intent(Intent.ACTION_SEND);
                conatctIntent.setData(Uri.parse("mailto:"));
                conatctIntent.putExtra(Intent.EXTRA_EMAIL, to);
                conatctIntent.putExtra(Intent.EXTRA_SUBJECT, "CricDroid feedback");
                conatctIntent.putExtra(Intent.EXTRA_TEXT, "");
                conatctIntent.setType("message/UTF-8");
                startActivity(Intent.createChooser(conatctIntent, "Please Choose your Email"));
                break;
            case R.id.terms:
                Intent termsIntent = new Intent(MainActivity.this, Terms.class);
                startActivity(termsIntent);
                break;
        }
        return true;
    }



    private void setFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.addToBackStack("");
        ft.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "CricDroid is an app that provides complete cricket coverage of all the matches around the globe! Download the app now to watch some of the most thrilling cricket encounters\n" + "https://play.google.com/store/apps/details?id=com.vivekraideveloper01vivekrai.cricdroid");
            startActivity(Intent.createChooser(shareIntent, "Share Using"));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to Exit!");
        builder.setTitle("CricDroid");
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}
