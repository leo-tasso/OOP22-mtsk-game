package game.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import api.Point2D;
import game.Engine;
import game.gameobject.Circle;
import game.gameobject.GameObject;
import game.minigame.Minigame;

/**
 * A class for the view using the swing library.
 */
public class SwingView implements View {
    private static final int SIZE = 700;
    private final Engine controller;
    private List<Minigame> l;
    private final List<JPanel> panel;
    private final JFrame frame;

    /**
     * A constructor for the window view of the game.
     * 
     * @param controller the controller
     */
    public SwingView(final Engine controller) {
        this.controller = controller;
        this.l = this.controller.getMinigameList();
        panel = new ArrayList<>();
        panel.add(new MinigamePanel());
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
            final JPanel newPanel = new MinigamePanel();
            frame.getContentPane().add(newPanel);
            panel.add(newPanel);
            frame.setVisible(true);
        }

        panel.forEach(p -> p.repaint());

    }

    @Override
    public void renderGameOver() {

    }

    private class MinigamePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paint(final Graphics g) {
            // l.stream().flatMap(m -> m.getGameObjects().stream()).forEach(o ->
            // this.paintObj(o, g));
            l.get(panel.indexOf(this)).getGameObjects().stream().forEach(o -> this.paintObj(o, g));
        }

        private void paintObj(final GameObject o, final Graphics g) {
            final Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());
            if (o instanceof Circle) {
                final Point2D pos = o.getCoor();
                final int y = (int) pos.getY();
                final int x = (int) pos.getX();
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(2f));
                final int rad = 15;
                g2.drawOval(x - rad, y - rad, rad * 2, rad * 2);
            }
        }
    }
}
