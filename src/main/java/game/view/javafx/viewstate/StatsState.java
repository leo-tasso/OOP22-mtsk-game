package game.view.javafx.viewstate;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import api.RecordLoaderImpl;
import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsState implements ViewState {
    private static final int BUTTON_SPACING = 20;
    private VBox root;

    public StatsState(final JavaFxViewCoordinator view, final Stage stage) {
        view.setGameState(Optional.empty());
        final Button exit = new ButtonFactory(BUTTON_SPACING)
                .create(stage, "Back to Main Menu", e -> Platform.runLater(() -> new ViewStateMenu(view, stage).display(view, stage)));
        final Map<Timestamp, Long> scores = new RecordLoaderImpl().getRecords();
        final LineChart<Number, Number> plot = new LineChart<>(
                new NumberAxis(0, scores.entrySet().size() + 1, 1),
                new NumberAxis(0, 50_000, 1_000));
        plot.setTitle(scores.size() == 0 ? "No Records Found..." : "Records");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        final MiniCounter counter = new MiniCounter();
        scores.entrySet()
                .stream()
                .sorted((a,b) -> a.getKey().getTime() > b.getKey().getTime() ? 1 : -1)
                .forEach(x -> series.getData().add(new XYChart.Data<>(counter.getCount(), x.getValue())));
        plot.getData().add(series);
        root = new VBox(BUTTON_SPACING, plot, exit);
        root.setAlignment(Pos.CENTER);
    }

    @Override
    public void display(JavaFxViewCoordinator jView, Stage stage) {
        stage.getScene().setRoot(root);
    }

    private class MiniCounter {
        private int count = 1;

        public int getCount() {
            return count++;
        }
    }
}
