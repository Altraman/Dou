package com.example.huqiang.douban.mvp.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.huqiang.douban.R;
import com.example.huqiang.douban.ui.AboutActivity;
import com.example.huqiang.douban.ui.AuthorInfoActivity;
import com.example.huqiang.douban.ui.RecommedActivity;
import com.example.huqiang.douban.utils.ThemeUtils;
import com.jaeger.library.StatusBarUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private CoordinatorLayout coordinatorLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RadioGroup radioGroup;
    private int currentItem;
    private int[] themeColors = new int[]{
            R.color.theme_red_base,
            R.color.theme_blue,
            R.color.theme_blue_light,
            R.color.theme_balck,
            R.color.theme_teal,
            R.color.theme_brown,
            R.color.theme_green,
            R.color.theme_red
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawerLayout, ThemeUtils.getThemeColor());
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.toolbarLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_home);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ThemeUtils.getToolBarColor());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        View headView = navigationView.getHeaderView(0);
        CircleImageView headerImageView = (CircleImageView) headView.findViewById(R.id.sdv_avatar);
        headerImageView.setImageResource(R.drawable.ic_avtar);
        navigationView.setItemIconTintList(ThemeUtils.getNaviItemIconTinkList());
        navigationView.setNavigationItemSelectedListener(this);

        MainViewPagerAdapter pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_dynamic);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_message);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        currentItem = 0;
                        break;
                    case R.id.rb_dynamic:
                        currentItem = 1;
                        break;
                    case R.id.rb_message:
                        currentItem = 2;
                        break;
                }
                viewPager.setCurrentItem(currentItem, false);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu_home:
                startActivity(new Intent(this, AuthorInfoActivity.class));
                break;
            case R.id.nav_menu_categories:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.nav_menu_recommend:
                startActivity(new Intent(this, RecommedActivity.class));
                break;
            case R.id.nav_menu_feedback:
                break;
            case R.id.nav_menu_setting:
                break;
            case R.id.nav_menu_theme:
                View view = LayoutInflater.from(this).inflate(R.layout.dialog_theme_color, null, false);
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theme_recycler_view);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                final ThemeColorAdapter adapter = new ThemeColorAdapter(themeColors, this);
                recyclerView.setAdapter(adapter);
                new AlertDialog.Builder(this)
                        .setTitle("主题选择")
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ThemeUtils.setThemeColor(getResources().getColor(themeColors[adapter.getNowPosition()]));
                                ThemeUtils.setThemePosition(adapter.getNowPosition());
                                recreate();
                            }
                        })
                        .show();
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }
}
