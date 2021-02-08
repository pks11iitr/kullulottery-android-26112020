package com.kuil.KuilLottery.acitvities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.fragment.AboutUsFragment;
import com.kuil.KuilLottery.fragment.DownlineAgentFragment;
import com.kuil.KuilLottery.fragment.GameFragment;
import com.kuil.KuilLottery.fragment.HistoryFragment;
import com.kuil.KuilLottery.fragment.NotificationFragment;
import com.kuil.KuilLottery.fragment.PrivacyPolicyFragment;
import com.kuil.KuilLottery.fragment.ResultFragment;

import com.kuil.KuilLottery.fragment.TermConditionsFragment;
import com.kuil.KuilLottery.session.SessonManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    int BackCounter;
    Snackbar snackbar;
    TextView TvMobileGuest;
    TextView tv_tool;
    static public TextView txtBalnc, txtUser, txtCommisn;

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    SessonManager sessonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool);
        tv_tool = findViewById(R.id.tv_tool);
        setSupportActionBar(toolbar);
        sessonManager = new SessonManager(MainActivity.this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        snackbar = Snackbar.make(drawer, "Press once again to exit!", Snackbar.LENGTH_LONG);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtBalnc = headerView.findViewById(R.id.txtBalnc);
        txtUser = headerView.findViewById(R.id.txtUser);
        txtCommisn = headerView.findViewById(R.id.txtCommisn);

        displaySelectedScreen(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        displaySelectedScreen(R.id.nav_home);
        BackCounter = 0;
    }

    @Override
    public void onBackPressed() {
        tv_tool.setText("Game");
        if (BackCounter == 0) {
            if (snackbar.isShown()) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                super.onBackPressed();
            } else {
                snackbar.show();
            }
        } else {
            displaySelectedScreen(R.id.nav_home);
        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        String title = getString(R.string.app_name);
        switch (itemId) {
            case R.id.nav_home:
                tv_tool.setText("Game");
                fragment = new GameFragment();
                BackCounter = 0;
                drawer.closeDrawers();
                break;
            case R.id.nav_gallery:
                tv_tool.setText("History");
                fragment = new HistoryFragment();
                drawer.closeDrawers();
                BackCounter++;
                break;

            case R.id.nav_downHist:
                startActivity(new Intent(MainActivity.this, DownLineActivity.class));
                drawer.closeDrawers();
                BackCounter++;
                break;

            case R.id.nav_result:
                tv_tool.setText("Result");
                fragment = new ResultFragment();
                drawer.closeDrawers();
                BackCounter++;
                break;

            case R.id.nav_notification:
//                tv_tool.setText("Notification");
//                drawer.closeDrawers();
//                fragment = new NotificationFragment();
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                BackCounter++;
                break;


//            case R.id.nav_about_us:
//                tv_tool.setText("About Us");
//                drawer.closeDrawers();
//                fragment = new AboutUsFragment();
//                BackCounter++;
//                break;

            case R.id.nav_agent:
                tv_tool.setText("DownLine Bill");
                drawer.closeDrawers();
                fragment = new DownlineAgentFragment();
                BackCounter++;
                break;


//            case R.id.nav_privacy_policy:
//                tv_tool.setText("Privacy Policy");
//                drawer.closeDrawers();
//                fragment = new PrivacyPolicyFragment();
//                BackCounter++;
//                break;

//            case R.id.nav_contact_us:
//                tv_tool.setText("Contact Us");
//                drawer.closeDrawers();
//                fragment = new ContactUsFragment();
//                BackCounter++;
//                break;


//            case R.id.nav_term_condition:
//                tv_tool.setText("Term and Conditions");
//                drawer.closeDrawers();
//                fragment = new TermConditionsFragment();
//                BackCounter++;
//                break;


            case R.id.nav_logout:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Are you sure you want to logout?");
                builder1.setCancelable(false);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                sessonManager.setToken("");

                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                return;

                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                BackCounter++;
                break;

        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            // getSupportActionBar().setTitle(title);
        }
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
}