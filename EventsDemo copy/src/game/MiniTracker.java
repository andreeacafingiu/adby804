package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import game.Main.Player;
import org.jbox2d.common.Vec2;

/**
 * Tracker for the small view
 */

public class MiniTracker implements StepListener {
    private Player player;
    private UserView view;
    public MiniTracker(UserView view, Player player) {
        this.view = view;
        this.player = player;
    }

    public void preStep(StepEvent e) { }

    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(player.getPosition()));
    }
}