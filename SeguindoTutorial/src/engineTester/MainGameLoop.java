package engineTester;

import entities.Camera;
import entities.Entity;
import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

    public static void main(String[] agrs){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        //Renderer renderer  = new Renderer();
        StaticShader shader = new StaticShader();
        Renderer renderer  = new Renderer(shader);

        float[] vertices = {
                -0.5f,0.5f,0,  -0.5f,-0.5f,0,  0.5f,-0.5f,0,  0.5f,0.5f,0,
                -0.5f,0.5f,1,  -0.5f,-0.5f,1,  0.5f,-0.5f,1,  0.5f,0.5f,1,
                 0.5f,0.5f,0,   0.5f,-0.5f,0,  0.5f,-0.5f,1,  0.5f,0.5f,1,
                -0.5f,0.5f,0,  -0.5f,-0.5f,0, -0.5f,-0.5f,1, -0.5f,0.5f,1,
                -0.5f,0.5f,1,  -0.5f,0.5f,0,   0.5f,0.5f,0,   0.5f,0.5f,1,
                -0.5f,-0.5f,1, -0.5f,-0.5f,0,  0.5f,-0.5f,0,  0.5f,-0.5f,1
        };

        float[] textureCoords = {
                0,0,  0,1,  1,1,  1,0,  0,0,  0,1,  1,1,  1,0,  0,0,
                0,1,  1,1,  1,0,  0,0,  0,1,  1,1,  1,0,  0,0,  0,1,
                1,1,  1,0,  0,0,  0,1,  1,1,  1,0
        };

        int[] indices = {
                0,1,3,  3,1,2,  4,5,7,
                7,5,6,  8,9,11,  11,9,10,
                12,13,15,  15,13,14,  16,17,19,
                19,17,18,  20,21,23,  23,21,22
        };

        //RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        RawModel model = OBJLoader.loadOBJModel("stall", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        //ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
        TexturedModel texturedModel = new TexturedModel(model, texture);



        //No tutorial ele junta tuto e da o nome de staticModel = textureModel

        Entity entity = new Entity(texturedModel, new Vector3f(0, 0,-50), 0, 0, 0, 1);
        Camera camera = new Camera();

        while (!Display.isCloseRequested()){
            entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            //renderer.render(textureModel);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();

            //ESC CLOSE
            if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
                return;
            }
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
