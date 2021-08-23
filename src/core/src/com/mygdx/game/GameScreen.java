package com.mygdx.game;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class GameScreen implements Screen {
    SpriteBatch batch  = new SpriteBatch();;
    AssetManager manager = new AssetManager();
    World world = new World(new Vector2(0, -1), true);
    Texture img;
    Game game;
    Sprite badLogicSprite;
    Body badLogicBody;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera;
    PhysicsSprite badLogic;
    HashSet<PhysicsSprite> gameObjects = new HashSet<PhysicsSprite>();

    public GameScreen(Game tmp){
        camera = new OrthographicCamera(100,100);
        game = tmp;
        manager.load("badlogic.jpg", Texture.class);
        manager.finishLoading();

        badLogic = new PhysicsSprite(world, manager, "badlogic.jpg", 0,0,15,15,100, false);
        gameObjects.add(badLogic);

        Box2D.init();

        /*
        debugMatrix = new Matrix4(camera.combined);
        float scale = 100;
        debugMatrix.scale(scale, scale, scale);

        debugRenderer = new Box2DDebugRenderer();
        */
    }

    int circleY = 100;
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        ScreenUtils.clear(0.9f,0.9f,0.9f,1);

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            game.setScreen(new TitleScreen(game));
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Iterator<PhysicsSprite> i = gameObjects.iterator();
        while(i.hasNext())
        {
            i.next().render(delta, batch);
        }
        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && badLogic.body.getLinearVelocity().y <= 0.7){
            badLogic.body.setLinearVelocity(0f, 0.8f);
        }
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        //debugRenderer.render(world, debugMatrix);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        manager.dispose();
        batch.dispose();
        world.dispose();

    }
}
