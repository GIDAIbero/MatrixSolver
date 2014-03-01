MatrixSolver
============

# What is this?
A simple Java library to do Matrix operations and transformations.
 This lightweight set of classes can be easily added to any project
 and provide basic matrix-operation capabilities. 
 
 This code is an adaptation of the code presented in the book
 "Numerical Recipes in C" by Press, Flannery, Teukolsky and Vetterling,
 [it's a very good read](http://www.amazon.com/Numerical-Recipes-Scientific-Computing-Edition/dp/0521431085%3FSubscriptionId%3DAKIAILSHYYTFIVPWUY6Q%26tag%3Dduckduckgo-d-20%26linkCode%3Dxm2%26camp%3D2025%26creative%3D165953%26creativeASIN%3D0521431085)
 
# What does it do?
 
  The basic purpose of this library is to make it an extendible set 
  of classes that provide matrix operations. The functions implemented
  so far are the Linear Equation System and the Determinant, since they
  are used in our Android version of MATSOL. 
  
  The main idea is to make this an extensible project so, hopefully, more
  extensions can be added in order to better the reach of this library.
  
# How do I use it?
 
  This is intended to be a really simple utility, no special dependencies,
  no special compilation steps, no jars, etc. So you should have this 
  prepared and added to your project in no time.
  
## How to install?
 
  Simply copy the MatrixSolver folder to your project, everything should 
  compile fine. 
  
### But I want to run the tests!
 
  The tests can be compiled using [ant](https://ant.apache.org/), the tests
  should run automatically and, if everything behaves as expected, all should 
  pass. 
  
  You can also consult the code in the tests for some examples of usage. But
  don't worry, examples will be presented here too.
  
## Example Usage with Determinants.
 
  After copying the folder with the classes (and probably add everything to your
  classpath), you can use a snippet like the following to calculate a determinant:
  ```java 
    import MatrixSolver.*;
    ...
    try{
      Determinant determinant = new Determinant(height,width);
      for(int i = 0; i < height; i++ ){
        for( int j = 0; j < width; j++ ){
          determinant.setValueAt( i, j, value);
        }
      }
      result = determinant.solve();
    }catch(UnsquaredMatrixException e){
     // in case you try to solve an unsquared determinant
    }catch(ElementOutOfRangeException e){
     // in case you try to set a value out of range
    }
  ```
  
## Example usage with Linear Equation Systems
 
  The same applies for a Linear Equation system. Do take into consideration
  that LinearEquationSystems have a result vector, you can set them by using
  the setValueAt function with the column being equal to the width. The 
  following snippet will illustrate this.
  ```java
  import MatrixSolver.*;
  ...
  try{
    LinearEquationSystem leq = new LinearEquationSystem(height,width);
    
    for(int i = 0; i < height; i++){
      for( int j = 0; j <= width; j++){
        leq.setValueAt(height,width,value);
      }
    }
    result = leq.solve();
    inverseMatrix = leq.returnMatrix();
  }catch(UnsquaredMatrixException e){
    // in case you don't send appropriate values
    // the result vector is implied, you do not have
    // to set it
  }catch(ElementOutOfRangeException e){
    // in case you try to set something out of bounds
  }catch(ImpossibleSolutionException e){
    // in case the matrix is not solvable. 
  }
  ```
  
# Other Information
  This code is licenced using the MIT license, by the Software 
  Development Group at Universidad Iberoamericana (Campus Ciudad
  de México)
    
  
