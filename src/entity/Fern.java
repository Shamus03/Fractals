package entity;

public class Fern extends Fractal {
    public Fern(float xPos1, float yPos1, float xPos2, float yPos2, Fractal parent) {
        super(xPos1, yPos1, xPos2, yPos2, parent);
    }

    void iterate() {
        double angle = Math.atan2(yPos2 - yPos1, xPos2 - xPos1), branchAngle;
        float length = (float) Math.sqrt(Math.pow(xPos2 - xPos1, 2) + Math.pow(yPos2 - yPos1, 2)), branchLength;
        float pointX, pointY, branchX, branchY;

        pointX = (float) (xPos1 + length / 11 * Math.cos(angle));
        pointY = (float) (yPos1 + length / 11 * Math.sin(angle));

        branchAngle = Math.atan2(3, 4) + angle;
        branchLength = length * 5 / 11;

        branchX = (float) (pointX + branchLength * Math.cos(branchAngle));
        branchY = (float) (pointY + branchLength * Math.sin(branchAngle));

        new Fern(pointX, pointY, branchX, branchY, this).addToList();

        pointX = (float) (xPos1 + length * 2 / 11 * Math.cos(angle));
        pointY = (float) (yPos1 + length * 2 / 11 * Math.sin(angle));

        branchAngle = Math.atan2(-3, 3) + angle;
        branchLength = (float) (length * Math.sqrt(18) / 11);

        branchX = (float) (pointX + branchLength * Math.cos(branchAngle));
        branchY = (float) (pointY + branchLength * Math.sin(branchAngle));

        new Fern(pointX, pointY, branchX, branchY, this).addToList();

        pointX = (float) (xPos1 + length * 3 / 11 * Math.cos(angle));
        pointY = (float) (yPos1 + length * 3 / 11 * Math.sin(angle));

        branchAngle = Math.atan2(-1, 7) + angle;
        branchLength = (float) (length * Math.sqrt(50) / 11);

        branchX = (float) (pointX + branchLength * Math.cos(branchAngle));
        branchY = (float) (pointY + branchLength * Math.sin(branchAngle));

        new Fern(pointX, pointY, branchX, branchY, this).addToList();

        new Line(xPos1, yPos1, pointX, pointY, this).addToList();
    }
}
