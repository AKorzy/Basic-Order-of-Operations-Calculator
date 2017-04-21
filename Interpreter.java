/**
 * Interpreter.java - parses string input representing an infix arithmetic
 *                 expression into tokens, and builds an expression tree.
 *                 The expression can use the operators =, +, -, *, /, %.
 *                 and can contain arbitrarily nested parentheses.
 *                 The = operator is assignment and must be absolutely lowest
 *                 precedence.
 * March 2013
 * rdb
 */
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Interpreter //extends JFrame
{
   //----------------------  class variables  ------------------------
    private String file = "";
   //---------------------- instance variables ----------------------
   private boolean      _printTree = true;  // if true print tree after each
   private SymbolTable table;//    expression tree is built 
   private  Stack< EToken > randStack;
   private  Stack< Operator > opStack; 
       
   
   //----------- constants
   
   //--------------------------- constructor -----------------------
   /**
    * If there is a command line argument use it as an input file.
    * otherwise invoke an interactive dialog.
    */
   public Interpreter( String[] args ) 
   {      
      if ( args.length > 0 )
         processFile( args[ 0 ] );
      else
         interactive();      
   }
   //--------------------- processFile -----------------------------
   /**
    * Given a String containing a file name, open the file and read it.
    * Each line should represent an expression to be parsed.
    */
   public void processFile( String fileName )
   {
        file = fileName; 
        try
        {
            FileReader reader = new FileReader( file );
            BufferedReader bread = new BufferedReader( reader );
            while( ( fileName = bread.readLine() ) != null )
            {
                processLine( fileName );
            }
            bread.close();
        }
        catch( FileNotFoundException fnfex ) 
        {
            System.out.println( "Unable to open file " + fileName );
        }
        catch( IOException ioex ) 
        {
            System.out.println( "Error reading file:" + fileName );
        }
    
   }
   //--------------------- processLine -----------------------------
   /**
    * Parse each input line -- it shouldn't matter whether it comes from
    * the file or the popup dialog box. It might be convenient to return
    * return something to the caller in the form of a String that can
    * be printed or displayed.
    */
   public String processLine( String line )
   {
       System.out.println(  "Input: " + line );
       String trimmed = line.trim();
       table = new SymbolTable();
       Float f = 0.0f;
       EvalInfix( line );
       System.out.println( randStack );
       System.out.println( opStack );
       if ( trimmed.length() == 0 || trimmed.charAt( 0 ) == '#' )
           return line;
       else
       {
           if( !randStack.isEmpty() )
           {
               EToken op = randStack.pop();
               f = op.eval();
           }
       }
       
        /*
         * get expression
         * build tree
         * print( based on boolean) 
         * evaluate tree
         * return value
         **/
       return Float.toString( f );
   }
   //--------------------- interactive -----------------------------
   /**
    * Use a file chooser to get a file name interactively, then 
    * go into a loop prompting for expressions to be entered one
    * at a time.
    */
   public void interactive()
   {
      JFileChooser fChooser = new JFileChooser( "." );
      fChooser.setFileFilter( new TextFilter() );
      int choice = fChooser.showDialog( null, "Pick a file of expressions" );
      if ( choice == JFileChooser.APPROVE_OPTION )
      {
         File file = fChooser.getSelectedFile();
         if ( file != null )
            processFile( file.getName() );
      }
      
      String prompt = "Enter an arithmetic expression: ";
      String expr = JOptionPane.showInputDialog( null, prompt );
      while ( expr != null && expr.length() != 0 )
      {
         String result = processLine( expr );
         JOptionPane.showMessageDialog( null, expr + "\n" + result );
         expr = JOptionPane.showInputDialog( null, prompt );
      }
   }
   /**
    * 
    * 
    * 
    * 
    */
   public void EvalInfix( String line )
   {
       /* create empty opStack, randStack
        for each token in infix expression
        if token is operand
        randStack.push( new operandNode )
        else if token is "("
        opStack.push( token )
        else if token is ")"
        while opStack.top() != "("
        pushOpNode( opStack.pop())
        opStack.pop() // pop "("
        else // it is an operator
        while ( opStack is not empty
        and prec(top) >= prec(token))
        pushOpNode( opStack.pop())
        opStack.push( token )
        // copy remaining operators to output
        while ( opStack !empty )
        pushOpNode( opStack.pop())          */
       ArrayList<EToken> tokens = new ArrayList<EToken>();
       Scanner scan = new Scanner( line );
       while( scan.hasNext() )
       {
           tokens.add( TokenFactory.categorize( scan.next() ) );
       }
       opStack = new Stack<Operator>(); //Node vs OperandNode vs operator stack?
       randStack = new Stack<EToken>();
       

       for ( int i = 0; i < tokens.size(); i++ )
       {
           if ( tokens.get( i ) instanceof Operand )
           {
               randStack.push( tokens.get( i ) );
           }
           else if( ( ( Operator ) tokens.get( i ) ).string.equals( "(" ) )
           {
               opStack.push( ( ( Operator ) tokens.get( i ) ) );
           }
           else if ( ( ( Operator ) tokens.get( i ) ).string.equals( ")" ) )
           {
               System.out.println( "opnode top: " + opStack.peek() );
               while( (  !opStack.peek().getData().equals( "(" ) ) )
               {
                   pushOpNode(   opStack.pop() );
               }
               opStack.pop();
           }
           else
           {
               while( !opStack.empty() &&
                     prec(  opStack.peek() ) >= prec( ( ( Operator) ( tokens.get( i ) )  ) ) )
               {
                   pushOpNode(  opStack.pop() );
               }
               opStack.push( ( ( Operator ) tokens.get( i ) ) );
           }    
       }
       while( !opStack.empty() )
       {
           pushOpNode( opStack.pop() );
       }  
       //opStack.push( tokens.get( i ) );
       
   }
   
   /**
    * pushes OP.
    * 
    * 
    */
   private void pushOpNode( Operator op )
   {
       if( !randStack.isEmpty() )
       {
           op.right = ( ( Operand ) randStack.pop() );
       }
       if( !randStack.isEmpty() )
       {
           op.left = ( ( ( Operand ) randStack.pop() ) );
       }
       randStack.push( new Number( op.eval() + "" ) );
       
       
   }
   
   //want to use inorder for final calculations
   /**
    * pushes OP.
    * 
    * 
    */
   private int prec( Operator token )
   {
       int prec = -1;
       if ( token instanceof Operator )
       {
           if (  token.string.equals( "+" ) || 
               token.string.equals( "-" ) )
           {
               prec = 1;
           }
           if (  token.string.equals( "/" ) || 
               token.string.equals( "*" ) || token.string.equals( "%" ) )
           {
               prec = 2;
           }
           if ( token.string.equals( "(" ) )
           {
               prec = 0;
           }
       }
       else
       {
           System.err.println( "How did you even manage this? " +
                              "operand precedence error" );  
           prec = -1;
       }
       return prec;
   }
   
   /**
    * 
    * 
    * 
    * 
    *
   public Float EvalExp( Node n )
   {
       //opStack
       //andStack
       Float l = null, r = null;
       Operator currOp;

       Node finalNode;
       while ( randStack.peek() != null )
       {
           Node topNode = randStack.peek(); 
           if( topNode instanceof OperatorNode  )
           {
               currOp = ( ( OperatorNode ) topNode ).getData();
               if( ( ( OperatorNode ) topNode ).left instanceof OperatorNode )
               {
                   currOp = ( ( Operator )( ( ( OperatorNode ) topNode ).left ) );
                   OperatorNode newNode = new OperatorNode( currOp );
                   EToken left = ( ( OperatorNode ) topNode ).left;
                   EToken right = ( ( OperatorNode ) topNode ).right;
                   newNode.setLeft( ( Operand ) left );
                   newNode.setRight( ( Operand ) right );
                   EvalExp( newNode );
               }
               if( ( ( OperatorNode ) topNode ).right instanceof OperatorNode )
               {
                   currOp = ( ( Operator )( ( ( OperatorNode ) topNode ).right ) );
                   OperatorNode newNode = new OperatorNode( currOp );
                   EToken left = ( ( OperatorNode ) topNode ).left;
                   EToken right = ( ( OperatorNode ) topNode ).right;
                   newNode.setLeft( ( Operand ) left );
                   newNode.setRight( ( Operand ) right );
                   EvalExp( newNode );
               }
               if ( ( ( OperatorNode ) topNode ).left instanceof Operand )
               {
                   l =  Float.parseFloat( ( (OperandNode) topNode ).left.string );
               }
               if ( ( ( OperatorNode ) topNode ).right instanceof Operand )
               {
                   r =  Float.parseFloat( ( (OperandNode) topNode ).right.string );
               }
               if ( currOp.string.equals( "*" ) && l != null && r != null )
               {
                   ( (OperandNode) topNode ).data.string = Float.toString( l * r );
               }
               if ( currOp.string.equals( "/" ) && l != null && r != null )
               {
                   ( (OperandNode) topNode ).data.string = Float.toString( l / r );
               }
               if ( currOp.string.equals( "-" ) && l != null && r != null )
               {
                   ( (OperandNode) topNode ).data.string = Float.toString( l - r );
               }
               if ( currOp.string.equals( "+" ) && l != null && r != null )
               {
                   ( (OperandNode) topNode ).data.string = Float.toString( l + r );
               }
               finalNode = randStack.pop();
           }
           else
           {
               if ( ( ( OperandNode ) randStack.peek() ).getData() instanceof Variable )
               {
                   Float vVal = Float.parseFloat( (  (Variable) ( ( OperandNode ) randStack.peek() ) .getData() ).string );
               }
               if ( ( ( OperandNode ) randStack.peek() ).getData() instanceof Number )
               {
                   Float nVal = Float.parseFloat( ( (Number) ( ( OperandNode ) randStack.peek() ).getData() ).string );
               }
           }
       }
       return 0f;
   }*/
   
   //+++++++++++++++++++++++++ inner class +++++++++++++++++++++++++++++++
   //---------------------------- TextFilter -----------------------------
   /**
    * This class is used with FileChooser to limit the choice of files
    * to those that end in *.txt
    */
   public class TextFilter extends javax.swing.filechooser.FileFilter
   {
      public boolean accept( File f ) 
      {
         if ( f.isDirectory() || f.getName().matches( ".*txt" ) )
            return true;
         return false;
      }
      public String getDescription()
      {
         return "*.txt files";
      }
   }
   //--------------------- main -----------------------------------------
   public static void main( String[] args )
   {
      Interpreter app = new Interpreter( args );
   }
}
