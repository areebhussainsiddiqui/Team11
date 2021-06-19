package com.ahs.football;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LineUp_Activity extends AppCompatActivity implements Formation {
    String Team;
    List<PlayerModel> modelList;
    TextView tvHomeGK;
    LinearLayout HomeLineUp1, HomeLineUp2, HomeLineUp3, HomeLineUp4;
    Spinner spinner;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_up_);
        initUi();
        Team = getIntent().getStringExtra("Team");
        modelList = new ArrayList<>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String spinerItem = spinner.getSelectedItem().toString().trim();
               // char[] a = spinerItem.toCharArray();
              //  String strNew = spinerItem.replaceAll("-", "").trim(); //
                OnSelectedFormation(spinerItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("match").child(Team).child("lineup")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            modelList.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                                String ShirtNumber = snapshot1.child("shirtNumber").getValue().toString();
                                String PlayerName = snapshot1.child("name").getValue().toString();
                                String PlayerPosition = snapshot1.child("position").getValue().toString();
                                if (PlayerPosition.equals("Goalkeeper")) {
                                    tvHomeGK.setText(ShirtNumber);

                                } else {
                                    modelList.add(new PlayerModel(ShirtNumber, PlayerName, PlayerPosition));

                                }
                            }
/*
                            for (int j = 0; j < ary.length; j++) {
                                for (int i = 0; i < modelList.size(); i++) {

                                   System.out.println(ary[j]);
                                    if (j == 0) {
                                        AddPlayer(HomeLineUp1, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);
                                    } else if (j == 1) {
                                        AddPlayer(HomeLineUp2, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);

                                    } else if (j == 2) {
                                        AddPlayer(HomeLineUp3, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);

                                    } else {
                                        AddPlayer(HomeLineUp4, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);

                                    }
                                }


                            }
*/
/*
                        for (int i = 0; i < modelList.size(); i++) {
                                if (i < 4) {
                                    AddPlayer(HomeLineUp1, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);
                                } else if (i > 4 && i <= 7) {
                                    AddPlayer(HomeLineUp2, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);

                                } else {
                                    AddPlayer(HomeLineUp3, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);

                                }
                            }
                        }
*/
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

/*
    private int getPositionCount(String formation) {
        int[] formationArray = getFormationArray(formation.split("-"));
        return formationArray.length;
    }

    private int[] getFormationArray(String[] stringArray) {
        int[] numberArray = new int[stringArray.length + 1];
        numberArray[0] = 1; // here is the goalkeeper.

        // adds the numbers from formation (for ex : 4-1-4-1)
        for (int i = 1; i < numberArray.length; i++) {
            numberArray[i] = Integer.parseInt(stringArray[i - 1]);
        }

        return numberArray;
    }
*/


    private void AddPlayer(LinearLayout layout, String ShirtNumber, final String PlayerName) {

        TextView tvTeam = new TextView(LineUp_Activity.this);

       /* for (int i = 0; i <= getPositionCount(formation); i++)
        {
            LinearLayoutCompat layoutPosition = new LinearLayoutCompat(this);

            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 0);
            layoutParams.weight = 1;
            layoutPosition.setLayoutParams(layoutParams);
            layoutPosition.setGravity(Gravity.CENTER);
            layoutPosition.setOrientation(LinearLayoutCompat.HORIZONTAL);

            layoutPosition.setId(i);

            layout.addView(layoutPosition);
        }
*/

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpToPx(50), dpToPx(50));
        lp.setMargins(dpToPx(30), dpToPx(30), dpToPx(30), dpToPx(30));
        tvTeam.setPadding(10, 10, 10, 10);
        tvTeam.setLayoutParams(lp);
        tvTeam.setBackgroundResource(R.drawable.custom_circlur_player_home);
        tvTeam.setText(ShirtNumber);
        tvTeam.setGravity(Gravity.CENTER);
        tvTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), PlayerName, Toast.LENGTH_LONG).show();
            }
        });
        layout.addView(tvTeam);
    }

    private void initUi() {
        HomeLineUp1 = findViewById(R.id.LL);
        HomeLineUp2 = findViewById(R.id.LL2);
        HomeLineUp3 = findViewById(R.id.LL3);
        HomeLineUp4 = findViewById(R.id.LL4);
        spinner = findViewById(R.id.spinner1);
        tvHomeGK = findViewById(R.id.tvHomeGK);


    }

    public int dpToPx(int dp) {
/*
     float density = this.getResources().getDisplayMetrics().density;
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        Toast.makeText(getApplicationContext(),density +"\n"+ metrics,Toast.LENGTH_SHORT).show();
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        int mGestureThreshold = (int) (GESTURE_THRESHOLD_DP * scale + 0.5f);
        int densityDpi = (int)(metrics.density * mGestureThreshold);
        return Math.round((float) dp * density);
*/
        return (int) (dp * this.getResources().getDisplayMetrics().density);
    }


    @Override
    public void OnSelectedFormation(String formation) {
       // Log.d("TAG", "OnSelectedFormation: " + formation[0]);
        int count = 0;
        char[] a = formation.toCharArray();
        String strNew = formation.replaceAll("-", "").trim(); //
        char[] format = strNew.toCharArray();
        Log.d("TAG", "onItemSelected:  Count" + format[0]);

        for(int k = 0; k < formation.length(); k++) {
            if(!Character.isDigit(a[k])) {
                count++;

                if(count == 1 ){
                    Log.d("TAG", "onItemSelected:  Count" + count);

                    for(int i = 0 ; i < format[0]; i++){
                        try {
                            AddPlayer(HomeLineUp1, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);
                        }
                        catch (Exception ex){
                            break;
                        }
                    }
                }
                else if(count == 2){
                    for(int i = 0 ; i <= format[1]; i++){
                        try {
                            AddPlayer(HomeLineUp2, modelList.get(format[0]+i).getShirtNumber(), modelList.get(format[0]+i).PlayerName);
                        }
                        catch (Exception ex){
                            break;
                        }
                    }
                }
                else if(count == 3){
                    for(int i = 0 ; i <= format[2]; i++){
                        try {
                            AddPlayer(HomeLineUp3, modelList.get(format[1]+i).getShirtNumber(), modelList.get(format[1]+i).PlayerName);
                        }
                        catch (Exception ex){
                            break;
                        }
                    }
                }
                else {
                    for(int i = 0 ; i <= format[3]; i++){
                        try {
                            AddPlayer(HomeLineUp3, modelList.get(format[2]+i).getShirtNumber(), modelList.get(format[2]+i).PlayerName);
                        }
                        catch (Exception ex){
                            break;
                        }
                    }
                }
                Log.d("TAG", "onItemSelected:  Count" + count);
            }
        }
/*        for (int i = 0; i < modelList.size(); i++) {
            if (i <= a[0]) {
                AddPlayer(HomeLineUp1, modelList.get(i).getShirtNumber(), modelList.get(i).PlayerName);
            }
            else {
                break;
            }*/

        }
    }

