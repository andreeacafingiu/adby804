package game.Weapons;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.*;

public class Bullet extends Projectile {

    public Bullet(World w, Vec2 from, Vec2 towards) {
        super(w, from, towards);

        //make shape
        CircleShape s =new CircleShape(0.5f);
        Fixture f = new SolidFixture(this, s);
        this.setFillColor(Color.black);
        this.addCollisionListener(new BulletImpact());
    }

    @Override
    public float getSpeed() {
        return 200;
    }
}
