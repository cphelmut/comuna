package com.example.comuna;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class menuPrincipal extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    protected FloatingActionButton fbtnRegistrar;
    protected int intPosicion = 0,  intIndicadorWidth;
    private Intent intentRegistrar;
    private View mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager =  findViewById(R.id.pager);
        mIndicator = findViewById(R.id.indicator);
        tabLayout = findViewById(R.id.tab_layout);
        intentRegistrar = new Intent(menuPrincipal.this, registrarNoticia.class);
        fbtnRegistrar = findViewById(R.id.btn_Registrar_ATVMenuPrincipal);
        fbtnRegistrar.setOnClickListener(this);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();
                float translationOffset =  (positionOffset+position) * intIndicadorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                Ocultar_Boton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
               intIndicadorWidth = tabLayout.getWidth() / tabLayout.getTabCount();
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = intIndicadorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new frgNoticias(), "Noticias");
        adapter.addFragment(new frgEventos(), "Eventos");
        adapter.addFragment(new frgMapa(), "Mapa");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Registrar_ATVMenuPrincipal:
                startActivity(intentRegistrar);
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            Ocultar_Boton(position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

    public void Ocultar_Boton(int pos){
        switch (pos){
            case 0:
                fbtnRegistrar.setVisibility(View.VISIBLE);
                intentRegistrar = new Intent(menuPrincipal.this, registrarNoticia.class);
                break;
            case 1:
                fbtnRegistrar.setVisibility(View.VISIBLE);
                intentRegistrar = new Intent(menuPrincipal.this, registrarEvento.class);
                break;

            case 2:

                fbtnRegistrar.setVisibility(View.GONE);
                intPosicion = 2;
                break;

        }
    }

}
