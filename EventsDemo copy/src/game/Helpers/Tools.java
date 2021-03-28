package game.Helpers;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates tools
 */
public class Tools extends DynamicBody {
    private static final Shape toolShape = new PolygonShape(
            -1.78f,0.99f,
            -0.2f,2.23f,
            0.9f,-1.99f,
            0.34f,-2.35f);

    // add sound
    private static SoundClip toolSound;
    static {
        try {
            toolSound = new SoundClip("data/toolsound.wav");
            System.out.println("Loading tool sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // add image
    private static final BodyImage image =
            new BodyImage("data/tool.gif", 5f);

    // add tool to the world
    public Tools(World w) {
        super(w,toolShape);
        addImage(image);
        this.addCollisionListener(new ToolsPickup(((GameLevel) w)));
    }

    @Override
    public void destroy() {
        toolSound.play();
        super.destroy();
    }
}
