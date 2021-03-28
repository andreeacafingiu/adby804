package game.Weapons;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public abstract class Projectile extends DynamicBody {

    public Projectile(World w, Vec2 from, Vec2 towards) {
        super(w);
        this.setPosition(from.add(towards.mul(10)));
        this.setLinearVelocity(towards.mul(getSpeed()));
    }

    public abstract float getSpeed();
}
