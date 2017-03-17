package util;

import java.util.*;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;

public class DialogUtils {

  public static final void showLanguageDialog() {//{{{

    Locale locale = Locale.getDefault();

    Alert alert = new Alert(AlertType.INFORMATION);
    String header = locale.equals(Locale.JAPAN)
      ? "言語の変更は次回起動時に適用されます"
      : "Apply changing Languages when next Starting";
    alert.setHeaderText(header);

    alert.showAndWait();

  }//}}}

  public static final void showForcedTerminationDialog() {//{{{

    Locale locale = Locale.getDefault();

    Alert alert = new Alert(AlertType.CONFIRMATION);

    String header = locale.equals(Locale.JAPAN)
      ? "通常の方法で本ソフトを終了できない場合以外は使用しないでください。"
      : "Should do this operation when you couldn't stop this application";
    alert.setHeaderText(header);

    String content = locale.equals(Locale.JAPAN)
      ? "本当に終了しますか？"
      : "Really execute ?";
    alert.setContentText(content);

    alert.showAndWait()
      .filter(r -> r == ButtonType.OK)
      .ifPresent(r -> Platform.exit());

  }//}}}

  // 汎用性のない実装
  public static final String showChoiseDialog() {//{{{

    Locale locale = Locale.getDefault();

    String walk     = locale.equals(Locale.JAPAN) ? "歩行グラ"     : "Walking Graphics";
    String sideView = locale.equals(Locale.JAPAN) ? "サイドビュー" : "Side View";

    List<String> choices = new ArrayList<>(2);
    choices.add(walk);
    choices.add(sideView);

    ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);

    String header = locale.equals(Locale.JAPAN)
      ? "開く画像ファイルの形式を選択してください。"
      : "Choice file format to open image.";
    dialog.setHeaderText(header);

    String result = dialog.showAndWait().orElse(walk);
    return result.equals(walk) ? "w" : "sv";

  }//}}}

}
