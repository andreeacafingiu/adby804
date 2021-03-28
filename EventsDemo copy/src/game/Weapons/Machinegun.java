package game.Weapons;
import city.cs.engine.Body;
import city.cs.engine.SoundClip;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Machinegun extends Weapon implements ActionListener {
    private Timer t;

    //add sound
    private static SoundClip shootingSound;
    static {
        try {
            shootingSound = new SoundClip("data/shootingSound.wav");
            System.out.println("Loading cube sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Machinegun(Body o) {
        super(o);

        t = new Timer(10, this);
    }

    @Override
    public void shoot() {
        Bullet g = new Bullet((GameLevel)getOwner().getWorld(), getOwner().getPosition(), getShootingDirection());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    shoot();
    shootingSound.play();
    }

    @Override
    public void startShooting() {
        t.start();
    }

    @Override
    public void stopShooting() {
        t.stop();
    }
}
