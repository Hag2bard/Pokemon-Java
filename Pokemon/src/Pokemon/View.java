package Pokemon;

import PokemonEditor.PokeEditor;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class View extends JFrame {

Canvas canvas;

    {
        try {
            canvas = new Canvas();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public View() {
        canvas.addKeyListener(canvas);
        addKeyListener(canvas);
        add(canvas);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1650,700);
        setLocationRelativeTo(null);
        //setFocusable(true);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

}
