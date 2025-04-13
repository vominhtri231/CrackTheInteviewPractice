package tri.vo.cracktheinteview.arraystring;

public class ZeroMatrix {

    void setZeroMatrix(int[][] matrix) {
        boolean isZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    isZero = true;
                    break;
                }
            }
        }
        if (!isZero) return;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        ZeroMatrix zeroMatrix = new ZeroMatrix();

        int[][] matrix1 = {{1, 2, 3, 4}, {4, 5, 6, 6}, {7, 8, 9, 9}};
        zeroMatrix.setZeroMatrix(matrix1);

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] == 0) {
                    throw new AssertionError();
                }
            }
        }

        int[][] matrix2 = {{1, 2}, {4, 0}, {7, 9}};
        zeroMatrix.setZeroMatrix(matrix2);
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                if (matrix2[i][j] != 0) {
                    throw new AssertionError();
                }
            }
        }
    }
}
