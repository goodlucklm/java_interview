public class ZigZagStringConversion {
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

    public static void main(String[] args) {
        ZigZagStringConversion zzsc = new ZigZagStringConversion();
        System.out.println("the result is: "+zzsc.convert("PAYPALISHIRING", 3));
    }
}
