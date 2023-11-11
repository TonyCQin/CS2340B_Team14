package com.example.basementdungeoncrawler.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.basementdungeoncrawler.Model.Collision;
import com.example.basementdungeoncrawler.Model.EdgeReached;
import com.example.basementdungeoncrawler.Model.Ghost;
import com.example.basementdungeoncrawler.Model.GoalReached;
import com.example.basementdungeoncrawler.Model.Movement;
import com.example.basementdungeoncrawler.Model.PlayerData;
import com.example.basementdungeoncrawler.Model.SerialKiller;
import com.example.basementdungeoncrawler.Model.Shadow;
import com.example.basementdungeoncrawler.Model.Skeleton;
import com.example.basementdungeoncrawler.R;
import com.example.basementdungeoncrawler.graphics.Tile;
import com.example.basementdungeoncrawler.graphics.TileMap;
import com.example.basementdungeoncrawler.graphics.TileSet;

import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.basementdungeoncrawler.graphics.MapLayout.NUMBER_OF_ROW_TILES;

import java.util.ArrayList;

public class MapView extends View {
    private ArrayList<Tile[][]> layers;
    private TileSet dungeonTileSet;
    private TileSet propTileSet;
    private int screenWidth;
    private int screenHeight;
    private int tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
    private int tileHeight = screenHeight / NUMBER_OF_ROW_TILES;
    private final PlayerData player;
    private Shadow shadow;
    private Skeleton skeleton;
    private Ghost ghost;
    private SerialKiller barry;
    private Collision collision;
    private GoalReached goalReached;
    private EdgeReached edgeReached;
    private GameScreen gameScreen;
    private Context context;
    private Movement movement;

    public MapView(Context context, ArrayList<Tile[][]> layers, TileMap tileMap,
                   GameScreen gameScreen, int x, int y, int radius) {
        super(context);
        this.layers = layers;
        this.gameScreen = gameScreen;
        dungeonTileSet = new TileSet(context, R.drawable.tiles2, 16);
        propTileSet = new TileSet(context, R.drawable.props, 16);

        this.context = context;

        Resources resources = context.getResources();
        screenHeight = resources.getDisplayMetrics().heightPixels;
        screenWidth = resources.getDisplayMetrics().widthPixels;
        tileWidth = screenWidth / NUMBER_OF_COLUMN_TILES;
        tileHeight = screenHeight / NUMBER_OF_ROW_TILES;

        collision = new Collision(tileMap);
        edgeReached = new EdgeReached(screenHeight, screenWidth);
        goalReached = new GoalReached();
        player = new PlayerData(getContext(), x, y, radius);
        shadow = new Shadow(getContext(), x + 20, y + 20,20, 10,
                30, 48);
        barry = new SerialKiller(getContext(), x - 50, y - 50,100, 50,
                30, 50);
        skeleton = new Skeleton(getContext(), x - 50, y - 100, 20, 15, 50, 10);
        ghost = new Ghost(getContext(), x - 100, y + 200, 30, 5, 60, 60);
        player.subscribe(collision);
        player.subscribe(edgeReached);
        player.subscribe(barry);
        player.subscribe(ghost);
        player.subscribe(skeleton);
        player.subscribe(shadow);
        this.movement = new Movement(player, collision);

        setFocusable(true);
    }

    /**
     * Function to draw stuff onto the canvas
     * @param canvas canvas to be drawn on (this one defaults to this view)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (Tile[][] layer : layers) {
            renderLayer(canvas, layer);
        }
        player.draw(canvas);
        shadow.draw(canvas);
        barry.draw(canvas);
        skeleton.draw(canvas);
        ghost.draw(canvas);
        super.onDraw(canvas);
    }

    /**
     * contains logic for creating the rect to draw the tile at
     * @param row row of the screen
     * @param col col of the screen
     * @return the rect defining the area a tile is drawn at
     */
    private Rect drawDestRect(int row, int col) {
        return new Rect(col * tileWidth,
                row * tileHeight,
                (col + 1) * tileWidth,
                (row + 1) * tileHeight
                );
    }

    /**
     * Iterates through a Tile[][] layer to draw all the tiles at their row and column
     * @param canvas canvas to drawn on
     * @param layer the layer being iterated over
     */
    private void renderLayer(Canvas canvas, Tile[][] layer) {
        for (int row = 0; row < NUMBER_OF_ROW_TILES; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMN_TILES; col++) {
                Tile toDraw = layer[row][col];
                drawTile(canvas, toDraw, row, col);
            }
        }
    }

    /**
     * contains logic determining the tile to draw, and drawing the tile
     * @param canvas the canvas to drawn on
     * @param tile the tile to draw
     * @param row the row the tile is drawn at
     * @param col the column the tile is drawn at
     */
    private void drawTile(Canvas canvas, Tile tile, int row, int col) {
        Rect srcRect = tile.getRect();
        Rect destRect = drawDestRect(row, col);
        int tileId = tile.getTileId();
        if (tileId > 0) {
            canvas.drawBitmap(dungeonTileSet.getBitmap(), srcRect, destRect, null);
        }
        if (tileId > 626) {
            canvas.drawBitmap(propTileSet.getBitmap(), srcRect, destRect, null);
        }

        if (tileId <= 0) {
            int width = 16; // Width of the Bitmap
            int height = 16; // Height of the Bitmap

            Bitmap redBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas testCanvas = new Canvas(redBitmap);

            Paint paint = new Paint();
            paint.setColor(Color.TRANSPARENT);
            paint.setAlpha(0);

            testCanvas.drawRect(0, 0, width, height, paint);
            Rect placeholderRect = new Rect(0, 0, 16, 16);
            canvas.drawBitmap(redBitmap, placeholderRect, destRect, null);
        }
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent e) {
        char direction = ' ';
        if (e.getAction() == KeyEvent.ACTION_DOWN) {
            if (e.isShiftPressed()) {
                switch (key) {
                case KeyEvent.KEYCODE_W:
                    direction = 'W';
                    ghost.move();
                    break;
                case KeyEvent.KEYCODE_A:
                    direction = 'A';
                    break;
                case KeyEvent.KEYCODE_S:
                    direction = 'S';
                    break;
                case KeyEvent.KEYCODE_D:
                    direction = 'D';
                    break;
                default:
                    break;
                }
                if (direction != ' ') {
                    movement.run(direction);
                }
            } else {
                switch (key) {
                case KeyEvent.KEYCODE_W:
                    direction = 'w';
                    break;
                case KeyEvent.KEYCODE_A:
                    direction = 'a';
                    break;
                case KeyEvent.KEYCODE_S:
                    direction = 's';
                    break;
                case KeyEvent.KEYCODE_D:
                    direction = 'd';
                    break;
                default:
                    break;
                }
                if (direction != ' ') {
                    movement.walk(direction);
                }
            }
            if (direction != ' ') {
                // edge checking logic
                Log.d("moved", "");
                if (EdgeReached.getEdgeReached().getIsEdgeReached()) {
                    Log.d("calling update", "");
                    gameScreen.update();
                }
                //goal checking logic
                Log.d("Is goal reached (mapview)", String.valueOf(
                    GoalReached.getGoalReached().getIsGoalReached()));
                if (GoalReached.getGoalReached().getIsGoalReached()) {
                    Log.d("calling update", "");
                    gameScreen.update();
                }

                player.move(direction, collision);
                invalidate();
                return true;
            }
        }
        return super.onKeyDown(key, e);
    }
}
