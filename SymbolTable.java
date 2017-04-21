
import java.util.*;
/**
 * SymbolTable - this class  maintains a symbol table for variables.
 * 
 * This functionality lends itself to a class that uses the Singleton pattern.
 * That is, it enforces a restriction that only 1 instance can ever be created.
 * 
 * @author Alex Korzyniowski
 */
public class SymbolTable
{
    //--------------------- class variables -------------------------------
    private static SymbolTable theTable = null;
    private  Stack<Node> randStack;
    private  Stack<Node> opStack;
    
    //--------------------- instance variables ----------------------------
    // Use a Hashtable or a HashMap to store information (value) using the id 
    // as the key
    ///////////////////////////////////////////////////////////
    protected HashMap< String, Float > hmap;
    
    
    //------------- constructor --------------------------------------------
    /**
     * Note the constructor is private!
     */
    public SymbolTable()
    {
        //////// Create the hashtable or hashmap ///////////////////
        hmap = new HashMap< String, Float >();
     
    }
    //------------- instance -----------------------------------------------
    /**
     * user code must call a static method to get the reference to the 
     * only allowed instance -- first call creates the instance.
     * 
     * @return SymbolTable
     */
    public static SymbolTable instance()
    {
        if ( theTable == null )
            theTable = new SymbolTable();
        return theTable;
    }
    
    //------------------------ setValue( String, float  ) ---------------------
    /**
     * Set an identifier's value to the specified value.
     * 
     * @param var String
     * @param num float
     */
    public void setValue( String op, Float and )
    {
        /////////////////////////////////////////////////////// 
        //    Need to save information into the hash table
        //
        // You are allowed to change the signatures; for example, you
        //    can use Float or Number as the parameter type, both
        //    here and in getValue
        //////////////////////////////////////////////////////////
        hmap.put( op, and );
        
    }
    //------------------------ getValue( String ) ---------------------------
    /**
     * Look up an identifier in the hash table and return its value.
     * If the identifier is not in the table, add it with a value of 0
     * and return 0.
     * 
     * @param var String
     * @return float
     */
    public float getValue( String var )
    {
        /////////////////////////////////////////////////////////////
        //  Use var as hash table key get value; return it as a float
        //    or a Number (Float)
        ////////////////////////////////////////////////////////////
//        if ( var == null)
//            System.err.println( "var = NULL" );
        if( hmap.containsKey( var ) )
        {
            return hmap.get( var ); 
        }
        else
        {
            hmap.put( var, 0f );
            return 0f;
        }
//      return 0;
    }
    //------------------------- toString() -------------------------------
     /**
     * to string conversion.
     * 
     * @return String
     */
    public String toString()
    {
        return hmap.toString();
    }
    
    /**
     * 
     * 
     * 
     * 
//     */
//    public Float EvalExp( Node n )
//    {
//        opStack
//        andStack
//        Float l = null, r = null;
//        Operator currOp;
//        Node topNode = randStack.peek(); 
//        if( topNode instanceof OperatorNode  )
//        {
//            currOp = ( ( OperatorNode ) topNode ).getData();
//            if( ( ( OperatorNode ) topNode ).left instanceof OperatorNode )
//            {
//                 currOp = ( ( Operator )( ( ( OperatorNode ) topNode ).left ) );
//                OperatorNode newNode = new OperatorNode( currOp );
//                EToken left = ( ( OperatorNode ) topNode ).left;
//                EToken right = ( ( OperatorNode ) topNode ).right;
//                newNode.setLeft( ( Operand ) left );
//                newNode.setRight( ( Operand ) right );
//                EvalExp( newNode );
//            }
//            if( ( ( OperatorNode ) topNode ).right instanceof OperatorNode )
//            {
//                currOp = ( ( Operator )( ( ( OperatorNode ) topNode ).right ) );
//                OperatorNode newNode = new OperatorNode( currOp );
//                EToken left = ( ( OperatorNode ) topNode ).left;
//                EToken right = ( ( OperatorNode ) topNode ).right;
//                newNode.setLeft( ( Operand ) left );
//                newNode.setRight( ( Operand ) right );
//                EvalExp( newNode );
//            }
//            if ( ( ( OperatorNode ) topNode ).left instanceof Operand )
//                l =  Float.parseFloat( ( (OperandNode) topNode ).left.string );
//            if ( ( ( OperatorNode ) topNode ).right instanceof Operand )
//                r =  Float.parseFloat( ( (OperandNode) topNode ).right.string );
//            if ( currOp.string.equals( "*" ) && l != null && r != null )
//                ( (OperandNode) topNode ).data.string = Float.toString( l * r );
//            if ( currOp.string.equals( "/" ) && l != null && r != null )
//                ( (OperandNode) topNode ).data.string = Float.toString( l / r );
//            if ( currOp.string.equals( "-" ) && l != null && r != null )
//                ( (OperandNode) topNode ).data.string = Float.toString( l - r );
//            if ( currOp.string.equals( "+" ) && l != null && r != null )
//                ( (OperandNode) topNode ).data.string = Float.toString( l + r );
//        }
//        else
//        {
//        if ( ( ( OperandNode ) randStack.peek() ).getData() instanceof Variable )
//        {
//            Float vVal = Float.parseFloat( (  (Variable) ( ( OperandNode ) randStack.peek() ) .getData() ).string );
//        }
//        if ( ( ( OperandNode ) randStack.peek() ).getData() instanceof Number )
//        {
//            Float nVal = Float.parseFloat( ( (Number) ( ( OperandNode ) randStack.peek() ).getData() ).string );
//        }
//        }
//       return 0f;
//    }
//    
//    /**
//     * 
//     * 
//     * 
//     * 
//     */
//    public void EvalInfix( String line )
//    {
//        /* create empty opStack, randStack
//         for each token in infix expression
//              if token is operand
//                  randStack.push( new operandNode )
//              else if token is "("
//                   opStack.push( token )
//              else if token is ")"
//         while opStack.top() != "("
//              pushOpNode( opStack.pop())
//             opStack.pop() // pop "("
//             else // it is an operator
//                 while ( opStack is not empty
//                       and prec(top) >= prec(token))
//                     pushOpNode( opStack.pop())
//             opStack.push( token )
//         // copy remaining operators to output
//         while ( opStack !empty )
//              pushOpNode( opStack.pop())          */
//        opStack = new Stack<Node>(); //Node vs OperandNode vs operator stack?
//        randStack = new Stack<Node>();
//        ArrayList<EToken> tokens = new ArrayList<EToken>();
//        tokens.add( TokenFactory.makeToken( line ) );
//        for ( int i = 0; i < tokens.size(); i++ )
//        {
//            if ( tokens.get( i ) instanceof Operand )
//            {
//                randStack.push( ( OperandNode ) tokens.get( i ) );
//            }
//            else if( ( ( Operator ) tokens.get( i ) ).string.equals( "(" ) )
//            {
//                opStack.push( ( ( OperatorNode ) tokens.get( i ) ) );
//            }
//            else if ( ( ( Operator ) tokens.get( i ) ).string.equals( ")" ) )
//            {
//                System.out.println( "opnode top: " + opStack.peek() );
//                while( ( ( ( OperatorNode ) opStack.peek()).getData().string != "(" ) )
//                {
//                    pushOpNode(  ((OperatorNode) opStack.pop() ).getData() );
//                }
//                opStack.pop();
//            }
//            else
//            {
//                while( !opStack.empty() &&
//                      prec( ( (OperatorNode) opStack.peek() ).getData() ) >= prec( ( ( Operator ) ( tokens.get( i ) ) ) ) )
//                {
//                    pushOpNode( ( (OperatorNode) opStack.pop()).getData() );
//                }
//                opStack.push(  ( (OperatorNode) tokens.get( i ) ) );
//            }
//            while( !opStack.empty() )
//            {
//                pushOpNode( ( (OperatorNode) opStack.pop() ).getData() );
//            }          
//        }
//        //opStack.push( tokens.get( i ) );
//
//    }
    
//    /**
//     * pushes OP.
//     * 
//     * 
//     */
//    private void pushOpNode( Operator op )
//    {
//        OperatorNode opNode = new OperatorNode( op );
//        opNode.right = ( ( ( OperandNode ) randStack.pop() ).right );
//        opNode.left = ( ( ( OperandNode ) randStack.pop() ).left );
//        randStack.push( opNode );
//    }
//    
    //want to use inorder for final calculations
      /**
     * pushes OP.
     * 
     * 
     */
//    private int prec( Operator token )
//    {
//        int prec = -1;
//        if ( token instanceof Operator )
//        {
//            if (  token.string.equals( "+" ) || 
//                 token.string.equals( "-" ) )
//            {
//                prec = 1;
//            }
//            if (  token.string.equals( "/" ) || 
//                 token .string.equals( "*" ) )
//            {
//                prec = 2;
//            }
//            if ( token.string.equals( "(" ) )
//            {
//                prec = 0;
//            }
//        }
//        else
//        {
//            System.err.println( "How did you even manage this? " +
//                                   "operand precedence error" );  
//            prec = -1;
//        }
//        return prec;
//    }

    
    //--------------------------- main -----------------------------------
    /**
     * Simple unit testing for SymbolTable.
     * 
     * @param args String[]
     */
    public static void main( String[] args )
    {
        SymbolTable st = SymbolTable.instance();
        st.setValue( "a", 4.0f );
        float val = st.getValue( "a" );
        System.out.println( "Test: should print 4: " + val );
        
        val = st.getValue( "b" );
        System.out.println( "Test: should print 0: " + val );
        
        st.setValue( "a", 6.0f );
        val = st.getValue( "a" );
        System.out.println( "Test: should print 6: " + val );
    }
}
