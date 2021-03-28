package game.Levels;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.Enemies.*;
import game.Helpers.Diamonds;
import game.Helpers.Tools;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Level2  extends GameLevel implements ActionListener {

    private Timer timer;

    public Level2(Game game) {
        //the base class will create the player, portal and the PortalEncounter
        super(game);

        // add background music
        try {
            gameMusic = new SoundClip("data/gametheme2.wav");
            gameMusic.loop();  // Set it to continuous playback
            gameMusic.setVolume(musicVol);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // make the ground
        Shape shape = new BoxShape(400, 0f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -19.5f));

        // make the platforms
        Shape platform1Shape = new BoxShape(13, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(40, 8f));
        platform1.setFillColor(Color.pink);
        platform1.setAlwaysOutline(true);

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(90, -4f));
        platform2.setFillColor(Color.pink);
        platform2.setAlwaysOutline(false);

        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(140, 6f));
        platform3.setFillColor(Color.pink);
        platform3.setAlwaysOutline(false);

        StaticBody platform4 = new StaticBody(this, platform1Shape);
        platform4.setPosition(new Vec2(180, 2.5f));
        platform4.setFillColor(Color.pink);
        platform4.setAlwaysOutline(false);
    }

    //add background image
    @Override
    public Image getBackgroundImage()  {
        Image back = new ImageIcon("data/background2.gif").getImage();
        return back;
    }

    @Override
    public void populate(Game game) {
        super.populate(game);

        timer = new Timer(10000, this);
        timer.setInitialDelay(1000);
        timer.start();

        getPortal().setPosition(new Vec2(90, 30));
        getPlayer().setPosition(new Vec2(-50,-10));
        getPlayer().setGravityScale(1);

        // make the enemies
        Shooter shooter = new Shooter(this);
        shooter.setPosition(new Vec2(180, 9f));

        for (int i = 0; i < 10; i++) {
            Shuttle shuttle = new Shuttle(this);
            shuttle.setPosition(new Vec2(i * 40 - 60, i*20-40));
            shuttle.startWalking(-40);
        }

        for (int i = 0; i < 30; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(i*50 - 230, -10));
            enemy.startWalking(30);
            enemy.setGravityScale(10);
        }

        // make the helpers
        for (int i = 0; i < 20; i++) {
            Diamonds diamond = new Diamonds(this);
            diamond.setPosition(new Vec2(i*20 - 20, 10));
        }

        // implement a tool that adds a life to the main character
        Tools tool = new Tools(this);
        tool.setPosition(new Vec2(100, 12));
    }

    // condition for the level to be complete
    @Override
    public boolean isComplete() {
        if (getPlayer().getDiamondCount() >= 2)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }

    @Override
    public void stopMusic()  {
        gameMusic.stop();
    }

    @Override
    public void resumeMusic()  {
        gameMusic.resume();
    }

    @Override
    public void volumeControl(float i) {
        if (musicVol + i > 0.9999 || musicVol + i < 0.0001)
            i = 0;
        musicVol = musicVol +i;
    }

    // add more diamonds at a interval of time for an easy play
    @Override
    public void actionPerformed(ActionEvent e) {
        Tools tool = new Tools(this);
        tool.setPosition(new Vec2(100, 12));
    }
}
