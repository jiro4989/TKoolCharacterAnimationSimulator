package app.menubar;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class TrimmingSelectorController {

  // FXML コンポーネント{{{

  @FXML private TitledPane titledPane;
  @FXML private ImageView imageView;
  @FXML private GridPane gridPane;

  @FXML private Button okButton;
  @FXML private Button cancelButton;

  //}}}

  // 初期化

  @FXML private void initialize() {//{{{
  }//}}}

  // イベントメソッド

  @FXML private void imageViewOnMouseDragOver(MouseEvent e) {//{{{

    double x = e.getX();
    double y = e.getY();

    System.out.println(String.format("x: %f, y: %f.", x, y));

  }//}}}

  // Setter

  void setImage(File file) {//{{{

    Image image   = new Image("file:" + file.getPath());
    double width  = image.getWidth();
    double height = image.getHeight();

    imageView.setImage(image);
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);

  }//}}}

}
