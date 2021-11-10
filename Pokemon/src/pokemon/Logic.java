package pokemon;



import java.util.ArrayList;

public class Logic {

    private ArrayList<Block> map1 = new ArrayList<>();
    private boolean isDeleteActive = false;
    private ObjectPlace objectPlace;

    public Logic(ObjectPlace objectPlace) {
        this.objectPlace = objectPlace;
    }

    public void setDeleteActive() {
        isDeleteActive = true;
        objectPlace.tilePanel.setSelectedX(-1);
        objectPlace.tilePanel.setSelectedY(-1);
        objectPlace.pokeEditor2.btnDeleteBlock.setSelected(true);
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
        objectPlace.tilePanel = tilePanel;
    }


}
