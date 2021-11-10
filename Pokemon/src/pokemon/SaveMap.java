package pokemon;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SaveMap {
    ArrayList<Block> mapArrayList = new ArrayList<>();
    MapPanel mapPanel;
    StringBuilder mapStringBuilder = new StringBuilder();

    public SaveMap(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        mapArrayList = mapPanel.getBlockArrayList();
        saveMapStringToFile(convertMapToString(this.mapArrayList));
    }

    private String convertMapToString(ArrayList<Block> mapArrayList) {
        for (int i = 0; i < mapArrayList.size(); i++) {
            mapStringBuilder.append(mapArrayList.get(i).getDestinationX() + ";");
            mapStringBuilder.append(mapArrayList.get(i).getDestinationY() + ";");
            mapStringBuilder.append(mapArrayList.get(i).getSourceX() + ";");
            mapStringBuilder.append(mapArrayList.get(i).getSourceY() + ";");
            mapStringBuilder.append("\n");

        }
        return mapStringBuilder.toString();
    }





    private void saveMapStringToFile(String mapString) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // int returnValue = jfc.showOpenDialog(null);
        int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            try (FileWriter writer = new FileWriter(selectedFile);
                 BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(mapString);
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }



}
