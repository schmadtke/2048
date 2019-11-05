package game;

import javax.swing.*;
import java.util.Arrays;

public class MainGame {
    private int[][] matrix = new int[4][4];
    Game board;

    public static void main(String[] args) {
        MainGame Game = new MainGame();
        Game.run();
    }

    private void run () {
        board = new Game();
        System.out.println(outputMatrix());
        NumberSpawner NS = new NumberSpawner(this);
        NS.spawnNumber();
        System.out.println(outputMatrix());
    }

    public int[][] getMatrix () {
        return matrix;
    }

    public int getFieldValue (int x, int y) {
        return matrix[x][y];
    }

    public void setMatrix (int[][] inputMatrix) {
        matrix = inputMatrix;
    }

    public void setFieldValue (int x, int y, int value) {
        matrix[x][y] = value;
        board.createGrid(matrix);

    }

    public String outputMatrix () {
        return Arrays.deepToString(matrix).replace("], ", "]\n");
    }
}
