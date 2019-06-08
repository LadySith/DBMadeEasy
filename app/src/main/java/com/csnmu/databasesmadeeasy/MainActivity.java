package com.csnmu.databasesmadeeasy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/*Code reference for tabs: https://www.youtube.com/watch?v=7zaKUc2zfpI   */
public class MainActivity extends FragmentActivity implements AboutFragment.OnFragmentInteractionListener, TopicsFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        // Adding Fragments
        adapter.AddFragment(new TopicsFragment(), "Topics");
        adapter.AddFragment(new AboutFragment(), "About");

        // Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // get toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);


        //Setting up MaterialDrawer: https://github.com/mikepenz/MaterialDrawer

        // build drawer

        //TODO: Add header with account details and DBME logo

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this).withTranslucentStatusBar(true).withHeaderBackground(R.drawable.navheader)
                .build();


        new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .withSelectedItem(-1)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Logout"))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            //When item in drawer selected, do these
                            case 1: {
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                break;
                            }
                        }
                        return true;
                    }
                }).build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
