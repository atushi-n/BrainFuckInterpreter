import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int[] array = new int[30];
    static int ptr;
    static ArrayList<Integer> loopPtr = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String str = new Scanner(System.in).next();
        for (int i = 0; i < str.length(); i++) {

            char order = str.charAt(i);

            if (order == '+') {//ポインタが指す値をインクリメントする。
                array[ptr]++;
                continue;
            }

            if (order == '-') {//ポインタが指す値をデクリメントする。
                array[ptr]--;
                continue;
            }

            if (order == '<') {
                ptr--;
                if (ptr >= array.length) {
                    throw new Exception("ポインタの参照先がおかしい");
                }
                continue;
            }

            if (order == '>') {
                ptr++;

                continue;
            }

            if (order == '.') {//ポインタが指す値を出力する
                System.out.print((char) array[ptr]);
                continue;
            }

            if (order == '[') {//ポインタの指す値が0なら、後の]までジャンプ(要するにwhile)
                loopPtr.add(i);
                continue;
            }
            if (order == ']') {//ポインタの指す値が0でなければ、前の[までジャンプ
                if (array[ptr] != 0) {
                    i = loopPtr.get(loopPtr.size() - 1) - 1;//コンティニューを考慮して-1する
                }
                loopPtr.remove(loopPtr.size() - 1);
                continue;
            }


        }
    }
}
