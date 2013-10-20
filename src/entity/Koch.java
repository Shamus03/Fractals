package entity;

import camera.Camera;

import java.awt.*;

public class Koch extends Fractal {
    public Koch(float xPos1, float yPos1, float xPos2, float yPos2, int iteration) {
        super(xPos1, yPos1, xPos2, yPos2, iteration);
    }

    void iterate() {
        iterated = true;

        float length = (float)Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2));
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1);

        float cX = (xPos1 + xPos2) / 2;
        float cY = (yPos1 + yPos2) / 2;

        float pointX = (float) (cX + length / 6 * Math.sqrt(2) * Math.cos(angle + Math.PI/2));
        float pointY = (float) (cY + length / 6 * Math.sqrt(2) * Math.sin(angle + Math.PI/2));

        float leftX = (float)(cX - length / 6 * Math.cos(angle));
        float leftY = (float)(cY - length / 6 * Math.sin(angle));

        float rightX = (float)(cX + length / 6 * Math.cos(angle));
        float rightY = (float)(cY + length / 6 * Math.sin(angle));

        new Koch(leftX, leftY, pointX, pointY, iteration + 1).addToList();
        new Koch(pointX, pointY, rightX, rightY, iteration + 1).addToList();

        new Koch(xPos1, yPos1, leftX, leftY, iteration + 1).addToList();
        new Koch(rightX, rightY, xPos2, yPos2, iteration + 1).addToList();

        removeFromList();
    }
}
