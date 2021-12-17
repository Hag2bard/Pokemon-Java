package Pokemon;

import PokemonEditor.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Canvas extends JPanel implements KeyListener, Runnable {

    private BlockArray mapLayer1;
    private BlockArray mapLayer2;
    private final LoadMap loadMap = new LoadMap();
    private BufferedImage tilesetBufferedImage;
    private BufferedImage spritesBufferedImage;
    private final int ZOOM = 2;
    private final int TILESIZE = 16;
    private final String tilesetFilename = "tileset-advance.png";
    private final String spritesFilename = "sprite.png";
    private int feetPosition = 2;  //1,2,3            (1 = rechter Fuß, 2 = normal, 3 = linker Fuß)
    private boolean lastStepRight = false;
    private String direction = "right";
    private boolean pressing = false;


    Timer moveTimer;

    boolean b;   // for starting and stoping animation


    private int positionX = 2 * TILESIZE * ZOOM;
    private int positionY = 2 * TILESIZE * ZOOM;
    private int newPositionX;
    private int newPositionX2;
    private int newPositionY;
    private int newPositionY2;
    private boolean keyPressed = false;



    public Canvas() throws IOException {
        if (loadMap.getMapString() != null) {
            this.mapLayer1 = loadMap.getLoadedMap()[0];
            this.mapLayer2 = loadMap.getLoadedMap()[1];
        }

        tilesetBufferedImage = loadMap.getBufferedImage(tilesetFilename);
        spritesBufferedImage = loadMap.getBufferedImage(spritesFilename);

        moveTimer = new Timer(5, ae -> {

            if (direction == "right" || direction == "left") {
                if (positionX == newPositionX) {
                    changeFeetPosition();
                    repaint();
//                    System.out.println("16 gelaufen");
//                    System.out.println(positionX);
                }

                if (positionX == newPositionX2) {
                    changeFeetPosition();
                    repaint();
//                    System.out.println("32 gelaufen");
//                    System.out.println(positionX);
                    moveTimer.stop();
                }
                switch (direction) {
                    case "right":
                        positionX++;
                        repaint();
                        break;
                    case "left":
                        positionX--;
                        repaint();
                        break;
                }
            }

            if (direction == "up" || direction == "down") {
                if (positionY == newPositionY) {
                    changeFeetPosition();
                    repaint();
//                    System.out.println("16 gelaufen");
//                    System.out.println(positionX);
                }

                if (positionY == newPositionY2) {
                    moveTimer.stop();
//                    System.out.println("32 gelaufen");
//                    System.out.println(positionX);
//                    if (pressing == false) {
//                        moveTimer.stop();
//                    }
//                    else {
//                        newPositionX = positionX - 16;
//                        newPositionX2 = positionX - 32;
//                    }
                }
                switch (direction) {
                    case "down":
                        positionY++;
                        repaint();
                        break;
                    case "up":
                        positionY--;
                        repaint();
                        break;
                }
            }



        });


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < mapLayer1.size(); i++) {
            g.drawImage(tilesetBufferedImage, mapLayer1.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer1.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer1.get(i).getSourceX() * TILESIZE, mapLayer1.get(i).getSourceY() * TILESIZE, (mapLayer1.get(i).getSourceX() + 1) * TILESIZE, (mapLayer1.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
        }
        for (int i = 0; i < mapLayer2.size(); i++) {
            g.drawImage(tilesetBufferedImage, mapLayer2.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer2.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer2.get(i).getSourceX() * TILESIZE, mapLayer2.get(i).getSourceY() * TILESIZE, (mapLayer2.get(i).getSourceX() + 1) * TILESIZE, (mapLayer2.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
        } //image, destinationX, destinationY, destinationX+1, destinationY+1, sourceX, sourceY, sourceX+1, sourceY+1, null

        if (direction == "left") {
            switch (feetPosition) {
                case 1:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 5, 77, 31, 130, null); //links rechter Fuß vorn
                    break;
                case 2:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 51, 76, 79, 130, null); //links kein Fuß vorn
                    break;
                case 3:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 98, 77, 126, 130, null); //links linker Fuß vorn
                    break;
            }
        }


        if (direction == "right") {
            switch (feetPosition) {
                case 1:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 98, 148, 126, 201, null); //rechts rechter Fuß vorn
                    break;
                case 2:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 52, 146, 80, 201, null);
                    break;
                case 3:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 5, 147, 32, 201, null); //rechts linker Fuß vorn
                    break;
            }
        }

        if (direction == "up") {
            switch (feetPosition) {
                case 1:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 4, 218, 32, 271, null); //oben rechter Fuß vorn
                    break;
                case 2:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 49, 217, 79, 270, null); //oben kein Fuß vorn
                    break;
                case 3:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 98, 218, 126, 270, null); //oben linker Fuß vorn
                    break;
            }
        }

        if (direction == "down") {
            switch (feetPosition) {
                case 1:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 2, 6, 32, 60, null); //Unten rechter Fuß vorn
                    break;
                case 2:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 49, 5, 79, 59, null); //Unten kein Fuß vorn
                    break;
                case 3:
                    g.drawImage(spritesBufferedImage, positionX, positionY, positionX + 28, positionY + 55, 96, 5, 127, 60, null); //Unten linker Fuß vorn
                    break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
pressing = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {


                direction = "left";
                newPositionX = positionX - 16;
                newPositionX2 = positionX - 32;
                moveTimer.start();

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("rechts111");
            direction = "right";
            newPositionX = positionX + 16;
            newPositionX2 = positionX + 32;
            moveTimer.start();
            System.out.println("rechts");

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = "up";
            newPositionY = positionY - 16;
            newPositionY2 = positionY - 32;
            moveTimer.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = "down";
            newPositionY = positionY + 16;
            newPositionY2 = positionY + 32;
            moveTimer.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
pressing = false;
        }
    }

    private void animate() {


    }

    @Override
    public void run() {

    }

    private void changeFeetPosition() {
        if (feetPosition == 1) {
            System.out.println("Rechter Fuß");
            feetPosition = 2;
            lastStepRight = true;
            repaint();
        } else if (feetPosition == 2 && lastStepRight) {
            System.out.println("normal");
            feetPosition = 3;
            repaint();
        } else if (feetPosition == 3) {
            System.out.println("linker Fuß");
            feetPosition = 2;
            lastStepRight = false;
            repaint();
        } else if (feetPosition == 2 && !lastStepRight) {
            System.out.println("normal");
            feetPosition = 1;
            repaint();
        }
    }



}
