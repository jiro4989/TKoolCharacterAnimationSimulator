package app.menubar;

import jiro.javafx.stage.MyFileChooser;

import app.Main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyMenuBar extends VBox {

  private final Main main;
  private final MyFileChooser mfc;

  // FXMLコンポーネント

//{{{

  @FXML private Menu fileMenu;
  @FXML private MenuItem openMenuItem;

//}}}

  // コンストラクタ

  public MyMenuBar(Main aMain) {//{{{

    main = aMain;
    mfc = new MyFileChooser.Builder("Image Files", "*.png").build();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("my_menu_bar.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }//}}}

  // FXMLイベント

  @FXML private void newFileMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      main.drawImage(file);

    });

  }//}}}

  @FXML private void quitMenuItemOnAction() {

    Platform.exit();

  }

}
