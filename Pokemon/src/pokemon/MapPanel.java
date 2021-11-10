package pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MapPanel extends JPanel implements MouseListener {
    private final ObjectPlace objectPlace;
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

    public void setMap1(ArrayList<Block> map1) {
        this.map1 = map1;
    }

    public ArrayList<Block> getMap1() {
        return this.map1;
    }

    private ArrayList<Block> map1 = new ArrayList<>();

    public MapPanel(ObjectPlace objectPlace) {
        this.objectPlace = objectPlace;
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
            map1.add(new Block(this.tilePanelSelectedX, this.tilePanelSelectedY, selectedX, selectedY));
            repaint();
        }
        if (objectPlace.logic.isDeleteActive()) {
            objectPlace.logic.deleteBlock2(selectedX, selectedY);
            repaint();
            objectPlace.tilePanel.repaint();

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


}