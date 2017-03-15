package app;

import jiro.java.util.MyProperties;

import app.config.ConfigStage;
import app.layout.PositionsFlowPane;
import app.menubar.MyMenuBar;
import app.standard.Standards;
import util.AlertUtils;

import java.io.*;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController {

  // 画像の規格
  private Standards walkStandard;
  private Standards sideViewStandard;

  // 設定変更画面
  private Optional<ConfigStage> configStageOpt = Optional.empty();

  //private Timeline fileObserver;
  private FileObserver fileObserver;

  // FXMLコンポーネント//{{{

  @FXML private MyMenuBar myMenuBar;
  @FXML private PositionsFlowPane positionsFlowPane;

  //}}}

  // 初期化

  @FXML private void initialize() {//{{{

    myMenuBar.setMainController(this);

    fileObserver = new FileObserver(200, new File("./presets/mvccs/walk/mv.preset"));

    //fileObserver = createFileObserver(
    //    200, new File("./presets/mvccs/walk/mv.preset"));
    //fileObserver.setCycleCount(Timeline.INDEFINITE);
    //fileObserver.play();

  }//}}}

  /*
  private Timeline createFileObserver(int duration, File file) {//{{{

    Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(duration), e -> {
      long modifiedTime = file.lastModified();
      Date date = new Date(modifiedTime);
      System.out.println(date);
    }));

    return timeLine;

  }//}}}
  */

  // メソッド

  public void drawImage(File file) {//{{{

    MyProperties mp = new MyProperties("./presets/walk/mv.preset");
    mp.load();

    final String DEF_W = "48";

    // TODO TEST VALUE
    int width     = Integer . parseInt(mp . getProperty("chara.width")  . orElse(DEF_W));
    int height    = Integer . parseInt(mp . getProperty("chara.height") . orElse(DEF_W));
    int row       = Integer . parseInt(mp . getProperty("row")          . orElse("4"));
    int column    = Integer . parseInt(mp . getProperty("column")       . orElse("3"));

    walkStandard = new Standards.Builder(width, height)
      .row(row) .column(column)
      .build();

    String filePath = file.getPath();
    positionsFlowPane.drawWalkImage(filePath, walkStandard);

    myMenuBar.setDisableConfigMenuItem(false);

  }//}}}

  public void drawSideViewImage(File file) {//{{{

    MyProperties mp = new MyProperties("./presets/side_view/mv.preset");
    mp.load();

    //// TODO TEST VALUE
    int row        = Integer . parseInt(mp . getProperty("row")          . orElse("6"));
    int column     = Integer . parseInt(mp . getProperty("column")       . orElse("9"));
    int frameCount = Integer . parseInt(mp . getProperty("frameCount")   . orElse("3"));

    // サイドビューは歩行グラと規格が異なるため
    sideViewStandard = new Standards.Builder(1, 1)
      .row(row) .column(column) .frameCount(frameCount)
      .build();

    String filePath = file.getPath();
    positionsFlowPane.drawSideViewImage(filePath, sideViewStandard);

    myMenuBar.setDisableConfigMenuItem(false);

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
