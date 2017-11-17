import java.util.ArrayList;
import java.util.List;

public class PhoneNumberLetterCombination {
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

    public static void main(String[] args) {
        PhoneNumberLetterCombination pnlc = new PhoneNumberLetterCombination();
        pnlc.letterCombinations("2");
    }
}
