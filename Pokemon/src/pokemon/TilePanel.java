package pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TilePanel extends JPanel implements MouseListener {

    private final PokeEditor2 pokeEditor2;
    private final Logic logic;

    private int selectedX = -1;
    private int selectedY = -1;
    private int preSelectedX = -1;
    private int preSelectedY = -1;
    private int tilesize = 16;
    private String filename = "tileset-advance.png";
    private BufferedImage tilesetBufferedImage;

    public TilePanel(PokeEditor2 pokeEditor2, Logic logic) {
        this.pokeEditor2 = pokeEditor2;
        this.logic = logic;

        try {
            loadBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pokeEditor2.runGetandSet();
        g.drawImage(tilesetBufferedImage, 0, 0, 128, 15971, null);
        if (selectedX != -1 && selectedY != -1) {
            g.setColor(Color.RED);
            g.drawRect(selectedX * tilesize, selectedY * tilesize, tilesize, tilesize);
        }
    }

    private void loadBufferedImage() throws IOException {
//        tilesetBufferedImage = ImageIO.read(getClass().getResourceAsStream("filename"));
        tilesetBufferedImage = ImageIO.read(getClass().getResource(filename));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (selectedX != -1 && selectedY != -1) {
            preSelectedX = selectedX;
            preSelectedY = selectedY;
        }
        selectedX = e.getPoint().x;
        selectedY = e.getPoint().y;
        selectedX = selectedX / 16;
        selectedY = selectedY / 16;
        logic.setDeleteInactive();
        pokeEditor2.btnDeleteBlock.setSelected(false);
        repaint();
        this.setFocusable(true);
        pokeEditor2.btnDeleteBlock.setFocusable(false);
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
        if (this.selectedX > 0)
            this.selectedX--;
    }

    public void moveRight() {
        if (this.selectedX < 7)
            this.selectedX++;
    }

    public void moveUp() {
        if (this.selectedY > 0)
            this.selectedY--;
    }

    public void moveDown() {
        if (this.selectedY < 15971)
            this.selectedY++;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }


}
