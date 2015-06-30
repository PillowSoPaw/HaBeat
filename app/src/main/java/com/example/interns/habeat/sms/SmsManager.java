package com.example.interns.habeat.sms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.interns.habeat.model.Contact;


public class SmsManager {
    private Context context;

    public SmsManager(Context context) {
        this.context = context;
    }

    public void sendToAll(Contact[] contacts, String message) {
        for (Contact c : contacts) {
            send(c, message);
        }
    }

    public void send(Contact contact, String message) {
        send(contact.getPhone(), message);
    }

    public void send(String phoneNumber, String message) {
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);
        android.telephony.SmsManager SMSManager = android.telephony.SmsManager.getDefault();
        SMSManager.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}
