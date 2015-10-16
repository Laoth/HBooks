package com.example.laotshi.hbooks;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.laotshi.hbooks.fragment.FirstFragment;
import com.example.laotshi.hbooks.fragment.SecondFragment;
import com.example.laotshi.hbooks.listen.OnNavigationItemSelected;

public class MainActivity extends AppCompatActivity {

    private static final String NAV_ITEM_ID = "NAV_ITEM_ID";
    private static final long DRAWER_CLOSE_DELAY_MS = 350;
    private DrawerLayout mDrawerLayout;
    private int mNavItemId;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirstFragment mFirstFragment = new FirstFragment();
    private SecondFragment mSecondFragment = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout); //Drawer Layout
        Toolbar toolBar = (Toolbar) findViewById(R.id.activity_main_toolbar); //Toolbar
        setSupportActionBar(toolBar);

        if (null == savedInstanceState) {
            mNavItemId = R.id.drawer_item_1;
        }else{
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelected);

        navigationView.getMenu().findItem(mNavItemId).setChecked(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        navigate(mNavItemId);

    }

    private Handler mDrawerActionHandler;
    public OnNavigationItemSelected onNavigationItemSelected = new OnNavigationItemSelected() {
        @Override
        public boolean onNavigationItemSelected(final MenuItem menuItem) {
            menuItem.setChecked(true);
            mNavItemId = menuItem.getItemId();

            mDrawerLayout.closeDrawer(GravityCompat.START);
            mDrawerActionHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate(menuItem.getItemId());
                }
            }, DRAWER_CLOSE_DELAY_MS);
            return true;
        }
    };

    /*

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        // update highlighted item in the navigation menu
        menuItem.setChecked(true);
        mNavItemId = menuItem.getItemId();

        // allow some time after closing the drawer before performing real navigation
        // so the user can see what is happening
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(menuItem.getItemId());
            }
        }, DRAWER_CLOSE_DELAY_MS);
        return true;
    }
     */

    private void navigate(final int itemId){
        switch (itemId){
            case R.id.drawer_item_1:
                getFragmentManager().beginTransaction().replace(R.id.content,mFirstFragment).commit();
                break;
            case R.id.drawer_item_2:
                getFragmentManager().beginTransaction().replace(R.id.content,mSecondFragment).commit();
                break;
            default:
                break;
        }
    }

    /*
    private void navigate(final int itemId) {
    switch (itemId) {
      case R.id.drawer_item_1:
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.content, mFirstFragment)
            .commit();
        break;
      case R.id.drawer_item_2:
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.content, mSecondFragment)
            .commit();
        break;
      default:
        // ignore
        break;
    }
  }
     */

    /*
    // set up the hamburger icon to open and close the drawer
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
        R.string.close);
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    mDrawerToggle.syncState();

    navigate(mNavItemId);
  }
     */

}
