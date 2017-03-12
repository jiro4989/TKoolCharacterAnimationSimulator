package app.layout;

import app.Main;
import app.image.MyImage;
import app.standard.Standards;
import app.charachip.CharaChipGridPane;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import static java.util.stream.IntStream.range;

public class PositionsFlowPane extends FlowPane {

  private List<CharaChipGridPane> charaChips;

  // コンストラクタ

  public PositionsFlowPane() {//{{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("positions_flow_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {

      loader.load();
      ObservableList<Node> paneList = getChildren();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  public void drawImage(String filePath, Standards standards) {//{{{

    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    charaChips = createCharaChips(originalImage, standards);
    putCharaChips(charaChips);
    animateAll();

  }//}}}

  private List<CharaChipGridPane> createCharaChips(MyImage originalImage, Standards standards) {//{{{

    int row    = standards.animation.row;
    int column = standards.animation.column;
    int width  = standards.size.width;
    int height = standards.size.height;

    List<CharaChipGridPane> ccgpList = new ArrayList<>(row * column);

    range(0, row).forEach(r -> {

      List<MyImage> animationList = new ArrayList<>(column);

      range(0, column).forEach(c -> {

        int x = c * width;
        int y = r * height;

        MyImage trimmedImage = new MyImage.Builder(originalImage)
          .x(x) .y(y)
          .width(width) .height(height)
          .build();

        animationList.add(trimmedImage);

      });

      MyImage last = animationList.get(animationList.size()-1);
      MyImage copy = new MyImage.Builder(last).build();
      animationList.add(copy);
      CharaChipGridPane ccgp = new CharaChipGridPane(animationList);
      ccgpList.add(ccgp);

    });

    return ccgpList;

  }//}}}

  private void putCharaChips(List<CharaChipGridPane> charaChips) {//{{{

    charaChips.stream().forEach(i -> getChildren().add(i));

  }//}}}

  private void animateAll() {//{{{

    charaChips.stream().forEach(c -> c.animate());

  }//}}}

}
