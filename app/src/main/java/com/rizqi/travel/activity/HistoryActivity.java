package com.rizqi.travel.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rizqi.travel.R;
import com.rizqi.travel.adapter.AlertDialogManager;
import com.rizqi.travel.session.SessionManager;

public class HistoryActivity extends AppCompatActivity {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

    }

    public void historyKereta(View v) {
        Intent i = new Intent(this, HistoryKeretaActivity.class);
        startActivity(i);
    }

    public void historyHotel(View v) {
        Intent i = new Intent(this, HistoryHotelActivity.class);
        startActivity(i);
    }

}
