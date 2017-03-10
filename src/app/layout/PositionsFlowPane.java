package app.layout;

import app.Main;
import app.charachip.CharaChipGridPane;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class PositionsFlowPane extends FlowPane {

  // コンストラクタ

  public PositionsFlowPane() {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("positions_flow_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());
    getChildren().add(new CharaChipGridPane());

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
