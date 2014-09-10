
/**
 * Write a description of class BalanceInquiry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BalanceInquiry extends Transaction
{
    // The instance variable "face" here is just another reference to 
    // the user interface. As an instance variable, "face" has class-wide scope.
    private UserInterface face;
    
    // in the constructor, we are passing in that reference, but
    // the argument "face" here only has local scope (local to the constructor only)
    public BalanceInquiry( int userAccountNumber, Screen atmScreen,
    BankDataBase atmBankDatabase, UserInterface face )
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );
        
        // We need to make sure that the "face" with CLASS-WIDE scope is the one
        // that gets assigned the value of the "face" with local scope
        // to resolve the differences between the the class-wide "face" and local-wide "face"
        // we have to use the keyword "this" to refer to the instance variable "face".
        // This allows use to ASSIGN the variable "face" with local scope to the instance variable
        // "face" with class-wide scope. (Remember that assignment goes from right to left)
        this.face = face;
        
    }

    @Override
    public void execute()
    {
        BankDataBase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        

        double availableBalance =
            bankDatabase.getAvailableBalance( getAccountNumber() );
        // get the total balance for the account involved
        double totalBalance =
            bankDatabase.getTotalBalance( getAccountNumber() );

        // display the balance information on the screen
        face.displayMessageLine( "Balance Information:" );
        face.displayMessage( " - Available balance: " );
        face.displayDollarAmount( availableBalance );
        face.displayMessage( "\n - Total balance: " );
        face.displayDollarAmount( totalBalance );
        face.displayMessageLine( "" );
    }
}
