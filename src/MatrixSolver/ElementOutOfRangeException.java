package MatrixSolver;

import MatrixSolver.MatrixSolverException;

public class ElementOutOfRangeException extends MatrixSolverException{
    public ElementOutOfRangeException(String m){
        super("Element Out Of Range:" + m);
    }
} 
