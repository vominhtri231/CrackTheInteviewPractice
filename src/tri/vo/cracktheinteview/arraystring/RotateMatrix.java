package tri.vo.cracktheinteview.arraystring;

import java.util.Arrays;

public class RotateMatrix {

    void rotate(int[][] matrix) {
        flipFirstDiagonal(matrix);
        flipX(matrix);
    }

    private void flipX(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix,
                        i, j,
                        i, n - 1 - j);
            }
        }
    }

    private void flipFirstDiagonal(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix,
                        i, j,
                        j, i);
            }
        }
    }

    private void swap(int[][] matrix, int a, int b, int c, int d) {
        int temp = matrix[a][b];
        matrix[a][b] = matrix[c][d];
        matrix[c][d] = temp;
    }

    public static void main(String[] args) {
        RotateMatrix rotateMatrix = new RotateMatrix();

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotateMatrix.rotate(matrix);
        int[][] wanted = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        if (!Arrays.deepEquals(wanted, matrix)) {
            throw new AssertionError();
        }
    }
}
