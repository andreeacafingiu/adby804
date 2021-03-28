package game.Enemies;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class ShuttleEncounter implements CollisionListener {

    private GameLevel level;
    public ShuttleEncounter(GameLevel level) {
        this.level = level;
    }

    // collision between enemy and player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Shuttle && e.getOtherBody() == level.getPlayer()) {
            level.getPlayer().decrementLifeCount();
            e.getReportingBody().destroy();
        }
    }
}
