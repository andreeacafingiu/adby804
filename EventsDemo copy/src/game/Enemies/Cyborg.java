package game.Enemies;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a robot
 */
public class Cyborg extends Walker {
    private static final Shape cyborgShape = new PolygonShape(
            -0.74f,4.42f,
            0.28f,4.46f,
            3.16f,-0.34f,
            2.7f,-4.12f,
            -3.02f,-4.1f);

    //add sound
    private static SoundClip cyborgSound;
    static {
        try {
            cyborgSound = new SoundClip("data/aliensound.wav");
            System.out.println("Loading cyborg sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //add image
    private static final BodyImage image =
            new BodyImage("data/cyborg.gif", 10f);  // add image

    //add enemy to the world
    public Cyborg(World world) {
        super(world, cyborgShape);
        addImage(image);
        this.addCollisionListener(new CyborgEncounter(((GameLevel) world)));
    }

    @Override
    public void destroy() {
        cyborgSound.play();
        super.destroy();
    }
}
