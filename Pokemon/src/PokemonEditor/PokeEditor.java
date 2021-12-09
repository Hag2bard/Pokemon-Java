
package PokemonEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokeEditor implements KeyListener {


    private int fieldHeight = 20;   //kein fixer Wert
    private int fieldWidth = 24;    //kein fixer Wert
    private final String filename = "tileset-advance.png";
    private JScrollPane tileJScrollPane;
    private TilePanel tilePanel;
    private MapPanel mapPanel;
    public JScrollPane mapJScrollPane;
    private JButton btnPickupTool;
    private JButton btnMultipleSelect;
    public JCheckBox btnDeleteBlock;
    private JTextField txtFieldAmountOfSelectedBlocks;
    private JTextField txtFieldSelectedLayer;
    private JCheckBox checkBoxReplaceBlock;

    public final JFrame mapCreator;
    private final int tilesize = 16;
    private final Logic logic;
    private final ObjectPlace objectPlace;

    public PokeEditor(ObjectPlace objectPlace) {
        this.objectPlace = objectPlace;
        objectPlace.setPokeEditor2(this);
        logic = new Logic(objectPlace, this);
        objectPlace.setLogic(logic);
        mapCreator = new JFrame("Pokemap-Creator");
        mapCreator.setLayout(null);

        this.tilePanel = new TilePanel(objectPlace);
        objectPlace.setTilePanel(tilePanel);
        this.tilePanel.addMouseListener(tilePanel);
        this.tilePanel.addKeyListener(this);
        this.tilePanel.setFocusable(true);
        this.tilePanel.setPreferredSize(new Dimension(128, 15971));
        this.mapPanel = new MapPanel(objectPlace);
        objectPlace.setMapPanel(mapPanel);
        this.mapPanel.addMouseListener(mapPanel);
        this.mapPanel.addKeyListener(this);
        this.mapPanel.setFocusable(true);
        this.mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.mapPanel.setPreferredSize(new Dimension(fieldWidth * tilesize * MapPanel.ZOOM, fieldHeight * tilesize * MapPanel.ZOOM));

        this.btnDeleteBlock = new JCheckBox("Block löschen");
        this.btnDeleteBlock.setBounds(1250, 10, 110, 50);
        this.btnDeleteBlock.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                logic.setMap(mapPanel.getMapLayer1(), mapPanel.getMapLayer2());
                logic.setDeleteActive();
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                logic.setDeleteInactive();
                tilePanel.setFocusable(true);

                tilePanel.repaint();

            }
        });

        this.btnMultipleSelect = new JButton();

        tileJScrollPane = new JScrollPane(tilePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tileJScrollPane.addKeyListener(this);
        tileJScrollPane.setBounds(1387, 0, 150, 800);
        tileJScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        tileJScrollPane.setFocusable(true);

        mapJScrollPane = new JScrollPane(mapPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);   //AS NEEDED!!!!
        mapJScrollPane.addKeyListener(this);
        mapJScrollPane.setBounds(0, 0, (fieldWidth * tilesize * MapPanel.ZOOM) + 4, (fieldHeight * tilesize * MapPanel.ZOOM) + 4);
        mapJScrollPane.getVerticalScrollBar().setUnitIncrement(20);  //passen 20?
        mapJScrollPane.setFocusable(true);
        ////////////
        PokeEditorMenu pokeEditorMenu = new PokeEditorMenu(objectPlace);
        pokeEditorMenu.addMenu();
        addButtonBar();
        ////////////

        mapCreator.setJMenuBar(pokeEditorMenu);
        mapCreator.add(tileJScrollPane);
        //mapCreator.add(mapPanel);
        mapCreator.add(mapJScrollPane);
        mapCreator.add(btnDeleteBlock);
        mapCreator.add(btnPickupTool);
        mapCreator.add(btnMultipleSelect);
        mapCreator.add(txtFieldAmountOfSelectedBlocks);
        mapCreator.add(txtFieldSelectedLayer);
        mapCreator.add(checkBoxReplaceBlock);

        mapCreator.addKeyListener(this);

        mapCreator.pack();
        mapCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapCreator.setVisible(true);

        mapCreator.setSize(1650, 700);
        mapCreator.setLocationRelativeTo(null);
        mapCreator.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tilePanel.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tilePanel.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            tilePanel.moveUp();

            final JScrollBar bar = mapJScrollPane.getVerticalScrollBar();
            int currentValue = bar.getValue();
            bar.setValue(currentValue + 10);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tilePanel.moveDown();


            final JScrollBar bar = mapJScrollPane.getVerticalScrollBar();
            int currentValue = bar.getValue();
            bar.setValue(currentValue - 10);
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void addButtonBar() {
        Icon pickupIconSelected = new ImageIcon(getClass().getResource("colorpicker2.png"));
        Icon pickupIconDeselected = new ImageIcon(getClass().getResource("colorpickerINV.png"));
        btnPickupTool = new JButton("pickupIconSelected");
        btnPickupTool.setIcon(pickupIconSelected);
        btnPickupTool.setBounds(1250, 60, 118, 128);
        btnPickupTool.setBorder(BorderFactory.createLineBorder(Color.black));
        btnPickupTool.setSelectedIcon(pickupIconDeselected);
        btnPickupTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!btnPickupTool.isSelected()) {
                    selectBtnPickupTool();
                    activatePickupTool();
                } else {
                    btnPickupTool.setSelected(false);
                }

            }
        });

        txtFieldAmountOfSelectedBlocks = new JTextField("Anzahl Blöcke: " + tilePanel.amountOfSelectedBlocks);
        txtFieldAmountOfSelectedBlocks.setBounds(1250, 240, 118, 70);
        txtFieldAmountOfSelectedBlocks.setBorder(BorderFactory.createLineBorder(Color.black));
        txtFieldAmountOfSelectedBlocks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(txtFieldAmountOfSelectedBlocks.getText());
                } catch (NumberFormatException exception) {
                    try {
                        String fieldText = txtFieldAmountOfSelectedBlocks.getText();
                        amount = Integer.parseInt(fieldText.substring(15, 16));
                    } catch (StringIndexOutOfBoundsException exception1) {
                        JOptionPane.showMessageDialog(null, "Geben Sie eine gültige Zahl ein!");
                    }
                }

                objectPlace.tilePanel.amountOfSelectedBlocks = amount;
                txtFieldAmountOfSelectedBlocks.setText("Anzahl Blöcke: " + tilePanel.amountOfSelectedBlocks);
            }
        });

        txtFieldSelectedLayer = new JTextField("Layer: " + mapPanel.getChoosedLayer());
        txtFieldSelectedLayer.setBounds(1250, 310, 118, 70);
        txtFieldSelectedLayer.setBorder(BorderFactory.createLineBorder(Color.black));
        txtFieldSelectedLayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int layer = 0;
                try {
                    layer = Integer.parseInt(txtFieldSelectedLayer.getText());
                } catch (NumberFormatException exception) {
                    try {
                        String fieldText = txtFieldSelectedLayer.getText();
                        layer = Integer.parseInt(fieldText.substring(7, 8));
                    } catch (StringIndexOutOfBoundsException exception1) {
                        JOptionPane.showMessageDialog(null, "Geben Sie eine gültige Zahl ein!");
                    }
                }
                objectPlace.mapPanel.setChoosedLayer(layer);
                txtFieldSelectedLayer.setText("Layer: " + mapPanel.getChoosedLayer());
                mapPanel.repaint();
            }
        });

        checkBoxReplaceBlock = new JCheckBox("Blöcke ersetzen");
        checkBoxReplaceBlock.setBounds(1250, 390, 118, 70);
        checkBoxReplaceBlock.setBorder(BorderFactory.createLineBorder(Color.black));
        checkBoxReplaceBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (mapPanel.getMapLayer1().isReplaceble() && mapPanel.getMapLayer2().isReplaceble()){
                   mapPanel.getMapLayer1().setReplaceble(false);
                   mapPanel.getMapLayer2().setReplaceble(false);
                }
               else {
                   mapPanel.getMapLayer1().setReplaceble(true);
                   mapPanel.getMapLayer2().setReplaceble(true);
               }
            }
        });

    }

    public void activatePickupTool() {
        mapPanel.activatePickupTool();
    }

    public void deactivatePickupTool() {
        mapPanel.deactivatePickupTool();
    }


    public void selectBtnPickupTool() {
        btnPickupTool.setSelected(true);
    }

    public void deselectBtnPickupTool() {
        btnPickupTool.setSelected(false);
    }

    public int getAmountOfBlocks() {
        return this.fieldHeight * this.fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }


}
