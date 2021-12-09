package PokemonEditor;


public class Logic {

    private BlockArray mapLayer1;
    private BlockArray mapLayer2;
    private boolean isDeleteActive = false;
    private ObjectPlace objectPlace;
    private PokeEditor pokeEditor;

    public Logic(ObjectPlace objectPlace, PokeEditor pokeEditor) {
        this.pokeEditor = pokeEditor;
        this.objectPlace = objectPlace;
    }

    public void setDeleteActive() {
        isDeleteActive = true;
//        objectPlace.tilePanel.setSelectedX(-1);  //  Hier muss die Logik eingebaut werden, dass nichts selektiert ist!!!!!!
//        objectPlace.tilePanel.setSelectedY(-1); //
        objectPlace.pokeEditor.btnDeleteBlock.setSelected(true);
    }

    public void setDeleteInactive() {
        isDeleteActive = false;
    }

    public void deleteBlock2(int selectedX, int selectedY) {
        switch (objectPlace.mapPanel.getChoosedLayer()) {
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


}
