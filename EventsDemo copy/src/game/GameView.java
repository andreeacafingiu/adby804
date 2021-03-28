package game;
import city.cs.engine.UserView;
import game.Main.Player;
import java.awt.*;

public class GameView extends UserView {
    private Game game;
    private Image back;
    private Player player;

    public GameView(Game game, int width, int height, Player player) {
        super(game.getLevel(), width, height);
        this.player = player;
        this.game = game;
    }

    // background image
   @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(game.getLevel().getBackgroundImage(), 0, 0, this);
    }

    // foreground
   @Override
    protected void paintForeground(Graphics2D g) {
       g.setFont(new Font("TimesRoman", Font.BOLD, 15));
       g.setColor(Color.CYAN);
       g.drawString(game.getLevel().getLevelName(), 10, 30);
       g.drawString("Lives:" + game.getLevel().getPlayer().getLifeCount(), 10, 50);
       g.drawString("Score:" + game.getLevel().getPlayer().getDiamondCount(), 10, 70);
   }
}