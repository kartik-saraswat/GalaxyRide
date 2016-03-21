package com.example.root.galaxyride.elements;

import com.example.root.galaxyride.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.scene.RajawaliScene;

import java.util.Random;

/**
 * Created by root on 20/3/16.
 */
public class Star extends SpaceSphereElement {

    private static final int POWER_COEFF = 20;

    public static Star STAR_SUN = new Star("SUN", 0.3f, origin, R.drawable.sun_texture_map);
    public static Star STAR_SMALL = new Star("STAR", 0.001f, origin, R.drawable.star_text);

    private PointLight starLight;

    public Star(String name, float radius, Vector3 initPosition, int texturedResourceId) {
        super(name, radius, initPosition, texturedResourceId);

        starLight = new PointLight();
        starLight.setPower(POWER_COEFF * radius);
        starLight.setPosition(initPosition.x, initPosition.y, initPosition.z);
    }

    @Override
    public void addToScene(RajawaliScene rajawaliScene) {
        super.addToScene(rajawaliScene);
        rajawaliScene.addLight(starLight);
    }

    public void addChild(int noOfChildren) {
        getSphere().setRenderChildrenAsBatch(true);
        Random random = new Random();
        for (int i = 0; i < noOfChildren; i++) {

            Object3D c = getSphere().clone();

            float s = random.nextFloat()*2 - 0.5f;
            float x = random.nextFloat() * 2 - 1f;
            float y = random.nextFloat() * 2 - 1f;
            float z = random.nextFloat() * 2 - 1f;

            c.setScale(s);
            c.setPosition(x, y, z);
            getSphere().addChild(c);
        }
    }

    @Override
    public void render() {
        super.render();
    }
}
