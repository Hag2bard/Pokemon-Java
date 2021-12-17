package PokemonEditor;

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

    private int selectedX = -1;
    private int selectedY = -1;
    private int preSelectedX = -1;
    private int preSelectedY = -1;
    private String filename = "tileset-advance.png";
    private BufferedImage tilesetBufferedImage;
    private boolean isPickupActive = false;
    private final int TILESIZE = 16;
    public static final int ZOOM = 2;
    private int repainted = 0;
    private int tilePanelSelectedX = -1;
    private int tilePanelSelectedY = -1;
    private ArrayList<Integer> selectedBlocksOnMapPanel;
    private int choosedLayer = 1;

    public BlockArray getMapLayer1() {
        return this.mapLayer1;
    }

    public BlockArray getMapLayer2() {
        return this.mapLayer2;
    }

    private BlockArray mapLayer1;
    private BlockArray mapLayer2;
    private TilePanel tilePanel;
    private PokeEditor pokeEditor;
    private Logic logic;

    public MapPanel(TilePanel tilePanel, PokeEditor pokeEditor, Logic logic) {

        this.tilePanel = tilePanel;
        this.pokeEditor = pokeEditor;
        this.logic = logic;
        mapLayer1 = new BlockArray(pokeEditor.getAmountOfBlocks());
        mapLayer2 = new BlockArray(pokeEditor.getAmountOfBlocks());
        selectedBlocksOnMapPanel = new ArrayList<>();
        try {
            loadBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // switch einbauen für layer 1, 2 oder beide eventuell mit blasszeichnung und ohne   5 Möglichkeiten

        float alpha = 1.0f;
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(alcom);


        //
        switch (choosedLayer) {
            case 1:
                alpha = 1.0f;
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d = (Graphics2D) g;
                g2d.setComposite(alcom);
                for (int i = 0; i < mapLayer1.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer1.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer1.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer1.get(i).getSourceX() * TILESIZE, mapLayer1.get(i).getSourceY() * TILESIZE, (mapLayer1.get(i).getSourceX() + 1) * TILESIZE, (mapLayer1.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }
                alpha = 0.5f;
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d = (Graphics2D) g;
                g2d.setComposite(alcom);
                for (int i = 0; i < mapLayer2.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer2.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer2.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer2.get(i).getSourceX() * TILESIZE, mapLayer2.get(i).getSourceY() * TILESIZE, (mapLayer2.get(i).getSourceX() + 1) * TILESIZE, (mapLayer2.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }
                break;
            case 2:
                alpha = 0.5f;
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d = (Graphics2D) g;
                g2d.setComposite(alcom);
                for (int i = 0; i < mapLayer1.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer1.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer1.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer1.get(i).getSourceX() * TILESIZE, mapLayer1.get(i).getSourceY() * TILESIZE, (mapLayer1.get(i).getSourceX() + 1) * TILESIZE, (mapLayer1.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }
                alpha = 1.0f;
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d = (Graphics2D) g;
                g2d.setComposite(alcom);
                for (int i = 0; i < mapLayer2.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer2.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer2.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer2.get(i).getSourceX() * TILESIZE, mapLayer2.get(i).getSourceY() * TILESIZE, (mapLayer2.get(i).getSourceX() + 1) * TILESIZE, (mapLayer2.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }
                break;
            case 3:
                alpha = 1.0f;
                alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d = (Graphics2D) g;
                g2d.setComposite(alcom);
                for (int i = 0; i < mapLayer1.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer1.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer1.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer1.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer1.get(i).getSourceX() * TILESIZE, mapLayer1.get(i).getSourceY() * TILESIZE, (mapLayer1.get(i).getSourceX() + 1) * TILESIZE, (mapLayer1.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }
                for (int i = 0; i < mapLayer2.size(); i++) {
                    g.drawImage(tilesetBufferedImage, mapLayer2.get(i).getDestinationX() * TILESIZE * ZOOM, mapLayer2.get(i).getDestinationY() * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationX() + 1) * TILESIZE * ZOOM, (mapLayer2.get(i).getDestinationY() + 1) * TILESIZE * ZOOM, mapLayer2.get(i).getSourceX() * TILESIZE, mapLayer2.get(i).getSourceY() * TILESIZE, (mapLayer2.get(i).getSourceX() + 1) * TILESIZE, (mapLayer2.get(i).getSourceY() + 1) * TILESIZE, null); // Das muss geändert werden, soll wo anders gezeichnet werden
                }


        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPickupActive) {
            //doPICKUP
            selectedBlocksOnMapPanel.clear();
            tilePanel.selectedBlocks.clear();
            for (int y = 0; y < tilePanel.amountOfSelectedBlocks; y++) {
                for (int x = 0; x < tilePanel.amountOfSelectedBlocks; x++) {
                    selectedBlocksOnMapPanel.add((e.getPoint().x / (16 * ZOOM)) + x);   //hier holt er sich alle Blöcke die er je nach Größe picken kann
                    selectedBlocksOnMapPanel.add((e.getPoint().y / (16 * ZOOM)) + y);
                }
            }
            for (int i = 0; i < selectedBlocksOnMapPanel.size(); i++) {

            }

            switch (choosedLayer) {
                case 1:
                    for (int i = 0; i < mapLayer1.size(); i++) {
                        for (int b = 0; b < selectedBlocksOnMapPanel.size(); b = b + 2) {
                            if (selectedBlocksOnMapPanel.get(b) == mapLayer1.get(i).getDestinationX() && selectedBlocksOnMapPanel.get(b + 1) == mapLayer1.get(i).getDestinationY()) {
                                //hier weiter machen

                                tilePanel.selectedBlocks.add(mapLayer1.get(i).getSourceX());
                                tilePanel.selectedBlocks.add(mapLayer1.get(i).getSourceY());
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < mapLayer2.size(); i++) {
                        for (int b = 0; b < selectedBlocksOnMapPanel.size(); b = b + 2) {
                            if (selectedBlocksOnMapPanel.get(b) == mapLayer2.get(i).getDestinationX() && selectedBlocksOnMapPanel.get(b + 1) == mapLayer2.get(i).getDestinationY()) {
                                //hier weiter machen

                                tilePanel.selectedBlocks.add(mapLayer2.get(i).getSourceX());
                                tilePanel.selectedBlocks.add(mapLayer2.get(i).getSourceY());
                            }
                        }
                    }
                    break;
            }


            // objectPlace.tilePanel.selectedBlocks = selectedBlocksOnMapPanel;
            pokeEditor.deactivatePickupTool();
            tilePanel.setFocusable(true);
            tilePanel.repaint();
            pokeEditor.deselectBtnPickupTool();

        } else {

            if (selectedX != -1 && selectedY != -1) {
                preSelectedX = selectedX;
                preSelectedY = selectedY;
            }
            selectedX = e.getPoint().x;
            selectedY = e.getPoint().y;
            selectedX = selectedX / (16 * ZOOM);
            selectedY = selectedY / (16 * ZOOM);
            if (!logic.isDeleteActive()) {

                int offset = (int) Math.sqrt(tilePanel.selectedBlocks.size() / 2);
                int x = 0;
                int y = 0;

                // hier auch einbauen dass nicht belegt werden darf


                for (int i = 0; i < tilePanel.selectedBlocks.size(); i = i + 2) {
                    switch (choosedLayer) {
                        case 1:
                            mapLayer1.add(tilePanel.selectedBlocks.get(i), tilePanel.selectedBlocks.get(i + 1), selectedX + x, selectedY + y);
                            break;
                        case 2:
                            mapLayer2.add(tilePanel.selectedBlocks.get(i), tilePanel.selectedBlocks.get(i + 1), selectedX + x, selectedY + y);
                            break;
                    }

                    x++;
                    if (x == offset) {
                        x = 0;
                        y++;
                    }
                }
                boolean doesNotExist = true;
                for (int i = 0; i < mapLayer1.size(); i++) {
                    if (mapLayer1.doesExist(selectedX, selectedY)) {
                        doesNotExist = false;
                    }

                }
                if (doesNotExist) {
                    //System.out.println("Block nicht belegt");
                    //System.out.println("Folgendes wird geaddet: this.tilePanelSelectedX, this.tilePanelSelectedY, selectedX, selectedY " + tilePanelSelectedX + " " + tilePanelSelectedY + " " + selectedX + " " + selectedY);
                    mapLayer1.add(this.tilePanelSelectedX, this.tilePanelSelectedY, selectedX, selectedY); /// Achtung fehler

                    ///tilepanelselectedX gibt es nicht mehr
                    ////
                    ///
                    //
                    repaint();
                }
                ///

                ///
            }
            if (logic.isDeleteActive()) {

                logic.deleteBlock2(selectedX, selectedY);
                repaint();
                tilePanel.repaint();
            }
        }
        repaint();
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

    public void setMapLayer1(BlockArray mapLayer1) {
        this.mapLayer1 = mapLayer1;
    }

    public void setMapLayer2(BlockArray mapLayer2) {
        this.mapLayer2 = mapLayer2;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }

    public BlockArray getBlockArrayLayer1() {
        return mapLayer1;
    }

    public BlockArray getBlockArrayLayer2() {
        return mapLayer2;
    }

    public void activatePickupTool() {
        this.isPickupActive = true;
    }

    public void deactivatePickupTool() {
        this.isPickupActive = false;
    }

    public void setChoosedLayer(int choosedLayer) {
        this.choosedLayer = choosedLayer;
    }

    public int getChoosedLayer() {
        return this.choosedLayer;
    }

}
