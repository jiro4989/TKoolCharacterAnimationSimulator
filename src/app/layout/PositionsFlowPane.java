package app.layout;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

public class PositionsFlowPane extends FlowPane {

  // コンストラクタ

  public PositionsFlowPane() {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("positions_flow_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

}
