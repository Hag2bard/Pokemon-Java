package pokemon;



import java.util.ArrayList;

public class Logic {

    private ArrayList<Block> map1 = new ArrayList<>();
    private boolean isDeleteActive = false;
    private TilePanel tilePanel;

    public void setDeleteActive() {
        isDeleteActive = true;
        tilePanel.setSelectedX(-1);
        tilePanel.setSelectedY(-1);
    }
    public void setDeleteInactive() {
        isDeleteActive = false;
    }

    public void deleteBlock2(int selectedX, int selectedY){
        for (int i=map1.size()-1; i>-1; i--){
            if (map1.get(i).getDestinationX() == selectedX && map1.get(i).getDestinationY() == selectedY){
                map1.remove(i);
                System.out.println("Entferne folgendes Objekt: " + (i));
                System.out.println("Länge der Arrayliste: " + map1.size());

            }
        }

    }


    public void setMap1(ArrayList<Block> map1) {
        this.map1 = map1;
    }

    public boolean isDeleteActive() {
        return isDeleteActive;
    }

    public void setTilePanel(TilePanel tilePanel) {
        this.tilePanel = tilePanel;
    }


}
