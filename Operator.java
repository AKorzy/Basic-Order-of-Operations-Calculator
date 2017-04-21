/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand
 * @author Alex Korzyniowski
 */

public class Operator extends EToken
{
//    protected EToken eOperator;
    protected String string, ns;
    protected EToken left, right; //cast to either operand or operator
    protected Float value;

    /**
     * Operator Constructor.
     * 
     * @param s String
     */
    Operator( String s )
    {
        string = s;
        ns = "<" + s + ">";
//        eOperator = TokenFactory.makeToken( s );
//        System.out.println( "Operator: " + s);
        eval();
    }
    public Float eval()
    {
//        eval( left );
//        eval( right );
        Float l = null, r = null;

        if ( this.left instanceof Operand )
        {
            l =  left.eval();
        }
        if ( this.right instanceof Operand )
        {
            r =  right.eval();
        }
        if ( string.equals( "*" ) && l != null && r != null )
        {
            value = ( l * r );
        }
        if ( string.equals( "/" ) && l != null && r != null )
        {
            value = ( l / r );
        }
        if ( string.equals( "-" ) && l != null && r != null )
        {
            value = ( l - r );
        }
        if ( string.equals( "+" ) && l != null && r != null )
        {
            value = l + r;
        }
        if ( string.equals( "%" ) && l != null && r != null )
        {
            value = l % r;
        }
        //=  has to set the value in Hmap to the data in right node
        if ( string.equals( "=" ) && ( left instanceof Variable ) && r != null )
        {
            System.out.println( "EQUALS TRIGGERED" );
            ( ( Variable) left ).value = right.eval();
            SymbolTable.instance().setValue( ( ( Variable) left ).string, right.eval() ); 
            value = SymbolTable.instance().getValue( ( ( Variable) left ).string );
        }
        return value;
    }

    public void setLeft( Operand o)
    {
        left = o;
    }
    public void setRight( Operand o )
    {
        right = o; 
    }
    public String getData( )
    {
        return string;
    }
    
    /**
     * PRINT TABLE HUE.
     * 
     * @return String
     */
    public String printable()
    {
        return ns;
    }
    
    /**
     * Non-standard toString.
     * 
     * @return String
     */
    public String toString()
    {
        return printable();
    }
   
}
