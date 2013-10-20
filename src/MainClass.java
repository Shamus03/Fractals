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

    public static void main(String args[]) {
        System.out.println("\n\t---Fractals---");

        System.out.println("1: Golden Beetle");
        System.out.println("2: Koch Curve");
        System.out.println("3: Dragon Curve\n");

        System.out.print("Enter your selection: ");
        int fractalType = new Scanner(System.in).nextInt();
        switch(fractalType) {
            case 1:
                new GoldenBeetle(-300,-100,350,-100, 0).addToList();
                break;
            case 2:
                new Koch(-1000,-250,1000,-250, 0).addToList();
                break;
            case 3:
                new Dragon(-200,-75,250,-75, 0).addToList();
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
        }.addToList();

        game = new Game();
        frame = new GameFrame("Fractals", game);
    }
}
