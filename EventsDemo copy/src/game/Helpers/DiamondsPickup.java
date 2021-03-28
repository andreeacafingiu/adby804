package game.Helpers;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class DiamondsPickup implements CollisionListener {

    private GameLevel level;

    public DiamondsPickup(GameLevel level) {
        this.level = level;
    }

    // collision between diamonds and player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Diamonds && e.getOtherBody() == level.getPlayer()) {
            level.getPlayer().incrementDiamondCount();
            e.getReportingBody().destroy();
        }
    }
}

