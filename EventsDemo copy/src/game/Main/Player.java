package game.Main;

import city.cs.engine.*;
import game.Levels.GameLevel;
import game.Weapons.GrenadeLauncher;
import game.Weapons.Handgun;
import game.Weapons.Machinegun;
import game.Weapons.Weapon;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Class creates a player
 */

public class Player extends Walker {
    private static final Shape playerShape = new PolygonShape(
            -2.91f,4.4f,
            2.49f,4.64f,
            2.63f,2.8f,
            1.53f,-3.28f,
            -3.27f,-4.59f,
            -4.01f,-3.52f,
            -3.87f,3.05f);

    private static final BodyImage image= new BodyImage("data/player.png", 10f);  // add image

    private int diamondCount;
    private int lifeCount;

    private Weapon[] weapons;
    private int activeWeapon;

    public Player(GameLevel level) {
        super(level, playerShape);   // calls the constructor of the superclass - Walker
        addImage(image);

        diamondCount = 0;
        lifeCount = 3;

        weapons = new Weapon[3];
        weapons[0] = new Handgun(this);
        weapons[1] = new Machinegun(this);
        weapons[2] = new GrenadeLauncher(this);
    }

    public Weapon getActiveWeapon() {
        return weapons[activeWeapon];
    }

    public void setActiveWeapon(int i) {
        activeWeapon = i;
    }

    // add sound to the player
    private static SoundClip playerSound;
    static {
        try {
            playerSound = new SoundClip("data/playersound.wav");
            System.out.println("Loading player sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public int getDiamondCount(){
        return diamondCount;
    }

    public void setDiamondCount(int dc) { diamondCount = dc; }

    // method to increment diamond counter
    public void incrementDiamondCount(){
        diamondCount++;
        System.out.println("Congrats!! You have " + diamondCount + " diamonds");
    }

    public int getLifeCount(){
        return lifeCount;
    }

    public void setLifeCount( int lc) { lifeCount = lc;}

    //method to decrement life counter
    public void decrementLifeCount() {
        lifeCount--;
        System.out.println("You've lost a life." +
                "You have " + lifeCount + " lives");
        if (lifeCount == 0) {
            playerSound.play();
            System.exit(0);    // exit when there isn't any lives left

        }
    }

    //method to increment life counter
    public void incrementLifeCount(){
        lifeCount++;
        System.out.println("You've gained a life." +
                "You have " + lifeCount + " lives");
    }
}
