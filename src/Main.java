import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int[] array = new int[30000];
    static int ptr;
    static ArrayList<Integer> loopPtr = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        JLabel outLabel = new JLabel();
        JFrame frame = new JFrame("out");
        frame.setSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(outLabel);
        frame.setVisible(true);

        String code = new Scanner(System.in).next();

        for (int i = 0; i < code.length(); i++) {

            char order = code.charAt(i);


            if (order == ',') {//入力から1バイト読み込んで、ポインタが指す値に代入
                System.out.print("入力>");
                String in = new Scanner(System.in).next();
                if (in.length() == 1) {
                    array[ptr] = in.toCharArray()[0];//加算かと思ったが代入だった...
                } else {
                    throw new Exception("入力は一文字のみです");
                }
                continue;
            }

            if (order == '+') {//ポインタが指す値をインクリメントする。
                array[ptr]++;
                continue;
            }

            if (order == '-') {//ポインタが指す値をデクリメントする。
                array[ptr]--;
                continue;
            }

            if (order == '<') {//ポインタをデクリメント (左にずらす）
                ptr--;
                if (ptr == -1) {
                    throw new Exception("ポインタの参照先がおかしい(ptrがで小さすぎ)");
                }
                continue;
            }

            if (order == '>') {//ポインタをインクリメント （右にずらす）
                ptr++;
                if (ptr == array.length) {
                    throw new Exception("ポインタの参照先がおかしい(ptrがでかすぎ)");
                }
                continue;
            }

            if (order == '.') {//ポインタが指す値を出力する
                outLabel.setText(outLabel.getText() + (char) array[ptr]);
                continue;
            }


            // [ ] の実装が難しい特にインクリメント部がややこしい


            if (order == '[') {//ポインタの指す値が0なら、後の]までジャンプ(要するにwhile)
                if (array[ptr] == 0) {
                    int j = i + 1;
                    int c = 0;
                    out:
                    while (true) {
                        if (code.charAt(j) == '[') {
                            c++;
                        }
                        if (code.charAt(j) == ']') {
                            if (c == 0) {
                                i = j;//直後というのがややこしい　頭がおかしくなる
                                break out;
                            }
                            if (c != 0) {
                                c--;
                            }
                        }
                        j++;
                    }
                } else {
                    loopPtr.add(i);//直後というのがややこしい　頭がおかしくなる
                }

                continue;
            }

            if (order == ']') {//ポインタの指す値が0でなければ、前の[までジャンプ
                if (array[ptr] != 0) {
                    i = loopPtr.get(loopPtr.size() - 1);
                } else {
                    loopPtr.remove(loopPtr.size() - 1);
                }
                continue;
            }
        }
    }
}
