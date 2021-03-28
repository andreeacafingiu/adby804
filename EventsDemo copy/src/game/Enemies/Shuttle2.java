package game.Enemies;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a shuttle2
 */

public class Shuttle2 extends Walker {

    private static final Shape shuttle2Shape = new PolygonShape(
            -5.56f,-0.13f,
            -5.61f,0.31f,
            0.85f,4.13f,
            5.34f,0.14f,
            1.52f,-4.21f);

    // add sound
    private static SoundClip shuttle2Sound;
    static {
        try {
            shuttle2Sound = new SoundClip("data/shuttlesound.wav");
            System.out.println("Loading shuttle sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // add image
    private static final BodyImage image =
            new BodyImage("data/shuttle2.png", 10f);  // add image

    //add enemy to the world
    public Shuttle2(World world) {
        super(world, shuttle2Shape);
        addImage(image);
        this.addCollisionListener(new Shuttle2Encounter(((GameLevel) world)));
    }

    @Override
    public void destroy() {
        shuttle2Sound.play();
        super.destroy();
    }
}
