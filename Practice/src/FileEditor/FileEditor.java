package FileEditor;

import java.io.*;

public class FileEditor {
    public static void main(String[] args) {
        String inputPath = "C:\\WorkSpace\\Repositories\\JavaDemo1_ETL\\TestFileFolder\\input"; // 入力ファイルがあるパス
        String outputPath = "C:\\WorkSpace\\Repositories\\JavaDemo1_ETL\\TestFileFolder\\output"; // 出力ファイルを保存するパス
        File inputDir = new File(inputPath);
        File[] files = inputDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        // 行ごとの編集処理を実行する
                        String editedLine = editLine(line);
                        stringBuilder.append(editedLine);
                        stringBuilder.append("\n");
                    }
                    reader.close();
                    // 出力ファイルを保存する
                    String outputFileName = file.getName();
                    String outputPathWithName = outputPath + "/" + outputFileName;
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputPathWithName));
                    writer.write(stringBuilder.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 行ごとの編集処理を実装する
    private static String editLine(String line) {
        // 例：行の先頭に"#"を追加する
        return "#" + line;
    }
}