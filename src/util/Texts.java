package util;

public class Texts {

  private static final String JAR_NAME = "tkccs";

  public static final String TITLE               = "TKool Character Chip Simulator";
  public static final String VERSION             = "ver 1.0.0";
  public static final String TITLE_VERSION       = TITLE + " - " + VERSION;

  public static final String BASIC_CSS           = "/app/res/css/basic.css";
  public static final String APP_ICON            = "/app/res/img/app_icon.png";

  public static final String WALK_PREST_DIR      = "./presets/" + JAR_NAME + "/walk";
  public static final String SIDE_VIEW_PREST_DIR = "./presets/" + JAR_NAME + "/side_view";
  public static final String WALK_PREST          = WALK_PREST_DIR      + "/mv.preset";
  public static final String VXACE_WALK_PREST    = WALK_PREST_DIR      + "/vxace.preset";
  public static final String SIDE_VIEW_PREST     = SIDE_VIEW_PREST_DIR + "/mv.preset";

  // preferences //{{{

  public static final String PROP_DIR          = "./properties/" + JAR_NAME;
  public static final String PREFERENCES_FILE  = PROP_DIR + "/preferences.xml";

  public static final String KEY_LANGS            = "languages";
  public static final String KEY_ALWAYS_ON_TOP    = "alwaysOnTop";
  public static final String KEY_ZOOM_RATE        = "zoomRate";
  public static final String KEY_DURATION         = "duration";
  public static final String KEY_FONT_SIZE        = "fontSize";
  public static final String KEY_WALK_PRESET      = "walkPreset";
  public static final String KEY_SIDE_VIEW_PRESET = "sideViewPreset";

  public static final String DEFAULT_VALUE_ALWAYS_ON_TOP = "false";
  public static final String DEFAULT_VALUE_ZOOM_RATE     = "100";
  public static final String DEFAULT_VALUE_DURATION      = "200";
  public static final String DEFAULT_VALUE_FONT_SIZE     = "11";

  //}}}

  // presets

  public static final String KEY_ROW          = "row";
  public static final String KEY_COLUMN       = "column";
  public static final String KEY_CHARA_WIDTH  = "chara.width";
  public static final String KEY_CHARA_HEIGHT = "chara.height";
  public static final String KEY_FRAME_COUNT  = "frameCount";

  // MVのデフォルト値//{{{

  public static final String WALK_PREST_DEFAULT_VALUE_ROW          = "4";
  public static final String WALK_PREST_DEFAULT_VALUE_COLUMN       = "3";
  public static final String WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH  = "48";
  public static final String WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT = "48";
  public static final String WALK_PREST_DEFAULT_VALUE_FRAME_COUNT  = "3";

  public static final String SIDE_VIEW_PREST_DEFAULT_VALUE_ROW          = "6";
  public static final String SIDE_VIEW_PREST_DEFAULT_VALUE_COLUMN       = "9";
  public static final String SIDE_VIEW_PREST_DEFAULT_VALUE_FRAME_COUNT  = "3";

  //}}}

  // VXACEのデフォルト値//{{{

  public static final String VXACE_WALK_PREST_DEFAULT_VALUE_ROW          = "4";
  public static final String VXACE_WALK_PREST_DEFAULT_VALUE_COLUMN       = "3";
  public static final String VXACE_WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH  = "32";
  public static final String VXACE_WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT = "32";
  public static final String VXACE_WALK_PREST_DEFAULT_VALUE_FRAME_COUNT  = "3";

  //}}}

}
