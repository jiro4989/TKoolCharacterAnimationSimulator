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

  private Optional<List<CharaChipGridPane>> charaChipOpt = Optional.empty();

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

  public void drawWalkImage(String filePath, Standards standards) {//{{{

    clear();
    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    charaChipOpt = Optional.ofNullable(originalImage.createWalkChips(standards, this));
    drawImages(charaChipOpt);

  }//}}}

  public void drawSideViewImage(String filePath, Standards standards) {//{{{

    clear();
    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    charaChipOpt = Optional.ofNullable(originalImage.createSideViewChips(standards, this));
    drawImages(charaChipOpt);

  }//}}}

  public void updateAnimationSpeed(int duration) {//{{{

    charaChipOpt.ifPresent(chips -> {
      chips.stream().forEach(c -> c.animate(duration));
    });

  }//}}}

  public void updateZoomRate(double rate) {//{{{

    charaChipOpt.ifPresent(chips -> {
      chips.stream().forEach(c -> c.setScale(rate));
    });

  }//}}}

  public void showSelectedPane(CharaChipGridPane selectedPane) {//{{{

    disableAllCharaChips();
    charaChipOpt.ifPresent(ccgpList -> {
      ccgpList.stream()
        .filter(ccgp -> ccgp == selectedPane)
        .forEach(ccgp -> {
          getChildren().add(ccgp);
        });
    });

  }//}}}

  public void showAllPane() {//{{{

    disableAllCharaChips();
    charaChipOpt.ifPresent(ccgpList -> {
      ccgpList.stream().forEach(ccgp -> {
        getChildren().add(ccgp);
      });
    });

  }//}}}

  public void showPreviousImage() {//{{{

    CharaChipGridPane first = (CharaChipGridPane) getChildren().get(0);
    charaChipOpt.ifPresent(ccgpList -> {

      int i = 0;
      for (CharaChipGridPane ccgp : ccgpList) {
        if (first == ccgp) {

          disableAllCharaChips();

          int lastIndex = ccgpList.size() - 1;
          int previousIndex = 0 < i ? i - 1 : lastIndex;
          getChildren().add(ccgpList.get(previousIndex));

          return;

        }

        i++;

      }

    });

  }//}}}

  public void showNextImage() {//{{{

    CharaChipGridPane first = (CharaChipGridPane) getChildren().get(0);
    charaChipOpt.ifPresent(ccgpList -> {

      int i = 0;
      for (CharaChipGridPane ccgp : ccgpList) {
        if (first == ccgp) {

          disableAllCharaChips();
          int nextIndex = i < ccgpList.size() - 1 ? i + 1 : 0;
          getChildren().add(ccgpList.get(nextIndex));
          return;

        }

        i++;

      }

    });

  }//}}}

  /**
   * トリミングした画像インスタンスから何から全部クリアする。
   */
  private void clear() {//{{{
    getChildren().clear();
    charaChipOpt = Optional.empty();
  }//}}}

  private void putCharaChips(List<CharaChipGridPane> charaChipOpt) {//{{{

    charaChipOpt.stream().forEach(i -> getChildren().add(i));

  }//}}}

  private void drawImages(Optional<List<CharaChipGridPane>> ccgpo) {//{{{

    ccgpo.ifPresent(chips -> {
      putCharaChips(chips);
      // TODO 値を決め打ちしている
      updateAnimationSpeed(100);
    });

  }//}}}

  /**
   * トリミングした画像インスタンスは保持し、Stage上に表示しないだけ。
   */
  private void disableAllCharaChips() {//{{{

    charaChipOpt.ifPresent(ccgpList -> {
      getChildren().removeAll(ccgpList);
    });

  }//}}}

}
