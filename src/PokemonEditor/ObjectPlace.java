package PokemonEditor;

public class ObjectPlace {

    public PokeEditor pokeEditor;
    public Logic logic;
    public TilePanel tilePanel;
    public SaveMap saveMap;
    public LoadMap loadMap;
    public Block block;
    public MapPanel mapPanel;

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public PokeEditor getPokeEditor2() {
        return pokeEditor;
    }
    public void setPokeEditor2(PokeEditor pokeEditor) {
        this.pokeEditor = pokeEditor;
    }

    public Logic getLogic() {
        return logic;
    }
    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public TilePanel getTilePanel() {
        return tilePanel;
    }
    public void setTilePanel(TilePanel tilePanel) {
        this.tilePanel = tilePanel;
    }

    public SaveMap getSaveMap() {
        return saveMap;
    }
    public void setSaveMap(SaveMap saveMap) {
        this.saveMap = saveMap;
    }

    public LoadMap getLoadMap() {
        return loadMap;
    }
    public void setLoadMap(LoadMap loadMap) {
        this.loadMap = loadMap;
    }

    public Block getBlock() {
        return block;
    }
    public void setBlock(Block block) {
        this.block = block;
    }


}

