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

public class Level4  extends GameLevel implements ActionListener {

    private Timer timer;

    public Level4 (Game game){
        //the base class will create the player, portal and the PortalEncounter
        super(game);

        // add background sound
        try {
            gameMusic = new SoundClip("data/gametheme4.wav");  // Open an audio input stream
            gameMusic.loop();  // Set it to continuous playback
            gameMusic.setVolume(musicVol);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // make the ground
        Shape shape = new BoxShape(400, 0f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -19.5f));

        // make a platform for easy play
        Shape platform1Shape = new BoxShape(13, 0.5f);
        StaticBody platform1 = new StaticBody(this, platform1Shape);
        platform1.setPosition(new Vec2(40, 8f));
        platform1.setFillColor(Color.black);
        platform1.setAlwaysOutline(true);
    }

    //add background image
    @Override
    public Image getBackgroundImage()  {
        Image back = new ImageIcon("data/background4.gif").getImage();
        return back;
    }

    @Override
    public void populate(Game game) {
        super.populate(game);

        timer = new Timer(20000, this);
        timer.setInitialDelay(1000);
        timer.start();

        getPortal().setPosition(new Vec2(90, 30));
        getPlayer().setPosition(new Vec2(-50,-10));
        getPlayer().setGravityScale(1);

        // make the enemies
        Cyborg cyborg = new Cyborg(this);
        cyborg.setPosition(new Vec2(-80, 300));
        cyborg.startWalking(15);
        cyborg.setGravityScale(2);

        Alien alien = new Alien(this);
        alien.setPosition(new Vec2(30, -10));
        alien.startWalking(-30);

        Alien alien2 = new Alien(this);
        alien2.setPosition(new Vec2(200, -10));
        alien2.startWalking(-30);

        Alien alien3 = new Alien(this);
        alien3.setPosition(new Vec2(300, -10));
        alien3.startWalking(-30);

        for (int i = 0; i < 30; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(i*50 - 200, 10));
            enemy.startWalking(30);
            enemy.setGravityScale(2);
        }

        for (int i = 0; i < 10; i++) {
            Shuttle shuttle = new Shuttle(this);
            shuttle.setPosition(new Vec2(i * 100 - 100, i*100-50));
            shuttle.startWalking(-40);
        }

        for (int i = 0; i < 10; i++) {
            Shuttle2 shuttle2 = new Shuttle2(this);
            shuttle2.setPosition(new Vec2(i * 40 - 60, i*20-30));
            shuttle2.startWalking(-40);
        }

        // make the helpers
        for (int i = 0; i < 20; i++) {
            Diamonds diamond = new Diamonds(this);
            diamond.setPosition(new Vec2(i * 20 - 20, 10));
        }

        // implement a tool that adds a life to the main character
        Body tool = new Tools(this);
        tool.setPosition(new Vec2( 30, 10));
    }

    // create the condition for the level to be complet
    @Override
    public boolean isComplete() {
        if (getPlayer().getDiamondCount() >= 4)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level4";
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

    // add more diamonds and tools for an easier play
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            Diamonds diamond = new Diamonds(this);
            diamond.setPosition(new Vec2(i * 10 - 20, 5));
        }

        // implement a tool that adds a life to the main character
        Body tool = new Tools(this);
        tool.setPosition(new Vec2( 30, 10));
    }
}
