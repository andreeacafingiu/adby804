/**
 * @author      Andreea Cafingiu
 * @version     1.0
 * @since       March 2021
 */

package game;
import city.cs.engine.UserView;
import game.Levels.*;
import game.Main.Player;
import game.Main.PlayerController;
import game.Weapons.ShootController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Initialise the Game
 */
public class Game implements ActionListener {
    /**
     * Initialise level
     */
    private GameLevel level;
    /**
     * initialise a view
     */
    private GameView view;
    /**
     * Initialise controller
     */
    private PlayerController controller;
    /**
     * Initialise userView
     */
    private UserView userView;
    /**
     * Initialise tracker
     */
    private Tracker tracker;

    /**
     * Initialise a JFrame frame
     */
    private  JFrame frame;
    /**
     *Initialise a menu
     */
    private JLabel menu;
    /**
     * Initialise a sound volume control button
     */
    private JButton volumeDown;
    /**
     * Initialise a sound volume control button
     */
    private JButton volumeUp;
    /**
     * Initialise a button to stop  the music
     */
    private JButton stopM;
    /**
     * Initialise a button to resume the music
     */
    private JButton resumeM;
    /**
     * Initialise a button for Level 1
     */
    private JButton lev1;
    /**
     * Initialise a button for Level 2
     */
    private JButton lev2;
    /**
     * Initialise a button for Level 3
     */
    private JButton lev3;
    /**
     * Initialise a button for Level 4
     */
    private JButton lev4;
    /**
     * Initialise a stop button
     */
    private JButton stop;
    /**
     * Initialise a start button
     */
    private JButton start;
    /**
     * Initialise an exit button
     */
    private JButton exit;
    /**
     * Initialise a save button
     */
    private JButton save;
    /**
     * Initialise a load button
     */
    private JButton load;

    /**
     * Initialise a new Game
     * <p>
     * Create the view, controller, tracker, frame, JPanel
     */
    public Game() {
        level = new Level1(this);
        level.start();
        level.populate(this);

        // make a view
        view = new GameView(this, 1000, 1000, level.getPlayer());
        view.setZoom(5);
        ShootController sc = new ShootController(view, level.getPlayer());
        view.addMouseListener(sc);
        view.addMouseMotionListener(sc);

        // controller
        controller = new PlayerController (level.getPlayer(), level, this);
        view.addKeyListener(controller);

        // mouse
        view.addMouseListener(new GiveFocus(view));   // give keyboard focus whenever the mouse enters the view
        view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));    // set coordinates relative to the view

        // tracker
        tracker = new Tracker(view, level.getPlayer());
        level.addStepListener(tracker);

        // frame
        frame = new JFrame("CyberCity");  // adds the view to a frame
        frame.add(view, BorderLayout.CENTER);

        // make frame
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(1000, 445));
        ImageIcon icon = new ImageIcon("data/diamond.gif");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // enable the frame to quit the application when the x button is pressed
        frame.setLocationByPlatform(true);
        frame.setResizable(false);   // don't let the frame be resized
        frame.pack();   // size the frame to fit the world view
        frame.setVisible(true);  // make the frame visible

        // make panel
        JPanel menuPanel= new JPanel();
        menuPanel.setBackground(Color.black);
        menu = new JLabel("MENU");
        menu.setHorizontalTextPosition(JLabel.CENTER);
        menu.setVerticalTextPosition(JLabel.CENTER);
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 33));
        menuPanel.add(menu, BorderLayout.CENTER);
        menu.setText("<html>" + "<body width='170'>" + "MENU" + "</html>");
        volumeDown = new JButton("VOL - ");
        volumeDown.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        volumeDown.setFocusable(false);
        volumeDown.addActionListener(this);
        menuPanel.add(volumeDown);
        volumeUp = new JButton("VOL +");
        volumeUp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        volumeUp.setFocusable(false);
        volumeUp.addActionListener(this);
        menuPanel.add(volumeUp);
        stopM = new JButton("MUTE");
        stopM.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        stopM.setFocusable(false);
        stopM.addActionListener( this);
        menuPanel.add(stopM);
        resumeM = new JButton("UNMUTE");
        resumeM.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        resumeM.setFocusable(false);
        resumeM.addActionListener(this);
        menuPanel.add(resumeM);

        // make panel with levels
        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.black);
        levelPanel.setLayout(new GridLayout(2, 2));
        lev1 = new JButton("LEVEL 1");
        lev1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lev1.setFocusable(false);
        lev1.addActionListener(this);
        levelPanel.add(lev1);
        lev2 = new JButton("LEVEL 2");
        lev2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lev2.setFocusable(false);
        lev2.addActionListener(this);
        levelPanel.add(lev2);
        lev3 = new JButton("LEVEL 3");
        lev3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lev3.setFocusable(false);
        lev3.addActionListener(this);
        levelPanel.add(lev3);
        lev4 = new JButton("LEVEL 4");
        lev4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lev4.setFocusable(false);
        lev4.addActionListener(this);
        levelPanel.add(lev4);

        // make panel with game actions
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Color.black);
        stop = new JButton("PAUSE");
        stop.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        stop.setFocusable(false);
        stop.addActionListener(this);
        actionPanel.add(stop);
        start = new JButton("RESUME");
        start.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        start.setFocusable(false);
        start.addActionListener(this);
        actionPanel.add(start);
        exit = new JButton("QUIT");
        exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        exit.setFocusable(false);
        exit.addActionListener(this);
        actionPanel.add(exit);
        save = new JButton("SAVE");
        save.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        save.setFocusable(false);
        save.addActionListener(this);
        actionPanel.add(save);
        load = new JButton("LOAD");
        load.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        load.setFocusable(false);
        load.addActionListener(this);
        actionPanel.add(load);

        JPanel miniviewPanel = new JPanel();
        miniviewPanel.setBackground(Color.black);
        userView = new UserView(level, 200, 170);
        userView.setZoom(1);
        level.addStepListener(new MiniTracker(userView, level.getPlayer()));
        miniviewPanel.add(userView);

        // add all panels together
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.black);
        panel2.setLayout(new GridLayout(4, 3));
        panel2.setPreferredSize(new Dimension(210, 20));
        panel2.add(menuPanel);
        panel2.add(levelPanel);
        panel2.add(actionPanel);
        panel2.add(miniviewPanel);

        // add all components to frame
        frame.add(panel2, BorderLayout.WEST);
        frame.add(view, BorderLayout.CENTER);
    }

    /**
     * Action for GUI buttons
     * <p>
     * Buttons for levels, sound, pause, resume, quit, save and load
     * @param  e action event
     */
   @Override
    public void actionPerformed(ActionEvent e) {
       Player player = level.getPlayer();

       // buttons
       if (e.getSource() == volumeDown) {
           level.volumeControl((float) -0.1);
       } else if (e.getSource() == volumeUp) {
           level.volumeControl((float) 0.1);
       } else if (e.getSource() == stopM) {
           level.stopMusic();
       } else if (e.getSource() == resumeM) {
           level.resumeMusic();
       } else if (e.getSource() == lev1) {
           level.stop();
           level.stopMusic();
           level = new Level1(this);
           level.resumeMusic();
           level.populate(this);
           newLevel();
       } else if (e.getSource() == lev2) {
           level.stop();
           level.stopMusic();
           level = new Level2(this);
           level.resumeMusic();
           level.populate(this);
           newLevel();
       } else if (e.getSource() == lev3) {
           level.stop();
           level.stopMusic();
           level = new Level3(this);
           level.resumeMusic();
           level.populate(this);
           newLevel();
       } else if (e.getSource() == lev4) {
           level.stop();
           level.stopMusic();
           level = new Level4(this);
           level.resumeMusic();
           level.populate(this);
           newLevel();
       } else if (e.getSource() == stop) {
           level.stop();
       } else if (e.getSource() == start) {
           level.start();
       } else if (e.getSource() == exit) {
           System.exit(0);
       } else if (e.getSource() == save) {
           JFileChooser fc = new JFileChooser();
           fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
           fc.setCurrentDirectory(new File("data/saves"));
           int returnVal = fc.showSaveDialog(save);

           if (returnVal == JFileChooser.APPROVE_OPTION) {
               File file = fc.getSelectedFile();
               try {
                   GameSaverLoader.save(level, file + ".ser");
                   System.out.println("Saved");
               } catch (IOException i) {
                   i.printStackTrace();
               }
           } else {
               System.out.println("Not Saved");
           }
       } else if (e.getSource() == load) {
           try {
               GameSaverLoader.load(level, this, "data/save.txt");
               this.setLevel(level);
               System.out.println("Loaded");
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
       }
   }

    /**
     * Update the view, controller and add tracker when using GUI levels buttons
     * <p>
     */
    // for levels buttons
    private void newLevel() {
        //add tracker
        tracker.updatePlayer(level.getPlayer());
        level.addStepListener(new Tracker(view, level.getPlayer()));
        level.addStepListener(new MiniTracker(userView, level.getPlayer()));

        //update view
        view.setWorld(level);
        view.setZoom(5);
        userView.setWorld(level);
        userView.setZoom(1);

        // update controller
        tracker.updatePlayer(level.getPlayer());
        view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));
        view.addMouseListener(new GiveFocus(view));
        ShootController sc = new ShootController(view, level.getPlayer());
        view.addMouseListener(sc);
        view.addMouseMotionListener(sc);

        // start new level
        level.start();
    }

    /**
     * Accessor for GameLevel
     * <p>
     * @return current level
     */
    public GameLevel getLevel() {
        return level;
    }

    /**
     * Update Method for LOAD STATE
     * <p>
     * When Loading set view, tracker, controller
     * @param  level name of the level
     */
    // for load state
    public void setLevel(GameLevel level) {
        this.level.stop();
        this.level.stopMusic();
        this.level = level;
        view.setWorld(this.level);
        view.setZoom(5);
        userView.setWorld(this.level);
        userView.setZoom(1);
        Tracker tracker = new Tracker(view, level.getPlayer());
        level.addStepListener(tracker);
        level.addStepListener(new MiniTracker(userView, level.getPlayer()));
        view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));
        view.addMouseListener(new GiveFocus(view));
        ShootController sc = new ShootController(view, level.getPlayer());
        view.addMouseListener(sc);
        view.addMouseMotionListener(sc);
        this.level.start();
   }

    /**
     * Advance to the next level of the game.
     * <p>
     * When progressing to the next level set the view, controller, tracker
     */
    public void goToNextLevel() {

        if (level instanceof Level1) {
            level.stop();
            level.stopMusic();
            level = new Level2(this);
            level.populate(this);
            view.setWorld(level);
            view.setZoom(5);
            userView.setWorld(level);
            userView.setZoom(1);
            view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));
            view.addMouseListener(new GiveFocus(view));
            ShootController sc = new ShootController(view, level.getPlayer());
            view.addMouseListener(sc);
            view.addMouseMotionListener(sc);
            Tracker tracker = new Tracker(view, level.getPlayer());
            tracker.updatePlayer(level.getPlayer());
            level.addStepListener(tracker);
            level.addStepListener(new MiniTracker(userView, level.getPlayer()));
            level.start();
        } else if (level instanceof Level2) {
            level.stop();
            level.stopMusic();
            level = new Level3(this);
            level.populate(this);
            view.setWorld(level);
            view.setZoom(5);
            userView.setWorld(level);
            userView.setZoom(1);
            view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));
            view.addMouseListener(new GiveFocus(view));
            ShootController sc = new ShootController(view, level.getPlayer());
            view.addMouseListener(sc);
            view.addMouseMotionListener(sc);
            Tracker tracker = new Tracker(view, level.getPlayer());
            level.addStepListener(tracker);
            level.addStepListener(new MiniTracker(userView, level.getPlayer()));
            level.start();
        } else if (level instanceof Level3) {
            level.stop();
            level.stopMusic();
            level = new Level4(this);
            level.populate(this);
            view.setWorld(level);
            view.setZoom(5);
            userView.setWorld(level);
            userView.setZoom(1);
            view.addMouseListener(new MouseHandler(level, view, this, level.getPlayer()));
            view.addMouseListener(new GiveFocus(view));
            ShootController sc = new ShootController(view, level.getPlayer());
            view.addMouseListener(sc);
            view.addMouseMotionListener(sc);
           Tracker tracker = new Tracker(view, level.getPlayer());
            level.addStepListener(tracker);
            level.addStepListener(new MiniTracker(userView, level.getPlayer()));
            level.start();
        } else if (level instanceof Level4) {
            Tracker tracker = new Tracker(view, level.getPlayer());
            level.addStepListener(tracker);
            level.addStepListener(new MiniTracker(userView, level.getPlayer()));
            System.out.println("Game complete!");
            JDialog diaScore = new JDialog(frame, true);
            Highscore highscore = new Highscore(level);
            diaScore.getContentPane().add(highscore.getPanelScore());
            diaScore.pack();
            diaScore.setVisible(true);
            System.exit(0);
        }
    }

    /**
     * Run Game
     * <p>
     * @param args Array
     */
    public static void main(String[] args) {
        new Game();
    }
}
