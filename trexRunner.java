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
import java.util.ArrayList;

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

	    GraphicsContext gc = gameCanvas.getGraphicsContext2D();      
	    String ground = "________________________________________________________________";
	    gc.setFill(Color.BLACK);    
	    gc.setLineWidth(2);                        
	    Font gameFont = Font.font("Times New Roman", FontWeight.LIGHT, 20);
	    gc.setFont(gameFont);        

	    Image trex = new Image("trex.png");  
	    Image cactus = new Image("cactus.png");

	    ArrayList<String> input = new ArrayList<String>();

	    gameScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
 
        gameScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });

	    new AnimationTimer() {
		    long lastNanoTime = System.nanoTime();
		    int cactusX = 800;
		    int trexY = 100;
		    boolean trexPeak = false;
		    public void handle(long currentNanoTime) {
		        long t = (currentNanoTime - lastNanoTime);
		        if (t >= 16666666) {
		            while (t >= 16666666) {

		            	if (input.contains("SPACE") || input.contains("UP")) {
		            		if (!trexPeak && trexY>5) {
		            			trexY -= 5;
		            		} else if (!trexPeak && trexY<=5) {
		            			trexPeak = true;
		            		} else if (trexPeak && trexY<100) {
		            			trexY += 5;
		            		} else if (trexPeak && trexY>=100) {
		            			trexPeak = false;
		            		} else {
		            			System.out.println("Error!");
		            		}
		            	} else {
		            		trexY = 100;
		            	}

		                if (cactusX <= 30) {
					        cactusX = 700;
					    } else {
					        cactusX -= 5;
					    }
					    t -= 16666666;
		            }
		            gc.clearRect(0, 0, 800, 230);
		            gc.fillText(ground, 80, 160);
			    	gc.drawImage(trex, 80, trexY);
		            gc.drawImage(cactus, cactusX, 110);
		            lastNanoTime = System.nanoTime();
		        }
		    }
		}.start();

	    gameStage.show();
	}
}