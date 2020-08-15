package com.jiro4989.tkcas.standard;

/**
 * サイドビューアニメーション画像の規格を表現するクラス。
 *
 * <p>サイドビューアニメーションでは、歩行グラの規格と異なり、1枚の画像ファイルの列数、行数による割合値で、1タイルの画像幅が決まる。
 *
 * <p>https://tkool.jp/mv/course/04.html
 */
public class SideViewImageStandard {
  /** アニメーションタイルの行数 */
  public final int row;

  /** アニメーションタイルの列数 */
  public final int column;

  /** アニメーションに使われる画像枚数 */
  public final int frameCount;

  public SideViewImageStandard(int row, int column, int frameCount) {
    this.row = row;
    this.column = column;
    this.frameCount = frameCount;
  }

  /** RPGツクールMV用の歩行グラ用規格 */
  public static class MV extends SideViewImageStandard {
    public MV() {
      super(6, 9, 3);
    }
  }
}
