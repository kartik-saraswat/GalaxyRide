package com.example.root.galaxyride.elements;

import com.example.root.galaxyride.R;

import org.rajawali3d.math.MathUtil;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.scene.RajawaliScene;

import static com.example.root.galaxyride.elements.Star.STAR_SUN;

public class Satellite extends SpaceSphereElement {

    private final static int SPEED_LEVEL_1 = 1;
    private final static int SPEED_LEVEL_2 = 2;
    private final static int SPEED_LEVEL_3 = 3;
    private final static int SPEED_LEVEL_4 = 4;
    private final static int SPEED_LEVEL_5 = 5;
    private final static int SPEED_LEVEL_6 = 6;
    private final static int SPEED_LEVEL_7 = 7;
    private final static int SPEED_LEVEL_8 = 8;


    public static Satellite PLANET_MERCURY = new Satellite("MERCURY", 0.02f, new Vector3(-0.3f, 0.0f, -0.3f), R.drawable.mercury_texture, SPEED_LEVEL_8);
    public static Satellite PLANET_VENUS = new Satellite("VENUS", 0.05f, new Vector3(-0.3f, 0.0f, 0.5f), R.drawable.venus_texture, SPEED_LEVEL_7);
    public static Satellite PLANET_EARTH = new Satellite("EARTH", 0.055f, new Vector3(0.4f, 0.0f, 0.5f), R.drawable.earth_texture, SPEED_LEVEL_6);
    public static Satellite PLANET_MARS = new Satellite("MARS", 0.03f, new Vector3(0.4f, 0.0f, -0.5f), R.drawable.mars_texture, SPEED_LEVEL_5);
    public static Satellite PLANET_JUPITER = new Satellite("JUPITER", 0.08f, new Vector3(0.7f, 0.0f, -0.7f), R.drawable.jupiter_texture, SPEED_LEVEL_4);
    public static Satellite PLANET_SATURN = new Satellite("SATURN", 0.08f, new Vector3(-0.8f, 0.0f, -0.8f), R.drawable.saturn_texture, SPEED_LEVEL_3);
    public static Satellite PLANET_URANUS = new Satellite("URANUS", 0.08f, new Vector3(0.9f, 0.0f, -0.8f), R.drawable.uranus_texture, SPEED_LEVEL_2);
    public static Satellite PLANET_NEPTUNE = new Satellite("NEPTUNE", 0.07f, new Vector3(-1.1f, 0.0f, -1.1f), R.drawable.neptune_texture, SPEED_LEVEL_1);
    public static Satellite SATELLITE_MOON = new Satellite("MOON", 0.01f, new Vector3(0.4f, 0.0f, 0.5f + 0.08f), R.drawable.pluto_texture, PLANET_EARTH, SPEED_LEVEL_6);

    boolean isRotating;

    private SpaceSphereElement aboutElement;
    private int speedOfRotation;

    public Satellite(String name, float radius, int texturedResourceId) {
        this(name, radius, origin, texturedResourceId, STAR_SUN, SPEED_LEVEL_6);
    }

    public Satellite(String name, float radius, Vector3 initPosition, int texturedResourceId) {
        this(name, radius, initPosition, texturedResourceId, STAR_SUN, SPEED_LEVEL_6);
    }

    public Satellite(String name, float radius, Vector3 initPosition, int texturedResourceId, int speedOfRotation) {
        this(name, radius, initPosition, texturedResourceId, STAR_SUN, speedOfRotation);
    }

    public Satellite(String name, float radius, Vector3 initPosition, int texturedResourceId, SpaceSphereElement aboutElement) {
        this(name, radius, initPosition, texturedResourceId, aboutElement, SPEED_LEVEL_6);
    }

    public Satellite(String name, float radius, Vector3 initPosition, int texturedResourceId, SpaceSphereElement aboutElement, int speedOfRotation) {
        super(name, radius, initPosition, texturedResourceId);
        this.aboutElement = aboutElement;
        this.speedOfRotation = speedOfRotation;
        this.isRotating = true;
    }


    @Override
    public void addToScene(RajawaliScene rajawaliScene) {
        super.addToScene(rajawaliScene);
        this.getSphere().getMaterial().enableLighting(true);
    }

    public int getSpeedOfRotation() {
        return speedOfRotation;
    }

    private double getInterpolatedTime() {
        return speedOfRotation * 0.005;
    }

    @Override
    public void render() {
        super.render();
        // Angle in radians (interpolated time from 0 to 1 results in radian angle 0 to 2PI)
        double angle = MathUtil.TWO_PI * getInterpolatedTime();
        tempVector.setAll(getSphere().getPosition());
        tempVector.rotateY(angle);
        if (aboutElement instanceof Satellite) {
            if (((Satellite) aboutElement).isRotating) {
                tempVector.subtract(aboutElement.getCurrentPosition());
                tempVector.rotateY(angle);
                tempVector.add(aboutElement.getCurrentPosition());
            }
        }
        setPosition(tempVector);
        getSphere().rotate(Vector3.Axis.Y, 2);
    }
}