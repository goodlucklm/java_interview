import java.util.Map;

public class SodukuQuestions {
    private boolean isValid(int startRow, int startColumn, int endRow, int endColumn, char[][] board) {
        boolean[] seen = new boolean[9];
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;
                if (!seen[ch-'1'])
                    seen[ch-'1'] = true;
                else
                    return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (!isValid(i,0, i, n-1, board)) {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!isValid(0, i, n-1, i, board)) {
                return false;
            }
        }

        for (int i = 0; i < n; i+=3) {
            for (int j = 0; j < n; j+=3) {
                if (!isValid(i, j, i+2, j+2, board)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
        };
        SodukuQuestions sq = new SodukuQuestions();
        System.out.println("your result is: "+sq.isValidSudoku(board));
    }
}
