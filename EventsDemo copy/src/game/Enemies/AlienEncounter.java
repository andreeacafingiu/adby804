package game.Enemies;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class AlienEncounter implements CollisionListener {

    private GameLevel level;

    public AlienEncounter( GameLevel level){
        this.level = level;
    }

    // collision between enemy and player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Alien && e.getOtherBody() == level.getPlayer()) {
            level.getPlayer().decrementLifeCount();
            e.getReportingBody().destroy();
        }
    }
}
