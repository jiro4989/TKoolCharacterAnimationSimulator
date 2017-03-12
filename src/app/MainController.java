package app;

import jiro.java.util.MyProperties;

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

    MyProperties mp = new MyProperties("./preset/mv_chara_chip.preset");
    mp.load();

    final String DEF_W = "48";

    // TODO TEST VALUE
    int x = 0;
    int y = 0;
    int width     = Integer . parseInt(mp . getProperty("chara.width")  . orElse(DEF_W));
    int height    = Integer . parseInt(mp . getProperty("chara.height") . orElse(DEF_W));
    int row       = Integer . parseInt(mp . getProperty("row")          . orElse("4"));
    int column    = Integer . parseInt(mp . getProperty("column")       . orElse("3"));
    int animCount = Integer . parseInt(mp . getProperty("anim.count")   . orElse("3"));

    standards = new Standards.Builder(width, height)
      .x(x) .y(y) .row(row) .column(column)
      .build();

    String filePath = file.getPath();
    positionsFlowPane.drawImage(filePath, standards);

  }//}}}

}
