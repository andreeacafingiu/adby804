package game.Weapons;

import city.cs.engine.DynamicBody;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class ExplosionImpact implements SensorListener {

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if (sensorEvent.getContactBody() instanceof DynamicBody) {
            sensorEvent.getContactBody().destroy();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
