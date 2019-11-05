package game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class NumberSpawner {

    private MainGame game;

    public NumberSpawner(MainGame Game) {
        game = Game;
    }

    private boolean spawnPossible () {
        int[][] matrix = game.getMatrix();
        ArrayList<Integer> testarray = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                testarray.add(matrix[i][j]);
            }
        }

        if (testarray.contains(0)){
            return true;
        }
        else {
            return false;
        }
    }

    public void spawnNumber() {
        if(spawnPossible()) {
            int[][] matrix = game.getMatrix();

            ArrayList<Integer> xs = new ArrayList<>();
            ArrayList<Integer> ys = new ArrayList<>();

            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    if(matrix[i][j] == 0) {
                        xs.add(i);
                        ys.add(j);
                    }
                }
            }

            int randomNum = ThreadLocalRandom.current().nextInt(0, xs.size() + 1);
            int x = xs.get(randomNum);
            int y = ys.get(randomNum);

            game.setFieldValue(x, y, 2);
        }
    }
}
