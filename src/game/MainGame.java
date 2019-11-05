package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainGame {
    private Tile[][] matrix = new Tile[4][4];
    RenderManager board;

    public static void main(String[] args) {
        MainGame Game = new MainGame();
        Game.run();
    }

    private void run () {
        initMatrix();
        boolean loop = true;
        board = new RenderManager(this);
        board.renderGrid(matrix);
        generateNewTile();
        board.refreshGrid(matrix);
    }

    private void initMatrix() {
        for (int i = 0; i < matrix[0].length; i++){
            for (int j = 0; j < matrix.length; j++){
                matrix[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getMatrix () {
        return matrix;
    }

    public int getFieldValue (int x, int y) {
        return matrix[x][y].getValue();
    }

    public void setMatrix (Tile[][] inputMatrix) {
        matrix = inputMatrix;
    }

    public void setFieldValue (int x, int y, int value) {
        matrix[x][y].setValue(value);
        board.refreshGrid(matrix);
        System.out.println(outputMatrix());
    }

    public String outputMatrix () {
        return Arrays.deepToString(matrix).replace("], ", "]\n");
    }

    public boolean generateNewTile() {

        if (!(hasEmptyTile())){
            return false;
        }

        Random random = new Random();

        while (true){
            int x = random.nextInt(4);
            int y = random.nextInt(4);

            if (matrix[x][y].getValue() == 0){
                matrix[x][y].setValue(getNewTileValue());
                return true;
            }
        }
    }

    private int getNewTileValue() {
        Random random = new Random();
        int rng = random.nextInt(2) + 1;
        return (rng * 2);
    }

    public void move(Direction direction) {
        for(int i = 0; i < 4; i++) {

            List<Tile> tiles = new ArrayList<>();

            for(int j = 0; j < 4; j++) {

                switch (direction) {
                    case LEFT:
                        tiles.add(matrix[i][j]);
                        break;
                    case RIGHT:
                        tiles.add(matrix[i][4 - j - 1]);
                        break;
                    case UP:
                        tiles.add(matrix[j][i]);
                        break;
                    case DOWN:
                        tiles.add(matrix[4 - j - 1][i]);
                        break;
                    default:
                        break;
                }
            }
            if (!(isEmptyTile(tiles))){
                slide(tiles);
            }
        }
        generateNewTile();
        board.refreshGrid(matrix);
        try {
            Thread.sleep(80);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isEmptyTile(List<Tile> tileSet) {

        for (Tile tile: tileSet){
            if (tile.getValue() != 0){
                return false;
            }
        }

        return true;
    }

    private void slide(List<Tile> tileSet){

        slideToEdge(tileSet);
        mergeTile(tileSet);

    }

    private void slideToEdge(List<Tile> tileSet){

        for (int i = 0; i < tileSet.size(); i++){

            if (remainingIsZero(tileSet, i)){
                return;
            }
            while (tileSet.get(i).getValue() == 0){
                slideTo(tileSet, i);
            }
        }

    }

    private boolean remainingIsZero(List<Tile> tileSet, int i) {

        List<Tile> remainingTile = new ArrayList<>();

        for (int j = i; j < tileSet.size(); j++){
            remainingTile.add(tileSet.get(j));
        }
        return (isEmptyTile(remainingTile));

    }

    private void slideTo(List<Tile> tileSet, int index){

        for (int j = index; j < tileSet.size() - 1; j++){
            tileSet.get(j).setValue(tileSet.get(j + 1).getValue());
        }

        tileSet.get(tileSet.size() - 1).clear();
    }

    private void mergeTile(List<Tile> tileSet){

        for (int i = 0; i < tileSet.size() - 1; i++){
            if (tileSet.get(i).equals(tileSet.get(i + 1))){
                tileSet.get(i).merge(tileSet.get(i + 1));
                tileSet.get(i + 1).clear();
                slideTo(tileSet, i + 1);
            }
        }

    }

    public boolean noPossibleMove(){
        if (hasEmptyTile()){
            return false;
        }

        return !(hasEqualNeighbour());
    }

    private boolean hasEmptyTile(){

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (matrix[i][j].getValue() == 0){
                    return true;
                }
            }
        }
        return false;

    }

    private boolean hasEqualNeighbour() {

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (j < 4 - 1){
                    if (matrix[i][j].equals(matrix[i][j + 1])){
                        return true;
                    }
                }
                if (i < 4 - 1){
                    if (matrix[i][j].equals(matrix[i + 1][j])){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}