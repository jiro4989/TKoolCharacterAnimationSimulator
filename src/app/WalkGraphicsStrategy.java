package app;

import jiro.java.util.MyProperties;

import app.MainController;
import app.standard.Standards;

import java.io.File;

class WalkGraphicsStrategy implements DrawImageStrategy {

  private final MyProperties myProperties;
  private final Standards standards;
  private final MainController controller;

  private static final String WIDTH = "48";

  WalkGraphicsStrategy(File preset, MainController controller) {//{{{
    myProperties = new MyProperties(preset);
    myProperties.load();
    int width     = Integer . parseInt(myProperties . getProperty("chara.width")  . orElse(WIDTH));
    int height    = Integer . parseInt(myProperties . getProperty("chara.height") . orElse(WIDTH));
    int row       = Integer . parseInt(myProperties . getProperty("row")          . orElse("4"));
    int column    = Integer . parseInt(myProperties . getProperty("column")       . orElse("3"));

    this.controller = controller;

    standards = new Standards.Builder(width, height)
      .row(row) .column(column)
      .build();
  }//}}}

  @Override
  public void drawImage(File file) {//{{{

    String filePath = file.getPath();
    controller.drawWalkImage(filePath, standards);

  }//}}}
}
