import java.util.*;

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

    public boolean isParenthesesValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch))
                st.push(ch);
            else {
                if (st.size() == 0 || map.get(ch) != st.peek()) return false;
                st.pop();
             }
        }
        return (st.size() == 0);
    }

    private void parenthesisGenerator(int upperBound, int currentDegree, String current, List<String> results) {
        if (currentDegree == 0) { // only choice is put (
            if (upperBound == 0)  // done!!
                results.add(current);
            else
                parenthesisGenerator(upperBound, currentDegree+1, current + '(', results);
        } else if (currentDegree == upperBound) { // only choice is put )
            parenthesisGenerator(upperBound-1, currentDegree-1, current + ')', results);
        } else {
            parenthesisGenerator(upperBound, currentDegree+1, current+'(', results);
            parenthesisGenerator(upperBound-1, currentDegree-1, current+')', results);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        parenthesisGenerator(n, 0, "", results);
        return results;
    }

    public int strStr(String haystack, String needle) {
        if (null == haystack || null == needle)
            return -1;
        int m = haystack.length(), n = needle.length();
        if (n == 0)
            return 0;

        for (int i = 0; i <= m-n; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                boolean match = true;
                for (int j = 1; j < n; j++) {
                    if (haystack.charAt(i+j) != needle.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    return i;
            }
        }

        return -1;

    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();

        int ls = s.length(), lwc = words.length;
        if (ls == 0 || lwc == 0) return results;

        int lw = words[0].length();
        Map<String, Integer> wordCombination = new HashMap<>();
        for (String word : words)
            wordCombination.put(word, wordCombination.getOrDefault(word, 0)+1);
        Map<String, Integer> seen = new HashMap<>();
        int j, start, count;

        for (int i = 0; i < lw; i++) {
            seen.clear();
            start = i;
            count = 0;
            for (j = i; j < ls-lw+1; j+=lw) {
                String word = s.substring(j, j+lw);
                if (wordCombination.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;
                    while (seen.get(word) > wordCombination.get(word)) {
                        String left = s.substring(start, start+lw);
                        seen.put(left, seen.get(left)-1);
                        start += lw;
                        count--;
                    }
                    if (count == lwc) {
                        results.add(start);
                        String left = s.substring(start, start+lw);
                        seen.put(left, seen.get(left)-1);
                        start += lw;
                        count--;
                    }
                } else {
                    seen.clear();
                    start = j+lw;
                    count = 0;
                }
            }
        }
        return results;
    }

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
        StringQuestions strq = new StringQuestions();
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo","bar"};
        System.out.println(strq.findSubstring(s, words));
    }
}
