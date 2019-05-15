package com.planet.treeplantations.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;
import com.planet.treeplantations.fragments.All_Tree_Fragment;
import com.planet.treeplantations.fragments.Dashbord_Java_Fragment;
import com.planet.treeplantations.fragments.Dashbord_New;
import com.planet.treeplantations.fragments.Project_Fragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean doubleBackToExitPressedOnce = false;
    private Pref user_pref;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user_pref = new Pref(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.dashboard);
        View hView = navigationView.getHeaderView(0);
        TextView name = (TextView) hView.findViewById(R.id.name_h);
        TextView emai = (TextView) hView.findViewById(R.id.email_h);
        name.setText(user_pref.loadPreferences("name"));
        emai.setText(user_pref.loadPreferences("email"));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int c = fragmentManager.getBackStackEntryCount();
            List<Fragment> g = fragmentManager.getFragments();


           /* if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                if(c!=1) {
                    getSupportFragmentManager().popBackStack();
                }
            } else {*/
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {

                //   if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

                //   if(c!=1) {
                //        getSupportFragmentManager().popBackStack();
                //   }
                        /*else{
                            if (doubleBackToExitPressedOnce) {
                                super.onBackPressed();
                                return;
                            }
                            this.doubleBackToExitPressedOnce = true;
                            Toast.makeText(this, "Please click BACK again to exit!",
                                    Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    doubleBackToExitPressedOnce = false;
                                }
                            }, 2000);
                        }*/

                //}else {

                //  super.onBackPressed();
                if (doubleBackToExitPressedOnce) {
                    // super.onBackPressed();
                    MainActivity.this.finishAffinity();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit!",
                        Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
            // }
            //     }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Alert_logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Alert_logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to Logout?");
        //Yes Button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();

            }
        });
        //No Button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void logout() {
        user_pref.savePreferences("Id", "");
        user_pref.savePreferences("name", "");
        user_pref.savePreferences("loginName", "");
        user_pref.savePreferences("loginName", "");
        user_pref.savePreferences("email", "");
        user_pref.savePreferences("mobile", "");
        user_pref.savePreferences("stateName", "");
        user_pref.savePreferences("userRole", "");
        user_pref.savePreferences("password", "");
        user_pref.savePreferences("pid", "");
        user_pref.savePreferences("sid", "");
        user_pref.savePreferences("sid", "");

        Intent abc = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(abc);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            share_with();

        }

        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void share_with() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "NTPC Tree Plantation");
        startActivity(Intent.createChooser(sharingIntent, "Share with"));
    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.dashboard:
                fragment = new Dashbord_Java_Fragment();
                //   fragment = new DashBoard_without_binding();
                fragment = new Dashbord_New();

                break;
            case R.id.project:
                fragment = new Project_Fragment();
                break;
            case R.id.all_tree:
                fragment = new All_Tree_Fragment();
                break;
//            case R.id.search:
//                fragment = new Search_Fragment();
//                break;
            case R.id.po_mou:
                Intent intent = new Intent(MainActivity.this, All_Master_Activity.class);
                startActivity(intent);
                break;
            case R.id.change_password:
                Intent intent1 = new Intent(MainActivity.this, Change_Password_Activity.class);
                startActivity(intent1);
                break;

        }


        //replacing the fragment
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();

            FragmentManager manager = getSupportFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

            if (!fragmentPopped) { //fragment not in back stack, create it.
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(backStateName);
                ft.commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
