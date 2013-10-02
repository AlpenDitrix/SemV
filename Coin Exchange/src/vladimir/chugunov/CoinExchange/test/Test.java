package vladimir.chugunov.CoinExchange.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vladimir.chugunov.CoinExchange.ATM;
import vladimir.chugunov.CoinExchange.IPrintable;

@SuppressWarnings("javadoc")
public class Test {

    /**
     * Instance of input-helper
     */
    static Parser p = new Parser();

    public static void main(String[] s) throws IOException {

        ATM atm = new ATM(readWorths(), new IPrintable() {

            @Override
            public void print(int[] worths, int[] coins) throws IOException {
                if (worths.length != coins.length) {
                    throw new RuntimeException("wrong dimensions");
                }
                for (int i = 0; i < worths.length; i++) {
                    if (coins[i] != 0) {
                        System.out.println("'" + worths[i] + "' : " + coins[i]);
                    }
                }
                System.out.println();
            }

        });
        // 2)WORK WITH HIM
        while (true) {
            try {
                atm.exchange(readMoney());
            } catch (Exception e) {
                System.out.println();
                break;
            }
        }
    }

    private static int readMoney() throws IOException {
        try {
            // I need this to show messages from differet streams (System.in and
            // System.err) in a proper order
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Messages.getString("EnterMoney"));
        return p.nextInteger();
    }

    private static int[] readWorths() throws IOException {
        System.out.println(Messages.getString("EnterWorths"));
        List<Integer> list = new ArrayList<>();
        String s = p.nextString();
        while (true) {
            try {
                if (s.contains(".")) {
                    s = s.replace(".", "");
                    if (!s.equals("")) {
                        parseWorth(list, s);
                    }
                    break;
                }
                parseWorth(list, s);
            } catch (NumberFormatException e) {
                System.err.println(Messages.getString("PleaseDigitsEtc"));
            }
            s = p.nextString();
        }
        
        // may be made better, but so will do
        if (list.size() < 1) {
            int[] v = { 1, 3, 5, 10 };
            System.out.println(Messages.getString("Good").concat(
                    Arrays.toString(v)));
            return v;
        } else {
            System.out.println(Messages.getString("Good").concat(
                    list.toString()));
            int[] v = new int[list.size()];
            for (int i = 0; i < v.length; i++) {
                v[i] = list.get(i);
            }
            return v;
        }
    }

    private static int parseWorth(List<Integer> list, String s) {
        int arrived = Integer.parseInt(s);
        if (arrived < 0) {
            System.err.println(Messages.getString("NotNegative"));
        } else if (arrived == 0) {
            System.err.println(Messages.getString("NotZero"));
        } else {
            if (!list.contains(arrived)) {
                list.add(arrived);
            }
        }
        return arrived;
    }
}
