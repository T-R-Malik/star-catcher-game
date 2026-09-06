package io.github.starcatcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class StarCatcher extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private Star star;
    private int misses;
    private int score;
    private boolean gameOver;
    private SpriteBatch batch;
    private BitmapFont font;
    private Viewport viewport;
    private Texture backgroundTex;
    private Texture gameOverBG;
    private Texture terrain;


    @Override
    public void create() {
        viewport = new FitViewport(640, 480);

        backgroundTex = new Texture(Gdx.files.internal("background/background.png"));
        gameOverBG = new Texture(Gdx.files.internal("background/gameover.png"));
        terrain = new Texture(Gdx.files.internal("background/terrain.png"));
        shapeRenderer = new  ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        player = new Player(viewport);
        star =  new Star(viewport);
        gameOver = false;
    }

    public void logic(StarState starstate) {
        if (starstate == StarState.MISSED){
            if (misses == 0 || misses == 1 ) {
                misses++;
            } else {
                misses++;
                gameOver = true;
            }
        } else if (starstate == StarState.CAUGHT) {
            score++;
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (!gameOver) {
            player.input();
            StarState starstate = star.movement(player);
            logic(starstate);
        }

        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (!gameOver) {
            batch.draw(backgroundTex, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            for (int i = 0; i < viewport.getWorldWidth(); i += 45) {
                batch.draw(terrain, i, 0);
                batch.draw(terrain, i, 5);
            }

            player.draw(batch);

            font.draw(batch, "Score: " + score, 450, 450);
            font.draw(batch,"Misses: " + misses, 420, 400);
        } else {
            batch.draw(gameOverBG, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            font.draw(batch, "GAME OVER!", 180, 350);
            font.draw(batch, "Score: " + score, 230, 300);
            font.draw(batch, "Press R to restart", 100, 250);
            font.draw(batch, "Press ESC to exit", 120, 200);
        }
        batch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(!gameOver) {
            star.draw(shapeRenderer);
        }
        shapeRenderer.end();

        if (gameOver && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartGame();
        }

        if (gameOver && Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            exit();
        }
    }

    private void exit() {
        Gdx.app.exit();
    }

    private void restartGame() {
        score = 0;
        misses = 0;
        gameOver = false;

        player.reset();
        star.reset();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
        backgroundTex.dispose();
        gameOverBG.dispose();
        terrain.dispose();
        player.dispose();
    }
}
