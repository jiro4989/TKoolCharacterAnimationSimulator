package app.charachip;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;

public class CharaChipGridPane extends GridPane {

  private final Main main;
  private final int index;

  @FXML private ImageView imageView;

  public CharaChipGridPane(Main aMain, int anIndex) {//{{{

    main = aMain;
    index = anIndex;

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
