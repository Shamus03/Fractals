package entity;

public class Squares extends Fractal {
    public Squares(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        super(xPos1, yPos1, xPos2, yPos2, parent);
    }

    void iterate() {
        float length = (float)Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2));
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1);

        float cX = (xPos1 + xPos2) / 2;
        float cY = (yPos1 + yPos2) / 2;

        float bottomLeftX = (float)(cX - length / 6 * Math.cos(angle));
        float bottomLeftY = (float)(cY - length / 6 * Math.sin(angle));

        float bottomRightX = (float)(cX + length / 6 * Math.cos(angle));
        float bottomRightY = (float)(cY + length / 6 * Math.sin(angle));

        float pointX = (float) (cX + length / 3 * Math.cos(angle + Math.PI/2));
        float pointY = (float) (cY + length / 3 * Math.sin(angle + Math.PI/2));

        float topLeftX = (float)(pointX - length / 6 * Math.cos(angle));
        float topLeftY = (float)(pointY - length / 6 * Math.sin(angle));

        float topRightX = (float)(pointX + length / 6 * Math.cos(angle));
        float topRightY = (float)(pointY + length / 6 * Math.sin(angle));

        new Squares(xPos1, yPos1, bottomLeftX, bottomLeftY, this).addToList();

        new Squares(bottomLeftX, bottomLeftY, topLeftX, topLeftY, this).addToList();
        new Squares(topLeftX, topLeftY, topRightX, topRightY, this).addToList();
        new Squares(topRightX, topRightY, bottomRightX, bottomRightY, this).addToList();

        new Squares(bottomRightX, bottomRightY, xPos2, yPos2, this).addToList();
    }
}
