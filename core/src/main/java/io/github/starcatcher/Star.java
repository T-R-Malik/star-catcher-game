package io.github.starcatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Star {
    private Rectangle bounds;
    private float speed = 300;
    StarState state;
    private Viewport viewport;


    public Star(Viewport viewport) {
        this.viewport = viewport;
        bounds = new Rectangle(100, viewport.getWorldHeight(), 50, 50);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public StarState movement(Player player) {
        bounds.y -= speed * Gdx.graphics.getDeltaTime();
        if (bounds.y + bounds.height < 0 ) {
            bounds.x = MathUtils.random(0, viewport.getWorldWidth() - bounds.width);
            bounds.y = viewport.getWorldHeight();

            return StarState.MISSED;

        }
        if (bounds.overlaps(player.getBounds())) {
            bounds.x = MathUtils.random(0, viewport.getWorldWidth() - bounds.width);
            bounds.y = viewport.getWorldHeight();
            return StarState.CAUGHT;
        }
        return StarState.FALLING;
    }

    public void reset() {
        bounds.set(100, viewport.getWorldHeight(), 50, 50);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x,  bounds.y, bounds.width, bounds.height);
    }
}
