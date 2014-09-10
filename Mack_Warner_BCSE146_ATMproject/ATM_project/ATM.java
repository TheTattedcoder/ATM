
/**
 * Write a description of class ATM here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ATM
{
    // instance variables - replace the example below with your own
    private boolean userAuthenticated;// whether user is authenticated
    private int currentAccountNumber;// current user's account number
    private Screen screen;// ATM's screen
    private CashDispenser cashDispenser;// ATM's cash dispenser
    private DepositSlot depositSlot;// ATM's deposit slot
    private BankDataBase bankDatabase;// account information database

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;

    //private BankDataBase bank;
    private UserInterface face;
    public ATM()
    {
        userAuthenticated = false;// user is not authenticated to start
        currentAccountNumber = 0;// no current account number to start
        screen = new Screen();// create screen
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDataBase();
        face = new UserInterface( bankDatabase );
        screen = new Screen();
    }

    public void run()
    {
        while (true)// while user IS authenticated
        {
            while (!userAuthenticated)//while user IS NOT authenticated
            {
                face.displayMessageLine("Welcome!");// displays message Welcome!
                authenticateUser();

            }

            // reset the display after you have authenticated the user
            
            performTransaction();
            userAuthenticated = false;
            currentAccountNumber = 0;
            face.displayMessageLine("Thank You! Goodbye!");
        }

    }

    private void authenticateUser()
    {
        face.displayMessage("\nPlease enter your account number: ");
        int accountNumber = face.getInput();
        face.displayMessage("\nEnter your PIN: ");
        int pin = face.getInput();

        userAuthenticated =
        bankDatabase.authenticateUser(accountNumber, pin);

        if (userAuthenticated)
        {
            currentAccountNumber = accountNumber;
        }
        else
        {
            face.displayMessageLine(
                "Invalid account number or PIN. Please try again.");
        }
    }

    public void performTransaction()
    {
        Transaction currentTransaction = null;
        boolean userExited = false;
        while(!userExited)
        {
            int mainMenuSelection = displayMainMenu();
            switch(mainMenuSelection)
            {
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                currentTransaction=
                creatTransaction(mainMenuSelection);

                currentTransaction.execute();
                break;
                case EXIT:
                face.displayMessageLine("\nExiting the system ...");
                userExited = true;
                break;
                default:
                face.displayMessageLine(
                    "\nYou did not enter a valid selection. Try again.");
                break;
            }
        }
    }

    private int displayMainMenu()
    {
        face.displayMessageLine("Main Menu");
        face.displayMessageLine("1 - View Balance");
        face.displayMessageLine("2 - Withdraw cash");
        face.displayMessageLine("3 - Deposit funds");
        face.displayMessageLine("4 - Exit\n");
        face.displayMessageLine("Enter a choice: ");
        return face.getInput();

    }

    private Transaction creatTransaction(int type)
    {
        Transaction temp = null;
        switch(type)
        {
            case BALANCE_INQUIRY:
            temp = new BalanceInquiry(
                currentAccountNumber, screen, bankDatabase, face);
            break;
            case WITHDRAWAL:
            temp = new Withdrawal(currentAccountNumber, screen, 
                bankDatabase, face, cashDispenser);
            break;
            case DEPOSIT:
            temp = new Deposit(currentAccountNumber, screen, bankDatabase,
                face, depositSlot);
            break;
        }
        return temp;
    }

}
