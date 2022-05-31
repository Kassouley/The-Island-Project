package fr.mcstudio.game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.mcstudio.board.Tile;
import fr.mcstudio.enums.Color;
import fr.mcstudio.enums.ExplorerStatus;
import fr.mcstudio.pawns.Boat;
import fr.mcstudio.pawns.Explorer;

/**
 * The Player class is used to store information about the player, such as the player's name, color,
 * and the number of moves left.
 */
public class Player {

    // The constructor of Player.
    public Player(String pseudo, Color color, boolean isBot, int resolution) {
        this.pseudo = pseudo;
        this.color = color;
        this.resolution = resolution;
        this.explorerList = initPlayerExplorer();
        this.isBot = isBot;
        this.moveLeft = 3;
        this.pawnOnBoardNumber = 0;
        this.boatToSet.add(new Boat(resolution));
        this.boatToSet.add(new Boat(resolution));
    }

  
    // A variable that stores the resolution of the game.
    private int resolution;

    // Declaring a variable called pseudo.
    private String pseudo;

    // A variable that stores the avatar of the player.
    private Icon avatar;

    // A variable that stores the color of the player.
    private Color color;

    // A variable that stores the number of pawns on the board.
    private int pawnOnBoardNumber;
   
    // Creating a new list of explorers.
    private List<Explorer> explorerList = new ArrayList<Explorer>();
    
    // Creating a new list of boats.
    private List<Boat> boatToSet = new ArrayList<Boat>();
    
    // Creating a new list of explorers.
    private List<Explorer> currentExplorerList = new ArrayList<Explorer>();

    // A list of tiles that the player has.
    private List<Tile> tileList = new ArrayList<Tile>();

    // A boolean variable that is true if the user is a bot, and false if the user is not a bot.
    private boolean isBot;

    // A variable that stores the number of moves left for the player.
    private int moveLeft;

    /**
     * This function sets the pseudo of the user
     * 
     * @param pseudo The name of the user
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

   /**
    * This function returns the pseudo of the user
    * 
    * @return The pseudo of the user.
    */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * This function sets the avatar of the user
     * 
     * @param avatar The icon.
     */
    public void setAvatar(Icon avatar) {
        this.avatar = avatar;
    }

    /**
     * This function returns the avatar of the user
     * 
     * @return The avatar of the user.
     */
    public Icon getAvatar() {
        return this.avatar;
    }

    /**
     * This function returns the color of the current player
     * 
     * @return The color of the car.
     */
    public Color getColor() {
        return this.color;
    }

   /**
    * This function returns the number of pawns on the board
    * 
    * @return The number of pawns on the board.
    */
    public int getPawnOnBoardNumber() {
        return this.pawnOnBoardNumber;
    }

    /**
     * This function increments the number of pawns on the board by one
     */
    public void addPawnOnBoardNumber() {
        this.pawnOnBoardNumber++;
    }

    /**
     * This function sets the boolean value of the isBot variable to the boolean value of the bool
     * variable
     * 
     * @param bool Whether or not the user is a bot.
     */
    public void setBot(boolean bool) {
        this.isBot = bool;
    }

    /**
     * This function returns a boolean value that is true if the user is a bot, and false if the user
     * is not a bot
     * 
     * @return The boolean value of isBot.
     */
    public boolean isBot() {
        return this.isBot;
    }

    /**
     * This function creates a list of explorers, each with a different treasure value
     * 
     * @return A list of explorers.
     */
    public List<Explorer> initPlayerExplorer() {
        List<Explorer> explorerList = new ArrayList<Explorer>();
        int[] treasureValue = new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 5, 6 };
        for (int i : treasureValue) {
            Explorer explorer = new Explorer(this.color, i);
            explorer.setPosition(0, 0, resolution, 68);
            explorer.createImage(resolution);
            explorerList.add(explorer);
        }
        return explorerList;
    }

    /**
     * This function removes one move from the moveLeft variable
     */
    public void removeOneMove() {
        if (this.moveLeft > 0) {
            this.moveLeft -= 1;
        }
    }

    /**
     * This function sets the number of moves left for the player
     * 
     * @param moveLeft the number of moves left for the player
     */
    public void setMoveLeft(int moveLeft) {
        if (moveLeft >= 0) {
            this.moveLeft = moveLeft;
        } else {
            System.out.println("Error : the number of move left cant be negative.");
        }
    }

    /**
     * This function returns the number of moves left
     * 
     * @return The moveLeft variable is being returned.
     */
    public int getMoveLeft() {
        return this.moveLeft;
    }

    /**
     * This function returns a list of explorers
     * 
     * @return The list of explorers.
     */
    public List<Explorer> getExplorerList() {
        return explorerList;
    }

    /**
     * This function returns a list of boats that are not yet set on the board
     * 
     * @return The list of boats that are to be set.
     */
    public List<Boat> getBoatToSet() {
        return boatToSet;
    }

    /**
     * This function checks if there are any explorers on board that are not dead or saved
     * 
     * @return A boolean value.
     */
    public boolean haveExplorerOnBoard() {
        for (Explorer e : this.currentExplorerList) {
            if (e.getStatus() != ExplorerStatus.DEAD
                    || e.getStatus() != ExplorerStatus.SAVED) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function returns the tileList
     * 
     * @return The list of tiles.
     */
    public List<Tile> getTileList() {
        return tileList;
    }

    /**
     * This function returns the number of explorers that are alive
     * 
     * @return The number of explorers that are alive.
     */
    public int getNumberExplorerAlive() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.NORMAL
                    || e.getStatus() == ExplorerStatus.SWIMMER
                    || e.getStatus() == ExplorerStatus.ONBOAT) {
                cmpt++;
            }
        }
        return cmpt;
    }

    /**
     * This function returns the number of explorers that have been saved
     * 
     * @return The number of explorers saved.
     */
    public int getNumberExplorerSaved() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.SAVED) {
                cmpt++;
            }
        }
        return cmpt;
    }

    /**
     * This function counts the number of explorers of the player that are dead
     * 
     * @return The number of explorers that are dead.
     */
    public int getNumberExplorerDead() {
        int cmpt = 0;
        for (Explorer e : currentExplorerList) {
            if (e.getStatus() == ExplorerStatus.DEAD) {
                cmpt++;
            }
        }
        return cmpt;
    }
    
   	/**
    * This function resets the move points of all explorers in the current explorer list
    */
    public void resetMovePointExplorer() {
   		for(Explorer e : this.currentExplorerList) {
   			if(e.getStatus() == ExplorerStatus.SWIMMER) {
   				e.setMovePoint(1);
   			} else {
   				e.setMovePoint(3);
   			}
   		}
   	}

   	/**
    * This function returns the current list of explorers of the player
    * 
    * @return The current explorer list.
    */
    public List<Explorer> getCurrentExplorerList() {
   		return currentExplorerList;
   	}

}
