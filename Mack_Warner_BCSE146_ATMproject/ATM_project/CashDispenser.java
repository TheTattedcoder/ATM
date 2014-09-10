
/**
 * Write a description of class CashDispenser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CashDispenser
{
   private final static int INITIAL_COUNT = 500;
   private int count;

    /**
     * Constructor for objects of class CashDispenser
     */
    public CashDispenser()
    {
        count = INITIAL_COUNT;
    }
    public void dispenseCash( int amount )
    {
        int billsRequired= amount / 20;
        count -= billsRequired;
    }
    public boolean isSufficientCashAvailable( int amount )
    {
        int billsRequired = amount / 20;
        
        if ( count >= billsRequired )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
