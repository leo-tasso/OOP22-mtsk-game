package game.view.javafx.viewstate;

import java.util.ArrayList;
import java.util.List;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class to model the menu state.
 */
public final class ViewStateMenu implements ViewState {
    private static final int FONT_SIZE = 24;
    private static final int BUTTON_SPACING = 10;
    private final Scene menuScene;

    /**
     * Constructor to initialize the state.
     * 
     * @param jview the {@link jview}.
     * @param stage the {@link stage} that will be used to display.
     */
    public ViewStateMenu(final JavaFxViewCoordinator jview, final Stage stage) {
        final ImageView logo = new ImageView(new Image(this.getClass().getResourceAsStream("/Title.png")));
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(stage.widthProperty().divide(2));
        logo.fitHeightProperty().bind(stage.heightProperty().divide(2));
        final List<Button> buttons = new ArrayList<>();
        final ButtonFactory bf = new ButtonFactory(FONT_SIZE);
        buttons.add(
                bf.create(stage, "Play", e -> Platform.runLater(() -> new GameStateImpl(jview).display(jview, stage))));
        buttons.add(bf.create(stage, "Stats", e -> Platform.runLater(null))); // TODO to be implemented, goes to stats
                                                                              // screen
        buttons.add(bf.create(stage, "Exit", e -> Platform.runLater(() -> Platform.exit())));

        final VBox root = new VBox(BUTTON_SPACING, logo);
        root.getChildren().addAll(buttons);
        root.setAlignment(Pos.CENTER);
        root.setBackground(null);
        menuScene = new Scene(root, stage.getWidth(), stage.getHeight());
        menuScene.setFill(Color.WHITE);
        final BackgroundImage backgroundImage = new BackgroundImage(
                new Image(this.getClass().getResourceAsStream("/Pattern.png")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        final Background background = new Background(backgroundImage);
        root.setBackground(background);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator jView, final Stage stage) {
        stage.setScene(menuScene);

    }

}
