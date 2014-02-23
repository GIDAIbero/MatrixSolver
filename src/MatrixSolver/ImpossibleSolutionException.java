package MatrixSolver;

import MatrixSolver.MatrixSolverException;

public class ImpossibleSolutionException extends MatrixSolverException{
    public ImpossibleSolutionException(String m){
        super("Impossible Solution:" + m);
    }

} 
