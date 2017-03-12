package app.standard;

public class AnimationStandard {

  private final int row;
  private final int column;

  public AnimationStandard(int row, int column) {//{{{

    this.row = row;
    this.column = column;

  }//}}}

  @Override
  public String toString() {
    return String.format("AnimationStandard: { row: %d, column: %d }", row, column);
  }

}
