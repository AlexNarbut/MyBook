package ru.startandroid.mybook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import ru.startandroid.mybook.adapter.TabsPageFragmentAdapter;
import ru.startandroid.mybook.timers.TimerSelecter;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private DrawerLayout drawLayout;
    FloatingActionsMenu timersMenu;
    private Toolbar  toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        InitToolbar();
        InitNavigationView();
        initTabLayout();
    }



    private void InitToolbar() {
        toolbar= (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tbar_title);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.news_item: {
                        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    public void clickFunc(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitNavigationView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawLayout,toolbar,R.string.view_navigation_open,R.string.view_navigation_close);
        drawLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigation =(NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.actionNavigatoinMainPage:
                        showTrainTab();
                        break;
                    case R.id.actionNavigatoinTimer:
                        showTimerActivity();
                        break;
                    case R.id.actionNavigatoinDownL:
                        showDownloadActivity();
                        break;
                    case R.id.actionNavigatoinBook:
                        showNotificationTab();
                        break;
                    case R.id.actionNavigatoinStat:
                        showStatistTab();
                        break;
                    case R.id.actionNavigatoinExit:
                        finish();
                        break;
                }
                return true;
            }
        });
    }

/* */
    private void initTabLayout() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabsPageFragmentAdapter adapter = new TabsPageFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tablayout = (TabLayout)findViewById(R.id.tablayout);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
       // tablayout.setTabMode(TabLayout.ANIMATION_DURATION);
        tablayout.setupWithViewPager(viewPager);
    }

    private void showTrainTab(){
        viewPager.setCurrentItem(Constans.TAB_ONE);
    }
    private void showTimerActivity(){
        Intent intent = new Intent(MainActivity.this, TimerSelecter.class);
        startActivity(intent);
    }
    private void showDownloadActivity(){
        Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
        startActivity(intent);
    }

    private void showNotificationTab(){
        viewPager.setCurrentItem(Constans.TAB_TWO);
    }
    private void showStatistTab(){viewPager.setCurrentItem(Constans.TAB_THREE);
    }

    private void AlertClick()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Составленная тренировка!")
                .setMessage("Что-то там, что-то там")
                .setNegativeButton("Закрыть",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }})
                .setCancelable(true).setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                        /* код добавления тренировки*/
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
