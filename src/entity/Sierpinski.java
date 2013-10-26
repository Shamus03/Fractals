package entity;

public class Sierpinski extends Fractal{

    public Sierpinski(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        super(xPos1, yPos1, xPos2, yPos2, parent);
    }

    void iterate() {
        float segmentLength = (float) (Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2)) / 2);
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1);

        float centerX = (xPos1 + xPos2) / 2;
        float centerY = (yPos1 + yPos2) / 2;

        float leftPointX = (float) (centerX + segmentLength * Math.cos(angle + Math.PI/2 + Math.PI/6));
        float leftPointY = (float) (centerY + segmentLength * Math.sin(angle + Math.PI/2 + Math.PI/6));

        float rightPointX = (float) (centerX + segmentLength * Math.cos(angle + Math.PI/2 - Math.PI/6));
        float rightPointY = (float) (centerY + segmentLength * Math.sin(angle + Math.PI/2 - Math.PI/6));

        new Sierpinski(xPos1, yPos1, centerX, centerY, this).addToList();
        new Sierpinski(centerX, centerY, xPos2, yPos2, this).addToList();
        new Sierpinski(leftPointX, leftPointY, rightPointX, rightPointY, this).addToList();
    }
}
