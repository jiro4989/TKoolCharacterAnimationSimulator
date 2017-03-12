package app.charachip;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

public class CharaChipGridPane extends GridPane {

  @FXML private ImageView imageView;

  public CharaChipGridPane(Image img) {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("chara_chip_grid_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {

      loader.load();
      // TODO
      double width = img.getWidth();
      double height = img.getHeight();
      setPrefWidth(width);
      setPrefHeight(height);
      imageView.setFitWidth(width);
      imageView.setFitHeight(height);

      imageView.setImage(img);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
