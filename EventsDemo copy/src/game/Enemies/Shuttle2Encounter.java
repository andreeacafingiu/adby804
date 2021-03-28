package game.Enemies;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class Shuttle2Encounter implements CollisionListener {

    private GameLevel level;

    public Shuttle2Encounter(GameLevel level) {
        this.level = level;
    }

    // collision between enemy and player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Shuttle2 && e.getOtherBody() == level.getPlayer()) {
            level.getPlayer().decrementLifeCount();
            e.getReportingBody().destroy();
        }
    }
}
