/**
 * @author      Andreea Cafingiu
 * @version     1.0
 * @since       March 2021
 */

package game.Levels;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.Game;
import game.Main.Player;
import game.Main.Portal;
import org.jbox2d.common.Vec2;
import java.awt.*;

/**
 * Set The Levels
 */
public abstract class GameLevel extends World {
    /**
     * Initialise SoundClip gameMusic
     */
    public SoundClip gameMusic;
    /**
     *  initialise and assign a value to musicVol
     */
    public float musicVol = (float) 0.5;
    /**
     * Initialise a Player player
     */
    private Player player;
    /**
     * Initialise a portal
     */
    private Portal portal;

    /**
     * GameLevel
     * <p>
     * @param  game add to game
     */
    public GameLevel(Game game) { }

    /**
     * Populate all the levels with a player and portal
     * <p>
     * @param  game add to game
     */
    public void populate(Game game) {
        //all levels have a player, a portal and the player needs to reach the portal to complete the game (hence a PortalEncounter)
        // make the main character
        player = new Player(this);
        player.setPosition(new Vec2(-50,-10));

        // make the portal
        portal = new Portal(this, game);
        portal.setPosition(new Vec2(90, 30));
    }

    /**
     * Get access to the Player
     * <p>
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Setter for the Player
     * <p>
     * @param  p Player
     */
    public void setPlayer(Player p) {
       player = p;
    }

    /**
     * Get access to the Portal
     * <p>
     * @return portal
     */
    public Portal getPortal() {
        return portal;
    }

    /**
     * Method for changing the Background Image in levels
     * <p>
     * @return -
     */
    public abstract Image getBackgroundImage();

    /**
     * Condition for the level to be completed
     * <p>
     * @return -
     */
    public abstract boolean isComplete();

    /**
     * Get the Name of the current level
     * <p>
     * @return -
     */
    public abstract String getLevelName();

    /**
     * Stop Music in GUI
     * <p>
     * Mute button in GUI
     */
    public abstract void stopMusic();

    /**
     * Resume Music in GUI
     * <p>
     * UNMUTE  button in GUI
     */
    public abstract void resumeMusic();

    /**
     * Volume control in GUI
     * <p>
     * @param  i Volume
     */
    public abstract void volumeControl(float i);


}
