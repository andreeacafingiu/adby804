package game.Enemies;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a robot
 */
public class Alien extends Walker {

    private static final Shape alienShape = new PolygonShape(
            -2.23f,5.02f,
            4.88f,1.2f,
            5.22f,0.46f,
            0.53f,-4.9f,
            -0.53f,-4.83f,
            -5.27f,1.3f);

    // add sound
    private static SoundClip alienSound;
    static {
        try {
            alienSound = new SoundClip("data/aliensound.wav");  // Open an audio input stream
            System.out.println("Loading alien sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //add image
    private static final BodyImage image =
            new BodyImage("data/alien.png", 10f);  // add image

    //add enemy to the world
    public Alien(World world) {
        super(world, alienShape);
        addImage(image);
        this.addCollisionListener(new AlienEncounter((GameLevel)world));

    }

    @Override
    public void destroy() {
        alienSound.play();
        super.destroy();
    }
}
