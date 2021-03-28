package game.Weapons;
import city.cs.engine.SoundClip;
import game.GameView;
import game.Main.Player;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.IOException;

public class ShootController implements MouseListener, MouseMotionListener {

    private Player player;
    private GameView view;

    //add sound
    private static SoundClip shootingSound;
    static {
        try {
            shootingSound = new SoundClip("data/shootingSound.wav");  // Open an audio input stream
            System.out.println("Loading cube sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public ShootController(GameView view,Player player) {
        this.player = player;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        player.getActiveWeapon().shoot();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Vec2 p = view.viewToWorld(new Point2D.Float(e.getX(), e.getY()));
        Vec2 direction = p.sub(player.getPosition());
        direction.normalize();
        player.getActiveWeapon().setShootingDirection(direction);
        player.getActiveWeapon().startShooting();
        shootingSound.play();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.getActiveWeapon().stopShooting();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Vec2 p = view.viewToWorld(new Point2D.Float(e.getX(), e.getY()));
        Vec2 direction = p.sub(player.getPosition());
        direction.normalize();
        player.getActiveWeapon().setShootingDirection(direction);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Vec2 p = view.viewToWorld(new Point2D.Float(e.getX(), e.getY()));
        Vec2 direction = p.sub(player.getPosition());
        direction.normalize();
        player.getActiveWeapon().setShootingDirection(direction);
    }
}
