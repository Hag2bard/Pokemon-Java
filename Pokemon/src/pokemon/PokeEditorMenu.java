package pokemon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PokeEditorMenu extends JMenuBar {

    private ObjectPlace objectPlace;



    public PokeEditorMenu(ObjectPlace objectPlace) {
        this.objectPlace = objectPlace;
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
            SaveMap saveMap = new SaveMap(objectPlace.mapPanel);
        });

        menuItemFileOpen.addActionListener(e -> {
            LoadMap loadMap = new LoadMap(objectPlace);
            if (loadMap.getMapString() != null) {
                objectPlace.mapPanel.setMapLayer1(loadMap.getLoadedMap()[0]);
                objectPlace.mapPanel.setMapLayer2(loadMap.getLoadedMap()[1]);
                objectPlace.mapPanel.setSelectedX(0);
                objectPlace.mapPanel.setSelectedY(0);
                objectPlace.tilePanel.selectedBlocks = new ArrayList<>();
                objectPlace.pokeEditor2.mapCreator.repaint();
                final JScrollBar bar = objectPlace.pokeEditor2.mapJScrollPane.getVerticalScrollBar();
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
                objectPlace.mapPanel.getMapLayer1().clean(objectPlace.pokeEditor2.getFieldHeight(), objectPlace.pokeEditor2.getFieldWidth());
                objectPlace.mapPanel.getMapLayer2().clean(objectPlace.pokeEditor2.getFieldHeight(), objectPlace.pokeEditor2.getFieldWidth());
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
            objectPlace.logic.setMap(objectPlace.mapPanel.getMapLayer1(), objectPlace.mapPanel.getMapLayer2());
            objectPlace.logic.setDeleteActive();

        });


    }
}
