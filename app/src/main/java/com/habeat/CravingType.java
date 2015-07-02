package com.habeat;


import com.habeat.selfhelp.deepbreathing.DeepBreathingActivity;
import com.habeat.selfhelp.selfmassage.SelfMassageActivity;
import com.habeat.selfhelp.urgesurfing.UrgeSurfingActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum CravingType {
    CIGARETTE, ALCOHOL;

    private List<Class> activities;
    private Random rand;

    CravingType() {
        activities = new ArrayList<>();
        rand = new Random();
    }

    public Class randomActivity() {
        activities.clear();
        switch (this) {
            case CIGARETTE: {
               // activities.add(DeepBreathingActivity.class);
                activities.add(SelfMassageActivity.class);
                break;
            }
            case ALCOHOL: {
                activities.add(UrgeSurfingActivity.class);
                break;
            }
        }
        int randomIndex = rand.nextInt(activities.size());
        return activities.get(randomIndex);
    }
}
