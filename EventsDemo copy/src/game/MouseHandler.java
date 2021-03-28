package game;
import game.Levels.GameLevel;
import game.Main.Player;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private GameLevel level;
    private GameView view;
    private Game game;
    private Player player;

    public MouseHandler(GameLevel level, GameView view, Game game, Player player){
        this.view = view;
        this.level = level;
        this.game = game;
        this.player = player;
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    // other methods to satisfy the interface
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
