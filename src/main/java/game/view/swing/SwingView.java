package game.view.swing;

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
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import api.ColorRGB;
import api.Point2D;
import game.Controller;
import game.ControllerImpl;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.GameObject;
import game.view.Drawings;
import game.view.View;

/**
 * A class for the view using the swing library.
 */
// @Deprecated
public class SwingView implements View {
    private static final int SIZE = 1000;
    private static final List<ColorRGB> BACKGROUND_COLORS = List.of(ColorRGB.orange(), ColorRGB.aqua(), ColorRGB.blue(),
            ColorRGB.green());
    private boolean fullScreen = true;
    private final Input input;
    private final List<MinigamePanel> panelList;
    private final JFrame frame;
    private final Controller controller;

    /**
     * A constructor for the window view of the game.
     */
    public SwingView() {
        this.input = new KeyboardInput();
        panelList = new ArrayList<>();
        final MinigamePanel newMinigame = new MinigamePanel();
        newMinigame.addKeyListener(newMinigame);
        newMinigame.setBackground(swingColor(BACKGROUND_COLORS.get(0)));
        panelList.add(newMinigame);
        frame = new JFrame("MTSK-Game");
        frame.setLayout(new GridLayout(1, 2));
        frame.getContentPane().setBackground(swingColor(BACKGROUND_COLORS.get(0)));
        frame.getContentPane().add(panelList.get(0));
        frame.setSize(SIZE, SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (fullScreen) {
            frame.setUndecorated(true);
        }
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        controller = new ControllerImpl(this);
        controller.startGame();
    }

    /**
     * Translates a ColorRGB into a Swing Color.
     * 
     * @param colorRGB the input color
     * @return the output Swing.Color
     */
    private Color swingColor(final ColorRGB colorRGB) {
        return new Color(colorRGB.getRed(), colorRGB.getGreen(), colorRGB.getBlue());
    }

    /**
     * The method to render the view.
     * 
     * @param objectsList a list of list of gameObjects for each minigame.
     */
    @Override
    public void render(final List<List<GameObject>> objectsList) {
        if (objectsList.size() > panelList.size()) {
            final MinigamePanel newMinigame = new MinigamePanel();
            newMinigame.setBackground(
                    swingColor(BACKGROUND_COLORS.size() > panelList.size() ? BACKGROUND_COLORS.get(panelList.size())
                            : BACKGROUND_COLORS.get(BACKGROUND_COLORS.size() - 1)));
            frame.getContentPane().add(newMinigame);
            panelList.add(newMinigame);
            if (panelList.size() > 2) {
                frame.setLayout(new GridLayout(2, 2));
            }
            frame.setVisible(true);
        }

        panelList.forEach(p -> {
            p.setObjectsList(objectsList.get(panelList.indexOf(p))); // Passes to the panel the list of object relative
                                                                     // to its index
            p.repaint();
        });
    }

    /**
     * Method to display the game over screen.
     * 
     * @param points the score to display.
     */
    @Override
    public void renderGameOver(final Long points) {
        final JPanel endScreen = new JPanel(new FlowLayout());
        final JLabel pointsLabel = new JLabel("You scored:" + Long.toString(points));
        final JButton again = new JButton("Play Again");
        again.addActionListener(a -> {
            new Thread(() -> new ControllerImpl(this).startGame()).start();
            frame.dispose();
        });
        final JButton exit = new JButton("Exit");
        exit.addActionListener(a -> frame.dispose());
        endScreen.add(pointsLabel);
        endScreen.add(again);
        endScreen.add(exit);
        frame.setContentPane(endScreen);
        frame.repaint();
        frame.setVisible(true);

    }

    /**
     * Getter to check if view is set to full screen.
     * 
     * @return if view is set to full screen.
     */
    public boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Method to toggle between full screen and windowed.
     */
    public void toggleFullScreen() {
        this.fullScreen = !this.fullScreen;
        frame.dispose();
        frame.setUndecorated(this.fullScreen);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private class MinigamePanel extends JPanel implements KeyListener {

        private static final int ASPECT_WIDTH = 16;
        private static final int ASPECT_HEIGHT = 9;
        private static final double ASPECT_RATIO = ASPECT_WIDTH / (double) ASPECT_HEIGHT;
        private static final int ONE_BUTTON = 49;
        private static final int NINE_BUTTON = 57;
        private static final int KEYCODE_NUM_DIFF = 48;
        private static final int A_BUTTON = 65;
        private static final int D_BUTTON = 68;
        private static final int S_BUTTON = 83;
        private static final int W_BUTTON = 87;
        private static final int F_BUTTON = 70;
        private static final long serialVersionUID = 1L;
        private List<GameObject> objectsList = new ArrayList<>();

        private void setObjectsList(final List<GameObject> objectsList) {
            this.objectsList = new ArrayList<>(objectsList);
        }

        MinigamePanel() {
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        @Override
        public void paintComponent(final Graphics g) {
            try {
                final Graphics2D g2 = (Graphics2D) g;

                Point2D startingPoint;
                super.paintComponent(g);

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2f));
                startingPoint = getStartingPoint();
                g2.drawRect((int) startingPoint.getX(), (int) startingPoint.getY(), boxWidth(), boxHeight());

                final Drawings d = new SwingDrawings(g2, startingPoint, boxHeight(), controller.getFieldHeight());
                if (!objectsList.isEmpty()) {
                    objectsList.forEach(o -> o.updateAspect(d));
                }
                // gets and paints only gameobjects of the same minigame index as the panel
            } catch (ClassCastException e) {
                frame.setVisible(false); // TODO add quit method to engine
                frame.dispose();
            }
        }

        private Point2D getStartingPoint() {
            if (isLarger()) {
                return new Point2D((panelList.get(0).getWidth() - panelList.get(0).getHeight() * ASPECT_RATIO) / 2, 0);
            }
            return new Point2D(0, (panelList.get(0).getHeight() - panelList.get(0).getWidth() / ASPECT_RATIO) / 2);
        }

        /**
         * Method to get the width of the canvas.
         *
         * @return the width of the canvas
         */
        private int boxWidth() {
            if (isLarger()) {
                return (int) (panelList.get(0).getHeight() * ASPECT_RATIO);
            }
            return panelList.get(0).getWidth();
        }

        /**
         * Method to get the height of the canvas.
         *
         * @return the height of the canvas
         */
        private int boxHeight() {
            if (isLarger()) {
                return panelList.get(0).getHeight();
            }
            return (int) (panelList.get(0).getWidth() / ASPECT_RATIO);
        }

        /**
         * Method to determine if the frame is larger than taller.
         *
         * @return whether the frame is larger
         */
        private boolean isLarger() {
            return panelList.get(0).getHeight() / ASPECT_HEIGHT < panelList.get(0).getWidth() / ASPECT_WIDTH;
        }

        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == W_BUTTON) {
                input.setMoveUp(true);
            } else if (e.getKeyCode() == S_BUTTON) {
                input.setMoveDown(true);
            } else if (e.getKeyCode() == D_BUTTON) {
                input.setMoveRight(true);
            } else if (e.getKeyCode() == A_BUTTON) {
                input.setMoveLeft(true);
            } else if (e.getKeyCode() >= ONE_BUTTON && e.getKeyCode() <= NINE_BUTTON) {
                input.setNumberPressed(Optional.of(e.getKeyCode() - KEYCODE_NUM_DIFF));
            }

        }

        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyReleased(final KeyEvent e) {
            if (e.getKeyCode() == W_BUTTON) {
                input.setMoveUp(false);
            } else if (e.getKeyCode() == S_BUTTON) {
                input.setMoveDown(false);
            } else if (e.getKeyCode() == D_BUTTON) {
                input.setMoveRight(false);
            } else if (e.getKeyCode() == A_BUTTON) {
                input.setMoveLeft(false);
            } else if (e.getKeyCode() == F_BUTTON) {
                toggleFullScreen();
            } else if (e.getKeyCode() >= ONE_BUTTON && e.getKeyCode() <= NINE_BUTTON) {
                input.setNumberPressed(Optional.empty());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String tutorial) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(frame, tutorial, "Instructions", JOptionPane.INFORMATION_MESSAGE);
                panelList.get(0).requestFocusInWindow();
                controller.setPaused(false);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewActive() {
        return frame.isShowing();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Input getInput() {
        return input.clone();
    }
}
