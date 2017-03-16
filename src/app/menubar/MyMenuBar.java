package app.menubar;

import jiro.javafx.stage.MyFileChooser;
import jiro.javafx.stage.AboutStage;

import app.MainController;
import app.standard.Standards;
import util.ResourceBundleWithUtf8;
import util.AlertUtils;
import static util.Texts.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyMenuBar extends VBox {

  private MainController mainController;
  private final MyFileChooser imagefileChooser;
  private final MyFileChooser walkPresetFileChooser;
  private final MyFileChooser sideViewPresetFileChooser;

  // FXMLコンポーネント{{{

  // ファイル
  @FXML private Menu     fileMenu;
  @FXML private MenuItem openCharaChipMenuItem;
  @FXML private MenuItem openSideViewMenuItem;
  @FXML private Menu     openRecentMenu;
  @FXML private MenuItem closeMenuItem;
  @FXML private MenuItem walkPresetMenuItem;
  @FXML private MenuItem sideViewPresetMenuItem;
  @FXML private MenuItem preferencesMenuItem;
  @FXML private MenuItem quitMenuItem;
  @FXML private MenuItem forcedTerminateMenuItem;

  // 表示
  @FXML private Menu          displayMenu;
  @FXML private MenuItem      previousMenuItem;
  @FXML private MenuItem      nextMenuItem;
  @FXML private MenuItem      zoomDownMenuItem;
  @FXML private MenuItem      zoomUpMenuItem;
  @FXML private MenuItem      durationDownMenuItem;
  @FXML private MenuItem      durationUpMenuItem;
  @FXML private MenuItem      onlyMenuItem;
  @FXML private CheckMenuItem alwaysOnTopMenuItem;

  // フォントサイズ変更メニュー
  @FXML private Menu          fontMenu;
  @FXML private ToggleGroup   fontGroup;
  @FXML private RadioMenuItem fontSize8RadioMenuItem;
  @FXML private RadioMenuItem fontSize9RadioMenuItem;
  @FXML private RadioMenuItem fontSize10RadioMenuItem;
  @FXML private RadioMenuItem fontSize11RadioMenuItem;
  @FXML private RadioMenuItem fontSize12RadioMenuItem;

  // 言語変更メニュー
  @FXML private Menu          langsMenu;
  @FXML private ToggleGroup   langGroup;
  @FXML private RadioMenuItem jpRadioMenuItem;
  @FXML private RadioMenuItem usRadioMenuItem;

  // ヘルプメニュー
  @FXML private Menu     helpMenu;
  @FXML private MenuItem aboutMenuItem;

  //}}}

  // コンストラクタ

  public MyMenuBar() {//{{{

    imagefileChooser  = new MyFileChooser.Builder("Image Files", "*.png").build();
    walkPresetFileChooser = new MyFileChooser.Builder("Preset Files", "*.preset")
      .initDir(WALK_PREST_DIR)
      .build();
    sideViewPresetFileChooser = new MyFileChooser.Builder("Preset Files", "*.preset")
      .initDir(SIDE_VIEW_PREST_DIR)
      .build();

    URL location = getClass().getResource("my_menu_bar.fxml");
    ResourceBundle resources = ResourceBundle.getBundle(
        "app.menubar.dict"
        , Locale.getDefault()
        , ResourceBundleWithUtf8.UTF8_ENCODING_CONTROL
        );
    FXMLLoader loader = new FXMLLoader(location, resources);

    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();

      fontSize8RadioMenuItem.setOnAction(e -> setFontSize(fontSize8RadioMenuItem));
      fontSize9RadioMenuItem.setOnAction(e -> setFontSize(fontSize9RadioMenuItem));
      fontSize10RadioMenuItem.setOnAction(e -> setFontSize(fontSize10RadioMenuItem));
      fontSize11RadioMenuItem.setOnAction(e -> setFontSize(fontSize11RadioMenuItem));
      fontSize12RadioMenuItem.setOnAction(e -> setFontSize(fontSize12RadioMenuItem));

      changeSelectedFontMenuItem();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }//}}}
  // FXMLイベント
  // ファイルメニュー
  @FXML private void openCharaChipMenuItemOnAction() {//{{{

    imagefileChooser.openFile().ifPresent(file -> {

      Standards std = mainController.getWalkStandards();
      TrimmingSelector ts = new TrimmingSelector(file, std);
      ts.showAndWait();

      setDisables(false);
      mainController.drawWalkImage(file);

    });

  }//}}}
  @FXML private void openSideViewMenuItemOnAction() {//{{{
    imagefileChooser.openFile().ifPresent(file -> {
      setDisables(false);
      mainController.drawSideViewImage(file);
    });
  }//}}}
  @FXML private void openRecentMenuItemOnAction() {//{{{
    imagefileChooser.openFile().ifPresent(file -> {
      // FIXME NullPointerexception 
      mainController.drawWalkImage(file);
    });
  }//}}}
  @FXML private void closeMenuItemOnAction() {//{{{
    mainController.clearImages();
    setDisables(true);
  }//}}}
  @FXML private void walkPresetMenuItemOnAction() {//{{{
    walkPresetFileChooser.openFile().ifPresent(file -> {

    });
  }//}}}
  @FXML private void sideViewPresetMenuItemOnAction() {//{{{
    sideViewPresetFileChooser.openFile().ifPresent(file -> {

    });
  }//}}}
  @FXML private void preferencesMenuItemOnAction() {//{{{
    mainController.showConfigStage();
  }//}}}
  @FXML private void quitMenuItemOnAction() {//{{{
    Platform.exit();
  }//}}}
  @FXML private void forcedTerminateMenuItemOnAction() {//{{{
    AlertUtils.showForcedTerminationAlert();
  }//}}}
  // 表示メニュー
  @FXML private void alwaysOnTopMenuItemOnAction() {//{{{
    mainController.changeAlwaysOnTop();
  }//}}}
  @FXML private void previousMenuItemOnAction() {//{{{
    mainController.showPreviousImage();
  }//}}}
  @FXML private void nextMenuItemOnAction() {//{{{
    mainController.showNextImage();
  }//}}}
  @FXML private void zoomDownMenuItemOnAction() {//{{{

    mainController.zoomDownImages();

  }//}}}
  @FXML private void zoomUpMenuItemOnAction() {//{{{

    mainController.zoomUpImages();

  }//}}}
  @FXML private void durationDownMenuItemOnAction() {//{{{
    mainController.durationDown();
  }//}}}
  @FXML private void durationUpMenuItemOnAction() {//{{{
    mainController.durationUp();
  }//}}}
  @FXML private void onlyMenuItemOnAction() {//{{{

    mainController.showOneImage();

  }//}}}
  // 言語メニュー
  @FXML private void jpRadioMenuItemOnAction() {//{{{
  }//}}}
  @FXML private void usRadioMenuItemOnAction() {//{{{
  }//}}}
  // ヘルプ
  @FXML private void aboutMenuItemOnAction() {//{{{
    AboutStage about = new AboutStage.Builder(TITLE, VERSION)
      .author("次郎 (Jiro)")
      .blog("次ログ")
      .blogUrl("http://jiroron666.hatenablog.com/")
      .css(BASIC_CSS)
      .appIcon(APP_ICON)
      .build();
    about.showAndWait();
  }//}}}
  // private メソッド
  private void setFontSize(RadioMenuItem rmi) {//{{{
    String fontSize = rmi.getText();
    mainController.setFontSize(fontSize);
  }//}}}
  private void setDisables(boolean disable) {//{{{

    onlyMenuItem         . setDisable(disable);
    previousMenuItem     . setDisable(disable);
    nextMenuItem         . setDisable(disable);
    zoomDownMenuItem     . setDisable(disable);
    zoomUpMenuItem       . setDisable(disable);
    durationDownMenuItem . setDisable(disable);
    durationUpMenuItem   . setDisable(disable);
    closeMenuItem        . setDisable(disable);

  }//}}}
  private void changeSelectedFontMenuItem() {//{{{
    String defaultLanguage = Locale.getDefault().getLanguage();
    String ja = Locale.JAPAN.getLanguage();

    if (defaultLanguage.equals(ja))
      jpRadioMenuItem.setSelected(true);
    else
      usRadioMenuItem.setSelected(true);
  }//}}}
  private void showLanguageAlert() {//{{{
    mainController.showLanguageAlert();
  }//}}}
  // Setter
  public void setMainController(MainController aMain) {//{{{
    mainController = aMain;
  }//}}}
}
