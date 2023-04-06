package game.view.javafx.viewstate;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * State to display the statistics screen.
 */
public class StatsState implements ViewState {
    private static final int BUTTON_SPACING = 20;
    private static final double Y_OFFSET = 1_000d;
    private final VBox root;

    /**
     * Constructor to initialize the State.
     * 
     * @param view   the {@link JavaFxViewCoordinator} coordinator.
     * @param stage  the Stage that will be used to display.
     */
    public StatsState(final JavaFxViewCoordinator view, final Stage stage) {
        view.setGameState(Optional.empty());
        final Button exit = new ButtonFactoryImpl(BUTTON_SPACING)
                .create(stage,
                "Back to Main Menu",
                e -> Platform.runLater(() -> new ViewStateMenu(view, stage).display(view, stage)));
        final Map<Timestamp, Long> scores = view.getStats();
        final LineChart<Number, Number> plot = new LineChart<>(
                new NumberAxis(0, scores.entrySet().size() + 1, 1),
                new NumberAxis(0, getUpperBound(scores), Y_OFFSET));
        plot.setTitle(scores.isEmpty() ? "No Records Found..." : "Records");
        plot.setLegendVisible(false);
        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        final MiniCounter counter = new MiniCounter();
        scores.entrySet()
                .stream()
                .sorted((a, b) -> a.getKey().getTime() > b.getKey().getTime() ? 1 : -1)
                .forEach(x -> series.getData().add(new XYChart.Data<>(counter.getCount(), x.getValue())));
        plot.getData().add(series);
        root = new VBox(BUTTON_SPACING, plot, exit);
        root.setAlignment(Pos.CENTER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator jView, final Stage stage) {
        stage.getScene().setRoot(root);
    }

    private double getUpperBound(final Map<Timestamp, Long> scores) {
        return scores.isEmpty() ? Y_OFFSET : scores.entrySet().stream()
                .map(x -> x.getValue())
                .max((a, b) -> a > b ? 1 : -1)
                .get() + Y_OFFSET;
    }

    /**
     * A support class to count how many matches were played.
     */
    private static class MiniCounter {
        private int count = 1;

        public int getCount() {
            return count++;
        }
    }
}
