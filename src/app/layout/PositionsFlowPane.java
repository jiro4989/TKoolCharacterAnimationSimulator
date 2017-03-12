package app.layout;

import jiro.java.util.MyProperties;

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

    final String DEF_W = "48";

    MyProperties mp = new MyProperties("./preset/mv_chara_chip.preset");
    mp.load();

    // TODO TEST VALUE
    int width     = Integer . parseInt(mp . getProperty("chara.width")  . orElse(DEF_W));
    int height    = Integer . parseInt(mp . getProperty("chara.height") . orElse(DEF_W));
    int row       = Integer . parseInt(mp . getProperty("row")          . orElse("4"));
    int column    = Integer . parseInt(mp . getProperty("column")       . orElse("3"));
    int animCount = Integer . parseInt(mp . getProperty("anim.count")   . orElse("3"));

    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();

    IntStream.range(0, row).forEach(r -> {

      IntStream.range(0, column).forEach(c -> {

        int x = c * width;
        int y = r * height;

        MyImage trimmedImage = new MyImage.Builder(originalImage)
          .x(x) .y(y)
          .width(width) .height(height)
          .build();

        // TODO
        trimmedImages.add(trimmedImage);

        if (c == 0)
          getChildren().add(new CharaChipGridPane(trimmedImage.getImage()));

      });

    });
  }//}}}

}
