package app.charachip;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

public class CharaChipGridPane extends GridPane {

  @FXML private ImageView imageView;

  public CharaChipGridPane() {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("chara_chip_grid_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
