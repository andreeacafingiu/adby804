package game.Main;
import city.cs.engine.*;
import game.Game;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a portal
 */

public class Portal extends StaticBody{

    private Game game;

    private static final Shape portalShape = new PolygonShape(
            -1.78f,0.99f,
            -0.2f,2.23f,
            0.9f,-1.99f,
            0.34f,-2.35f);

    // add sound
    private static SoundClip portalSound;
    static {
        try {
            portalSound = new SoundClip("data/portalsound.wav");  // Open an audio input stream
            System.out.println("Loading tool sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // add image
    private static final BodyImage image =
            new BodyImage("data/portal.png", 20f);    // add image

    public Portal(World w, Game game) {
        super(w,portalShape);
        this.game = game;
        addImage(image);
        this.addCollisionListener(new PortalEncounter((((GameLevel) w)), game));
    }

    @Override
    public void destroy() {
        portalSound.play();
        super.destroy();
    }
}
