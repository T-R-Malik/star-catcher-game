package io.github.starcatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    Rectangle bounds;

    public Player() {
        bounds = new Rectangle(100, 100, 100, 100);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void input() {
        float speed = 300;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bounds.x > 0) {
            bounds.x -= speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)  && bounds.x + bounds.width + speed * Gdx.graphics.getDeltaTime() <= Gdx.graphics.getWidth()) {
            bounds.x += speed * Gdx.graphics.getDeltaTime();
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width,
            bounds.height);
    }
}
