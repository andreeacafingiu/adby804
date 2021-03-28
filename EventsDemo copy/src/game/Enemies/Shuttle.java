package game.Enemies;
import city.cs.engine.*;
import game.Levels.GameLevel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a shuttle
 */

public class Shuttle extends Walker {

    private static final Shape shuttleShape = new PolygonShape(
            -8.07f,0.0f,
            1.5f,4.97f,
            7.87f,2.03f,
            7.87f,-2.05f,
            2.32f,-4.89f);

    //add sound
    private static SoundClip shuttleSound;
    static {
        try {
            shuttleSound = new SoundClip("data/shuttlesound.wav");
            System.out.println("Loading shuttle sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //add image
    private static final BodyImage image =
            new BodyImage("data/shuttle.png", 10f);  // add image

    // add shuttle to the world
    public Shuttle(World world) {
        super(world, shuttleShape);
        addImage(image);
        this.addCollisionListener(new ShuttleEncounter(((GameLevel) world)));
    }

    @Override
    public void destroy() {
        shuttleSound.play();
        super.destroy();
    }
}
