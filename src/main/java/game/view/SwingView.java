package game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import api.Input;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.Engine;
import game.minigame.Minigame;

/**
 * A class for the view using the swing library.
 */
public class SwingView implements View {
    private static final int SIZE = 700;
    private final Engine controller;
    private final Input input;
    private List<Minigame> l;
    private final List<JPanel> panel;
    private final JFrame frame;

    /**
     * A constructor for the window view of the game.
     * 
     * @param controller the main engine
     * @param input      the input list to update
     */
    @SuppressFBWarnings
    public SwingView(final Engine controller, final Input input) {
        this.input = input;
        this.controller = controller;
        this.l = this.controller.getMinigameList();
        panel = new ArrayList<>();
        final MinigamePanel newMinigame = new MinigamePanel();
        newMinigame.addKeyListener(newMinigame);
        newMinigame.setBackground(Color.ORANGE);
        panel.add(newMinigame);
        frame = new JFrame("MTSK-Game");
        frame.setLayout(new GridLayout(1, 2));
        frame.getContentPane().add(panel.get(0));
        frame.setSize(SIZE, SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * A method to render the view.
     */
    @Override
    public void render() {
        l = controller.getMinigameList();
        if (controller.getMinigameList().size() > panel.size()) {
            final MinigamePanel newMinigame = new MinigamePanel();
            newMinigame.addKeyListener(newMinigame);
            frame.getContentPane().add(newMinigame);
            panel.add(newMinigame);
            frame.setVisible(true);
        }
        panel.forEach(p -> p.repaint());
    }

    @Override
    public void renderGameOver() {

    }

    private class MinigamePanel extends JPanel implements KeyListener {

        private static final int LEFT_BUTTON = 65;
        private static final int RIGHT_BUTTON = 68;
        private static final int DOWN_BUTTON = 83;
        private static final int UP_BUTTON = 87;
        private static final long serialVersionUID = 1L;

        MinigamePanel() {
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        @SuppressFBWarnings
        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final Drawings d = new DrawingsImpl((Graphics2D) g);
            // gets and paints
            // only gameobjects
            // of the same
            // minigame index as
            // the panel
            l.get(panel.indexOf(this)).getGameObjects().stream().forEach(o -> o.updateAspect(d));
        }

        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == UP_BUTTON) {
                input.setMoveUp(true);
            } else if (e.getKeyCode() == DOWN_BUTTON) {
                input.setMoveDown(true);
            } else if (e.getKeyCode() == RIGHT_BUTTON) {
                input.setMoveRight(true);
            } else if (e.getKeyCode() == LEFT_BUTTON) {
                input.setMoveLeft(true);
            }
        }

        @Override
        public void keyTyped(final KeyEvent e) {
        }

        @Override
        public void keyReleased(final KeyEvent e) {
            if (e.getKeyCode() == UP_BUTTON) {
                input.setMoveUp(false);
            } else if (e.getKeyCode() == DOWN_BUTTON) {
                input.setMoveDown(false);
            } else if (e.getKeyCode() == RIGHT_BUTTON) {
                input.setMoveRight(false);
            } else if (e.getKeyCode() == LEFT_BUTTON) {
                input.setMoveLeft(false);
            }
        }
    }
}
