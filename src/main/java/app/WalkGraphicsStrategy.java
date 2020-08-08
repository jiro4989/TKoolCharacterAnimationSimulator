package app;

import static util.Texts.*;

import app.standard.Standards;
import java.io.File;
import jiro.java.util.MyProperties;

class WalkGraphicsStrategy implements DrawImageStrategy {

  private final MainController controller;

  WalkGraphicsStrategy(MainController controller) { // {{{

    this.controller = controller;
  } // }}}

  @Override
  public void drawImage(File file) { // {{{

    controller.drawWalkImage(file);
  } // }}}

  static Standards createStandard(String path) { // {{{

    MyProperties myProperties = new MyProperties(path);
    myProperties.load();
    int width =
        Integer.parseInt(
            myProperties.getProperty(KEY_CHARA_WIDTH).orElse(WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH));
    int height =
        Integer.parseInt(
            myProperties
                .getProperty(KEY_CHARA_HEIGHT)
                .orElse(WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT));
    int row =
        Integer.parseInt(myProperties.getProperty(KEY_ROW).orElse(WALK_PREST_DEFAULT_VALUE_ROW));
    int column =
        Integer.parseInt(
            myProperties.getProperty(KEY_COLUMN).orElse(WALK_PREST_DEFAULT_VALUE_COLUMN));

    Standards std = new Standards.Builder(width, height).row(row).column(column).build();

    return std;
  } // }}}
}
