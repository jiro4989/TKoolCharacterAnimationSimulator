package com.jiro4989.tkcas.standard;

/**
 * 歩行グラアニメーション画像の規格を表現するクラス。
 *
 * <p>https://tkool.jp/mv/course/04.html
 */
public class WalkImageStandard {
  /** アニメーションチップの横幅 */
  public final int width;

  /** アニメーションチップの縦幅 */
  public final int height;

  /** アニメーションタイルの行数 */
  public final int row;

  /** アニメーションタイルの列数 */
  public final int column;

  /** アニメーションに使われる画像枚数 */
  public final int frameCount;

  public WalkImageStandard(int width, int height, int row, int column, int frameCount) {
    this.width = width;
    this.height = height;
    this.row = row;
    this.column = column;
    this.frameCount = frameCount;
  }

  /** RPGツクールMV用の歩行グラ用規格 */
  public static class MV extends WalkImageStandard {
    public MV() {
      super(48, 48, 1, 3, 3);
    }
  }

  /** RPGツクールVXACE用の歩行グラ用規格 */
  public static class VXACE extends WalkImageStandard {
    public VXACE() {
      super(32, 32, 1, 3, 3);
    }
  }
}
