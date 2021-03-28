package game.Weapons;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;

public class BulletImpact implements CollisionListener {

    //implement collision
    @Override
    public void collide(CollisionEvent collisionEvent) {
    if (collisionEvent.getOtherBody() instanceof DynamicBody){
         collisionEvent.getOtherBody().destroy();
         collisionEvent.getReportingBody().destroy();
     } else
         collisionEvent.getReportingBody().destroy();
    }
}
