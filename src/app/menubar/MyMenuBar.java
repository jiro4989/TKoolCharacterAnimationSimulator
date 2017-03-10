package app.menubar;

import jiro.javafx.stage.MyFileChooser;

import app.MainController;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyMenuBar extends VBox {

  private MainController mainController;
  private final MyFileChooser mfc;

  // FXMLコンポーネント

  //{{{

  @FXML private Menu fileMenu;
  @FXML private MenuItem openMenuItem;
  @FXML private ToggleGroup layoutGroup;

  //}}}

  // コンストラクタ

  public MyMenuBar() {//{{{

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

  @FXML private void openFileMenuItemOnAction() {//{{{

    mfc.openFile().ifPresent(file -> {

      // FIXME NullPointerexception 
      mainController.drawImage(file);

    });

  }//}}}

  @FXML private void flowLayoutMenuItemOnAction() {//{{{

    System.out.println("FlowLayout");

  }//}}}

  @FXML private void quitMenuItemOnAction() {//{{{

    Platform.exit();

  }//}}}

  // Setter

  public void setMainController(MainController aMain) {//{{{
    mainController = aMain;
  }//}}}

}
