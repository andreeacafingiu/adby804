package game.Helpers;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates diamonds
 */

public class Diamonds  extends DynamicBody {
    private static final Shape diamondsShape = new PolygonShape(
            -0.68f,0.84f,
            0.67f,0.85f,
            1.17f,0.34f,
            0.0f,-0.83f,
            -1.17f,0.31f);

    //add sound
    private static SoundClip diamondsSound;
    static {
        try {
            diamondsSound = new SoundClip("data/diamondsound.wav");
            System.out.println("Loading diamonds sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // add image
    private static final BodyImage image =
            new BodyImage("data/diamond.gif", 3f);  // add image

    // add diamonds to the world
    public Diamonds(World w) {
        super(w,diamondsShape);
        addImage(image);
        this.addCollisionListener(new DiamondsPickup((((GameLevel) w))));
    }

    @Override
    public void destroy() {
        diamondsSound.play();
        super.destroy();
    }
}
