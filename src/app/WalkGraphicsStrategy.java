package app;

import jiro.java.util.MyProperties;

import app.MainController;
import app.standard.Standards;

import java.io.File;

class WalkGraphicsStrategy implements DrawImageStrategy {

  private final MainController controller;

  private static final String WIDTH = "48";

  WalkGraphicsStrategy(MainController controller) {//{{{

    this.controller = controller;

  }//}}}

  @Override
  public void drawImage(File file) {//{{{

    controller.drawWalkImage(file);

  }//}}}

  static Standards createStandard(String path) {//{{{

    MyProperties myProperties = new MyProperties(path);
    myProperties.load();
    int width     = Integer . parseInt(myProperties . getProperty("chara.width")  . orElse(WIDTH));
    int height    = Integer . parseInt(myProperties . getProperty("chara.height") . orElse(WIDTH));
    int row       = Integer . parseInt(myProperties . getProperty("row")          . orElse("4"));
    int column    = Integer . parseInt(myProperties . getProperty("column")       . orElse("3"));

    Standards std = new Standards.Builder(width, height)
      .row(row) .column(column)
      .build();

    return std;

  }//}}}

}
