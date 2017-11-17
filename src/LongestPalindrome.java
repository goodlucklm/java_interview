public class LongestPalindrome {
    private int expandFromCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return (end-1) - (start+1) + 1;
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) return s;

        int start = 0, end = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int longest = Math.max(len1, len2);
            if (end - start + 1 < longest) {
                start = Math.max(i - (longest-1) / 2, 0);
                end = i + longest / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        LongestPalindrome LP = new LongestPalindrome();
        String s = LP.longestPalindrome("abcbacccccc");
        System.out.println(s);
    }
}
