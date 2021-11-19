package pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PokeEditor2 implements KeyListener {

    private int tilePanelSelectedX = -1;
    private int tilePanelSelectedY = -1;
    private int fieldHeight = 20;   //kein fixer Wert
    private int fieldWidth = 24;    //kein fixer Wert
    private final String filename = "tileset-advance.png";
    private JScrollPane tileJScrollPane;
    private JScrollPane mapJScrollPane;
    private TilePanel tilePanel;
    private MapPanel mapPanel;
    private JButton btnPickupTool;
    private JButton btnMultipleSelect;

    public JCheckBox btnDeleteBlock;
    private JMenuBar menuBar;
    public final JFrame mapCreator;
    private final int tilesize = 16;
    private final Logic logic;
    private final ObjectPlace objectPlace;

    public PokeEditor2(ObjectPlace objectPlace) {

        this.objectPlace = objectPlace;
        logic = new Logic(objectPlace);
        mapCreator = new JFrame("Pokemap-Creator");
        mapCreator.setLayout(null);

        objectPlace.setLogic(logic);

        this.tilePanel = new TilePanel(objectPlace);
        this.logic.setTilePanel(tilePanel);
        this.tilePanel.addMouseListener(tilePanel);
        this.tilePanel.addKeyListener(this);
        this.tilePanel.setFocusable(true);
        this.tilePanelSelectedX = tilePanel.getSelectedX();
        this.tilePanelSelectedY = tilePanel.getSelectedY();
        this.tilePanel.setPreferredSize(new Dimension(128, 15971));

        this.mapPanel = new MapPanel(objectPlace);
        this.mapPanel.addMouseListener(mapPanel);
        this.mapPanel.addKeyListener(this);
        this.mapPanel.setFocusable(true);
        this.mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.mapPanel.setPreferredSize(new Dimension(fieldWidth * tilesize * MapPanel.ZOOM, fieldHeight * tilesize * MapPanel.ZOOM));

        this.btnDeleteBlock = new JCheckBox("Block löschen");
        this.btnDeleteBlock.setBounds(1250, 10, 110, 50);
        this.btnDeleteBlock.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                logic.setMap1(mapPanel.getMap1());
                logic.setDeleteActive();
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                logic.setDeleteInactive();
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
        addMenu();
        addButtonBar();
////////////

        mapCreator.setJMenuBar(menuBar);
        mapCreator.add(tileJScrollPane);
        //mapCreator.add(mapPanel);
        mapCreator.add(mapJScrollPane);
        mapCreator.add(btnDeleteBlock);
        mapCreator.add(btnPickupTool);
        mapCreator.add(btnMultipleSelect);

        mapCreator.addKeyListener(this);

        mapCreator.pack();
        mapCreator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapCreator.setVisible(true);

        mapCreator.setSize(1500, 500);
        mapCreator.setLocationRelativeTo(null);
        mapCreator.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void getAndSet(TilePanel tilePanel, MapPanel mapPanel) {
        this.tilePanelSelectedX = tilePanel.getSelectedX();
        this.tilePanelSelectedY = tilePanel.getSelectedY();
        mapPanel.setTilePanelSelectedX(this.tilePanelSelectedX);
        mapPanel.setTilePanelSelectedY(this.tilePanelSelectedY);
    }

    public void runGetandSet() {
        getAndSet(tilePanel, mapPanel);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            tilePanel.moveLeft();
            runGetandSet();
            tilePanel.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tilePanel.moveRight();
            runGetandSet();
            tilePanel.repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            tilePanel.moveUp();
            runGetandSet();
            tilePanel.repaint();

            final JScrollBar bar = mapJScrollPane.getVerticalScrollBar();
            int currentValue = bar.getValue();
            bar.setValue(currentValue + 10);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tilePanel.moveDown();
            runGetandSet();
            tilePanel.repaint();

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

    public void addMenu() {
        menuBar = new JMenuBar();
        JMenu menuFile =
                new JMenu("Datei");
        JMenu menuEdit =
                new JMenu("Bearbeiten");
        JMenu menuHelp =
                new JMenu("Hilfe");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuHelp);
        JMenu menuFileNew =
                new JMenu("Neu");

        menuFile.add(menuFileNew);

        //Hinzufügen von Menüeinträgen in das Dateimenü
        JMenuItem menuItemFileNewText =
                new JMenuItem("Text");
        JMenuItem menuItemFileNewImage =
                new JMenuItem("Bild");
        JMenuItem menuItemFileOpen =
                new JMenuItem("Öffnen");
        JMenuItem menuItemFileSave =
                new JMenuItem("Speichern");
        JMenuItem menuItemFileSaveAs =
                new JMenuItem("Speichern als");
        JMenuItem menuItemFileExit =
                new JMenuItem("Beenden");

        menuItemFileSave.addActionListener(e -> {
            System.out.println("Speichern");
            SaveMap saveMap = new SaveMap(mapPanel);
        });

        menuItemFileOpen.addActionListener(e -> {
            LoadMap loadMap = new LoadMap(objectPlace);
            if (loadMap.getMapString() != null) {
                mapPanel.setMap1(loadMap.getLoadedMap());
                tilePanel.setSelectedX(0);
                tilePanel.setSelectedY(0);
                mapPanel.setSelectedX(0);
                mapPanel.setSelectedY(0);
                mapCreator.repaint();
                final JScrollBar bar = mapJScrollPane.getVerticalScrollBar();
                int currentValue = bar.getValue();
                bar.setValue(currentValue + 200);
            }
        });


        menuFileNew.add(menuItemFileNewText);
        menuFileNew.add(menuItemFileNewImage);
        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileSave);
        menuFile.add(menuItemFileSaveAs);
        menuFile.addSeparator();
        menuFile.add(menuItemFileExit);

        JMenuItem menuItemEditDelete =
                new JMenuItem("Block Löschen");
        JMenuItem menuItemEditCopy =
                new JMenuItem("Kopieren");
        JMenuItem menuItemEditPaste =
                new JMenuItem("Einfügen");

        menuEdit.add(menuItemEditDelete);
        menuEdit.add(menuItemEditCopy);
        menuEdit.add(menuItemEditPaste);

        //Hinzufügen von Menüeinträgen in das Hilfemenü
        JMenuItem menuItemHelpHelp =
                new JMenuItem("Hilfe");

        menuHelp.add(menuItemHelpHelp);

        //Hinzufügen der Menüleiste zum Frame


        menuItemEditDelete.addActionListener(e -> {
            logic.setMap1(mapPanel.getMap1());
            logic.setDeleteActive();

        });


    }

    public void addButtonBar() {
        Icon pickupIconSelected = new ImageIcon(getClass().getResource("colorpicker2.png"));
        Icon pickupIconDeselected = new ImageIcon(getClass().getResource("colorpickerGR.png"));
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
                    System.out.println("Nun selektiert");
                    activatePickupTool();
                } else {
                    btnPickupTool.setSelected(false);
                }

            }
        });

    }

    public void activatePickupTool(){
        mapPanel.activatePickupTool();
    }

    public void deactivatePickupTool(){
        mapPanel.deactivatePickupTool();
    }


    public void selectBtnPickupTool(){
        btnPickupTool.setSelected(true);
    }

    public void deselectBtnPickupTool(){
        btnPickupTool.setSelected(false);
    }


}
