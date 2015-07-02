package com.example.interns.habeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.interns.habeat.slides.FirstSlide;
import com.example.interns.habeat.slides.FourthSlide;
import com.example.interns.habeat.slides.SecondSlide;
import com.example.interns.habeat.slides.ThirdSlide;
import com.github.paolorotolo.appintro.AppIntro;


public class Intro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(new FirstSlide(), getApplicationContext());
        addSlide(new SecondSlide(), getApplicationContext());
        addSlide(new ThirdSlide(), getApplicationContext());
        addSlide(new FourthSlide(), getApplicationContext());
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(),getString(R.string.skip),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
