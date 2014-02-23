package MatrixSolver;

import MatrixSolver.MatrixSolverException;

public class UnsquaredMatrixException extends MatrixSolverException{
    public UnsquaredMatrixException(String m){
        super("Unsquare Matrix:" + m);
    }
} 
