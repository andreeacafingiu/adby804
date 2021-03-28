package game;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import game.Enemies.*;
import game.Helpers.Diamonds;
import game.Helpers.Tools;
import game.Levels.*;
import game.Main.Player;
import game.Main.Portal;
import org.jbox2d.common.Vec2;

import java.io.*;

/**
 * Class creates save and load state
 */

public class GameSaverLoader {

    // save state
    public static void save(GameLevel level, String fileName)
            throws IOException {

        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, false);
            writer.write(level.getLevelName() + "\n");

            for (StaticBody body : level.getStaticBodies()) {
                if (body instanceof Portal) {
                    writer.write("Portal," + body.getPosition().x + "," +
                            body.getPosition().y +"\n");
                }
            }
            for (DynamicBody body : level.getDynamicBodies()) {
                if (body instanceof Player) {
                    writer.write("Player," + body.getPosition().x + "," +
                            body.getPosition().y + "," +
                            ((Player) body).getLifeCount() + "," +
                            ((Player) body).getDiamondCount() + "\n");

                } else if (body instanceof Diamonds) {
                    writer.write("Diamonds," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if (body instanceof Tools) {
                    writer.write("Tools," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if (body instanceof Alien) {
                    writer.write("Alien," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }else if (body instanceof Cyborg) {
                    writer.write("Cyborg," + body.getPosition().x + "," + body.getPosition().y+ "\n");
                }else if (body instanceof Enemy) {
                    writer.write("Enemy," + body.getPosition().x + "," + body.getPosition().y+ "\n");
                }else if (body instanceof Shuttle) {
                    writer.write("Shuttle," + body.getPosition().x + "," + body.getPosition().y+ "\n");
                }else if (body instanceof Shuttle2) {
                    writer.write("Shuttle2," + body.getPosition().x + "," + body.getPosition().y+ "\n");
                }
            }
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    //load state
    public static GameLevel load(GameLevel level,Game game, String fileName)
            throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

             level = null;
            if (line.equals("Level1"))
                level = new Level1(game);
            else if (line.equals("Level2"))
                level = new Level2(game);
            else if (line.equals("Level3"))
                level = new Level3(game);
            else if (line.equals("Level4"))
                level = new Level4(game);
            System.out.println("Level =" + level);

            line = reader.readLine();
            while (line != null) {

                String[] tokens = line.split(",");

                if (tokens[0].equals("Player")) {
                    Player p = new Player(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    p.setPosition(new Vec2(x,y));
                    int lc = Integer.parseInt(tokens[3]);
                    p.setLifeCount(lc);
                    int dc = Integer.parseInt(tokens[4]);
                    p.setDiamondCount(dc);

                    level.setPlayer(p);


                } else  if (tokens[0].equals("Diamonds")) {
                    Diamonds d = new Diamonds(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    d.setPosition(new Vec2(x,y));


                } else  if (tokens[0].equals("Tools")) {
                    Tools t = new Tools(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    t.setPosition(new Vec2(x,y));


                } else  if (tokens[0].equals("Alien")) {
                    Alien a = new Alien(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    a.setPosition(new Vec2(x,y));
                    a.startWalking(-30);

                } else  if (tokens[0].equals("Cyborg")) {
                    Cyborg c = new Cyborg(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x,y));
                    c.startWalking(20);


                } else  if (tokens[0].equals("Enemy")) {
                    Enemy e = new Enemy(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x,y));
                    e.startWalking(30);


                } else  if (tokens[0].equals("Shuttle")) {
                    Shuttle s = new Shuttle(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    s.setPosition(new Vec2(x,y));
                    s.startWalking(-40);


                } else  if (tokens[0].equals("Shuttle2")) {
                    Shuttle2 s2 = new Shuttle2(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    s2.setPosition(new Vec2(x,y));
                    s2.startWalking(-40);


                } else  if (tokens[0].equals("Portal")) {
                    Portal po = new Portal(level, game);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    po.setPosition(new Vec2(x, y));
                }

                line = reader.readLine();
            }

            return level;

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
