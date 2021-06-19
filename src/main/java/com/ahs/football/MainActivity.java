package com.ahs.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_away, iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    protected void onStart() {
        Glide.with(this).load("https://assets.stickpng.com/images/580b57fcd9996e24bc43c4df.png").into(iv_home);
        Glide.with(this).load("https://assets.stickpng.com/images/584a9b47b080d7616d298778.png").into(iv_away);
        super.onStart();
    }

    private void initUI() {
        iv_away = findViewById(R.id.iv_away);
        iv_home = findViewById(R.id.iv_home);
    }

    @Override
    public void onClick(View view) {
        if (view == iv_home) {
            Intent intent = new Intent(this, LineUp_Activity.class);
            intent.putExtra("Team", "homeTeam");
            startActivity(intent);
        }
        if (view == iv_away) {
            Intent intent = new Intent(this, LineUp_Activity.class);
            intent.putExtra("Team", "awayTeam");
            startActivity(intent);
        }
    }
}