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


    @Override
    public void create() {
        viewport = new FitViewport(640, 480);

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
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(!gameOver) {
            player.draw(shapeRenderer);
            star.draw(shapeRenderer);
        }
        shapeRenderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (!gameOver) {
            font.draw(batch, "Score: " + score, 450, 450);
            font.draw(batch,"Misses: " + misses, 420, 400);
        } else {
            font.draw(batch, "GAME OVER!", 180, 350);
            font.draw(batch, "Score: " + score, 230, 300);
            font.draw(batch, "Press R to restart", 100, 250);
        }
        batch.end();

        if (gameOver && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartGame();
        }
    }

    private void restartGame() {

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
    }
}
