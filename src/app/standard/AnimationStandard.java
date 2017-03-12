package app.standard;

public class AnimationStandard {

  public final int row;
  public final int column;

  public AnimationStandard(int row, int column) {//{{{

    this.row = row;
    this.column = column;

  }//}}}

  @Override
  public String toString() {
    return String.format("AnimationStandard: { row: %d, column: %d }", row, column);
  }

}
