package com.example.basementdungeoncrawler.view;

import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.MapLayout;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.graphics.TileSet;
import com.example.basementdungeoncrawler.graphics.TmxParser;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.start_screen);
        //testing
//        TmxParser parser = new TmxParser(this);
//        List<List<Integer>> tileIds = parser.parseTmxFile(R.raw.new_map1);
//        Log.d("parser output", tileIds.toString());
//        MapLayout mapLayout = new MapLayout(this);
//        Map1View map1View = new Map1View(this, tileIds);
//        setContentView(map1View);

        //more testing
//        ImageView imageView = new ImageView(this);
//        Bitmap bitmap = new TileSet(this, R.drawable.tiles2, 16).getBitmap();
//
//        imageView.setImageBitmap(bitmap);
//        setContentView(imageView);
        //

        //Drawing a rectangle
//        Rect rect = new TileSet(this, R.drawable.tiles2, 16).getTile(1).getRect();
//        TestView testView = new TestView(this, rect);
//        setContentView(testView);
        //
        MapLayout mapLayout = new MapLayout(this);
        Log.d("mapLayout", String.valueOf(mapLayout.getLayout()));
        TileMap tileMap = new TileMap(this);
        Log.d("tileMap", String.valueOf(tileMap.getTileMap()));
        Map1View map1View = new Map1View(this, tileMap.getTileMap());
        setContentView(map1View);
    }

//        //setting up start and quit buttons
//        Button quitButton = findViewById(R.id.QuitButton);
//        quitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                quit(v);
//            }
//        });
//
//        Button startButton = findViewById(R.id.StartButton);
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainActivity(v);
//            }
//        });
//
//    }
//    //takes you to the next screen; parameters: current screen to screen you want to go to
//    public void mainActivity(View view) {
//        startActivity(new Intent(MainActivity.this, ConfigScreen.class));
//    }
//
//    //quits
//    public void quit(View view) {
//        MainActivity.this.finish();
//        System.exit(0);
//    }
}
