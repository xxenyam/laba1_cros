package laba1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Task4 {

    public static void main(String[] args) {
        // Приклади створення та використання масивів
        Integer[] intArray = createRandomArray(Integer.class, 5);
        System.out.println("Масив int: " + Arrays.toString(intArray));

        String[] stringArray = createRandomArray(String.class, 3);
        System.out.println("Масив String : " + Arrays.toString(stringArray));

        Double[] doubleArray = createRandomArray(Double.class, 4);
        System.out.println("Масив Double : " + Arrays.toString(doubleArray));

        // Приклади створення та використання матриць
        Integer[][] intMatrix = createRandomMatrix(Integer.class, 3, 5);
        System.out.println("Матриця Int(" + intMatrix.length + "x" + intMatrix[0].length + "):");
        printMatrix(intMatrix);

        intMatrix = resizeMatrix(intMatrix, 4, 6);
        System.out.println("Змінена матриця Int (" + intMatrix.length + "x" + intMatrix[0].length + "):");
        printMatrix(intMatrix);

        intMatrix = resizeMatrix(intMatrix, 3, 7);
        System.out.println("Змінена матриця Int (" + intMatrix.length + "x" + intMatrix[0].length + "):");
        printMatrix(intMatrix);

        Integer[][] intMatrix2 = createRandomMatrix(Integer.class, 2, 2);
        System.out.println("Інша матриця Int (" + intMatrix2.length + "x" + intMatrix2[0].length + "):");
        printMatrix(intMatrix2);
    }

    public static <T> T[] createRandomArray(Class<T> type, int length) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(type, length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if (type.equals(Integer.class)) {
                array[i] = type.cast(random.nextInt(100));
            } else if (type.equals(Double.class)) {
                array[i] = type.cast(random.nextDouble() * 100);
            } else if (type.equals(String.class)) {
                array[i] = type.cast("String" + random.nextInt(100));
            }
        }
        return array;
    }

    public static <T> T[][] createRandomMatrix(Class<T> type, int rows, int cols) {
        @SuppressWarnings("unchecked")
        T[][] matrix = (T[][]) Array.newInstance(type, rows, cols);
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (type.equals(Integer.class)) {
                    matrix[i][j] = type.cast(random.nextInt(100));
                } else if (type.equals(Double.class)) {
                    matrix[i][j] = type.cast(random.nextDouble() * 100);
                } else if (type.equals(String.class)) {
                    matrix[i][j] = type.cast("String" + random.nextInt(100));
                }
            }
        }
        return matrix;
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (T[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static <T> T[][] resizeMatrix(T[][] matrix, int newRows, int newCols) {
        int oldRows = matrix.length;
        T[][] resizedMatrix = Arrays.copyOf(matrix, newRows);
        for (int i = 0; i < newRows; i++) {
            if (i < oldRows) {
                resizedMatrix[i] = Arrays.copyOf(matrix[i], newCols);
            } else {
                resizedMatrix[i] = createRandomArray((Class<T>) matrix.getClass().getComponentType().getComponentType(), newCols);
            }
        }
        return resizedMatrix;
    }
}




