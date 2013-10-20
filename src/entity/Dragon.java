package entity;

import camera.Camera;

import java.awt.*;

public class Dragon extends Fractal {
    public Dragon(float xPos1, float yPos1, float xPos2, float yPos2, int iteration) {
        super(xPos1, yPos1, xPos2, yPos2, iteration);
    }

    void iterate() {
        iterated = true;

        float length = (float)Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2));
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1);

        float cX = (xPos1 + xPos2) / 2;
        float cY = (yPos1 + yPos2) / 2;

        float pointX = (float) (cX + length / Math.sqrt(8) * Math.sqrt(2) * Math.cos(angle + Math.PI/2));
        float pointY = (float) (cY + length / Math.sqrt(8) * Math.sqrt(2) * Math.sin(angle + Math.PI/2));

        new Dragon(xPos1, yPos1, pointX, pointY, iteration + 1).addToList();
        new Dragon(xPos2, yPos2, pointX, pointY, iteration + 1).addToList();

        removeFromList();
    }
}
