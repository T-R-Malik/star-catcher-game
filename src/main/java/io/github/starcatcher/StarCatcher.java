package io.github.starcatcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class StarCatcher extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private Star star;
    private int misses;
    private int score;
    private boolean gameOver;


    @Override
    public void create() {
        shapeRenderer = new  ShapeRenderer();
        player = new Player();
        star =  new Star();
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

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            player.draw(shapeRenderer);
            star.draw(shapeRenderer);
            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
