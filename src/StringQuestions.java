public class StringQuestions {
    public int myAtoi(String str) {
        if (null == str) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        long base = 0;
        int i = 0;
        char ch = str.charAt(i);
        int factor = 1;
        if (ch == '+' || ch == '-') {
            if (str.length() < 2 || !Character.isDigit(str.charAt(i+1))) return 0;
            if (ch == '-') factor = -1;
            i++;
        }
        while (i < str.length() && str.charAt(i) == '0') i++;
        while (i < str.length()) {
            ch = str.charAt(i++);
            if (Character.isDigit(ch)) {
                base = base*10+((ch-'0')*factor);
                if (base > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                else if (base < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            } else {
                return (int)base;
            }
        }
        return (int)base;
    }

    public String longestCommonPrefix(String[] strs) {
        int i = 0;
        char ch;
        String prefix = "";
        while (true) {
            if (strs.length > 0 && strs[0].length() > i)
                ch = strs[0].charAt(i);
            else
                return prefix;
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != ch ) {
                    return prefix;
                }
            }
            prefix += ch;
            i++;
        }
    }

    public static void main(String[] args) {
        StringQuestions strq = new StringQuestions();
        System.out.println(strq.longestCommonPrefix(  new String[]{"a", "b"} ));
    }
}
