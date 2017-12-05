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

    public boolean solveMySudoku(char[][] board) {
        if (!isValidSudoku(board))
            return false;
        boolean matched = false;
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++ ) {
                        board[i][j] = ch;
                        if (solveMySudoku(board))
                            return true;
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        solveMySudoku(board);
        return;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        SodukuQuestions sq = new SodukuQuestions();
        sq.solveSudoku(board);
        System.out.println("your result is: "+sq.isValidSudoku(board));
    }
}
