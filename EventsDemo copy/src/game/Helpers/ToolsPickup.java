package game.Helpers;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class ToolsPickup implements CollisionListener {
    private GameLevel level;

    public ToolsPickup(GameLevel l) {
        this.level = l;
    }

    // collision between tool and player
   @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Tools && e.getOtherBody() == level.getPlayer()) {
            level.getPlayer().incrementLifeCount();
            e.getReportingBody().destroy();
        }
    }
}
