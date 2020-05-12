package fun.oook.cw.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class Launch extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setOpacity(0);
        primaryStage.show();

        final Stage secondaryStage = new Stage();
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        secondaryStage.initOwner(primaryStage);

        final Parent root = FXMLLoader.load(getClass().getResource("/fun/oook/cw/gui/widget/widget.fxml"));
        final Scene scene = new Scene(root);
        secondaryStage.setScene(scene);
        secondaryStage.show();

        // 定位到屏幕右上角
        final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        secondaryStage.setX(visualBounds.getMaxX() - 25 - scene.getWidth());
        secondaryStage.setY(visualBounds.getMinY() + 25);

        // 使部件能够使用鼠标拖动
        scene.setOnMousePressed(event -> {
            xOffset = secondaryStage.getX() - event.getScreenX();
            yOffset = secondaryStage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            secondaryStage.setX(event.getScreenX() + xOffset);
            secondaryStage.setY(event.getScreenY() + yOffset);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
