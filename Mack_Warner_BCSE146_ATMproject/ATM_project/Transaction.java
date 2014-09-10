
/**
 * Abstract class Transaction - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Transaction
{
    // instance variables - replace the example below with your own
    private int accountNumber;
    private  Screen screen;
    private BankDataBase bankDatabase;
    

    
    public Transaction( int userAccountNumber, Screen atmScreen, BankDataBase
     atmBankDatabase )
     
    {
       accountNumber = userAccountNumber;
       screen = atmScreen;
       bankDatabase = atmBankDatabase;
    }
    public int getAccountNumber()
    {
        return accountNumber;
    }
    public Screen getScreen()
    {
        return screen;
    }
    public BankDataBase getBankDatabase()
    {
        return bankDatabase;
    }
    abstract public void execute();
}
