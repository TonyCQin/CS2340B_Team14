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
import com.example.basementdungeoncrawler.Model.EnemyCollision;
import com.example.basementdungeoncrawler.Model.Orc;
import com.example.basementdungeoncrawler.Model.GoalReached;
import com.example.basementdungeoncrawler.Model.Movement;
import com.example.basementdungeoncrawler.Model.Player;
import com.example.basementdungeoncrawler.Model.Shaman;
import com.example.basementdungeoncrawler.Model.Mage;
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
    private final Player player;
    private Mage mage;
    private Skeleton skeleton;
    private Orc orc;
    private Shaman shaman;
    private Collision collision;
    private EnemyCollision mageCollision;
    private EnemyCollision orcCollision;
    private EnemyCollision shamanCollision;
    private EnemyCollision skeletonCollision;
    private GoalReached goalReached;
    private EdgeReached edgeReached;
    private GameScreen gameScreen;
    private Context context;
    private Movement movement;
    private boolean pressedSpace = false;
    private Paint paint = new Paint(R.color.red);

    public MapView(Context context, ArrayList<Tile[][]> layers, TileMap tileMap,
                   GameScreen gameScreen, int x, int y) {
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
        shamanCollision = new EnemyCollision(tileMap);
        mageCollision = new EnemyCollision(tileMap);
        orcCollision = new EnemyCollision(tileMap);
        skeletonCollision = new EnemyCollision(tileMap);

        edgeReached = new EdgeReached(screenHeight, screenWidth);
        goalReached = new GoalReached();

        player = new Player(getContext(), x, y);
        mage = new Mage(getContext(), 800, 1100, 20, 10, 64, 48);
        shaman = new Shaman(getContext(), 300, 1300, 100, 50, 64, 50);
        skeleton = new Skeleton(getContext(), 400, 1300, 20, 15, 64, 10);
        orc = new Orc(getContext(), 200, 1200, 30, 5, 64, 64);

        mage.setCollision(mageCollision);
        shaman.setCollision(shamanCollision);
        skeleton.setCollision(skeletonCollision);
        orc.setCollision(orcCollision);

        player.subscribe(collision);
        player.subscribe(edgeReached);
        player.subscribe(mageCollision);
        player.subscribe(shamanCollision);
        player.subscribe(orcCollision);
        player.subscribe(skeletonCollision);

        shaman.subscribe(shamanCollision);
        orc.subscribe(orcCollision);
        skeleton.subscribe(skeletonCollision);
        mage.subscribe(mageCollision);

        this.movement = new Movement(player, collision);
        player.setMovement(this.movement);

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
        orc.draw(canvas);
        player.draw(canvas);
        mage.draw(canvas);
        shaman.draw(canvas);
        skeleton.draw(canvas);
        if (pressedSpace) {
            canvas.drawCircle((float) player.getPositionX() + 100,
                    (float) player.getPositionY() + 80,
                    (float) player.getAttackRadius(), paint);
            pressedSpace = false;
        }
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

    public void drawPlayerAttack(Canvas canvas, double attackRadius) {
        canvas.drawCircle((float) player.getPositionX(), (float) player.getPositionY(),
                (float) player.getAttackRadius(), paint);
        invalidate();
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent e) {
        gameScreen.checkDeath();
        char direction = ' ';
        if (e.getAction() == KeyEvent.ACTION_DOWN) {
            if (e.isShiftPressed()) {
                switch (key) {
                case KeyEvent.KEYCODE_W:
                    direction = 'W';
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
                case KeyEvent.KEYCODE_SPACE:
                    pressedSpace = true;
                    if (player.attack(mage)) {
                        mage.die(context);
                    }
                    if (player.attack(orc)) {
                        orc.die(context);
                    }
                    if (player.attack(shaman)) {
                        shaman.die(context);
                    }
                    if (player.attack(skeleton)) {
                        skeleton.die(context);
                    }
                    direction = ' ';
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
                case KeyEvent.KEYCODE_SPACE:
                    pressedSpace = true;
                    Log.d("Mage position", String.valueOf(mage.getPositionX()));
                    if (player.attack(mage)) {
                        mage.die(context);
                    }
                    if (player.attack(orc)) {
                        orc.die(context);
                    }
                    if (player.attack(shaman)) {
                        shaman.die(context);
                    }
                    if (player.attack(skeleton)) {
                        skeleton.die(context);
                    }
                    direction = ' ';
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
                if (GoalReached.getGoalReached().getIsGoalReached()) {
                    Log.d("calling update", "");
                    gameScreen.update();
                }
                Log.d("moving", "");
                player.move(direction, collision);
                shaman.move();
                skeleton.move();
                orc.move();
                mage.move();
                invalidate();
                return true;
            }
        }
        return super.onKeyDown(key, e);
    }
}
