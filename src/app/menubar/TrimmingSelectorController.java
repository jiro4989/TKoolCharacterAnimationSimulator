package app.menubar;

import app.standard.Standards;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class TrimmingSelectorController {

  private int blockWidth = 0;
  private int blockHeight = 0;

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

    int x = (int) e.getX();
    int y = (int) e.getY();

    int gridX = x / blockWidth * blockWidth;
    int gridY = y / blockHeight * blockHeight;

    int imgWidth  = (int) imageView.getFitWidth();
    int imgHeight = (int) imageView.getFitHeight();

    if (   (gridX + blockWidth)  <= imgWidth
        && (gridY + blockHeight) <= imgHeight
       )
    {

      gridPane.setLayoutX(gridX);
      gridPane.setLayoutY(gridY);

    }

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

  void setStandards(Standards std) {//{{{

    int width  = std.width;
    int height = std.height;
    int row    = std.row;
    int column = std.column;

    blockWidth  = width * column;
    blockHeight = height * row;

  }//}}}

}
