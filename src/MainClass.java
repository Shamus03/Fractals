import camera.Camera;
import entity.*;
import frame.GameFrame;
import game.Game;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class MainClass {
    public static Game game;
    public static GameFrame frame;
    
    static float startScale = 1.0E35f;

    public static void main(String args[]) {
        System.out.println("\n\t---Fractals---");

        System.out.println("1: Golden Beetle");
        System.out.println("2: Koch Snowflake");
        System.out.println("3: Dragon Curve\n");

        System.out.print("Enter your selection: ");
        int fractalType = new Scanner(System.in).nextInt();
        switch(fractalType) {
            case 1:
                new GoldenBeetle(-300 * startScale,-100 * startScale,350 * startScale,-100 * startScale, 0).addToList();
                break;
            case 2:
                float radius = 275 * startScale;
                double startAngle = Math.PI/2;

                float xPos = 0 * startScale;
                float yPos = -15 * startScale;

                float[] xPosList = {0, 0, 0};
                float[] yPosList = {0, 0, 0};

                xPosList[0] = xPos + (float)(radius * Math.cos(startAngle));
                yPosList[0] = yPos + (float)(radius * Math.sin(startAngle));
                xPosList[1] = xPos + (float)(radius * Math.cos(2*Math.PI/3 + startAngle));
                yPosList[1] = yPos + (float)(radius * Math.sin(2*Math.PI/3 + startAngle));
                xPosList[2] = xPos + (float)(radius * Math.cos(4*Math.PI/3 + startAngle));
                yPosList[2] = yPos + (float)(radius * Math.sin(4*Math.PI/3 + startAngle));

                new Koch(xPosList[1], yPosList[1], xPosList[0], yPosList[0], 0).addToList();
                new Koch(xPosList[2], yPosList[2], xPosList[1], yPosList[1], 0).addToList();
                new Koch(xPosList[0], yPosList[0], xPosList[2], yPosList[2], 0).addToList();
                break;
            case 3:
                new Dragon(-200 * startScale,-75 * startScale,250 * startScale,-75 * startScale, 0).addToList();
                break;
            default:
                System.out.println("Invalid selection.  Press Enter to quit.");
                new Scanner(System.in).nextLine();
                System.exit(1);
                break;
        }

        new Entity() {
            float moveSpeed = .5f;
            float scaleSpeed = .001f;
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

                Fractal.iterationTimer(delta);
            }

            public void draw(Graphics2D g) {
                g.setColor(Color.black);
                g.drawString("Entities: " + Entity.entities.size(), 0, 10);
            }
        }.addToList();

        game = new Game();
        frame = new GameFrame("Fractals", game);
        Camera.scale = 1 / startScale;
    }
}
