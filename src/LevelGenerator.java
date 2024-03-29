import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Level generator
 */
public class LevelGenerator {

    public static final int LARGEUR = 40, HAUTEUR = 14;

    /**
     * Generate a level of the game as a pair containing two elements: 1.
     * a 2D array of Booleans representing the walls 2. An array of Strings
     * describing the objects on the game
     *
     * The treasures are represented with:
     *
     * treasure: item: x: y
     *
     *
     * Monsters are represented with:
     *
     * monster: item: x: y
     *
     *
     * The output is represented with:
     *
     * output: x: y
     *
     * @param level the level number to generate (1, 2, 3, ...)
     * @return A description of the level
     */
    public static Paire<boolean[][], String[]> generateLevel(int level) {

        boolean[][] walls = Automate2D.randomGrid(LARGEUR, HAUTEUR);

        ArrayList<int[]> freeCells = getRandomFreeCells(walls);

        int nbTresors = 2;
        int nbMonstres = (int) (2 * Math.sqrt(level));

        String[] items = generateItems(nbTresors + nbMonstres);

        String[] objects = new String[nbTresors + nbMonstres + 2];

        for (int i = 0; i < nbTresors; i++) {
            objects[i] = "tresor:" + items[i] + ":" + freeCells.get(i)[0] + ":" + freeCells.get(i)[1];
        }

        for (int i = 0; i < nbMonstres; i++) {
            int idx = i + nbTresors;
            objects[idx] = "monstre:" + items[idx] + ":" + freeCells.get(idx)[0] + ":" + freeCells.get(idx)[1];
        }

        int exitPos = nbTresors + nbMonstres;
        objects[exitPos] = "sortie:" + freeCells.get(exitPos)[0] + ":" + freeCells.get(exitPos)[1];

        int zoePos = nbTresors + nbMonstres + 1;
        objects[zoePos] = "zoe:" + freeCells.get(zoePos)[0] + ":" + freeCells.get(zoePos)[1];

        return new Paire<>(walls, objects);
    }

    private static ArrayList<int[]> getRandomFreeCells(boolean[][] map) {

        ArrayList<int[]> freeCells = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!map[i][j]) {
                    freeCells.add(new int[]{j, i});
                }
            }
        }

        Random rnd = new Random();
        int n = freeCells.size();

        for (int i = n - 1; n > 1; n--) {
            int idx = rnd.nextInt(i);
            int[] tmp = freeCells.get(i);
            freeCells.set(i, freeCells.get(idx));
            freeCells.set(idx, tmp);
        }

        return freeCells;
    }

    private static String[] generateItems(int n) {

        String[] items = new String[n];

        items[0] = "hexaforce";

        for (int i = 1; i < n; i++) {
            double dice = Math.random();

            if (dice < 0.3) {
                items[i] = "potionvie";
            } else {
                items[i] = "coeur";
            }
        }

        // Shuffle
        Random rnd = new Random();
        for (int i = n - 1; n > 1; n--) {
            int idx = rnd.nextInt(i);
            String tmp = items[i];
            items[i] = items[idx];
            items[idx] = tmp;
        }

        return items;
    }

}