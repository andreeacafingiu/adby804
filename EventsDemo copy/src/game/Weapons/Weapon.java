package game.Weapons;
import city.cs.engine.Body;
import org.jbox2d.common.Vec2;

public abstract class Weapon {
    private Body owner;
    private Vec2 towards;

  public Weapon(Body o) {
        owner = o;
    }

    public Body getOwner() {
        return owner;
    }

    public abstract void shoot();

    public void startShooting() {};
    public void stopShooting() {};

    public void setShootingDirection(Vec2 towards) {
        this.towards = towards;
    }

    public Vec2 getShootingDirection() {
        return this.towards;
    }
}
