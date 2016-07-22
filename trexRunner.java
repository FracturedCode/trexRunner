import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class trexRunner extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage gameStage) {
		gameStage.setTitle("T-Rex Runner");

		Group root = new Group();
		Scene gameScene = new Scene(root);
		gameStage.setScene(gameScene);

		Canvas gameCanvas = new Canvas(800, 230);
		root.getChildren().add(gameCanvas);

		String ground = "________________________________________________________________";

		GraphicsContext gc = gameCanvas.getGraphicsContext2D();

		gc.setFill(Color.BLACK);
		gc.setLineWidth(2);
		Font gameFont = Font.font("Times New Roman", FontWeight.LIGHT, 20);
		gc.setFont(gameFont);
		gc.fillText(ground, 80, 160);

		Image trex = new Image("trex.png");
		Image cactus = new Image("cactus.png");
		gc.drawImage(trex, 80, 100);
		gc.drawImage(cactus, 800, 100);

		new AnimationTimer() {
			long lastNanoTime = System.nanoTime();
			int cactusX = 800;
			public void handle(long currentNanoTime) {
				long t = (currentNanoTime - lastNanoTime);
				while (t >= 10000000) {
					gc.clearRect(0, 0, 800, 230);
					gc.fillText(ground, 80, 160);
					gc.drawImage(trex, 80, 100);
					if (cactusX <= 0) {
						cactusX = 800;
					} else {
						cactusX = cactusX - 5;
					}
					gc.drawImage(cactus, cactusX, 110);
					lastNanoTime = System.nanoTime();
					t -= 16666666;
				}
			}
		}.start();

		gameStage.show();
	}
}