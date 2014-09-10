
/**
 * Write a description of class BankDataBase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankDataBase
{
    private Account[] accounts;
    //private boolean buildingDisplayValue;
    //private int displayValue;
    //private int number;
    /**
     * Constructor for objects of class BankDataBase
     */
    public BankDataBase()
    {
       accounts = new Account[ 2 ];
       accounts[ 0 ] = new Account( 12345, 54321, 1000.0, 1200.0 );
       accounts[ 1 ] = new Account( 98765, 56789, 200.0, 200.0 );
    }
    
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    private Account getAccount( int accountNumber )
    {
        for( Account currentAccount : accounts )
        {
            if( currentAccount.getAccountNumber() == accountNumber )
            {
                return currentAccount;
            }
            
        }
        return null;
    }
    public boolean authenticateUser( int userAccountNumber, int userPIN )
    {
        Account userAccount = getAccount( userAccountNumber );
        
        if( userAccount != null )
        {
            return userAccount.validatePIN( userPIN );
        }
        else
        { 
            return false;
        }
    }
    
    public double getAvailableBalance( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getAvailableBalance();
    }
    public double getTotalBalance( int userAccountNumber )
    {
        return getAccount( userAccountNumber ).getTotalBalance();
    }
    public void credit( int userAccountNumber, double amount )
    { 
        getAccount( userAccountNumber ).credit( amount );
    }
    public void debit( int userAccountNumber, double amount )
    {
        getAccount( userAccountNumber ).debit( amount );
    }
}
