//Bereinigen vor Speichern

package PokemonEditor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveMap {
    private BlockArray mapArrayLayer1;
    private BlockArray mapArrayLayer2;
    private MapPanel mapPanel;
    private StringBuilder mapStringBuilder = new StringBuilder();

    public SaveMap(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        mapArrayLayer1 = mapPanel.getBlockArrayLayer1();
        mapArrayLayer2 = mapPanel.getBlockArrayLayer2();
        saveMapStringToFile(convertMapToString(this.mapArrayLayer1, this.mapArrayLayer2));
    }

    private String convertMapToString(BlockArray mapArrayLayer1, BlockArray mapArrayLayer2) {
        for (int i = 0; i < mapArrayLayer1.size(); i++) {
            mapStringBuilder.append(mapArrayLayer1.get(i).getDestinationX() + ";");
            mapStringBuilder.append(mapArrayLayer1.get(i).getDestinationY() + ";");
            mapStringBuilder.append(mapArrayLayer1.get(i).getSourceX() + ";");
            mapStringBuilder.append(mapArrayLayer1.get(i).getSourceY() + ";");
        }
        mapStringBuilder.append("NEXTLAYER");
        for (int i = 0; i < mapArrayLayer2.size(); i++) {
            mapStringBuilder.append(mapArrayLayer2.get(i).getDestinationX() + ";");
            mapStringBuilder.append(mapArrayLayer2.get(i).getDestinationY() + ";");
            mapStringBuilder.append(mapArrayLayer2.get(i).getSourceX() + ";");
            mapStringBuilder.append(mapArrayLayer2.get(i).getSourceY() + ";");
        }
        return mapStringBuilder.toString();
    }





    private void saveMapStringToFile(String mapString) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // int returnValue = jfc.showOpenDialog(null);
        int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();


            try (FileWriter writer = new FileWriter(selectedFile);
                 BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(mapString);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }



}
