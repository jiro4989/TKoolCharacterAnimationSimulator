package app.layout;

import app.Main;
import app.image.MyImage;
import app.standard.Standards;
import app.charachip.CharaChipGridPane;

import java.io.IOException;
import java.util.stream.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

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

  public void drawImage(String filePath, Standards standards) {

    // TODO
    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    //Image trimmedImage = 

    IntStream.range(0, 4).forEach(r -> {

      IntStream.range(0, 3).forEach(c -> {

        // TODO
        //Image tileImage = new Image();

      });

    });
  }

}
