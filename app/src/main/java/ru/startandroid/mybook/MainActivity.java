package ru.startandroid.mybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import ru.startandroid.mybook.adapter.TabsPageFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private DrawerLayout drawLayout;
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
                switch ( menuItem.getItemId())
                {
                    case R.id.news:
                    {
                        Intent intent = new Intent(MainActivity.this,NewsActivity.class);
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
                Intent intent = new Intent(MainActivity.this,NewsActivity.class);
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
                    case R.id.actionNavigatoinBook:
                        showNotificationTab();
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
        tablayout.setupWithViewPager(viewPager);
    }

    private void showNotificationTab(){
        viewPager.setCurrentItem(Constans.TAB_TWO);
    }

    private void initActionMenu()
    {
        //SetActionButtonClick(R.id.action_a,new Intent(MainActivity.this,ClockActivity.class));
    }
    private void SetActionButtonClick(int actionButtonId,final Intent intent)
    {
        final FloatingActionButton actionButton = (FloatingActionButton)findViewById(actionButtonId);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

}
