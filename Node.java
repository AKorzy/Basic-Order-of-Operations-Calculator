/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand.
 * 
 * @author Alex Korzyniowski
 */
public abstract class Node extends EToken
{
    /**
     * Non-standard toString.
     * 
     * @return String
     */
    abstract public String printable();
    
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
    
 
