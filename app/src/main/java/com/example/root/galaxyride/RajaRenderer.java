package com.example.root.galaxyride;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import org.rajawali3d.renderer.RajawaliRenderer;

import static com.example.root.galaxyride.elements.Satellite.PLANET_EARTH;
import static com.example.root.galaxyride.elements.Satellite.PLANET_JUPITER;
import static com.example.root.galaxyride.elements.Satellite.PLANET_MARS;
import static com.example.root.galaxyride.elements.Satellite.PLANET_MERCURY;
import static com.example.root.galaxyride.elements.Satellite.PLANET_NEPTUNE;
import static com.example.root.galaxyride.elements.Satellite.PLANET_SATURN;
import static com.example.root.galaxyride.elements.Satellite.PLANET_URANUS;
import static com.example.root.galaxyride.elements.Satellite.PLANET_VENUS;
import static com.example.root.galaxyride.elements.Satellite.SATELLITE_MOON;
import static com.example.root.galaxyride.elements.Star.STAR_SMALL;
import static com.example.root.galaxyride.elements.Star.STAR_SUN;

public class RajaRenderer extends RajawaliRenderer {
    Context context;
    MyArcballCamera arcball;

    public RajaRenderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);
    }

    @Override
    protected void initScene() {

        STAR_SMALL.addToScene(getCurrentScene());
        STAR_SMALL.addChild(1000);
        STAR_SUN.addToScene(getCurrentScene());

        PLANET_MERCURY.addToScene(getCurrentScene());
        PLANET_VENUS.addToScene(getCurrentScene());
        PLANET_EARTH.addToScene(getCurrentScene());
        PLANET_MARS.addToScene(getCurrentScene());
        PLANET_SATURN.addToScene(getCurrentScene());
        PLANET_JUPITER.addToScene(getCurrentScene());
        PLANET_URANUS.addToScene(getCurrentScene());
        PLANET_NEPTUNE.addToScene(getCurrentScene());
        SATELLITE_MOON.addToScene(getCurrentScene());


        // getCurrentScene().addChild(Geometry.xAxisLine);
        // getCurrentScene().addChild(Geometry.yAxisLine);
        // getCurrentScene().addChild(Geometry.zAxisLine);




        arcball = new MyArcballCamera(mContext, ((Activity) mContext).findViewById(R.id.relative_layout));
        arcball.setPosition(0, 0, 4);
        getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
    }

    @Override
    public void onRender(final long elapsedTime, final double deltaTime) {
        super.onRender(elapsedTime, deltaTime);
        STAR_SMALL.render();
        STAR_SUN.render();
        PLANET_MERCURY.render();
        PLANET_VENUS.render();
        PLANET_EARTH.render();
        PLANET_MARS.render();
        PLANET_SATURN.render();
        PLANET_JUPITER.render();
        PLANET_URANUS.render();
        PLANET_NEPTUNE.render();
        SATELLITE_MOON.render();
    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
    }
}
