package main;

import camera.Camera;
import entity.*;
import frame.GameFrame;
import game.Game;
import input.Input;

import java.awt.*;
import java.util.Scanner;

public class MainClass {
    public static Game game;
    public static GameFrame frame;
    
    static float startScale = 1.0E35f;

    static int fractalType;

    public static void main(String args[]) {
        System.out.println("\n\t---Fractals---");

        System.out.println("1: Golden Beetle");
        System.out.println("2: Koch Snowflake");
        System.out.println("3: Dragon Curve");
        System.out.println("4: Squares\n");

        System.out.print("Enter your selection: ");
        fractalType = new Scanner(System.in).nextInt();

        game = new Game();
        frame = new GameFrame("Fractals", game);

        newFractal();

        new Entity() {
            float moveSpeed = .5f;
            float scaleSpeed = .001f;

            boolean lastADDState, lastSUBTRACTState, lastRState;

            public void tick(int delta) {
                if(Input.UP)
                    Camera.yPos += moveSpeed / Camera.scale * delta;
                if(Input.DOWN)
                    Camera.yPos -= moveSpeed / Camera.scale * delta;
                if(Input.LEFT)
                    Camera.xPos -= moveSpeed / Camera.scale * delta;
                if(Input.RIGHT)
                    Camera.xPos += moveSpeed / Camera.scale * delta;

                if(Input.EQUALS)
                    Camera.scale *= Math.pow(1 + scaleSpeed, delta);
                if(Input.MINUS)
                    Camera.scale *= Math.pow(1 - scaleSpeed, delta);

                if(Input.ADD && !lastADDState)
                    Fractal.nextIteration();
                lastADDState = Input.ADD;

                if(Input.SUBTRACT && !lastSUBTRACTState)
                    Fractal.previousIteration();
                lastSUBTRACTState = Input.SUBTRACT;

                if(Input.R && !lastRState) {
                    Camera.reset();
                    Fractal.resetIteration();
                    Entity.entities.clear();
                    this.addToList();
                    MainClass.newFractal();
                }
                lastRState = Input.R;
            }

            public void draw(Graphics2D g) {
                g.setColor(Color.black);
                g.drawString("Entities: " + Entity.entities.size(), 0, 12);
                g.drawString("Iteration: " + Fractal.getCurrentIteration(), 0, 24);
            }
        }.addToList();
    }

    public static void newFractal() {
        float xPos, yPos;
        float xPosList[] = {0, 0, 0};
        float yPosList[] = {0, 0, 0};
        double startAngle;

        switch(fractalType) {
            case 1:
                new GoldenBeetle(-300 * startScale,-100 * startScale,350 * startScale,-100 * startScale, null).addToList();
                break;
            case 2:
                float radius = 275 * startScale;
                startAngle = Math.PI/2;

                xPos = 0 * startScale;
                yPos = -15 * startScale;

                xPosList[0] = xPos + (float)(radius * Math.cos(startAngle));
                yPosList[0] = yPos + (float)(radius * Math.sin(startAngle));
                xPosList[1] = xPos + (float)(radius * Math.cos(2*Math.PI/3 + startAngle));
                yPosList[1] = yPos + (float)(radius * Math.sin(2*Math.PI/3 + startAngle));
                xPosList[2] = xPos + (float)(radius * Math.cos(4*Math.PI/3 + startAngle));
                yPosList[2] = yPos + (float)(radius * Math.sin(4*Math.PI/3 + startAngle));

                new Koch(xPosList[1], yPosList[1], xPosList[0], yPosList[0], null).addToList();
                new Koch(xPosList[2], yPosList[2], xPosList[1], yPosList[1], null).addToList();
                new Koch(xPosList[0], yPosList[0], xPosList[2], yPosList[2], null).addToList();
                break;
            case 3:
                new Dragon(-200 * startScale,-75 * startScale,250 * startScale,-75 * startScale, null).addToList();
                break;
            case 4:
                float size = 275 * startScale;

                xPos = 0 * startScale;
                yPos = -15 * startScale;

                new Squares(xPos + size/2, yPos + size/2, xPos + size/2, yPos - size/2, null).addToList();
                new Squares(xPos + size/2, yPos - size/2, xPos - size/2, yPos - size/2, null).addToList();
                new Squares(xPos - size/2, yPos - size/2, xPos - size/2, yPos + size/2, null).addToList();
                new Squares(xPos - size/2, yPos + size/2, xPos + size/2, yPos + size/2, null).addToList();
                break;
            default:
                System.out.println("Invalid selection.  Press Enter to quit.");
                new Scanner(System.in).nextLine();
                System.exit(1);
                break;
        }

        Camera.scale = 1 / startScale;
    }
}
