package com.example.root.galaxyride.elements;

import android.util.Log;

import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.scene.RajawaliScene;

public class SpaceSphereElement {

    public static Vector3 origin = new Vector3(0, 0, 0);
    public static Vector3 tempVector = new Vector3(0, 0, 0);

    private final String name;
    private final float radius;
    private final Vector3 initPosition;

    private final int texturedResourceId;
    private final Sphere sphere;
    public Material material;
    Texture texture;
    private Vector3 previousPosition;

    public SpaceSphereElement(String name, float radius, Vector3 initPosition, int texturedResourceId) {
        this.name = name;
        this.radius = radius;
        this.texturedResourceId = texturedResourceId;
        this.initPosition = initPosition;
        this.previousPosition = initPosition;
        this.material = new Material();
        this.texture = new Texture(this.name, this.texturedResourceId);
        this.sphere = new Sphere(this.radius, 24, 24);
    }

    public void initialize() {
        this.material.enableLighting(true);
        this.material.setDiffuseMethod(new DiffuseMethod.Lambert());
        this.material.setColor(0);

        try {
            this.material.addTexture(this.texture);
        } catch (ATexture.TextureException error) {
            Log.d("SPHERE_SPACE", "TEXTURE ERROR");
        }

        this.sphere.setMaterial(material);
        this.sphere.setPosition(initPosition);
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        return radius;
    }

    public int getTexturedResourceId() {
        return texturedResourceId;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public Vector3 getInitPosition() {
        return initPosition;
    }

    public Vector3 getCurrentPosition() {
        return sphere.getPosition();
    }

    public Vector3 getPreviousPosition() {
        return previousPosition;
    }

    public void addToScene(RajawaliScene rajawaliScene) {
        initialize();
        rajawaliScene.addChild(sphere);
    }

    public void render() {
        previousPosition = sphere.getPosition();
    }
}
