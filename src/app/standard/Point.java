package app.standard;

/**
 * 座標クラス
 */
class Point {

  public final int x;
  public final int y;

  Point(int x, int y) {//{{{

    this.x = x;
    this.y = y;

  }//}}}

  @Override
  public String toString() {
    return String.format("Point: { x: %d, y: %d }", x, y);
  }

}
