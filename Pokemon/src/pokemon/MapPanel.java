

package pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dch
 */
public class MapPanel extends JPanel implements MouseListener {
    private final ObjectPlace objectPlace;
    private PokeEditor2 pokeEditor2;
    private boolean isPickupActive = false;
    private int selectedX = -1;
    private int selectedY = -1;
    private int preSelectedX = -1;
    private int preSelectedY = -1;
    private final int TILESIZE = 16;
    public static final int ZOOM = 2;
    private String filename = "tileset-advance.png";
    private BufferedImage tilesetBufferedImage;
    private int repainted = 0;
    private int tilePanelSelectedX = -1;
    private int tilePanelSelectedY = -1;
    Graphics2D graphics2D;
    private ArrayList<Integer> selectedBlocksOnMapPanel;

    public void setMap1(ArrayList<Block> map1) {
        this.map1 = map1;
    }

    public ArrayList<Block> getMap1() {
        return this.map1;

    }

    private ArrayList<Block> map1 = new ArrayList<>();

    public MapPanel(ObjectPlace objectPlace) {
        this.objectPlace = objectPlace;
        selectedBlocksOnMapPanel = new ArrayList<Integer>();
        try {
            loadBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        for (int i = 0; i < map1.size(); i++) {
            g.drawImage(tilesetBufferedImage, map1.get(i).getDestinationX() * TILESIZE * ZOOM, map1.get(i).getDestinationY() * TILESIZE * ZOOM, (map1.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (map1.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, map1.get(i).getSourceX() * TILESIZE, map1.get(i).getSourceY() * TILESIZE, (map1.get(i).getSourceX() + 1) * TILESIZE, (map1.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPickupActive) {
            //doPICKUP

            for (int y = 0; y < objectPlace.tilePanel.amountOfSelectedBlocks; y++) {
                for (int x = 0; x < objectPlace.tilePanel.amountOfSelectedBlocks; x++) {
                    selectedBlocksOnMapPanel.add((e.getPoint().x / 16) + x);
                    selectedBlocksOnMapPanel.add((e.getPoint().y / 16) + y);
                }
            }

//            selectedX = e.getPoint().x;                                 //Hole Koordinaten wo im MapPanel geklickt wurde
//            selectedY = e.getPoint().y;                                 //Hole Koordinaten wo im MapPanel geklickt wurde
//            selectedX = selectedX / (16 * ZOOM);                        //Wandle Koordinaten in Block-Koordinaten um (Ein Block hat 16 Pixel (mit Zoom 32))
//            selectedY = selectedY / (16 * ZOOM);                        //Wandle Koordinaten in Block-Koordinaten um (ZOOM ist 2)
            for (int i = 0; i < map1.size(); i++) {
//                int destX = map1.get(i).getDestinationX();              //Hole DestinationX und Y aus aktuellem Index der Block-Arrayliste und
//                int destY = map1.get(i).getDestinationY();              // speichere diese in lokale Variablen destX und destY
//                if (destX == selectedX && destY == selectedY) {         //Wenn die ZielKoordinaten im aktuellen Index dem im MapPanel geklicktem entsprechen
////                    tilePanelSelectedX = map1.get(i).getSourceX();      // dann speicher
////                    tilePanelSelectedY = map1.get(i).getSourceY();
//                    objectPlace.tilePanel.setSelectedX(map1.get(i).getSourceX());
//                    objectPlace.tilePanel.setSelectedY(map1.get(i).getSourceY());
////                    objectPlace.tilePanel.setSelectedX(tilePanelSelectedX);
////                    objectPlace.tilePanel.setSelectedY(tilePanelSelectedY);
//                    this.pokeEditor2 = objectPlace.getPokeEditor2();
//                    this.pokeEditor2.deactivatePickupTool();
//                    objectPlace.tilePanel.setFocusable(true);
//                    objectPlace.tilePanel.repaint();
//                    break;
//                }
                objectPlace.tilePanel.selectedBlocks = selectedBlocksOnMapPanel;
                objectPlace.pokeEditor2.deactivatePickupTool();
                objectPlace.tilePanel.setFocusable(true);
                objectPlace.tilePanel.repaint();
            }
            objectPlace.pokeEditor2.runGetandSet();
            objectPlace.pokeEditor2.deselectBtnPickupTool();

        } else {
            if (selectedX != -1 && selectedY != -1) {
                preSelectedX = selectedX;
                preSelectedY = selectedY;
            }
            selectedX = e.getPoint().x;
            selectedY = e.getPoint().y;
            selectedX = selectedX / (16 * ZOOM);
            selectedY = selectedY / (16 * ZOOM);
            objectPlace.pokeEditor2.runGetandSet();
            if (!objectPlace.logic.isDeleteActive()) {
                int offset = (int) Math.sqrt((objectPlace.tilePanel.selectedBlocks.size()/2));
                int x = 0; int y = 0;
                for (int i = 0; i < objectPlace.tilePanel.selectedBlocks.size(); i=i+2) {
                    map1.add(new Block(objectPlace.tilePanel.selectedBlocks.get(i), objectPlace.tilePanel.selectedBlocks.get(i+1), selectedX+x, selectedY+y));
                    x++;
                    if (x==offset) {
                        x=0; y++;
                    }
                }
                map1.add(new Block(this.tilePanelSelectedX, this.tilePanelSelectedY, selectedX, selectedY));
                repaint();
            }
            if (objectPlace.logic.isDeleteActive()) {
                objectPlace.logic.deleteBlock2(selectedX, selectedY);
                repaint();
                objectPlace.tilePanel.repaint();

            }
        }

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

    private void loadBufferedImage() throws IOException {
        //tilesetBufferedImage = ImageIO.read(getClass().getResourceAsStream(filename));
        tilesetBufferedImage = ImageIO.read(getClass().getResource(filename));
    }

    public void setTilePanelSelectedX(int tilePanelSelectedX) {
        this.tilePanelSelectedX = tilePanelSelectedX;
    }

    public void setTilePanelSelectedY(int tilePanelSelectedY) {
        this.tilePanelSelectedY = tilePanelSelectedY;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }

    public ArrayList<Block> getBlockArrayList() {
        return map1;
    }

    public void activatePickupTool() {
        this.isPickupActive = true;
    }

    public void deactivatePickupTool() {
        this.isPickupActive = false;
    }


}