package app.charachip;

import app.Main;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.*;

public class CharaChipGridPane extends GridPane {

  private final Main main;
  private final int index;
  @FXML private ImageView imageView;

  public CharaChipGridPane(Main aMain, int anIndex) {
    main = aMain;
    index = anIndex;
  }

}
