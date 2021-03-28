package game.Enemies;
import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a shooter
 */
public class Shooter extends StaticBody {
    private static final Shape shooterShape = new PolygonShape(-1.68f,2.66f,
            1.08f,3.86f,
            2.22f,3.8f,
            4.16f,-3.06f,
            -3.58f,-3.32f,
            -4.04f,-1.04f);

    // add sound
    private static SoundClip shooterSound;
    static {
        try {
            shooterSound = new SoundClip("data/cubesound.wav");
            System.out.println("Loading shooter sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // add image
    private static final BodyImage image =
            new BodyImage("data/shooter.png", 20f);

    //add shooter to the world
    public Shooter(World w) {
        super(w,shooterShape);
        addImage(image);
    }

    @Override
    public void destroy() {
        shooterSound.play();
        super.destroy();
    }
}
