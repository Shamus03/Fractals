package entity;

import camera.Camera;
import main.MainClass;
import shape.Polygon2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Fractal extends Entity {
    static int currentIteration = 0;

    static int originGroups = 0;

    Fractal parent;

    int originGroup;

    int iteration;

    float xPos1, yPos1, xPos2, yPos2;

    boolean iterated;

    public Fractal(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        this.parent = parent;

        if(parent != null) {
            this.iteration = parent.iteration + 1;
            this.originGroup = parent.originGroup;
        } else {
            this.iteration = currentIteration;
            this.originGroup = originGroups;
            originGroups++;
        }

        this.xPos1 = xPos1;
        this.yPos1 = yPos1;
        this.xPos2 = xPos2;
        this.yPos2 = yPos2;

        iterated = false;
    }

    public void tick(int delta) {
        if(!iterated && iteration < currentIteration) {
            iterate();
            iterated = true;
            removeFromList();
        }
        if(iteration > currentIteration) {
            removeFromList();
            if(parent != null) {
                if(!Entity.entities.contains(parent)) {
                    parent.addToList();
                    parent.iterated = false;
                }
            }
        }
        updateBoundingBox();
        if(!Camera.isOnScreen(this) && iteration != 0)
            for(Entity e: Entity.entities)
                if(e != this && e instanceof Fractal)
                    if(((Fractal)e).originGroup == this.originGroup) {
                        removeFromList();
                        break;
                    }
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

    public static int getCurrentIteration() {
        return currentIteration;
    }

    public static void nextIteration() {
        currentIteration++;
    }

    public static void previousIteration() {
        if(currentIteration > 0)
            currentIteration--;
    }

    public static void resetIteration() {
        currentIteration = 0;
    }
}
