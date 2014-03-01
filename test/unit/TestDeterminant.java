/**************** 
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 GIDA Ibero (Campus Ciudad de México)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 ****************/ 

import org.junit.Before;
import org.junit.After;
import org.junit.*;
import org.junit.Assert;
import java.lang.Math;
import java.lang.Exception;

import java.lang.System;

import MatrixSolver.Determinant;
import MatrixSolver.UnsquaredMatrixException;
import MatrixSolver.ElementOutOfRangeException;
import MatrixSolver.ImpossibleSolutionException;

public class TestDeterminant {

    Determinant test_matrix;

    /* Test constructor for unsquare matreces. 
     * we expect the Determinant to throw an exception for unsquared
     * matreces
     */
    @Test(expected = UnsquaredMatrixException.class)
    public void test_unsquared_constructor() throws UnsquaredMatrixException{
        System.out.print("Testing unsquared matrix constructor...\n\t");
        try{
            test_matrix = new Determinant(10,11);
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
            test_matrix = new Determinant(-1,10);
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
            test_matrix = new Determinant(10,-1);
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
            test_matrix = new Determinant(height,width);
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
            test_matrix = new Determinant(height,width);
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
            test_matrix = new Determinant(height,width);
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
            test_matrix = new Determinant(height,width);
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
     * at (0,0). (0,width-1), (height-1,0), (height-1,width-1) (height-1,width).
     * This traverses all possible combinations for a valid
     * set value at call:
     *  Set a value in the lowest possible element:          0,0
     *  Set a value in the top-right corner for the matrix   0,width-1
     *  Set a value in the bottom-left corner                height-1,0
     *  Set a value in the bottom-right corner of the matrix height-1,width-1
     */
    @Test
    public void test_edge_cases_setValueAt() throws 
                          UnsquaredMatrixException,ElementOutOfRangeException{
        int height=5;
        int width=5;
        try{
            test_matrix = new Determinant(height,width);
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
                    test_matrix.setValueAt(height-1,0,10.0f),0.1);
            Assert.assertEquals(10.0f,
                    test_matrix.setValueAt(height-1,width-1,10.0f),0.1);
        }catch(ElementOutOfRangeException e){
            System.out.println(e.toString());
            throw e;
        }
    }

    /* Test determinant equals 0 case.
     *
     *  If the values for the determinant are predicted to be 0, the program
     *  returns an exception indicating that the value should be 0.
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
            test_matrix = new Determinant(height,width);
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
        float[] result;
        try{
            test_matrix = new Determinant(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,2.0f);
            test_matrix.setValueAt(0,1,5.0f);
            test_matrix.setValueAt(1,0,1.0f);
            test_matrix.setValueAt(1,1,7.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            result = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        Assert.assertEquals(9.0f,result[0],.00001f);
    }

    /* Test solvable matrix coherence 2
     *
     * provided a solvable matrix, we expect the results to be coherent with an
     * existing well known 2x2 matrix. This second test will attempt to perform
     * the same with bigger value
     * 
     */
    @Test
    public void test_solvable_matrix_size_2_solve2() throws 
            ImpossibleSolutionException,UnsquaredMatrixException,
            ElementOutOfRangeException{
        
        int height=2;
        int width=2;
        float[] result;
        
        try{
            test_matrix = new Determinant(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,  20.0f);
            test_matrix.setValueAt(0,1, 400.0f);
            test_matrix.setValueAt(1,0,1700.0f);
            test_matrix.setValueAt(1,1,   5.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            result = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        Assert.assertEquals(-679900.00f,result[0],.00001f);
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
        float[] result;
        
        try{
            test_matrix = new Determinant(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0,   5.0f);
            test_matrix.setValueAt(0,1,   1.0f);
            test_matrix.setValueAt(0,2,   0.0f);

            test_matrix.setValueAt(1,0,   5.0f);
            test_matrix.setValueAt(1,1,   3.0f);
            test_matrix.setValueAt(1,2,   1.0f);
            
            test_matrix.setValueAt(2,0,   3.0f);
            test_matrix.setValueAt(2,1,   2.0f);
            test_matrix.setValueAt(2,2,   5.0f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            result = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        Assert.assertEquals(43.0f,result[0],.00001f);
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
        float[] result;
        
        try{
            test_matrix = new Determinant(height,width);
        }catch(UnsquaredMatrixException e){
            throw e;
        }
        try{
            test_matrix.setValueAt(0,0, -53.0f);
            test_matrix.setValueAt(0,1,  10.0f);
            test_matrix.setValueAt(0,2,  28.0f);

            test_matrix.setValueAt(1,0, 501.0f);
            test_matrix.setValueAt(1,1,-300.0f);
            test_matrix.setValueAt(1,2,   1.0f);
            
            test_matrix.setValueAt(2,0,  19.0f);
            test_matrix.setValueAt(2,1,   2.0f);
            test_matrix.setValueAt(2,2,   0.5f);
        }catch(ElementOutOfRangeException e){
            throw e;
        }
        try{
            result = test_matrix.solve();
        }catch(ImpossibleSolutionException e){
            throw e;
        }
        Assert.assertEquals(193397.01562f,result[0],.00001f);
    }

}
