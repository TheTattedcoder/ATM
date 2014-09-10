import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Write a description of class UserInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserInterface implements ActionListener
{
    // instance variables - replace the example below with your own
    private BankDataBase bank;
    private JFrame frame;
    private JTextArea display;
    private JLabel status;
    //     private int displayValue;
    //     private int numberPressed;
    private static final int GET_ACCOUNT_NUMBER = 0;
    private static final int GET_PIN_NUMBER = 1;
    private static final int MAIN_MENU_OPTION = 2;
    private String lastCommand = "";
    //     private static final int EXIT = 3;
    // use this to keep track of whatever you are entering 
    // via the button interface (you should still write
    // a getKeypadInput method, and an appendKeypadInput method
    // 
    private String keypadInput = "";

    /**
     * Constructor for objects of class UserInterface
     */
    public UserInterface(BankDataBase engine)
    {
        bank = engine;
        makeFrame();
        frame.setVisible(true);
        //reDisplay();

    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }

    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener( this );
        panel.add(button);
    }

    private void makeFrame()
    {
        frame = new JFrame("DMB"); 

        frame.getContentPane().setPreferredSize(new Dimension(800, 800));

        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(50,50 ));
        contentPane.setBorder(new EmptyBorder( 25, 25, 25, 25));

        display = new JTextArea();
        display.setPreferredSize(new Dimension(500,500));
        contentPane.add(display, BorderLayout.NORTH);
        display.setText("");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
        addButton(buttonPanel, "1");
        addButton(buttonPanel, "2");
        addButton(buttonPanel, "3");

        addButton(buttonPanel, "4");
        addButton(buttonPanel, "5");
        addButton(buttonPanel, "6");

        addButton(buttonPanel, "7");
        addButton(buttonPanel, "8");
        addButton(buttonPanel, "9");
        addButton(buttonPanel, "");
        addButton(buttonPanel, "0");

        JPanel buttonPanelD = new JPanel(new GridLayout(2,1 ));
        addButton(buttonPanelD, "X Cancel");
        addButton(buttonPanelD, "O Enter");
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanelD, BorderLayout.EAST);

        frame.pack();
    }

    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

        if(command.equals("0") ||
        command.equals("1") ||
        command.equals("2") ||
        command.equals("3") ||
        command.equals("4") ||
        command.equals("5") ||
        command.equals("6") ||
        command.equals("7") ||
        command.equals("8") ||
        command.equals("9")) {
            setLastCommand(command);
            
            appendCommandToKeypadInput( command );
            
            display.append( command );
        }
        // You could use the actual string "O Enter" as your sentinel value
        // to "tell" the ATM to read in the account number or PIN or whatever you
        // are entering at the time
        else if ( command.equals("O Enter") ) {
            setLastCommand(command);
            display.append( "\n" );
            
        }
       
    }
    public String getLastCommand()
    {
        return lastCommand;
    }
    public void setLastCommand(String currentCommand)
    {
        lastCommand = currentCommand;
    }

    public void appendCommandToKeypadInput(String numberPressed)
    {
        keypadInput += numberPressed;
    }

    public int getInput()
    {
        keypadInput = "";
        setLastCommand("");
       

                while (!lastCommand.equals("O Enter"))
                {
                    if( lastCommand.equals("O Enter"))
                   {
                     return Integer.parseInt( keypadInput );   
                   }
              }

        return Integer.parseInt( keypadInput );       
    }

    public void displayMessage( String message )
    {
        display.append( message );
    }

    public void displayMessageLine( String message )
    {
        display.append("\n" + message );
    }

    public void displayDollarAmount( double amount )
    {
        display.append( Double.toString(amount) );
    }
    public void reDisplay()
    {   
        display.setText("");
    }
}

