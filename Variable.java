/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand
 * 
 * @author Alex Korzyniowski
 */

public class Variable extends Operand
{
    protected String string, ns; 
    protected Float value;
    /**
     * Variable Constructor.
     * 
     * @param s String
     */
    public Variable( String s )
    {
        string = s; 
        ns = "@" + s;
        SymbolTable.instance().setValue( string, value ); 
    }
    
    public Float eval()
    {
        return SymbolTable.instance().getValue( string );
    }
      
    /**
     * prints yo ish.
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
