package game.Enemies;
import city.cs.engine.*;
import game.Levels.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a robot
 */
public class Enemy extends Walker {

    private static final Shape enemyShape = new PolygonShape(
            -2.23f,5.02f,
            4.88f,1.2f,
            5.22f,0.46f,
            0.53f,-4.9f,
            -0.53f,-4.83f,
            -5.27f,1.3f);

    //add sound
    private static SoundClip enemySound;
    static {
        try {
            enemySound = new SoundClip("data/aliensound.wav");
            System.out.println("Loading enemy sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //add image
    private static final BodyImage image =
            new BodyImage("data/enemy.png", 10f);

    //add enemy to the world
    public Enemy(World world) {
        super(world, enemyShape);
        addImage(image);
        this.addCollisionListener(new EnemyEncounter(((GameLevel) world)));
    }

    @Override
    public void destroy() {
        enemySound.play();
        super.destroy();
    }
}
