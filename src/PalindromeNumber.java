public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int y = x;
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            if (Math.abs(result) > Integer.MAX_VALUE)
                return false;
            x /= 10;
        }
        return result == y;

    }

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        int n = -2147447412;
        System.out.println(""+n+" is a palindrome? "+pn.isPalindrome(n));
    }
}
