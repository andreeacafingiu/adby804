package game;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.Main.Player;
import org.jbox2d.common.Vec2;

/**
 * Class implements tracker
 */

public class Tracker implements StepListener {
    private GameView view;
    private Player player;

    public Tracker(GameView view, Player player) {
        this.view = view;
        this.player = player;
    }

    public void preStep(StepEvent e) {}

    public void postStep(StepEvent e) {

        view.setCentre(new Vec2(player.getPosition()));
    }

    public Player updatePlayer(Player p) {
        this.player = p;
        return player;
    }
}