package com.company;


public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] content;

    public Matrix(int rows, int columns) throws MatrixException {
        if (columns <= 0) throw new MatrixException("Incorrect columns");
        if (rows <= 0) throw new MatrixException("Incorrect rows");

        this.rows = rows;
        this.columns = columns;
        content = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                content[i][j] = 0;
            }
        }
    }

    public Matrix(double[][] array) throws MatrixException {
        if (array == null) throw new MatrixException("NULL array received");

        content = array;
        rows = array.length;
        columns = array[0].length;
    }

    public int getRows() {

        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getContent() {
        return content;
    }

    public double getValue(int rows, int columns) throws MatrixException {
        if (rows > this.rows || rows < 0) throw new MatrixException("Invalid parameter: rows");
        if (columns > this.columns || columns < 0) throw new MatrixException("Incorrect parameter: columns");

        return content[rows][columns];
    }

    public void setValue(int rows, int columns, double value) throws MatrixException {
        if (rows > this.rows || rows < 0) throw new MatrixException("Invalid parameter: rows");
        if (columns > this.columns || columns < 0) throw new MatrixException("Incorrect parameter: columns");
        content[rows][columns] = value;
    }

    public Matrix addition(Matrix matrix2) throws MatrixException {
        compatibilityTest(matrix2);
        Matrix matrixResult = new Matrix(this.rows, this.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrixResult.setValue(i, j, this.getValue(i, j) + matrix2.getValue(i, j));
            }
        }
        return matrixResult;
    }

    public Matrix subtraction(Matrix matrix2) throws MatrixException {
        compatibilityTest(matrix2);
        Matrix matrixResult = new Matrix(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrixResult.setValue(i, j, this.getValue(i, j) - matrix2.getValue(i, j));
            }
        }
        return matrixResult;
    }

    public Matrix multiplication(Matrix matrix2) throws MatrixException {
        if (this.columns != matrix2.rows) throw new MatrixException("Incompatible matrix");

        Matrix matrixResult = new Matrix(this.rows, matrix2.columns);
        for (int i = 0; i < matrixResult.rows; i++) {
            for (int j = 0; j < matrixResult.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    matrixResult.setValue(i, j, this.getValue(i, k) * matrix2.getValue(k, j) + matrixResult.getValue(i, j));
                }
            }
        }
        return matrixResult;
    }


    public void display() {

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.content[i][j] + ", ");
            }
            System.out.println();
        }
    }

    private void compatibilityTest(Matrix matrix2) throws MatrixException {
        if (matrix2.content.length != this.content.length ||
                matrix2.content[0].length != this.content[0].length) throw new MatrixException("incompatible matrix");
    }
}
