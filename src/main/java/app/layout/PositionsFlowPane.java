package app.layout;

import app.charachip.CharaChipGridPane;
import app.image.MyImage;
import app.standard.Standards;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class PositionsFlowPane extends FlowPane {

  private Optional<List<CharaChipGridPane>> charaChipOpt = Optional.empty();
  private boolean viewerMode = false;

  // コンストラクタ

  public PositionsFlowPane() { // {{{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("positions_flow_pane.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {

      loader.load();

    } catch (IOException e) {
      e.printStackTrace();
    }
  } // }}}

  public void drawWalkImage(String filePath, Standards standards) { // {{{

    clearImages();
    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    charaChipOpt = Optional.ofNullable(originalImage.createWalkChips(standards, this));
    drawImages(charaChipOpt);
  } // }}}

  public void drawSideViewImage(String filePath, Standards standards) { // {{{

    clearImages();
    MyImage originalImage = new MyImage.Builder("file:" + filePath).build();
    charaChipOpt = Optional.ofNullable(originalImage.createSideViewChips(standards, this));
    drawImages(charaChipOpt);
  } // }}}

  public void updateAnimationSpeed(int duration) { // {{{

    charaChipOpt.ifPresent(
        chips -> {
          chips.stream().forEach(c -> c.animate(duration));
        });
  } // }}}

  public void updateZoomRate(double rate) { // {{{

    charaChipOpt.ifPresent(
        chips -> {
          chips.stream().forEach(c -> c.setScale(rate));
        });
  } // }}}

  public void showPreviousImage() { // {{{

    CharaChipGridPane first = (CharaChipGridPane) getChildren().get(0);
    charaChipOpt.ifPresent(
        ccgpList -> {
          viewerMode = true;
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
  } // }}}

  public void showNextImage() { // {{{

    CharaChipGridPane first = (CharaChipGridPane) getChildren().get(0);
    charaChipOpt.ifPresent(
        ccgpList -> {
          viewerMode = true;
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
  } // }}}

  /** トリミングした画像インスタンスから何から全部クリアする。 */
  public void clearImages() { // {{{
    getChildren().clear();
    charaChipOpt = Optional.empty();
    viewerMode = false;
  } // }}}

  /** {@link MainController}から呼び出されるメソッド。 表示しているパネルの先頭の要素を表示する。 */
  public void switchViewerMode() { // {{{

    charaChipOpt.ifPresent(
        ccgpList -> {
          if (!viewerMode) {
            viewerMode = true;
            CharaChipGridPane first = ccgpList.get(0);
            disableAllCharaChips();
            getChildren().add(first);
            return;
          }
          showAllPane();
        });
  } // }}}

  /** {@link CharaChipGridPane}から呼ばれるメソッド。 渡されたインスタンスのパネル以外を表示しなくする。 */
  public void switchViewerMode(CharaChipGridPane selectedPane) { // {{{
    if (!viewerMode) {
      viewerMode = true;
      showSelectedPane(selectedPane);
      return;
    }
    showAllPane();
  } // }}}

  private void drawImages(Optional<List<CharaChipGridPane>> ccgpo) { // {{{

    ccgpo.ifPresent(
        chips -> {
          chips.stream().forEach(i -> getChildren().add(i));
        });
  } // }}}

  /** トリミングした画像インスタンスは保持し、Stage上に表示しないだけ。 */
  private void disableAllCharaChips() { // {{{

    charaChipOpt.ifPresent(
        ccgpList -> {
          getChildren().removeAll(ccgpList);
        });
  } // }}}

  private void showSelectedPane(CharaChipGridPane selectedPane) { // {{{

    disableAllCharaChips();
    charaChipOpt.ifPresent(
        ccgpList -> {
          ccgpList
              .stream()
              .filter(ccgp -> ccgp == selectedPane)
              .forEach(
                  ccgp -> {
                    getChildren().add(ccgp);
                  });
        });
  } // }}}

  private void showAllPane() { // {{{

    disableAllCharaChips();
    charaChipOpt.ifPresent(
        ccgpList -> {
          viewerMode = false;
          ccgpList
              .stream()
              .forEach(
                  ccgp -> {
                    getChildren().add(ccgp);
                  });
        });
  } // }}}
}
