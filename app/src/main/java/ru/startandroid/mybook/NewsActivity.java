package ru.startandroid.mybook;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NewsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        InitToolbar();
    }

    private void InitToolbar() {
        toolbar= (Toolbar)findViewById(R.id.news_toolbar);
        toolbar.setTitle(R.string.news_title);
        toolbar.inflateMenu(R.menu.menu_news);
    }
}
