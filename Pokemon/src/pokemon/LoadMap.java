package pokemon;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LoadMap {

    String mapString;
    String[] mapStringArray;

    public ArrayList<Block> getLoadedMap() {
        return loadedMap;
    }

    ArrayList<Block> loadedMap;

    public LoadMap() {
        this.mapString = loadMapStringFromFile();
        loadedMap = convertStringToMap(this.mapString);
    }

    private String loadMapStringFromFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        String mapString = null;
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());

            Path fileName = Path.of(selectedFile.getAbsolutePath());
//            String content  = "hello world !!";
//            Files.writeString(fileName, content);

            String actual = null;
            try {
                actual = Files.readString(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(actual);
            mapString = actual;

        }
        return mapString;
    }

    private ArrayList<Block> convertStringToMap(String mapString) {
        mapStringArray = this.mapString.split(";");
        String temp0 = null;
        String temp1 = null;
        String temp2 = null;
        String temp3 = null;
        int tempInt0 = 0;
        int tempInt1 = 0;
        int tempInt2 = 0;
        int tempInt3 = 0;

        ArrayList<Block> map1 = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < mapStringArray.length; i++) {
            System.out.println("I=" + i + " value:" + mapStringArray[i]);

            switch (counter) {
                case 0 -> {
                    temp0 = mapStringArray[i];
                    temp0 = temp0.trim();
                }
                case 1 -> {
                    temp1 = mapStringArray[i];
                    temp1 = temp1.trim();
                }
                case 2 -> {
                    temp2 = mapStringArray[i];
                    temp2 = temp2.trim();
                }
                case 3 -> {
                    temp3 = mapStringArray[i];
                    temp3 = temp3.trim();

                }
                default -> throw new IllegalStateException("Unexpected value: " + counter);
            }

            counter++;
            if (counter == 4) {
                counter = 0;
                tempInt0 = Integer.parseInt(temp0);
                tempInt1 = Integer.parseInt(temp1);
                tempInt2 = Integer.parseInt(temp2);
                tempInt3 = Integer.parseInt(temp3);
                map1.add(new Block(tempInt2, tempInt3, tempInt0, tempInt1));
            }
        }
        return map1;
    }


}
