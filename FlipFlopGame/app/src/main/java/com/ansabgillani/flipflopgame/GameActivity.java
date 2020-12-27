package com.ansabgillani.flipflopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private Button but00,but01,but02,
            but10,but11,but12,
            but20,but21,but22,
            but30,but31,but32;
    private int hearts,stars,music,drop, crown, cloud, card_bg;
    private int successfulClicked = 0;
    private boolean turnedOverCard = false;
    private Button lastClicked = null;

    private List<Integer> resultantImages;
    private List<Button> buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        contentSetUp();
        int i=0;
        for(final Button bt: buttons){
            final int fI = i;
            bt.setText(resultantImages.get(fI));
            bt.setTextSize(0.0F);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!turnedOverCard){
                        lastClicked = bt;
                        bt.setBackgroundResource(resultantImages.get(fI));
                        turnedOverCard = true;
                    }
                    else {
                        bt.setBackgroundResource(resultantImages.get(fI));
                        if(bt.getText() == lastClicked.getText()){
                            bt.setEnabled(false);
                            lastClicked.setEnabled(false);
                            lastClicked = null;
                            turnedOverCard = false;
                            successfulClicked +=2;
                            if(successfulClicked == 12){
                                finish();
                            }
                        }
                        else{
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    turnedOverCard = false;
                                    bt.setBackgroundResource(card_bg);
                                    lastClicked.setBackgroundResource(card_bg);
                                    lastClicked = null;
                                }
                            }, 1000);
                        }
                    }
                }
            });
            i++;
        }
    }

    private void contentSetUp(){
        but00 = findViewById(R.id.but00);
        but01 = findViewById(R.id.but01);
        but02 = findViewById(R.id.but02);
        but10 = findViewById(R.id.but10);
        but11 = findViewById(R.id.but11);
        but12 = findViewById(R.id.but12);
        but20 = findViewById(R.id.but20);
        but21 = findViewById(R.id.but21);
        but22 = findViewById(R.id.but22);
        but30 = findViewById(R.id.but30);
        but31 = findViewById(R.id.but31);
        but32 = findViewById(R.id.but32);

        card_bg = R.drawable.card_bg;
        hearts = R.drawable.hearts;
        stars = R.drawable.stars;
        cloud = R.drawable.cloud;
        crown = R.drawable.crown;
        drop = R.drawable.drop;
        music = R.drawable.music;

        resultantImages = new ArrayList<Integer>(Arrays.asList(hearts, stars, cloud, crown, drop, music,
                hearts, stars, cloud, crown, drop, music));
        Collections.shuffle(resultantImages);
        buttons = new ArrayList<Button>(Arrays.asList(
                but00,but01,but02,
                but10,but11,but12,
                but20,but21,but22,
                but30,but31,but32));
    }
}