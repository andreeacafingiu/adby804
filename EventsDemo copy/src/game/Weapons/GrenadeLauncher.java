package game.Weapons;
import city.cs.engine.Body;
import game.Levels.GameLevel;

public class GrenadeLauncher  extends Weapon{

    public GrenadeLauncher(Body o) {
        super(o);
    }

    @Override
    public void shoot() {
        Grenade g = new Grenade((GameLevel) getOwner().getWorld(), getOwner().getPosition(), getShootingDirection());
    }
}
