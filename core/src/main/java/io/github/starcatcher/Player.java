package io.github.starcatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {
    private Rectangle bounds;
    private Viewport viewport;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        bounds = new Rectangle(100, 100, 100, 100);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void input() {
        float speed = 300;
        float movement = speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bounds.x - movement >= 0) {
            bounds.x -= movement;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)  && bounds.x + bounds.width + movement <= viewport.getWorldWidth()) {
            bounds.x += movement;
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width,
            bounds.height);
    }
}
