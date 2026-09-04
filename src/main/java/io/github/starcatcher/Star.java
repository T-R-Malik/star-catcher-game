package io.github.starcatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Star {
    private Rectangle bounds;
    private float speed = 300;
    StarState state;


    public Star() {
        bounds = new Rectangle(100, Gdx.graphics.getHeight(), 50, 50);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public StarState movement(Player player) {
        bounds.y -= speed * Gdx.graphics.getDeltaTime();
        if (bounds.y < 0 ) {
            bounds.x = MathUtils.random(0, Gdx.graphics.getWidth() - bounds.width);
            bounds.y = Gdx.graphics.getHeight();
            return StarState.MISSED;

        }
        if (bounds.overlaps(player.getBounds())) {
            bounds.x = MathUtils.random(0, Gdx.graphics.getWidth() - bounds.width);
            bounds.y = Gdx.graphics.getHeight();
            return StarState.CAUGHT;
        }
        return StarState.FALLING;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x,  bounds.y, bounds.width, bounds.height);
    }
}
