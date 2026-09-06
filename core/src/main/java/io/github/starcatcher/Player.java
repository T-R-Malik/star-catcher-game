package io.github.starcatcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {
    private Rectangle bounds;
    private Viewport viewport;
    private TextureRegion[] playerFrames;
    private int currentFrame;
    private float animTimer;
    private boolean walking;
    private boolean wasWalking;
    private boolean facingRight = true;

    private TextureRegion[] walkFrames;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        bounds = new Rectangle(100, 0, 100, 100);

        playerFrames = new TextureRegion[12];
        playerFrames[0] = loadFrame("movement/idle/Wraith_02_Idle_000.png");
        playerFrames[1] = loadFrame("movement/idle/Wraith_02_Idle_001.png");
        playerFrames[2] = loadFrame("movement/idle/Wraith_02_Idle_002.png");
        playerFrames[3] = loadFrame("movement/idle/Wraith_02_Idle_003.png");
        playerFrames[4] = loadFrame("movement/idle/Wraith_02_Idle_004.png");
        playerFrames[5] = loadFrame("movement/idle/Wraith_02_Idle_005.png");
        playerFrames[6] = loadFrame("movement/idle/Wraith_02_Idle_006.png");
        playerFrames[7] = loadFrame("movement/idle/Wraith_02_Idle_007.png");
        playerFrames[8] = loadFrame("movement/idle/Wraith_02_Idle_008.png");
        playerFrames[9] = loadFrame("movement/idle/Wraith_02_Idle_009.png");
        playerFrames[10] = loadFrame("movement/idle/Wraith_02_Idle_010.png");
        playerFrames[11] = loadFrame("movement/idle/Wraith_02_Idle_011.png");

        walkFrames = new TextureRegion[12];
        walkFrames[0] = loadFrame("movement/walking/Wraith_02_Moving Forward_000.png");
        walkFrames[1] = loadFrame("movement/walking/Wraith_02_Moving Forward_001.png");
        walkFrames[2] = loadFrame("movement/walking/Wraith_02_Moving Forward_002.png");
        walkFrames[3] = loadFrame("movement/walking/Wraith_02_Moving Forward_003.png");
        walkFrames[4] = loadFrame("movement/walking/Wraith_02_Moving Forward_004.png");
        walkFrames[5] = loadFrame("movement/walking/Wraith_02_Moving Forward_005.png");
        walkFrames[6] = loadFrame("movement/walking/Wraith_02_Moving Forward_006.png");
        walkFrames[7] = loadFrame("movement/walking/Wraith_02_Moving Forward_007.png");
        walkFrames[8] = loadFrame("movement/walking/Wraith_02_Moving Forward_008.png");
        walkFrames[9] = loadFrame("movement/walking/Wraith_02_Moving Forward_009.png");
        walkFrames[10] = loadFrame("movement/walking/Wraith_02_Moving Forward_010.png");
        walkFrames[11] = loadFrame("movement/walking/Wraith_02_Moving Forward_011.png");
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void input() {
        float speed = 300;
        float movement = speed * Gdx.graphics.getDeltaTime();

        walking = false;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bounds.x - movement >= 0) {
            bounds.x -= movement;
            walking = true;
            facingRight = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && bounds.x + bounds.width + movement <= viewport.getWorldWidth()) {
            bounds.x += movement;
            walking = true;
            facingRight = true;
        }
    }

    public void reset() {
        bounds.set(100, 0, 100, 100);
    }

    public void draw(SpriteBatch batch) {
        TextureRegion[] currentAnimation;

        if (walking) {
            currentAnimation = walkFrames;
        } else {
            currentAnimation = playerFrames;
        }

        if (walking != wasWalking) {
            currentFrame = 0;
            animTimer = 0;
            wasWalking = walking;
        }

        animTimer += Gdx.graphics.getDeltaTime();
        if (animTimer >= 0.1f) {
            currentFrame++;
            animTimer = 0;

            if (currentFrame >= currentAnimation.length) {
                currentFrame = 0;
            }
        }

        batch.draw(currentAnimation[currentFrame], bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void dispose() {
        for (TextureRegion frame : playerFrames) {
           // frame.d
        }
    }

    private TextureRegion loadFrame(String path) {
        return new TextureRegion(new Texture(Gdx.files.internal(path)));
    }
}
