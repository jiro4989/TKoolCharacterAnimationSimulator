package app;

import jiro.java.util.MyProperties;

import app.MainController;
import app.standard.Standards;

import java.io.File;

class SideViewStrategy implements DrawImageStrategy {

  private final MainController controller;

  SideViewStrategy(MainController controller) {//{{{

    this.controller = controller;

  }//}}}

  @Override
  public void drawImage(File file) {//{{{

    controller.drawSideViewImage(file);

  }//}}}

  static Standards createStandard(String path) {//{{{

    MyProperties mp = new MyProperties(path);
    mp.load();
    int row        = Integer . parseInt(mp . getProperty("row")          . orElse("6"));
    int column     = Integer . parseInt(mp . getProperty("column")       . orElse("9"));
    int frameCount = Integer . parseInt(mp . getProperty("frameCount")   . orElse("3"));

    // サイドビューは歩行グラと規格が異なるため
    Standards std = new Standards.Builder(1, 1)
      .row(row) .column(column) .frameCount(frameCount)
      .build();

    return std;

  }//}}}

}
