package game.Weapons;
import city.cs.engine.*;
import game.Levels.GameLevel;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Bomb implements ActionListener {

    private DynamicBody bomb;
    private DynamicBody explosion;
    private static SoundClip bombSound;
    private  static SoundClip explosionSound;

    // add sound
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

    private int timerCount;

    public Bomb(GameLevel world, Vec2 position, int time) {

        // add shape and image
        bomb = new DynamicBody(world, new CircleShape(2f));
        bomb.addImage(new BodyImage("data/bomb.png", 5f));
        bomb.setPosition(position);

        Timer t = new Timer(time, this);
        t.start();
        t.setRepeats(false);

        timerCount = 0;
        bombSound.play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timerCount++;

        // first code
        if(timerCount == 1) {
            bomb.destroy();
            bombSound.stop();
            explosionSound.play();

            explosion = new DynamicBody(bomb.getWorld(), new CircleShape(10f));
            explosion.addImage(new BodyImage("data/explosion.png", 20f));
            explosion.setPosition(bomb.getPosition());

            Timer t = new Timer(500, this);
            t.setRepeats(false);
            t.start();

            explosion.addCollisionListener(new CollisionListener() {
                @Override
                public void collide(CollisionEvent collisionEvent) {
                    if (collisionEvent.getOtherBody() instanceof DynamicBody) {
                        collisionEvent.getOtherBody().destroy();
                    }
                }
            });
        }

        if (timerCount == 2) {
            //second code
            explosion.destroy();
        }
    }
}