package PokemonEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PokeEditorMenu extends JMenuBar {

    private MapPanel mapPanel;
    private TilePanel tilePanel;
    private PokeEditor pokeEditor;
    private Logic logic;

    public PokeEditorMenu(MapPanel mapPanel, TilePanel tilePanel, PokeEditor pokeEditor, Logic logic) {
        this.mapPanel = mapPanel;
        this.tilePanel = tilePanel;
        this.pokeEditor = pokeEditor;
        this.logic = logic;
    }

    public void addMenu() {
//        menuBar = new JMenuBar();
        JMenu menuFile =
                new JMenu("Datei");
        JMenu menuEdit =
                new JMenu("Bearbeiten");
        JMenu menuHelp =
                new JMenu("Hilfe");

        this.add(menuFile);
        this.add(menuEdit);
        this.add(menuHelp);
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
            SaveMap saveMap = new SaveMap(mapPanel);
        });

        menuItemFileOpen.addActionListener(e -> {
            LoadMap loadMap = new LoadMap();
            if (loadMap.getMapString() != null) {
                mapPanel.setMapLayer1(loadMap.getLoadedMap()[0]);
                mapPanel.setMapLayer2(loadMap.getLoadedMap()[1]);
                mapPanel.setSelectedX(0);
                mapPanel.setSelectedY(0);
                tilePanel.selectedBlocks = new ArrayList<>();
                pokeEditor.repaintMapCreatorJFrame();
                final JScrollBar bar = pokeEditor.getBar();
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
        JMenuItem menuItemEditClean =
                new JMenuItem("Bereinigen");
        JMenuItem menuItemEditPaste =
                new JMenuItem("Einfügen");

        menuItemEditClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapPanel.getMapLayer1().clean(pokeEditor.getFieldHeight(), pokeEditor.getFieldWidth());
                mapPanel.getMapLayer2().clean(pokeEditor.getFieldHeight(), pokeEditor.getFieldWidth());
            }
        });

        menuEdit.add(menuItemEditDelete);
        menuEdit.add(menuItemEditClean);
        menuEdit.add(menuItemEditPaste);

        //Hinzufügen von Menüeinträgen in das Hilfemenü
        JMenuItem menuItemHelpHelp =
                new JMenuItem("Hilfe");

        menuHelp.add(menuItemHelpHelp);

        //Hinzufügen der Menüleiste zum Frame


        menuItemEditDelete.addActionListener(e -> {
            logic.setMap(mapPanel.getMapLayer1(), mapPanel.getMapLayer2());
            logic.setDeleteActive();

        });


    }
}
