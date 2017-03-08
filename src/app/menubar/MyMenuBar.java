package app.menubar;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyMenuBar extends VBox {

  // FXMLコンポーネント

//{{{

  @FXML private Menu fileMenu;
  @FXML private MenuItem openMenuItem;

//}}}

  // コンストラクタ

  public MyMenuBar() {//{{{

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

    System.out.println("Open Menu.");

  }//}}}

}
