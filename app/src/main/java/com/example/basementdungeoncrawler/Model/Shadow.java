package com.example.basementdungeoncrawler.Model;

import android.content.Context;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.basementdungeoncrawler.R;

public class Shadow extends Enemy {

    public Shadow(Context context, double positionX, double positionY, int HP, int damage, int radius, int speed,
                  Paint paint) {
        super(context, positionX, positionY, 20, 10, 100, 48, new Paint());
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);
    }



}
