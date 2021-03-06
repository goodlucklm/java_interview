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

    private final String[] NUMBERTOLETTER = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits.length() == 0) return results;
        results.add(digits);
        replaceNumberWithLetter(results);
        return results;
    }

    private void replaceNumberWithLetter(List<String> results) {
        int n = results.size();
        if (n == 0) return;
        if (!Character.isDigit(results.get(0).charAt(0))) return;

        for (int i = 0; i < n; i++) {
            String s = results.remove(0);
            if (s.length() > 0 && Character.isDigit(s.charAt(0))) {
                char c = s.charAt(0);
                s = s.substring(1);
                int j = Character.getNumericValue(c);
                for (int k = 0; k < NUMBERTOLETTER[j].length(); k++) {
                    results.add(s+NUMBERTOLETTER[j].charAt(k));
                }
            }
        }
        replaceNumberWithLetter(results);
    }

    private int getNextRow(int totalRows, int elementIndex) {
        int trend = elementIndex % (totalRows+totalRows-2);
        if (trend < totalRows)
            return trend;
        else {
            int shift = trend - totalRows;
            return (totalRows-2) - shift;
        }
    }

    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;

        // construct the holding structure
        String[] results = new String[numRows];
        for (int i = 0; i < results.length; i++) {
            results[i] = new String();
        }

        // split the stirng in segments of leng numRows+numRows-2
        for (int i = 0; i < s.length(); i++) {
            int row = getNextRow(numRows, i);
            results[row] += s.charAt(i);
        }

        String result = "";
        for (String r : results)
            result += r;
        return result;
    }

    public int longestValidParentheses(String s) {
        // check if s is null
        if (null == s)
            return 0;
        int maxLength = 0, n = s.length(), currLength, dgree = 0, i = 0;
        int[] dgree_recorder = new int[n+1];
        dgree_recorder[0] = -1;

        while (i < n) {
            char ch = s.charAt(i);
            if (ch == '(') {
                dgree++;
                dgree_recorder[dgree] = i;
            } else if (dgree > 0) {
                dgree--;
                currLength = i - dgree_recorder[dgree];
                maxLength = Math.max(currLength, maxLength);
            } else {
                dgree_recorder[0] = i;
                dgree = 0;
            }
            i++;
        }
        return maxLength;
    }

    public String countAndSay(int n) {
        List<Integer> ints = new ArrayList<>();
        List<Integer> turn;
        ints.add(1);
        int count = 0, i, size, current;
        while (n > 1) {
            size = ints.size();
            i = 0;
            turn = new ArrayList<>();
            while (i < size) {
                current = ints.get(i);
                count = 0;
                while (count + i < size && ints.get(count + i) == current)
                    count++;
                turn.add(count);
                turn.add(current);
                i += count;
            }
            ints = turn;
            n--;
        }
        String res = "";
        for (int j : ints)
            res += j;
        return res;
    }

    public String multiply(String num1, String num2) {
        StringBuilder res = new StringBuilder("0");
        int carrier = 0, product = 0, digit = 0, level = 0, shift = 0, position, current, sum, n;
        char n1, n2;
        if (null == num1 || null == num2 || "0".equals(num1) || "0".equals(num2))
            return res.toString();
        for (int i = num1.length()-1; i >=0; i--) {
            n1 = num1.charAt(i);
            for (int j = num2.length()-1; j >=0; j--) {
                n2 = num2.charAt(j);
                product = (n1-'0')*(n2-'0');
                carrier = product/10;
                digit = product%10;
                n = res.length();
                position = n-1 - (level+shift);
                while (position < 0) {
                    res.insert(0, '0');
                    position++;
                }
                current = res.charAt(position);
                sum = (current-'0')+digit;
                digit = sum%10;
                carrier += sum/10;
                res.setCharAt(position, Character.forDigit(digit, 10));
                while (carrier > 0) {
                    position--;
                    if (position >= 0) {
                        current = res.charAt(position);
                        sum = current - '0' + carrier;
                        digit = sum % 10;
                        carrier = sum / 10;
                        res.setCharAt(position, Character.forDigit(digit, 10));
                    } else {
                        res = res.insert(0, Character.forDigit(carrier,10));
                        carrier = 0;
                    }
                }
                shift++;
            }
            level++;
            shift = 0;
        }
        return res.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (null == strs)
            return res;
        int n = strs.length;
        char[] chs;
        String sorted;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            chs = s.toCharArray();
            Arrays.sort(chs);
            sorted = new String(chs);
            if (map.containsKey(sorted))
                map.get(sorted).add(s);
            else {
                List<String> curr = new ArrayList<>();
                curr.add(s);
                res.add(curr);
                map.put(sorted, curr);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        StringQuestions strq = new StringQuestions();
        System.out.println("Hey, you got this: "+strq.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
