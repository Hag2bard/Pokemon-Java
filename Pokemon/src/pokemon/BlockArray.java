package pokemon;


public class BlockArray {

    private final Block[] blockArray;
    private int lastFreeIndex = 0;
    private boolean isReplaceble = false;

    public BlockArray(int size) {
        this.blockArray = new Block[size];
    }


    public boolean doesExist(int destinationX, int destinationY) {
        for (int i = 0; i < size(); i++) {
            Block block = blockArray[i];
            if (block.getDestinationX() == destinationX && block.getDestinationY() == destinationY) {
                return true;
            }
        }
        return false;
    }

    public int indexOfCoordinates(int destinationX, int destinationY) {
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i].getDestinationX() == destinationX && blockArray[i].getDestinationY() == destinationY) {
                return i;
            }
        }
        return -1;
    }

    public void add(int sourceX, int sourceY, int destinationX, int destinationY) {

        if (doesExist(destinationX, destinationY)) {

            if (isReplaceble) {

                blockArray[indexOfCoordinates(destinationX, destinationY)].setSource(sourceX, sourceY);
            }
        }

        if (!isFull()) {

            if (!doesExist(destinationX, destinationY)) {

                blockArray[lastFreeIndex] = new Block(sourceX, sourceY, destinationX, destinationY);
                lastFreeIndex++;
            }
        }
    }

    public void delete(int destinationX, int destinationY) {
        if (lastFreeIndex > 0) {
            for (int i = 0; i < size(); i++) {
                if (blockArray[i].getDestinationX() == destinationX && blockArray[i].getDestinationY() == destinationY) {
                    blockArray[i] = blockArray[size() - 1];
                    blockArray[size() - 1] = null;
                    System.out.println("lastFreeIndex" + lastFreeIndex);
                    lastFreeIndex--;

                }
            }
        }
    }

    private boolean isFull() {
        return lastFreeIndex == blockArray.length;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] != null) {
                size = i + 1;
            }
        }
        return size;

    }

    public Block get(int index) {
        return blockArray[index];
    }


    public boolean isReplaceble() {
        return isReplaceble;
    }

    public void setReplaceble(boolean replaceble) {
        isReplaceble = replaceble;
    }

    public void clean(int fieldHeight, int fieldWidth) {

        for (int y = 0; y < fieldHeight + 10; y++) {
            for (int x = fieldWidth; x < fieldWidth + 10; x++) {
                if (doesExist(x, y)) {

                }
                delete(x, y);
            }
        }

        for (int y = fieldHeight; y < fieldHeight + 10; y++) {
            for (int x = 0; x < fieldWidth + 10; x++) {
                if (doesExist(x, y)) {
                    delete(x, y);
                }
            }
        }

    }


}
