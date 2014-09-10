
/**
 * Write a description of class Deposit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deposit extends Transaction
{
    // instance variables - replace the example below with your own
    private double amount; // amount to deposit
    private UserInterface face; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0;
    private BankDataBase bankDatabase;

    /**
     * Constructor for objects of class Deposit
     */
    public Deposit(int userAccountNumber, Screen atmScreen, BankDataBase bankDatabase, UserInterface UI, DepositSlot atmDepositSlot)
    {
        super( userAccountNumber, atmScreen, bankDatabase);
        face = UI;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute()
    {
        BankDataBase bankDatabase = getBankDatabase(); // get reference
        Screen screen = getScreen(); // get reference

        amount = promptForDepositAmount();
        if ( amount != CANCELED )
        {
            // request deposit envelope containing specified amount
            face.displayMessage(
                "\nPlease insert a deposit envelope containing " );
            face.displayDollarAmount( amount );
            face.displayMessageLine( "." );
            boolean envelopeReceived = depositSlot.isEnvelopeReceived();
            // check whether deposit envelope was received
            if ( envelopeReceived )
            {
                face.displayMessageLine( "Your envelope has been " +
                    "received.\nNOTE: The money just deposited will not " +
                    "be available until we verify the amount of any " +
                    "enclosed cash and your checks clear." );
                bankDatabase.credit( getAccountNumber(), amount );
            } // end if
            else // deposit envelope not received
            {
                face.displayMessageLine( "You did not insert an " +
                    "envelope, so the ATM has canceled your transaction." );
            } // end else
        } // end if
        else // user canceled instead of entering amount
        {
            face.displayMessageLine( "Canceling transaction..." );
        } // end else
    } // end method execute
    private double promptForDepositAmount()
    {
        Screen screen = getScreen(); // get reference to screen

        // display the prompt
        face.displayMessage( "\nPlease enter a deposit amount in " +
            "CENTS (or 0 to cancel): " );
        int input = face.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if ( input == CANCELED )
            return CANCELED;
        else
        {
            return ( double ) input / 100; // return dollar amount
        } // end else
    } // end method promptForDepositAmount
} // end class Deposit


