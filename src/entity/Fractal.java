package entity;

import camera.Camera;

import java.awt.*;

public abstract class Fractal extends Entity {
    static int iterationLimit = 0;
    static int iterationDelay = 1000;
    static int iterationUpTimer = iterationDelay;

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
        if(!iterated && iteration < iterationLimit)
            iterate();
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
            if(iterationLimit >= 0)
                iterationLimit++;
        }

        if(delta > 100)
            iterationLimit = -1;
    }
}
