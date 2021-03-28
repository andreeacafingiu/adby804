package game.Levels;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.Enemies.Alien;
import game.Enemies.Cyborg;
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

public class Level1  extends GameLevel implements ActionListener {

    private Timer timer;

    public Level1(Game game){
        //the base class will create the player, portal and the PortalEncounter
        super(game);

        // add background music
        try {
            gameMusic = new SoundClip("data/gametheme1.wav");
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
        Shape platform1Shape = new BoxShape(13, 0.1f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(-10, -8f));
        platform1.setFillColor(Color.blue);

        StaticBody platform2 = new StaticBody(this, platform1Shape);
        platform2.setPosition(new Vec2(40, 5f));
        platform2.setFillColor(Color.blue);

        StaticBody platform3 = new StaticBody(this, platform1Shape);
        platform3.setPosition(new Vec2(90, -8f));
        platform3.setFillColor(Color.blue);

        StaticBody platform4 = new StaticBody(this, platform1Shape);
        platform4.setPosition(new Vec2(140, 5f));
        platform4.setFillColor(Color.blue);

        StaticBody platform5 = new StaticBody(this, platform1Shape);
        platform5.setPosition(new Vec2(190, 3f));
        platform5.setFillColor(Color.blue);

        StaticBody platform6 = new StaticBody(this, platform1Shape);
        platform6.setPosition(new Vec2(250, 2f));
        platform6.setFillColor(Color.blue);

        StaticBody platform7 = new StaticBody(this, platform1Shape);
        platform7.setPosition(new Vec2(300, 4f));
        platform7.setFillColor(Color.blue);

        // make the walls
        Shape shape2 = new BoxShape(0, 30f);  //make the walls
        StaticBody wall = new StaticBody(this, shape2);
        wall.setPosition(new Vec2(400f, 10.5f));

        Shape wall1Shape = new BoxShape(0, 30f);
        StaticBody wall1 = new StaticBody(this, wall1Shape);
        wall1.setPosition(new Vec2(-400f, 10.5f));
    }

    // change background image
    @Override
    public Image getBackgroundImage()  {
        Image back = new ImageIcon("data/background1.gif").getImage();
        return back;
    }

    @Override
    public void populate(Game game){
        super.populate(game);

        timer = new Timer(5000, this);
        timer.setInitialDelay(100);
        timer.start();

        getPlayer().setPosition(new Vec2(-50,-10));
        getPlayer().setGravityScale(1);

        // make the enemies
        Cyborg cyborg = new Cyborg(this);
        cyborg.setPosition(new Vec2(-80, 300));
        cyborg.startWalking(15);
        cyborg.setGravityScale(2);   // set gravity

        Cyborg cyborg2 = new Cyborg(this);
        cyborg2.setPosition(new Vec2(-100, 300));
        cyborg2.startWalking(15);
        cyborg2.setGravityScale(2);

        for (int i = 0; i < 10; i++) {
            Alien alien = new Alien(this);
            alien.setPosition(new Vec2(i * 200- 100, -10));
            alien.startWalking(-50);
        }

        // make the helpers
        //add 21 diamonds
        for (int i = 0; i < 21; i++) {
            Diamonds diamond = new Diamonds(this);
            diamond.setPosition(new Vec2(i * 20 - 20, 10));
        }

        // implement a tool that adds a life to the main character
        Tools tool = new Tools(this);
        tool.setPosition(new Vec2( 30, 10));
    }

    //condition for the level to be complete
    @Override
    public boolean isComplete() {
        if (getPlayer().getDiamondCount() >= 1)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level1";
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

    // add more enemies at an interval of time
    @Override
    public void actionPerformed(ActionEvent ae) {
        Alien alien = new Alien(this);
        alien.setPosition(new Vec2(200, -10));
        alien.startWalking(-50);
    }
}

