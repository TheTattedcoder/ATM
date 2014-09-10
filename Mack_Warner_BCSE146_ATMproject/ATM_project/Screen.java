
/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen
{
   
    public void displayMessage( String message )
    {
        System.out.print( message );
    }

    public void displayMessageLine( String message )
    {
        System.out.println( message );
    }

    public void displayDollarAmount( double amount )
    {
        System.out.printf( "$%,.2f",amount );
    }

    public void getDisplayValue(int displayValue)
    {
        System.out.print( displayValue );
    }

   
    
    
    }

