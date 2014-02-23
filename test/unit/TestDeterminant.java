package TestMatrixSolver;

//import junit.framework.*;
import org.junit.Before;
import org.junit.After;
import org.junit.*;
import java.lang.Math;
import java.lang.Exception;

import java.lang.System;

import MatrixSolver.Matrix;
import MatrixSolver.UnsquaredMatrixException;
public class TestDeterminant {

    Matrix test_matrix;
    // Test constructor for unsquare matreces. 
    @Test(expected = UnsquaredMatrixException.class)
    public void test_constructor_1(){
        //test_matrix = new Matrix(10,11);
    }

    @Test public void testTwo(){
        System.out.println("during");
    }

}
