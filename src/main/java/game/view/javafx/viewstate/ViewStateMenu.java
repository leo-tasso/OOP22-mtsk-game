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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class to model the menu state.
 */
public final class ViewStateMenu implements ViewState {
    private static final int FONT_SIZE = 24;
    private static final int BUTTON_SPACING = 10;
    private static final int ASPECT_WIDTH = 16;
    private static final int ASPECT_HEIGHT = 9;

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
        final Button playButton = new Button("Play");
        playButton.setOnAction(e -> Platform.runLater(() -> new GameStateImpl(jview).display(jview, stage)));
        buttons.add(playButton);

        final Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.runLater(() -> Platform.exit()));
        buttons.add(exitButton);

        buttons.forEach(b -> {
            b.prefWidthProperty().bind(stage.widthProperty().divide(ASPECT_HEIGHT));
            b.prefHeightProperty().bind(stage.heightProperty().divide(ASPECT_WIDTH));
            b.setFont(Font.font("futura", FONT_SIZE));
            b.setStyle("-fx-background-color: black; -fx-background-radius: 20px; -fx-text-fill: white;");
            b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: black;-fx-text-fill: white;"));
            b.setOnMouseExited(e -> b
                    .setStyle("-fx-background-color: black; -fx-background-radius: 20px; -fx-text-fill: white;"));
            b.setOnMousePressed(e -> b.setStyle("-fx-background-color: #3D3D3D; -fx-text-fill: white;"));
            b.setOnMouseReleased(e -> b.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
        });

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
