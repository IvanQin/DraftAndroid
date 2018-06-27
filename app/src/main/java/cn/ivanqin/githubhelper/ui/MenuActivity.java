package cn.ivanqin.githubhelper.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.util.Map;
import java.util.Set;

import cn.ivanqin.githubhelper.R;
import cn.ivanqin.githubhelper.TestActivity;
import cn.ivanqin.githubhelper.receiver.BatteryAlarmReceiver;

public class MenuActivity extends AppCompatActivity {
    public static final String EXTRA_LOGIN_NAME = "EXTRA_LOGIN_NAME";
    public static final String ARGUMENT_MAIN_DISPLAY = "ARGUMENT_MAIN_DISPLAY";
    public static final int CODE_LOGIN_NAME = 0;
    private NavigationView mNavView;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle toggle;
    private Fragment mainFragment;
    private Fragment homeFragment;
    private BroadcastReceiver mBatteryAlarmReceiver;
    private static final String DEBUG_TAG = MenuActivity.class.getSimpleName();
    private final Context context = this;


    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_menu);
        init();

    }

    private void init() {

        mToolbar = findViewById(R.id.toolbar);
        mNavView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar
                , R.string.menuOpen, R.string.menuClose) {
            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }
        };
        drawerLayout.addDrawerListener(toggle);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (item.getItemId()) {

                    case R.id.menu_account:
                        mainFragment = new MainFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString(ARGUMENT_MAIN_DISPLAY, "hey");
//                        mainFragment.setArguments(bundle);
                        transaction.replace(R.id.main_container, mainFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        transaction.show(mainFragment);
                        break;
                    case R.id.menu_home:
                        homeFragment = new TestFragment();

//                        homeFragment.setArguments(bundle);
                        transaction.replace(R.id.main_container, homeFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        transaction.show(homeFragment);
                        break;
                    case R.id.menu_search:
                        startLoginActivity(context);
                        break;
                    default:
                        break;
                }

                drawerLayout.closeDrawers();
                Log.e("IvanQin", String.format("item %d selected", item.getItemId()));
                return true;
            }
        });

        mNavView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Log.e("IvanQin", "Close drawers");
            }
        });
//        mainFragment = new MainFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.main_container, mainFragment);
//        transaction.commit();
//        transaction.show(mainFragment);


//        SharedPreferences sp = getSharedPreferences("test",MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBatteryAlarmReceiver = new BatteryAlarmReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryAlarmReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBatteryAlarmReceiver != null) {
            unregisterReceiver(mBatteryAlarmReceiver);
        }

    }

    @Override
    public void onPostCreate(Bundle onSavedInstanceState) {
        super.onPostCreate(onSavedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(DEBUG_TAG, "on touch event");
        int action = event.getAction();
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.e(DEBUG_TAG, "action was down");
                break;
            case (MotionEvent.ACTION_MOVE):
                Log.e(DEBUG_TAG, "action was move");
                break;
            case (MotionEvent.ACTION_UP):
                Log.e(DEBUG_TAG, "action was up");
                break;
            case (MotionEvent.ACTION_CANCEL):
                Log.e(DEBUG_TAG, "action was cancel");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODE_LOGIN_NAME:
                if (resultCode == Activity.RESULT_OK) {
                    String loginName = data.getStringExtra(EXTRA_LOGIN_NAME);
                    Log.e(DEBUG_TAG, loginName);
                    mainFragment = new MainFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(MenuActivity.ARGUMENT_MAIN_DISPLAY, loginName);
                    mainFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container, mainFragment).commit();
                }
                break;
            default:
                break;
        }
    }

    public static void startLoginActivity(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
//        starter.putExtra();
        ((Activity) context).startActivityForResult(starter, CODE_LOGIN_NAME);

    }


}
