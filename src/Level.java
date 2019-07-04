/**
 * The Level class performs the recommanded actions in order to generate a matrix representing the game's board. It
 * places all objects in their respective coodinates at the start of each level.
 */

public class Level {

    private static int level = 0;
    private Player player = new Player(0, 0);
    private Exit exit;
    private GameObject[][] gameBoard = new GameObject[LevelGenerator.HAUTEUR][LevelGenerator.LARGEUR];
    private GameObject[] overlappingObjs = new GameObject[8];

    /**
     * Constructor for the class Level.
     */
    public Level() {

        // Get's incremented everytime a level is created to serve as parameter to upcoming level
        this.level = ++level;
        createLevel();
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {

        String renderLevel = "";

        // Loops through game board matrix and prints every object's appearance
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] instanceof GameObject) {
                    renderLevel += gameBoard[i][j].getAppearance();
                } else { // Not a game object (null) puts a space
                    renderLevel += " ";
                }
            }
            renderLevel += "\n";
        }

        return renderLevel;
    }

    /**
     * Places the walls of the respective level and the items at their given coordinates in order to create a level.
     */
    public void createLevel() {

        GameObject[][] gameBoard = new GameObject[LevelGenerator.HAUTEUR][LevelGenerator.LARGEUR];
        Paire<?, ?> pair = LevelGenerator .generateLevel(Level.getLevel());

        placeWalls(pair, gameBoard); // Places walls first
        placeItems(pair, gameBoard); // Places every object in it's respective coordinate

        this.gameBoard = gameBoard; // Reinitialise game board
    }

    /**
     * Places all the walls of the current level.
     * @param pair: Holds a pair object composed of a key (true or false) representing a wall or not.
     * @param gameBoard: Holds a matrix of game objects representing the game board.
     */
    public void placeWalls(Paire<?, ?> pair, GameObject[][] gameBoard) {

        boolean[][] walls = (boolean[][]) pair.getKey(); // True if it's a wall object

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[i].length; j++) {
                if (walls[i][j]) { // When it's true create a wall object at this coordinate
                    gameBoard[i][j] = new Walls();
                }
            }
        }
    }

    /**
     * Places all the items of the current level (Player, TreasureChests, Enemys, Exit).
     * @param pair: Holds a pair object composed of a value (typeOfObj:item:x:y or typeOfObj:x:y).
     * @param gameBoard: Holds a matrix of game objects representing the game board.
     */
    public void placeItems(Paire<?, ?> pair, GameObject[][] gameBoard) {

        String[] items = (String[]) pair.getValue();
        int index = 0;

        for (int i = 0; i < items.length; i++) {
            String[] objInfo = items[i].split(":");
            String obj = objInfo[0]; // Object type
            int x = Integer.parseInt(objInfo[objInfo.length - 2]); // X coordinate
            int y = Integer.parseInt(objInfo[objInfo.length - 1]); // Y coordinate

            switch (obj) { // Depending on object, create an object of this type at given coordinates
                case ("tresor"):
                    gameBoard[y][x] = new TreasureChest(objInfo[1]);
                    break;
                case ("monstre"):
                    Enemy enemy = new Enemy(objInfo[1], x, y);
                    overlappingObjs[index++] = enemy; // Adds enemy to overlappingObjs
                    gameBoard[y][x] = enemy;
                    break;
                case ("sortie"): 
                    Exit exit = new Exit(x, y);
                    setExit(exit);
                    overlappingObjs[index++] = exit; // Adds exit to overlappingObjs
                    gameBoard[y][x] = exit;
                    break;
                case ("zoe"):
                    player.setX(x);
                    player.setY(y);
                    overlappingObjs[index++] = player; // Adds player to overlappingObjs
                    gameBoard[y][x] = player;
                    break;
            }
        }
    }

    /**
     * Performs all the enemy movements for this specific level for every monster.
     */
    public void enemyMovement() {

        // Find every instance of an enemy in this level and make him move
        for (int i = 0; i < overlappingObjs.length; i++) {
            if (overlappingObjs[i] instanceof Enemy) {
                Enemy enemy = (Enemy) overlappingObjs[i];

                enemy.move(this);
            }
        }
    }

    /**
     * Renders the player's info (Player's health points and number of Hexaforces collected).
     * @return: The string representation of the player's health points and number of Hexaforces at this moment.
     */
    public String renderPlayerInfo() {
        return "Zoe: " + nbOfHearts() + "|" + nbOfHexaforces();
    }

    /**
     * Gives the string representation of the hearts the player has.
     * @return: A string made of the number of hearts (\u2665) the player has.
     */
    private String nbOfHearts() {

        String stringOfHearts = "";
        int nbOfHearts = (int) this.player.getHp();

        for (int i = 0; i < this.player.getMaxHp(); i++) { // Adds a heart symbol for every health point player has
            stringOfHearts += (nbOfHearts > 0? "\u2665 ": "_ ");
            nbOfHearts--;
        }

        return stringOfHearts;
    }

    /**
     * Gives the string representation of the pieces of Hexaforces the player has.
     * @return: A string made of the number of Hexaforces (\u2206) the player has.
     */
    private String nbOfHexaforces() {

        String stringOfHexaforces = "";
        int nbOfHexaforces = (int) this.player.getNbOfHexaforces();

        // Adds a triangle symbol for every Hexaforce player has
        for (int i = 0; i < this.player.getMaxNbOfHexaforces(); i++) {
            stringOfHexaforces += (nbOfHexaforces > 0? "\u2206 ": (i == this.player.getMaxNbOfHexaforces() - 1? "_":
                                    "_ "));
            nbOfHexaforces--;
        }

        return stringOfHexaforces;
    }

    // GETTERS & SETTERS
    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Level.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameObject[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameObject[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameObject[] getOverlappingObjs() {
        return overlappingObjs;
    }

    public void setOverlappingObjs(GameObject[] overlappingObjs) {
        this.overlappingObjs = overlappingObjs;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

}