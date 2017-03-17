package util;

import jiro.java.util.MyProperties;

import java.io.File;
import static util.Texts.*;

public class PresetsUtils {

  public static void mkInitDirs() {//{{{

    File propDir = new File(PROP_DIR);
    propDir.mkdirs();

    File walkPresetDir = new File(WALK_PREST_DIR);
    walkPresetDir.mkdirs();

    File sideViewPresetDir = new File(SIDE_VIEW_PREST_DIR);
    sideViewPresetDir.mkdirs();

  }//}}}

  public static void mkInitPresets() {//{{{

    File walk = new File(WALK_PREST);
    if (!walk.exists()) {

      MyProperties walkMp = new MyProperties(walk);

      walkMp.setProperty(KEY_ROW          , WALK_PREST_DEFAULT_VALUE_ROW);
      walkMp.setProperty(KEY_COLUMN       , WALK_PREST_DEFAULT_VALUE_COLUMN);
      walkMp.setProperty(KEY_CHARA_WIDTH  , WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH);
      walkMp.setProperty(KEY_CHARA_HEIGHT , WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT);
      walkMp.setProperty(KEY_FRAME_COUNT  , WALK_PREST_DEFAULT_VALUE_FRAME_COUNT);

      walkMp.store();

    }

    File sideView = new File(WALK_PREST);
    if (!sideView.exists()) {

      MyProperties sideViewMp = new MyProperties(sideView);

      sideViewMp.setProperty(KEY_ROW          , SIDE_VIEW_PREST_DEFAULT_VALUE_ROW        );
      sideViewMp.setProperty(KEY_COLUMN       , SIDE_VIEW_PREST_DEFAULT_VALUE_COLUMN     );
      sideViewMp.setProperty(KEY_FRAME_COUNT  , SIDE_VIEW_PREST_DEFAULT_VALUE_FRAME_COUNT);

      sideViewMp.store();

    }

  }//}}}

}
