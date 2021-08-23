package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsSprite {

    AssetManager manager;
    World world;
    Texture img;
    Sprite sprite;
    Body body;
    float worldScale;
    float width;
    float height;

    public PhysicsSprite(World world, AssetManager manager, String textureName, float xPos, float yPos, float width, float height, float worldScale, boolean isStatic){
        this.world = world;
        this.manager = manager;
        this.worldScale = worldScale;
        this.width = width;
        this.height = height;
        img = (Texture)  manager.get(textureName);
        sprite = new Sprite(img);

        sprite.setPosition(xPos, yPos);

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX() / worldScale, sprite.getY() / worldScale);
        if(isStatic) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/(worldScale * 2), height/(worldScale * 2));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }
    public void render(float delta, Batch batch) {
        batch.draw(sprite, body.getPosition().x * worldScale - height/2, body.getPosition().y * worldScale - width/2, width, height);
    }

}
