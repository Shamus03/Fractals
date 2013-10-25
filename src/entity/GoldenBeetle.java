package entity;

import camera.Camera;

import java.awt.*;

public class GoldenBeetle extends Fractal {
    public GoldenBeetle(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        super(xPos1, yPos1, xPos2, yPos2, parent);
    }

    void iterate() {
        float length = (float)Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2));
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1);

        float cX = (xPos1 + xPos2) / 2;
        float cY = (yPos1 + yPos2) / 2;

        float pointX = (float) (cX + length / 3 * Math.cos(angle + Math.PI/4));
        float pointY = (float) (cY + length / 3 * Math.sin(angle + Math.PI/4));

        new GoldenBeetle(xPos1, yPos1, pointX, pointY, this).addToList();
        new GoldenBeetle(xPos2, yPos2, pointX, pointY, this).addToList();
    }
}
