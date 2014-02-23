
//import junit.framework.*;
import org.junit.Before;
import org.junit.After;
import org.junit.*;
import org.junit.Assert;
import java.lang.Math;
import java.lang.Exception;

import java.lang.System;

import MatrixSolver.LinearEquationSystem;
import MatrixSolver.UnsquaredMatrixException;
import MatrixSolver.ElementOutOfRangeException;
import MatrixSolver.ImpossibleSolutionException;

public class TestLinearEquationSystem {

    LinearEquationSystem test_matrix;

    /* Test constructor for unsquare matreces. 
     * we expect the LinearEquationSystem to throw an exception for unsquared
     * matreces
     */
    @Test(expected = UnsquaredMatrixException.class)
    public void test_unsquared_constructor() throws UnsquaredMatrixException{
        System.out.print("Testing unsquared matrix constructor...\n\t");
        try{
            test_matrix = new LinearEquationSystem(10,11);
        }catch(UnsquaredMatrixException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test case for negative values on the constructor. 
     * an UnsquaredMatrixException should be thrown in case the constructor 
     * containes negative indeces, for either rows or columns.
     */
    @Test(expected = UnsquaredMatrixException.class)
    public void test_negative_rows_constructor()
        throws UnsquaredMatrixException{
        System.out.print("Testing negative value matrix constructor...\n\t");
        try{
            test_matrix = new LinearEquationSystem(-1,10);
        }catch(UnsquaredMatrixException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test case for negative values on the constructor. 
     * an UnsquaredMatrixException should be thrown in case the constructor 
     * containes negative indeces, for either rows or columns.
     */
    @Test(expected = UnsquaredMatrixException.class)
    public void test_negative_columns_constructor()
        throws UnsquaredMatrixException{
        System.out.print("Testing negative value matrix constructor...\n\t");
        try{
            test_matrix = new LinearEquationSystem(10,-1);
        }catch(UnsquaredMatrixException e){
            System.out.println(e.toString());
            throw e;
        }
    }
   

    /* Test case for invalid ranges in the setValueAt functions */
    /* Test for negative indeces in the setValueAt method
     * Since the row or the column value is negative, an 
     * ElementOutOfRangeException should be thrown
     */
    @Test(expected = ElementOutOfRangeException.class)
    public void test_negative_row_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        System.out.print("Testing negative index set for rows...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(-1,1,10.0f);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test for negative indeces in the setValueAt method
     * Since the row or the column value is negative, an 
     * ElementOutOfRangeException should be thrown
     */
    @Test(expected = ElementOutOfRangeException.class)
    public void test_negative_column_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        System.out.print("Testing negative index set for columns...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(1,-1,10.0f);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }


    /* Test for overflow indeces in the setValueAt method
     * Since the row or the column value is negative, an 
     * ElementOutOfRangeException should be thrown
     */
    @Test(expected = ElementOutOfRangeException.class)
    public void test_overflow_column_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        System.out.print("Testing overflow index set for columns...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(1,width+1,10.0f);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }


    /* Test for overflow indeces in the setValueAt method
     * Since the row or the column value is negative, an 
     * ElementOutOfRangeException should be thrown
     */
    @Test(expected = ElementOutOfRangeException.class)
    public void test_overflow_row_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        System.out.print("Testing overflow index set for rows...\n\t");
        
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(height,1,10.0f);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test for edge cases in the setValueAt function: we try to set the values
     * at (0,0). (0,width-1), (0,width-1), (height-1,0), (height-1,width-1) and
     * (height-1,width). This traverses all possible combinations for a valid
     * set value at call:
     *  Set a value in the lowest possible element:          0,0
     *  Set a value in the top-right corner for the matrix   0,width-1
     *  Set a value in the base element of the result vector 0,width
     *  Set a value in the bottom-left corner                height-1,0
     *  Set a value in the bottom-right corner of the matrix height-1,width-1
     *  Set a value in the top element of the result vector  height-1,width 
     */
    @Test
    public void test_edge_cases_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            //(0.0)
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(0,0,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(0,width-1,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(0,width,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(height-1,0,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(height-1,width-1,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(height-1,width-1,10.0f),0.1);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test singular exception
     *
     * If a singular matrix is provided, the algorithm should throw an exception
     * notifying that the matrix provided is singular and, hence, unsolvable.
     *
     */
    @Test(expected = ImpossibleSolutionException.class)
    public void test_singular_matrix_solve() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        System.out.print("Testing singular matrix solve method...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,0.0f);
            test_matrix.setValueAt(0,1,0.0f);
            test_matrix.setValueAt(1,0,0.0f);
            test_matrix.setValueAt(1,1,0.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    @Test(expected = ImpossibleSolutionException.class)
    public void test_singular_matrix_solve2() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        System.out.print("Testing singular matrix solve method...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,3.0f);
            test_matrix.setValueAt(0,1,1.0f);
            test_matrix.setValueAt(0,2,5.0f);
            test_matrix.setValueAt(1,0,6.0f);
            test_matrix.setValueAt(1,1,2.0f);
            test_matrix.setValueAt(1,2,15.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test unsolvable matrix exception
     * 
     * If the Linear Equation System provided doesn't have a unique solution
     * ImpossibleSolutionException will be thrown
     */
    @Test(expected = ImpossibleSolutionException.class)
    public void test_unsolvable_matrix_solve() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        System.out.print("Testing unsolvable matrix solve method...\n\t");
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,4.0f);
            test_matrix.setValueAt(0,1,2.0f);
            test_matrix.setValueAt(0,2,-120.0f);
            test_matrix.setValueAt(1,0,-2.0f);
            test_matrix.setValueAt(1,1,-1.0f);
            test_matrix.setValueAt(1,2,-22.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            System.out.println(e.toString());
            throw e;
        }
    }


    /* Test solvable matrix coherence
     *
     * provided a solvable matrix, we expect the results to be coherent with an
     * existing well known 2x2 matrix
     * 
     */
    @Test
    public void test_solvable_matrix_size_2_solve() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        float[][] matrix; 
        float[]   resultVector;
        float[][] knownMatrix = new float[height][width];
        float[]   knownResults = new float[height];
        
        knownMatrix[0][0] = .333f;
        knownMatrix[0][1] = -.333f;
        knownMatrix[1][0] = -.667f;
        knownMatrix[1][1] = 1.667f;
    
        knownResults[0] = .667f;
        knownResults[1] = -.333f;
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,5.0f);
            test_matrix.setValueAt(0,1,1.0f);
            test_matrix.setValueAt(0,2,3.0f);
            test_matrix.setValueAt(1,0,2.0f);
            test_matrix.setValueAt(1,1,1.0f);
            test_matrix.setValueAt(1,2,1.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            resultVector = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        matrix = test_matrix.returnMatrix();
        Assert.assertArrayEquals(knownResults,resultVector,.01f);
        Assert.assertArrayEquals(knownMatrix[0],matrix[0],.01f);
        Assert.assertArrayEquals(knownMatrix[1],matrix[1],.01f);
    }

    /* Test solvable matrix coherence 2
     *
     * provided a solvable matrix, we expect the results to be coherent with an
     * existing well known 2x2 matrix. This second test will attempt to perform
     * the same with bigger values
     * 
     */
    @Test
    public void test_solvable_matrix_size_2_solve2() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        float[][] matrix; 
        float[]   resultVector;
        float[][] knownMatrix = new float[height][width];
        float[]   knownResults = new float[height];
        
        knownMatrix[0][0] = .00343f;
        knownMatrix[0][1] = .00412f;
        knownMatrix[1][0] = -.06902f;
        knownMatrix[1][1] = .01717f;
    
        knownResults[0] =  .58516f;
        knownResults[1] = -.56181f;
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,  50.0f);
            test_matrix.setValueAt(0,1, -12.0f);
            test_matrix.setValueAt(0,2,  36.0f);
            test_matrix.setValueAt(1,0, 201.0f);
            test_matrix.setValueAt(1,1,  10.0f);
            test_matrix.setValueAt(1,2, 112.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            resultVector = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        matrix = test_matrix.returnMatrix();
        Assert.assertArrayEquals(knownResults,resultVector,.0001f);
        Assert.assertArrayEquals(knownMatrix[0],matrix[0],.0001f);
        Assert.assertArrayEquals(knownMatrix[1],matrix[1],.0001f);
    }


    /* Test solvable matrix coherence 3
     *
     * provided a solvable matrix, we expect the results to be coherent with an
     * existing well known 3x3 matrix. This third test will attempt to perform
     * the same with a bigger matrix
     * 
     */
    @Test
    public void test_solvable_matrix_size_3_solve() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=3;
        int width=3;
        float[][] matrix; 
        float[]   resultVector;
        float[][] knownMatrix = new float[height][width];
        float[]   knownResults = new float[height];
        
        knownMatrix[0][0] = -.2f;
        knownMatrix[0][1] =  .6f;
        knownMatrix[0][2] = -.2f;

        knownMatrix[1][0] =  .6f;
        knownMatrix[1][1] =  .2f;
        knownMatrix[1][2] = -.4f;
        
        knownMatrix[2][0] =  0f;
        knownMatrix[2][1] = -1f;
        knownMatrix[2][2] =  1f;
    
        knownResults[0] =   .6f;
        knownResults[1] =  1.2f;
        knownResults[2] = -2.0f;
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,   1.0f);
            test_matrix.setValueAt(0,1,   2.0f);
            test_matrix.setValueAt(0,2,   1.0f);
            test_matrix.setValueAt(0,3,   1.0f);

            test_matrix.setValueAt(1,0,   3.0f);
            test_matrix.setValueAt(1,1,   1.0f);
            test_matrix.setValueAt(1,2,   1.0f);
            test_matrix.setValueAt(1,3,   1.0f);
            
            test_matrix.setValueAt(2,0,   3.0f);
            test_matrix.setValueAt(2,1,   1.0f);
            test_matrix.setValueAt(2,2,   2.0f);
            test_matrix.setValueAt(2,3,  -1.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            resultVector = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        matrix = test_matrix.returnMatrix();
        Assert.assertArrayEquals(knownResults,resultVector,.001f);
        Assert.assertArrayEquals(knownMatrix[0],matrix[0],.001f);
        Assert.assertArrayEquals(knownMatrix[1],matrix[1],.001f);
        Assert.assertArrayEquals(knownMatrix[2],matrix[2],.001f);
    }


    /* Test solvable matrix coherence 4
     *
     * provided a solvable matrix, we expect the results to be coherent with an
     * existing well known 3x3 matrix. This third test will attempt to perform
     * the same with a bigger matrix and bigger numbers
     * 
     */
    @Test
    public void test_solvable_matrix_size_3_solve2() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=3;
        int width=3;
        float[][] matrix; 
        float[]   resultVector;
        float[][] knownMatrix = new float[height][width];
        float[]   knownResults = new float[height];
        
        knownMatrix[0][0] = -.00065f;
        knownMatrix[0][1] = -.00159f;
        knownMatrix[0][2] =  .01779f;

        knownMatrix[1][0] = -.00195f;
        knownMatrix[1][1] =  .004651f;
        knownMatrix[1][2] =  .04833f;
        
        knownMatrix[2][0] =  .0215f;
        knownMatrix[2][1] =  .00064f;
        knownMatrix[2][2] = -.05930f;
    
        knownResults[0] = -.12226f;
        knownResults[1] = -.34102f;
        knownResults[2] = 3.393f;
        try{
            test_matrix = new LinearEquationSystem(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0, 100.0f);
            test_matrix.setValueAt(0,1,  27.0f);
            test_matrix.setValueAt(0,2,  52.0f);
            test_matrix.setValueAt(0,3, 155.0f);

            test_matrix.setValueAt(1,0,-301.0f);
            test_matrix.setValueAt(1,1, 112.0f);
            test_matrix.setValueAt(1,2,   1.0f);
            test_matrix.setValueAt(1,3,   2.0f);
            
            test_matrix.setValueAt(2,0,  33.0f);
            test_matrix.setValueAt(2,1,  11.0f);
            test_matrix.setValueAt(2,2,   2.0f);
            test_matrix.setValueAt(2,3,  -1.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            resultVector = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        matrix = test_matrix.returnMatrix();
        Assert.assertArrayEquals(knownResults,resultVector,.0001f);
        Assert.assertArrayEquals(knownMatrix[0],matrix[0],.00001f);
        Assert.assertArrayEquals(knownMatrix[1],matrix[1],.00001f);
        Assert.assertArrayEquals(knownMatrix[2],matrix[2],.00001f);
    }

}
