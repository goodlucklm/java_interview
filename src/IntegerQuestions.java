public class IntegerQuestions {
    public int divide(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;

        if (divisor == 1)
            return dividend;
        else if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            else
                return -dividend;
        } else {
            boolean isNegative = false, moreThanOne = false;
            if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
                isNegative = true;
            if (dividend > 0)
                dividend = -dividend;
            if (divisor > 0)
                divisor = -divisor;
            int reminder = dividend, result = 0, dvg, tmp = 1;
            while (reminder <= divisor) {
                dvg = divisor;
                tmp = 1;
                moreThanOne = false;
                while (dvg >= reminder && dvg > Integer.MIN_VALUE / 2) {
                    dvg = dvg << 1;
                    tmp = tmp << 1;
                    moreThanOne = true;
                }
                if (moreThanOne) {
                    dvg = dvg >> 1;
                    tmp = tmp >> 1;
                }
                reminder -= dvg;
                result += tmp;
            }
            if (isNegative)
                result = -result;
            return result;
        }
    }

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

    public double myPow(double x, int n) {
        if (n == 0)
            return 1.0;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                n = -(n/2);
                x = 1/(x*x);
            } else {
                n = -n;
                x = 1 / x;
            }
        }
        return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }

    public static void main(String[] args) {
        IntegerQuestions iq = new IntegerQuestions();
        System.out.println(iq.myPow(2.0,-2147483648));
    }
}
