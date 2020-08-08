package app;

import static util.Texts.*;

import app.standard.Standards;
import java.io.File;
import jiro.java.util.MyProperties;

class SideViewStrategy implements DrawImageStrategy {

  private final MainController controller;

  SideViewStrategy(MainController controller) { // {{{

    this.controller = controller;
  } // }}}

  @Override
  public void drawImage(File file) { // {{{

    controller.drawSideViewImage(file);
  } // }}}

  static Standards createStandard(String path) { // {{{

    MyProperties mp = new MyProperties(path);
    mp.load();
    int row = Integer.parseInt(mp.getProperty(KEY_ROW).orElse(SIDE_VIEW_PREST_DEFAULT_VALUE_ROW));
    int column =
        Integer.parseInt(mp.getProperty(KEY_COLUMN).orElse(SIDE_VIEW_PREST_DEFAULT_VALUE_COLUMN));
    int frameCount =
        Integer.parseInt(
            mp.getProperty(KEY_FRAME_COUNT).orElse(SIDE_VIEW_PREST_DEFAULT_VALUE_FRAME_COUNT));

    // サイドビューは歩行グラと規格が異なるため
    Standards std =
        new Standards.Builder(1, 1).row(row).column(column).frameCount(frameCount).build();

    return std;
  } // }}}
}
