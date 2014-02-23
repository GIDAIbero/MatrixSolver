package MatrixSolver;

import java.lang.Exception;

public class MatrixSolverException extends Exception{
    String message;
    public MatrixSolverException(String m){
        super();
        message = new String(m);
    }

    public String toString(){
        return message;
    }
} 
