/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand
 */

public abstract class Operand extends EToken 
{
    protected String string;
    protected EToken eOperand;
    protected Variable var;
    protected Number num;
    
    
    abstract public Float eval();
    /**
     * Non-standard toString
     */
//    Operand()
//    {
//        
//    }
//    Operand( String s )
//    {
////        if( s != null ) 
////        {
////            string = s;
////            if ( Character.isLetter( s.charAt( 0 ) ) )
////            {
//////                System.out.println ( "ETOKEN: Letter Found: " + s.charAt( 0 ) );
////                num = new Number( s );
////            }
////            
////            else
////            {
//////                System.out.println ( "ETOKEN: Variable Found: " + s );
////                var = new Variable( s );
////            }
////        }
//    }
    public String toString()
    {
        return printable();
    }

  
    public String printable()
    {
        return string;
    }
    
  
}
