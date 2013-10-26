package entity;

public class Line extends Fractal {

    public Line(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        super(xPos1, yPos1, xPos2, yPos2, parent);
    }

    void iterate() {
        this.addToList();
    }
}
