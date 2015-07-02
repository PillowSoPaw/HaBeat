package com.habeat.selfhelp.selfmassage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.habeat.R;
import com.habeat.selfhelp.selfmassage.util.MassagePagerAdapter;


public class SelfMassageActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_massage);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MassagePagerAdapter(getSupportFragmentManager(), this));
    }

    public void onClickBackBtn(View view) {
        finish();
    }

}
