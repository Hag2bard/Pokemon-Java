package PokemonEditor;


import java.util.Map;

public class Logic {

    private BlockArray mapLayer1;
    private BlockArray mapLayer2;
    private boolean isDeleteActive = false;
    private PokeEditor pokeEditor;

    private MapPanel mapPanel;

    public Logic(PokeEditor pokeEditor) {
        this.pokeEditor = pokeEditor;
    }

    public void setDeleteActive() {
        isDeleteActive = true;
//        objectPlace.tilePanel.setSelectedX(-1);  //  Hier muss die Logik eingebaut werden, dass nichts selektiert ist!!!!!!
//        objectPlace.tilePanel.setSelectedY(-1); //
        pokeEditor.setBtnDeleteBlock(true);
    }

    public void setDeleteInactive() {
        isDeleteActive = false;
    }

    public void deleteBlock2(int selectedX, int selectedY) {
        switch (mapPanel.getChoosedLayer()) {
            case 1:
                mapLayer1.delete(selectedX, selectedY);
                break;
            case 2:
                mapLayer2.delete(selectedX, selectedY);
                break;
            case 3:
                mapLayer1.delete(selectedX, selectedY);
                mapLayer2.delete(selectedX, selectedY);
                break;
        }
    }


    public void setMap(BlockArray mapLayer1, BlockArray mapLayer2) {
        this.mapLayer1 = mapLayer1;
        this.mapLayer2 = mapLayer2;
    }

    public boolean isDeleteActive() {
        return isDeleteActive;
    }



    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }
}
