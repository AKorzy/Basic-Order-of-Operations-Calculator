/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand
 * @author Alex Korzyniowski
 */

public class Number extends Operand 
{
    protected String string, ns; 
    protected Float value;
    /**
     * Number constructor.
     * 
     * @param s String
     */
    public Number( String s )
    {
        ns = "@" + s;
        string = s; 
        value = Float.parseFloat( s );
    }
    
    public Float eval()
    {
        return value;
    }
    /**
     * printable.
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
