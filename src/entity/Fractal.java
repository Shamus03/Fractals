package entity;

import camera.Camera;
import shape.Polygon2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Fractal extends Entity {
    static int iterationLimit = 0;
    static int iterationDelay = 10;
    static int iterationUpTimer = iterationDelay;
    static boolean iterating = true;

    int iteration;

    float xPos1, yPos1, xPos2, yPos2;

    boolean iterated;

    public Fractal(float xPos1, float yPos1, float xPos2, float yPos2, int iteration) {
        this.iteration = iteration;

        this.xPos1 = xPos1;
        this.yPos1 = yPos1;
        this.xPos2 = xPos2;
        this.yPos2 = yPos2;

        iterated = false;
    }

    public void tick(int delta) {
        if(!iterated && iteration < iterationLimit) {
            iterate();
        }
        updateBoundingBox();
        if(!Camera.isOnScreen(this))
            removeFromList();
    }

    public void updateBoundingBox() {
        Rectangle2D rect = new Rectangle2D.Float(xPos1, yPos1, xPos2 - xPos1, yPos2 - yPos1);
        boundingBox = new Polygon2D(rect);
    }

    abstract void iterate();

    public void draw(Graphics2D g) {
        g.setColor(color);
        Camera.drawLine(xPos1, yPos1, xPos2, yPos2, g);
    }

    public static void iterationTimer(int delta) {
        iterationUpTimer -= delta;
        if(iterationUpTimer <= 0) {
            iterationUpTimer += iterationDelay;
            if(iterating)
                iterationLimit++;
        }

        iterating = delta < 5;
    }
}
