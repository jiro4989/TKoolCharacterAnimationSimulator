package com.jiro4989.tkcas.util;

import static com.jiro4989.tkcas.util.Texts.*;

import java.io.File;

public class PresetsUtils {

  public static void mkInitDirs() { 

    File propDir = new File(PROP_DIR);
    propDir.mkdirs();

    File walkPresetDir = new File(WALK_PREST_DIR);
    walkPresetDir.mkdirs();

    File sideViewPresetDir = new File(SIDE_VIEW_PREST_DIR);
    sideViewPresetDir.mkdirs();

    File logDir = new File(LOG_DIR);
    logDir.mkdirs();
  } 

  public static void mkInitPresets() { 

    mkMVWalkPreset();
    mkVXACEWalkPreset();
    mkMVSideViewPreset();
  } 

  private static void mkMVWalkPreset() { 

    File walk = new File(WALK_PREST);
    if (!walk.exists()) {

      MyProperties walkMp = new MyProperties(walk);

      walkMp.setProperty(KEY_ROW, WALK_PREST_DEFAULT_VALUE_ROW);
      walkMp.setProperty(KEY_COLUMN, WALK_PREST_DEFAULT_VALUE_COLUMN);
      walkMp.setProperty(KEY_CHARA_WIDTH, WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH);
      walkMp.setProperty(KEY_CHARA_HEIGHT, WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT);
      walkMp.setProperty(KEY_FRAME_COUNT, WALK_PREST_DEFAULT_VALUE_FRAME_COUNT);

      walkMp.store();
    }
  } 

  private static void mkVXACEWalkPreset() { 

    File walk = new File(VXACE_WALK_PREST);
    if (!walk.exists()) {

      MyProperties walkMp = new MyProperties(walk);

      walkMp.setProperty(KEY_ROW, VXACE_WALK_PREST_DEFAULT_VALUE_ROW);
      walkMp.setProperty(KEY_COLUMN, VXACE_WALK_PREST_DEFAULT_VALUE_COLUMN);
      walkMp.setProperty(KEY_CHARA_WIDTH, VXACE_WALK_PREST_DEFAULT_VALUE_CHARA_WIDTH);
      walkMp.setProperty(KEY_CHARA_HEIGHT, VXACE_WALK_PREST_DEFAULT_VALUE_CHARA_HEIGHT);
      walkMp.setProperty(KEY_FRAME_COUNT, VXACE_WALK_PREST_DEFAULT_VALUE_FRAME_COUNT);

      walkMp.store();
    }
  } 

  private static void mkMVSideViewPreset() { 

    File walk = new File(SIDE_VIEW_PREST);
    if (!walk.exists()) {

      MyProperties walkMp = new MyProperties(walk);

      walkMp.setProperty(KEY_ROW, SIDE_VIEW_PREST_DEFAULT_VALUE_ROW);
      walkMp.setProperty(KEY_COLUMN, SIDE_VIEW_PREST_DEFAULT_VALUE_COLUMN);
      walkMp.setProperty(KEY_FRAME_COUNT, SIDE_VIEW_PREST_DEFAULT_VALUE_FRAME_COUNT);

      walkMp.store();
    }
  } 
}
