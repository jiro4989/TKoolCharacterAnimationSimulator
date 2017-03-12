package app.standard;

/**
 * 座標クラス
 */
class Point {

  final int x;
  final int y;

  Point(int x, int y) {//{{{

    this.x = x;
    this.y = y;

  }//}}}

  @Override
  public String toString() {
    return String.format("Point: { x: %d, y: %d }", x, y);
  }

}
