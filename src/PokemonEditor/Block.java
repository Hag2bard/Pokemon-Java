package PokemonEditor;

public class Block {
    private int sourceX = -1;
    private int sourceY = -1;
    private int destinationX = -1;
    private int destinationY = -1;


    public Block(int sourceX, int sourceY, int destinationX, int destinationY) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    public int getSourceX() {
        return sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setSource(int sourceX, int sourceY) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
    }

}
