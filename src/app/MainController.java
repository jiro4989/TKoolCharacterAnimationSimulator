package app;

import app.menubar.MyMenuBar;
import app.layout.PositionsFlowPane;
import app.standard.Standards;

import java.io.*;
import javafx.fxml.FXML;

public class MainController {

  /** 画像やアニメの規格 */
  private Standards standards;

  // FXMLコンポーネント//{{{

  @FXML private MyMenuBar myMenuBar;
  @FXML private PositionsFlowPane positionsFlowPane;

  //}}}

  // 初期化

  @FXML private void initialize() {//{{{

    myMenuBar.setMainController(this);

  }//}}}

  public void drawImage(File file) {//{{{

    // TODO TEST CODE
    int x = 0;
    int y = 0;
    int row = 1;
    int column = 3;
    int width = 48;
    int height = 48;

    standards = new Standards.Builder(width, height)
      .x(x) .y(y) .row(row) .column(column)
      .build();

    String filePath = file.getPath();
    positionsFlowPane.drawImage(filePath, standards);

  }//}}}

}
