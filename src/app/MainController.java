package app;

import jiro.java.util.MyProperties;

import app.config.ConfigStage;
import app.layout.PositionsFlowPane;
import app.menubar.MyMenuBar;
import app.standard.Standards;
import util.AlertUtils;

import java.io.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController {

  // 画像描画戦略
  private DrawImageStrategy strategy;

  // 画像の規格
  private Standards walkStandard;
  private Standards sideViewStandard;

  // 設定変更画面
  private Optional<ConfigStage> configStageOpt = Optional.empty();

  // ファイルの更新時間監視
  private static FileObserver fileObserver;

  // FXMLコンポーネント//{{{

  @FXML private MyMenuBar myMenuBar;
  @FXML private PositionsFlowPane positionsFlowPane;

  //}}}

  // 初期化

  @FXML private void initialize() {//{{{

    myMenuBar.setMainController(this);
    walkStandard = WalkGraphicsStrategy.createStandard("./presets/mvccs/walk/mv.preset");
    sideViewStandard = SideViewStrategy.createStandard("./presets/mvccs/side_view/mv.preset");

  }//}}}

  // メソッド

  public void updateImages(File file) {//{{{
    strategy.drawImage(file);
  }//}}}

  public void drawWalkImage(File file) {//{{{

    if (fileObserver != null) fileObserver.stop();

    fileObserver = new FileObserver(200, file, this);
    strategy     = new WalkGraphicsStrategy(this);

    positionsFlowPane.drawWalkImage(file.getPath(), walkStandard);

    configStageOpt.ifPresent(cs -> {
      cs.applyZoomRate();
      cs.applyAnimationSpeed();
    });

  }//}}}

  public void drawSideViewImage(File file) {//{{{

    if (fileObserver != null) fileObserver.stop();

    fileObserver = new FileObserver(200, file, this);
    strategy     = new SideViewStrategy(this);

    positionsFlowPane.drawSideViewImage(file.getPath(), sideViewStandard);

    configStageOpt.ifPresent(cs -> {
      cs.applyZoomRate();
      cs.applyAnimationSpeed();
    });

  }//}}}

  public void clearImages() {//{{{
    positionsFlowPane.clearImages();
  }//}}}

  public void showConfigStage() {//{{{

    configStageOpt.ifPresent(c -> {
      if (c.isShowing())
        c.hide();
      else
        c.show();
    });

  }//}}}

  public void updateZoomRate(double zoom) {//{{{

    positionsFlowPane.updateZoomRate(zoom);

  }//}}}

  public void updateZoomRate(ScrollEvent e) {//{{{

    configStageOpt.ifPresent(cs -> {
      cs.changeZoomRate(e);
    });

  }//}}}

  public void updateAnimationSpeed(int duration) {//{{{

    positionsFlowPane.updateAnimationSpeed(duration);

  }//}}}

  public void changeAlwaysOnTop() {//{{{

    Stage stage = getStage();
    boolean alwaysOnTop = getStage().isAlwaysOnTop();
    stage.setAlwaysOnTop(!alwaysOnTop);

  }//}}}

  public void showLanguageAlert() {//{{{
    AlertUtils.showLanguageAlert();
  }//}}}

  public void showPreviousImage() {//{{{
    positionsFlowPane.showPreviousImage();
  }//}}}

  public void showNextImage() {//{{{
    positionsFlowPane.showNextImage();
  }//}}}

  public void zoomDownImages() {//{{{
    configStageOpt.ifPresent(cs -> cs.zoomDown());
  }//}}}

  public void zoomUpImages() {//{{{
    configStageOpt.ifPresent(cs -> cs.zoomUp());
  }//}}}

  public void durationDown() {//{{{
    configStageOpt.ifPresent(cs -> cs.durationDown());
  }//}}}

  public void durationUp() {//{{{
    configStageOpt.ifPresent(cs -> cs.durationUp());
  }//}}}

  public void showOneImage() {//{{{
    positionsFlowPane.switchViewerMode();
  }//}}}

  void setConfigStageInstance() {//{{{
    ConfigStage cs = new ConfigStage(positionsFlowPane, this);
    configStageOpt = Optional.ofNullable(cs);
  }//}}}

  void resizeConfigStage() {//{{{

    configStageOpt
      .ifPresent(c -> {
        c.resize(positionsFlowPane);
      });

  }//}}}

  private Stage getStage() {//{{{

    return (Stage) positionsFlowPane.getScene().getWindow();

  }//}}}

  // Setter

  public void setFontSize(String fontSize) {//{{{

    VBox root = (VBox) positionsFlowPane.getScene().lookup("#root");
    root.setStyle("-fx-font-size:" + fontSize + "pt;");

  }//}}}

}
