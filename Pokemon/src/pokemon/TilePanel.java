package pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TilePanel extends JPanel implements MouseListener {

 private final ObjectPlace objectPlace;
 private int tilesize = 16;
 private String filename = "tileset-advance.png";
 private BufferedImage tilesetBufferedImage;
 public ArrayList<Integer> selectedBlocks;    // - Werte sind X und + Werte sind Y
 public int amountOfSelectedBlocks = 1;    // kann auch 3 sein oder 4 oder 1!!!


 public TilePanel(ObjectPlace objectPlace) {
  this.objectPlace = objectPlace;
  selectedBlocks = new ArrayList<Integer>();

  try {
   loadBufferedImage();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 @Override
 protected void paintComponent(Graphics g) {
  super.paintComponent(g);
  g.drawImage(tilesetBufferedImage, 0, 0, 128, 15971, null);

  g.setColor(Color.MAGENTA);
  int selectedBlocksX = -1;
  int selectedBlocksY = -1;

  for (int i = 0; i < selectedBlocks.size(); i = i + 2) {

   selectedBlocksX = selectedBlocks.get(i);
   selectedBlocksY = selectedBlocks.get(i + 1);
   g.drawRect(selectedBlocksX * tilesize, selectedBlocksY * tilesize, tilesize, tilesize);//sollte eigentlich fertig sein an dieser Stelle
  }
 }

 private void loadBufferedImage() throws IOException {
  //        tilesetBufferedImage = ImageIO.read(getClass().getResourceAsStream("filename"));
  tilesetBufferedImage = ImageIO.read(getClass().getResource(filename));
 }

 @Override
 public void mouseClicked(MouseEvent e) {
  selectedBlocks.clear();
  for (int y = 0; y < amountOfSelectedBlocks; y++) {
   for (int x = 0; x < amountOfSelectedBlocks; x++) {
    selectedBlocks.add((e.getPoint().x / 16) + x);
    selectedBlocks.add((e.getPoint().y / 16) + y);
   }
  }
  repaint();
  this.setFocusable(true);
 }



 @Override
 public void mousePressed(MouseEvent e) {

 }

 @Override
 public void mouseReleased(MouseEvent e) {

 }

 @Override
 public void mouseEntered(MouseEvent e) {

 }

 @Override
 public void mouseExited(MouseEvent e) {

 }


 public void moveLeft() {
  if (selectedBlocks.get(0) > 0){
   int previousSelectedX = selectedBlocks.get(0);
   int previousSelectedY = selectedBlocks.get(1);
   selectedBlocks.clear();
   for (int y = 0; y < amountOfSelectedBlocks; y++) {
    for (int x = 0; x < amountOfSelectedBlocks; x++) {
     selectedBlocks.add((previousSelectedX-1) + x);
     selectedBlocks.add(previousSelectedY + y);
    }
   }
   repaint();

  }
 }

 public void moveRight() {


  if (selectedBlocks.get((selectedBlocks.size()-2)) < 7){

   int previousSelectedX = selectedBlocks.get(0);
   int previousSelectedY = selectedBlocks.get(1);
   selectedBlocks.clear();
   for (int y = 0; y < amountOfSelectedBlocks; y++) {
    for (int x = 0; x < amountOfSelectedBlocks; x++) {
     selectedBlocks.add(previousSelectedX+1 + x);
     selectedBlocks.add(previousSelectedY + y);
    }
   }
   repaint();
  }
 }

 public void moveUp() {
  if (selectedBlocks.get(1) > 0){
   int previousSelectedX = selectedBlocks.get(0);
   int previousSelectedY = selectedBlocks.get(1);
   selectedBlocks.clear();
   for (int y = 0; y < amountOfSelectedBlocks; y++) {
    for (int x = 0; x < amountOfSelectedBlocks; x++) {
     selectedBlocks.add(previousSelectedX + x);
     selectedBlocks.add((previousSelectedY-1) + y);
    }
   }
   repaint();

  }
 }

 public void moveDown() {
  if (selectedBlocks.get(selectedBlocks.size()-1) < 997){
   int previousSelectedX = selectedBlocks.get(0);
   int previousSelectedY = selectedBlocks.get(1);
   selectedBlocks.clear();
   for (int y = 0; y < amountOfSelectedBlocks; y++) {
    for (int x = 0; x < amountOfSelectedBlocks; x++) {
     selectedBlocks.add(previousSelectedX + x);
     selectedBlocks.add(previousSelectedY+1 + y);
    }
   }
   repaint();

  }
 }


 public void setAmountOfSelectedBlocks(int amountOfSelectedBlocks) {
  this.amountOfSelectedBlocks = amountOfSelectedBlocks;
 }

}