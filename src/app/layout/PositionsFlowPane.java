package app.layout;

import app.Main;
import app.image.MyImage;
import app.standard.Standards;
import app.charachip.CharaChipGridPane;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class PositionsFlowPane extends FlowPane {

  private List<MyImage> trimmedImages = new ArrayList<>(12);

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

  public void drawImage(String filePath, Standards standards) {//{{{

    // TODO TEST VALUE
    int width = 48;
    int height = 48;

    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();

    IntStream.range(0, 4).forEach(row -> {

      IntStream.range(0, 3).forEach(column -> {

        int x = column * width;
        int y = row * height;

        MyImage trimmedImage = new MyImage.Builder(originalImage)
          .x(x) .y(y)
          .width(width) .height(height)
          .build();

        // TODO
        trimmedImages.add(trimmedImage);
        getChildren().add(new CharaChipGridPane(trimmedImage.getImage()));

      });

    });
  }//}}}

}
