package game.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.Point2D;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.Engine;
import game.controlling.Input;
import game.minigame.Minigame;

/**
 * A class for the view using the swing library.
 */
public class SwingView implements View {
    private static final int SIZE = 700;
    private final Engine controller;
    private final Input input;
    private List<Minigame> minigameList;
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
        this.minigameList = this.controller.getMinigameList();
        panel = new ArrayList<>();
        final MinigamePanel newMinigame = new FlappyPanel();
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
     * The method to render the view.
     */
    @Override
    public void render() {
        minigameList = controller.getMinigameList();
        if (controller.getMinigameList().size() > panel.size()) {
            final MinigamePanel newMinigame = new FlappyPanel();
            newMinigame.addKeyListener(newMinigame);
            frame.getContentPane().add(newMinigame);
            panel.add(newMinigame);
            frame.setVisible(true);
        }
        panel.forEach(p -> p.repaint());
    }

    /**
     * Method to display the game over screen.
     * 
     * @param points the score to display.
     */
    @Override
    public void renderGameOver(final Long points) {
        final JPanel endScreen = new JPanel(new FlowLayout());
        final JLabel pointsLabel = new JLabel(Long.toString(points));
        endScreen.add(pointsLabel);
        frame.setContentPane(endScreen);
        frame.repaint();
        frame.setVisible(true);

    }

    private class MinigamePanel extends JPanel implements KeyListener {

        private static final int ASPECT_WIDTH = 16;
        private static final int ASPECT_HEIGHT = 9;
        private static final double ASPECT_RATIO = ASPECT_WIDTH / (double) ASPECT_HEIGHT;
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
            final Graphics2D g2 = (Graphics2D) g;
            Point2D startingPoint;
            super.paintComponent(g);

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2f));
            startingPoint = getStartingPoint();
            g2.drawRect((int) startingPoint.getX(), (int) startingPoint.getY(), boxWidth(), boxHeight());

            final Drawings d = new SwingDrawings(g2, startingPoint, boxHeight());
            if (!minigameList.isEmpty()) {
                minigameList.get(panel.indexOf(this)).getGameObjects().stream().forEach(o -> o.updateAspect(d));
            }
            // gets and paints only gameobjects of the same minigame index as the panel
        }

        private Point2D getStartingPoint() {
            if (isLarger()) {
                return new Point2D((panel.get(0).getWidth() - panel.get(0).getHeight() * ASPECT_RATIO) / 2, 0);
            }
            return new Point2D(0, (panel.get(0).getHeight() - panel.get(0).getWidth() / ASPECT_RATIO) / 2);
        }

        /**
         * Method to get the width of the canvas.
         *
         * @return the width of the canvas
         */
        private int boxWidth() {
            if (isLarger()) {
                return (int) (panel.get(0).getHeight() * ASPECT_RATIO);
            }
            return panel.get(0).getWidth();
        }

        /**
         * Method to get the height of the canvas.
         *
         * @return the height of the canvas
         */
        private int boxHeight() {
            if (isLarger()) {
                return panel.get(0).getHeight();
            }
            return (int) (panel.get(0).getWidth() / ASPECT_RATIO);
        }

        /**
         * Method to determine if the frame is larger than taller.
         *
         * @return whether the frame is larger
         */
        private boolean isLarger() {
            return panel.get(0).getHeight() / ASPECT_HEIGHT < panel.get(0).getWidth() / ASPECT_WIDTH;
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

    private class FlappyPanel extends MinigamePanel {
        private static final int SPACEBAR = 32;
        private static final long serialVersionUID = 2L;

        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == SPACEBAR) {
                input.setMoveUp(true);
                input.setMoveDown(false);
            }
        }

        @Override
        public void keyTyped(final KeyEvent e) {
        }

        @Override
        public void keyReleased(final KeyEvent e) {
            if (e.getKeyCode() == SPACEBAR) {
                input.setMoveUp(false);
                input.setMoveDown(true);
            }
        }
    }
}
