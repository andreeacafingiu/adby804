/**
 * @author      Andreea Cafingiu
 * @version     1.0
 * @since       March 2021
 */

package game.Main;
import city.cs.engine.Walker;
import game.GameSaverLoader;
import game.Weapons.Bomb;
import game.Game;
import game.Levels.GameLevel;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Implements the Controller for Player
 */
public class PlayerController implements KeyListener {
    /**
     * assign a value to WALKING_SPEED
     */
    private static final float WALKING_SPEED = 60;
    /**
     * initialise JUMPING_SPEED and assign a value
     */
    private static final float JUMPING_SPEED = 16;

    /**
     * Initialise a game
     */
    private Game game;
    /**
     * Initialise a body of type Walker
     */
    private Walker body;
    /**
     * Initialise a level
     */
    private GameLevel level;

    /**
     * Control the player in every level
     * <p>
     * @param  body current body
     * @param  level current level
     * @param  game current game
     */
    public PlayerController(Walker body, GameLevel level, Game game) {
        this.body = body;
        this.level = level;
        this.game = game;
    }

    /**
     * Key Typed Method
     * <p>
     * @param  e event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Key Commands
     * <p>
     * @param  e event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands
        //LEFT = walk backwards
        if (code == KeyEvent.VK_LEFT) {
            game.getLevel().getPlayer().startWalking(-WALKING_SPEED);
        }
        //RIGHT = walk forward
        else if (code == KeyEvent.VK_RIGHT) {
            game.getLevel().getPlayer().startWalking(WALKING_SPEED);
        }
        //UP = jump
        else if (code == KeyEvent.VK_UP) {
            Vec2 v = game.getLevel().getPlayer().getLinearVelocity();
            if (Math.abs(v.y) < 0.01f) {
                game.getLevel().getPlayer().jump(JUMPING_SPEED);
            }
        }
        // J = big jump
        else if (code == KeyEvent.VK_J) {
            game.getLevel().getPlayer().jump(30);
        }
        // Q = Quit
        else if (code == KeyEvent.VK_Q) {
            System.exit(1);
        }
        // ENTER = bomb
        else if (code == KeyEvent.VK_ENTER) {
            Bomb b = new Bomb(game.getLevel(), game.getLevel().getPlayer().getPosition().add(new Vec2(20, 0)), 3000);
        }
        // 1 = handgun
        else if(code == KeyEvent.VK_1) {
            ((Player)body).setActiveWeapon(0);
        }
        // 2 = machinegun
        else if(code == KeyEvent.VK_2) {
            ((Player)body).setActiveWeapon(1);
        }
        // 3 = grenade
        else if(code == KeyEvent.VK_3) {
            ((Player) body).setActiveWeapon(2);
        }
        // S = Save level
        else if (code == KeyEvent.VK_S) {
            try {
                GameSaverLoader.save(game.getLevel(), "data/save.txt");
                System.out.println("Saved");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        // L = Load level
        else if (code == KeyEvent.VK_L) {
            try {
                level = GameSaverLoader.load(level, game,"data/save.txt");
                game.setLevel(level);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Stop walking when the key is released
     * <p>
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {           // stop walking when the key is released
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            game.getLevel().getPlayer().stopWalking();
        } else if (code == KeyEvent.VK_RIGHT) {
            game.getLevel().getPlayer().stopWalking();
        } else if (code == KeyEvent.VK_UP) {
            game.getLevel().getPlayer().stopWalking();
        } else if (code == KeyEvent.VK_J) {
            game.getLevel().getPlayer().stopWalking();
        }
    }
}