package game.Weapons;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Grenade extends Projectile implements ActionListener {
    Fixture smallCircle;

    //add sound
    private static SoundClip bombSound;
    private  static SoundClip explosionSound;
    static {
        try {
            bombSound = new SoundClip("data/bomb.wav");
            explosionSound = new SoundClip("data/explosion.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Grenade(World w, Vec2 from, Vec2 towards) {
        super(w, from, towards);

        CircleShape s =new CircleShape(2f);
        smallCircle = new SolidFixture(this, s);
        this.setFillColor(Color.black);

        Timer t = new Timer(100, this);
        t.setInitialDelay(3000);
        t.start();

        bombSound.play();
    }

    @Override
    public float getSpeed() {
        return 5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(smallCircle != null) {
            smallCircle.destroy();

            bombSound.stop();
            explosionSound.play();

            smallCircle = null;
            CircleShape s = new CircleShape(10f);
           GhostlyFixture f = new GhostlyFixture(this, s);
            this.setFillColor(Color.red);

            Sensor sensor = new Sensor(this, s);
            sensor.addSensorListener(new ExplosionImpact());
        } else {
            this.destroy();
        }
    }
}
