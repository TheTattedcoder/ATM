
/**
 * Write a description of class Withdrawal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Withdrawal extends Transaction
{
    private int amount; // amount to withdraw
//     private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; 
    private final static int CANCELED = 6;
    private UserInterface face;
    // no-argument constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen,
    BankDataBase atmBankDatabase, UserInterface atmKeypad,
    CashDispenser atmCashDispenser)
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );

        face = atmKeypad;
        cashDispenser = atmCashDispenser;
        
    }

    @Override
    public void execute()
    {
        boolean cashDispensed = false; // cash was not dispensed yet
        double availableBalance; // amount available for withdrawal

        // get references to bank database and screen
        BankDataBase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // loop until cash is dispensed or the user cancels
        do
        {
            // obtain a chosen withdrawal amount from the user
            amount = displayMenuOfAmounts();

            // check whether user chose a withdrawal amount or canceled
            if ( amount != CANCELED )
            {
                // get available balance of account involved
                availableBalance =
                bankDatabase.getAvailableBalance( getAccountNumber() );

                // check whether the user has enough money in the account
                if ( amount <= availableBalance )
                {
                    if ( cashDispenser.isSufficientCashAvailable( amount ) )
                    {
                        // update the account involved to reflect the withdrawal
                        bankDatabase.debit( getAccountNumber(), amount );

                        cashDispenser.dispenseCash( amount ); // dispense cash
                        cashDispensed = true; // cash was dispensed

                        // instruct user to take cash
                        face.displayMessageLine( "\nYour cash has been" +
                            " dispensed. Please take your cash now." );
                    } // end if
                    else // cash dispenser does not have enough cash
                        face.displayMessageLine(
                            "\nInsufficient cash available in the ATM." +
                            "\n\nPlease choose a smaller amount." );
                } // end if
                else // not enough money available in user's account
                {
                    face.displayMessageLine(
                        "\nInsufficient funds in your account." +
                        "\n\nPlease choose a smaller amount." );
                } // end else
            }
            else // user chose cancel menu option
            {
                face.displayMessageLine( "\nCanceling transaction..." );
                return; // return to main menu because user canceled
            } // end else
        } while ( !cashDispensed );

    } // end method execute
    private int displayMenuOfAmounts()
    {
        int userChoice = 0; // local variable to store return value

        Screen screen = getScreen(); // get screen reference

        // array of amounts to correspond to menu numbers
        int[] amounts = { 0, 20, 40, 60, 100, 200 };

        // loop while no valid choice has been made
        while ( userChoice == 0 )
        {
            // display the withdrawal menu
            face.displayMessageLine( "Withdrawal Menu:" );
            face.displayMessageLine( "1 - $20" );
            face.displayMessageLine( "2 - $40" );
            face.displayMessageLine( "3 - $60" );
            face.displayMessageLine( "4 - $100" );
            face.displayMessageLine( "5 - $200" );
            face.displayMessageLine( "6 - Cancel transaction" );
            face.displayMessage( "Choose a withdrawal amount: " );

            int input = face.getInput(); // get user input through keypad

            // determine how to proceed based on the input value
            switch ( input )
            {
                case 1: // if the user chose a withdrawal amount
                case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:
                userChoice = amounts[ input ]; // save user's choice
                break;
                case CANCELED: // the user chose to cancel
                userChoice = CANCELED; // save user's choice
                break;
                default: // the user did not enter a value from 1-6
                face.displayMessageLine(
                    "\nInvalid selection. Try again." );
            } // end switch
        } // end while
        return userChoice;
    }

}


