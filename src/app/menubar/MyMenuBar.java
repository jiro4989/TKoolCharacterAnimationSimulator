package app.menubar;

import jiro.javafx.stage.MyFileChooser;

import app.MainController;
import util.ResourceBundleWithUtf8;

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
  private final MyFileChooser mfc;

  // FXMLコンポーネント{{{

  // ファイル
  @FXML private Menu     fileMenu;
  @FXML private MenuItem openCharaChipMenuItem;
  @FXML private MenuItem openSideViewMenuItem;
  @FXML private Menu     openRecentMenu;
  @FXML private MenuItem closeMenuItem;
  @FXML private MenuItem quitMenuItem;
  @FXML private MenuItem forcedTerminateMenuItem;

  // 表示
  @FXML private Menu          displayMenu;
  @FXML private CheckMenuItem alwaysOnTopMenuItem;
  @FXML private CheckMenuItem disableMenuBarMenuItem;
  @FXML private CheckMenuItem displayConfigMenuItem;

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

    mfc = new MyFileChooser.Builder("Image Files", "*.png").build();

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
    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  // FXMLイベント

  // ファイルメニュー

  @FXML private void openCharaChipMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      // FIXME NullPointerexception 
      mainController.drawImage(file);

    });

  }//}}}

  @FXML private void openSideViewMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      // FIXME NullPointerexception 
      mainController.drawImage(file);

    });

  }//}}}

  @FXML private void openRecentMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      // FIXME NullPointerexception 
      mainController.drawImage(file);

    });

  }//}}}

  @FXML private void closeMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      // FIXME NullPointerexception 
      mainController.drawImage(file);

    });

  }//}}}

@FXML private void quitMenuItemOnAction() {//{{{

    Platform.exit();

  }//}}}

  @FXML private void forcedTerminateMenuItemOnAction() {//{{{

    Platform.exit();

  }//}}}

  // 表示メニュー

  @FXML private void alwaysOnTopMenuItemOnAction() {//{{{

    mainController.changeAlwaysOnTop();

  }//}}}

  @FXML private void displayConfigMenuItemOnAction() {//{{{

    mainController.showConfigStage();

  }//}}}

  // 言語メニュー

  @FXML private void jpRadioMenuItemOnAction() {//{{{

    Platform.exit();

  }//}}}

  @FXML private void usRadioMenuItemOnAction() {//{{{

    Platform.exit();

  }//}}}

  // Setter

  public void setMainController(MainController aMain) {//{{{
    mainController = aMain;
  }//}}}

  public void setDisableConfigMenuItem(boolean disable) {//{{{

    displayConfigMenuItem.setDisable(disable);

  }//}}}

}
