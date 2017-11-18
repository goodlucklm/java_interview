import java.util.HashMap;

public class RomanInteger {
    static String[] ROMANS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    static int[] NUMBERS =   {1000, 900,  500, 400,  100, 90,   50,  40,   10,  9,    5,   4,    1};

    public String intToRoman(int num) {

        String result = "";
        for (int i = 0; i < ROMANS.length; i++) {
            while (num >= NUMBERS[i]) {
                result += ROMANS[i];
                num -= NUMBERS[i];
            }
        }
        return result;
    }

    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < ROMANS.length; i++)
            map.put(ROMANS[i], NUMBERS[i]);
        String behindMe = "I";
        String tmp;
        int result = 0;
        for (int i = s.length(); i > 0; i--) {
            tmp = s.substring(i-1,i);
            if (map.get(tmp) >= map.get(behindMe)) result += map.get(tmp);
            else result -= map.get(tmp);
            behindMe = tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        RomanInteger ri = new RomanInteger();
        System.out.println("the result is "+ri.romanToInt("LXXXII"));
    }
}
