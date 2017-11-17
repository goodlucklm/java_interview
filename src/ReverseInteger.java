public class ReverseInteger {
    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            if (Math.abs(result) > Integer.MAX_VALUE)
                return 0;
            x /= 10;
        }
        return (int)result;
    }
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        int abc = -2147483648;
        System.out.println("you get: "+abc);
    }
}
