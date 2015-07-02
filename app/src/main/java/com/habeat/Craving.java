package com.habeat;

import org.joda.time.DateTime;

public class Craving {
    private CravingType type;
    private DateTime dateTime;

    public Craving(CravingType type, DateTime dateTime) {
        this.type = type;
        this.dateTime = dateTime;
    }

    public CravingType getType() {
        return type;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
