package game.Main;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.Levels.GameLevel;

public class PortalEncounter implements CollisionListener {
    private GameLevel level;
    private Game game;

    public PortalEncounter(GameLevel level, Game game){
        this.level = level;
        this.game = game;
    }

    //go to the next level
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player
                && level.isComplete()){
            e.getReportingBody().destroy();
            game.goToNextLevel();
        }
    }
}
