
import java.util.*;
/**
 * TokenFactory: a factory class, which makes tokens from String fields.
 * This is an example of a Factory pattern.
 *
 * @author Alex Korzyniowski
 */
public class TokenFactory
{
     /**
     * Note the constructor is private!
     * 
     * @param s String
     * @return EToken
     */
    public static EToken makeToken( String s )
    {
            if ( Character.isJavaIdentifierStart( s.charAt( 0 ) ) )
            {
                for ( int i = 0; i < s.length(); i++ )
                {
                    Character c = s.charAt( i );
                    if ( Character.isJavaIdentifierPart( c ) )
                    {
                        System.out.println ( "Character is Valid: " + c );
                    } 
                    else
                    {
                        System.out.println ( "Character is invalid: " + c );
                    }
                }
                return categorize( s );
            }
            else
            {
                System.err.println ( "Invalid Start: " + s );
            }

        
        return null;
    }

    /**
     * Categorize ETokens into proper types.
     *
     *
     * @return EToken
     * @param s String
     */
    public static EToken categorize ( String s ) 
    {
        String operators = "*/-+%()=";
        
        if ( operators.contains( s )  )
        {
//            System.out.println( "operators true" );
            if ( s.length() != 1 ) 
            {
                //is this a negative number instead?
                if ( s.substring( 0, 1 ) == "-" )
                {
                    System.out.println( "Negative # found" );
                    for( int i = 0; i < s.length(); i++ )
                    {
                        if ( !Character.isLetter( s.charAt( i ) ) )
                            continue;
                        else
                            System.err.println( "ERROR: space Missing" );
                    
                    }
                }
                
            }
            return new Operator ( s );
        }
        
        else 
        {
//               return new Operand( s );
            if( s != null ) 
            {
                
                if ( Character.isLetter( s.charAt( 0 ) ) )
                {
//      System.out.println ( "ETOKEN: Letter Found: " + s.charAt( 0 ) );
                    return new Variable( s );
                }
                
                else
                {
                    String token = s;
                    float value;
                    try 
                    {
                        value = Float.parseFloat( token );
                        
                    }
                    catch ( NumberFormatException e ) 
                    {
                        System.err.println( "Not a Valid Number input: " + s );
                    } 
//                    System.err.println( "Is Number?: " + s );
                    return new Number( s );
                }
                
            }
//            throw new IllegalArgumentException( "invalid input" ); //previously returned null
            return null;
        }
//        return categorize( s );
    }
}
