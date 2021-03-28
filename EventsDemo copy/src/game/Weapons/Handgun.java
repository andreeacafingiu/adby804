package game.Weapons;
import city.cs.engine.Body;
import game.Levels.GameLevel;

public class Handgun extends Weapon {

    public Handgun(Body o) {
        super(o);
    }

    @Override
    public void shoot() {
        Bullet b = new Bullet((GameLevel) getOwner().getWorld(), getOwner().getPosition(), getShootingDirection());
    }
}
