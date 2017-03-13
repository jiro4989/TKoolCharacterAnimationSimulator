package util;

import java.util.Locale;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtils {

  public static final void showLanguageAlert() {//{{{
    Locale locale = Locale.getDefault();

    Alert alert = new Alert(AlertType.INFORMATION);
    String header = locale.equals(Locale.JAPAN)
      ? "言語の変更は次回起動時に適用されます"
      : "Apply changing Languages when next Starting";
    alert.setHeaderText(header);
    alert.showAndWait();
  }//}}}
  public static final void showForcedTerminationAlert() {//{{{
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

}
