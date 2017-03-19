TKool Character Animation Simulator マニュアル
================================================================================

- バージョン       : 1.0.1
- 作者             : 次郎 (Jiro)
- 作成日           : 2017/03/18
- 最終更新日       : 2017/03/19
- 連絡先           : [次ログ](http://jiroron666.hatenablog.com/)
- 実行ファイル名   : tkcas.jar
- 動作確認・開発環境
  - OS             : Windows 10 Home, Linux Mint 18.1
  - プロセッサ     : 3.50GHz Intel Core i7-4770K
  - メモリ         : 8GB RAM
  - Javaバージョン : 1.8.0-121

![tkcasデモ](./gif/tkcas_demo01.gif)

![tkcasデモ](./gif/tkcas_demo02.gif)

目次
--------------------------------------------------------------------------------

1. [ソフトウェア概要                  ](#ソフトウェア概要)
1. [ファイル構成                      ](#ファイル構成)
1. [動作条件                          ](#動作条件)
1. [実行方法                          ](#実行方法)
1. [使い方                            ](#使い方)
   1. [ファイルを開く                 ](#ファイルを開く)
   1. [画像の確認                     ](#画像の確認)
   1. [表示するレイアウトの調整       ](#表示するレイアウトの調整)
1. [画像規格プリセット                ](#画像規格プリセット)
   1. [プリセットの確認               ](#プリセットの確認)
   1. [プリセットの編集               ](#プリセットの編集)
      1. [プリセット編集画面の操作方法](#プリセット編集画面の操作方法)
1. [ショートカットキー                ](#ショートカットキー)
1. [FAQ                               ](#FAQ)
1. [利用規約                          ](#利用規約)
   1. [補足                           ](#補足)
1. [バージョンアップ方法              ](#バージョンアップ方法)
1. [その他・作者からのお願い          ](#その他・作者からのお願い)
1. [アンインストール方法              ](#アンインストール方法)
1. [更新履歴                          ](#更新履歴)

ソフトウェア概要
--------------------------------------------------------------------------------

このソフトは、ペイントソフトでキャラチップを編集した時に、キャラチップのアニメー
ションがゲーム内でどのように再生されるかをシミュレートするためのツールです。

キャラチップを上書き保存するなどして更新されたタイミングを監視し、変更された画像
をリアルタイムでシミュレートします。この際、本ソフトは画面最前面に表示することが
可能です。

これにより、イラストを手軽に編集し、その変更を容易に確認することが可能です。面倒
な確認作業にかかる時間を大幅に削減することができ、作成するキャラチップの品質向上
に役立てられるかと思います。

また、本ソフトはJava言語のみを使用してマルチプラットフォームを意識して作成しまし
た。Mac環境では未確認ですが、Windows, Mac, LinuxといったメジャーなOSであればどの
環境でも動作するはずです。

ファイル構成
--------------------------------------------------------------------------------

zip配布時の構成

- README.html        : READMEへのリンク
- java_download.html : Javaアップデート公式サイトへのリンク
- tkcas.jar          : 実行ファイル

初回起動時に生成されるフォルダ (各種ファイルの保存・管理用)

- logs       : 最近開いたファイルログ
- presets    : 画像規格ファイル
- properties : 設定ファイル

上記の生成されるフォルダ類は、アンインストールする時以外に場所を移動したり、削除
したりしないようにしてください。予期せぬ動作の原因になります。

動作条件
--------------------------------------------------------------------------------

本ソフトを実行するにはJavaがインストールされている必要があります。また、本ソフト
作成時のJavaのバージョン以上がインストーされている必要があります。

もし本ソフトをダブルクリックで実行できなかった場合は、Webブラウザで下記URLの公式
サイトを開くか、同封の java_download.html をダブルクリックして公式サイトを開いて
Javaをインストールまたはアップデートしてください。

[Javaダウンロード](https://java.com)

それでも起動できなかった場合は、お手数ですがFAQの項目を確認した後に、本ドキュメ
ント先頭の連絡先より作者にご報告ください。

実行方法
--------------------------------------------------------------------------------

"tkcas.jar"をダブルクリックしてください。LinuxOSをご使用の方はターミナルから
"java -jar tkcas.jar"と打ち込んでください。

使い方
--------------------------------------------------------------------------------

### ファイルを開く

ファイルメニューの'開く'を選択して、アニメーションしたいキャラチップを選択してく
ださい。

この時、歩行グラのファイルを開いた場合、対象とするキャラを選択する必要があります
。ダブルクリックでアニメーションしたいキャラを選択してください。

### 画像の確認

画像が正常に読み込めたかどうか確認します。

本ソフトでは表示しているキャラチップのアニメーション速度や拡大率を変更できます。
ショートカットキー、あるいはファイルメニューの'設定'の項目から変更できます。

正常にアニメーションで来ていれば成功です

正常にアニメーション出来ていなければ、ファイルの規格が正しいか確認する必要があり
ます。詳細は[プリセットの確認](#プリセットの確認)の項目を参照してください。

### 表示するレイアウトの調整

キャラチップの配置はウィンドウサイズに依存します。

ウィンドウの横幅が、すべてのキャラチップを表示するのに十分であった場合、キャラチ
ップは1行ですべてを表示しようとします。

配置を1行ではなく2行に配置したいと考えた場合は、ウィンドウの横幅を縮小し、キャラ
チップの配置が自分の期待しているようになるまで調整してください。

画像規格プリセット
--------------------------------------------------------------------------------

本ソフトではTKoolとタイトルに銘打っているとおり、RPGツクールシリーズの画像ファイ
ルを扱うことが出来ます。

プリセットという設定ファイルを追加することで、柔軟に異なる画像規格のファイルでも
アニメーションすることが可能になっています。

本ソフトでは配布時に自動でツクールMVとVXACEのプリセットを生成するようにしていま
す。

それ以前のバージョンのツクールの画像をアニメーションしたい場合は、プリセットを自
作する必要があります。詳細は[プリセットの編集](#プリセットの編集)の項目を参照し
てください。

### プリセットの確認

ファイルメニューの'歩行グラ'と'サイドビュー'という項目から現在のプリセットが確認
できます。

プリセットを変更したい場合はその下の'プリセットを開く'という項目から選択してくだ
さい。

### プリセットの編集

プリセットの編集画面を表示します。

この画面では行、列、1キャラチップあたりの横幅と高さ、アニメーションのフレーム数
を設定します。

プレビュー画像を読み込むと、画面右側の規格プレビューの下に比較用の画像を表示する
ことができます。

#### プリセット編集画面の操作方法

- 行、列、横幅、高さの項目の左右にあるボタンは数値の増減を扱います。

- 行、列、横幅、高さの項目のテキスト入力欄上のでマウスホイールを使用すると、値を
  増減します。

- マウスホイールによる値の操作時にCtrlキー、またはShiftキーを押すと値の上限幅
  を変更できます。

  - Ctrl  :  5ずつ増減
  - Shift : 10ずつ増減

ショートカットキー
--------------------------------------------------------------------------------

| キー         | 動作                               | 備考 |
|:-------------|:-----------------------------------|:-----|
| Ctrl-O       | 歩行グラを開く                     |      |
| Ctrl-Shift-O | サイドビューを開く                 |      |
| Ctrl-P       | 歩行グラのプリセットを開く         |      |
| Ctrl-Shift-P | サイドビューのプリセットを開く     |      |
| Ctrl-E       | 歩行グラのプリセットを編集する     |      |
| Ctrl-Shift-E | サイドビューのプリセットを編集する |      |
| Q            | アニメーション画像の縮小           |      |
| E            | アニメーション画像の拡大           |      |
| A            | 前の単一アニメーション画像に変更   |      |
| D            | 次の単一アニメーション画像に変更   |      |
| C            | 表示モードの切替                   |      |
| W            | アニメーションの低速化             |      |
| S            | アニメーションの高速化             |      |
| F1           | バージョン情報                     |      |

FAQ
--------------------------------------------------------------------------------

### Q1. 実行ファイルをダブルクリックしても動作しない

ご利用の環境にJavaがインストールされているか確認してください。また、Javaがインス
トールされていても、本ソフト作成時のJavaのバージョン以下をご利用の場合、動作しな
い場合があります。

Javaのインストール、アップデート方法については[動作条件](#動作条件)の項目を参照
してください。

### Q2. 以前は動いていたのに突然起動しなくなった

実行時に自動生成されたフォルダをすべて削除してください。ただし、これはあくまでも
一時的な対処法です。

もしそれで起動するようになったのでしたら、よろしければ作者にバグ報告していただけ
ると助かります。また、起動しなくなる前に何を行っていたかも報告していただけると、
本ソフトのバグ修正が容易になり、品質向上に役立てることができます。

### Q3. READMEのレイアウトが崩れている

仕様です。フォントが異なるとレイアウトが崩れて見えることがあります。

メモ帳などでこのドキュメントを開いているのでしたら、書式メニューのフォントからMS
ゴシック(MS Pゴシックではありません)を選択すると作者が期待している通りのレイアウ
ト表示になります。

### Q4. 画像を上書き保存すると表示モードが戻る

既知の不具合です。

私の設計が悪くて今の時点ではどうしても戻ってしまいます。可能なら後のバージョンで
修正したいと考えています。

利用規約
--------------------------------------------------------------------------------

- 配布している素材を利用したことで発生した問題に対して、私は一切の責任を負いませ
  ん。

- 著作権は私(次郎)が有しています。私が死亡して50年経過するまで決して放棄すること
  はありません。

- 再配布はお控えください。

- 利用規約を事前連絡なしに変更する場合があります。その場合は最新の規約が適用され
  るものとします。

### 補足

利用できる作品

- ツクール用に作成しましたがツクール作品以外の素材作成が目的でも利用可能です。
- 有償作品、エロゲー、グロゲーでも利用可能です。
- ゲーム作品以外でも利用可能です。

利用報告

- READMEへの記載は利用者の任意です。私から強制することはありません。
- 使用報告もしなくて結構です。したいひとだけしてください。

バージョンアップ
--------------------------------------------------------------------------------

本ソフト配布時のzipに同封のREADME.htmlをダブルクリックしてください。本ソ
フトのプログラムを公開しているGithubというサイトにジャンプします。

移動先のサイトに表示されているバージョン情報が、現在ご使用のバージョンよりも上
がっている場合は、移動先サイトの実行ファイルをダウンロードしてご利用ください。

ダウンロード方法がわからなかった場合は、本ソフトをダウンロードしたツクマテコミュ
ニティ・または作者ブログのソフト配布ページのDropboxのリンクから、最新版をダウン
ロードしてください。

その他・作者からのお願い
--------------------------------------------------------------------------------

- バグの報告は連絡先の作者ブログか、ツクマテコミュニティのツール配布のページでお
  願いします。

- 新しい機能の実装のご要望をいただいた場合、実装する場合もあるかもしれませんが、
  必ずではないことをご了承ください。

アンインストール方法
--------------------------------------------------------------------------------

本ソフトはレジストリを変更していませんので、配布時のzipフォルダごとゴミ箱にぶち
込んでいただければアンインストールできます。

もし本ソフトの実行ファイルをフォルダの外に移動して実行した場合は、自動で生成され
る各種フォルダを削除していただければ、アンインストールは完了です。

実行時に生成されるフォルダについては[ファイル構成](#ファイル構成)の項目を参照し
てください。

更新履歴
--------------------------------------------------------------------------------

Ver1.0.0 : 2017/03/18
- プログラム公開

Ver1.0.1 : 2017/03/19
- 縦横比が異なる画像を扱おうとするとエラーが発生していた不具合を修正

