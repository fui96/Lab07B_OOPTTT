import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTJFrame extends JFrame{

    //Panels
    JPanel MainPanel,TitlePanel,GamePanel,ConsolePanel;
    //TextAreas
    JTextArea GameTextArea;
    JScrollPane GameScrollPane;
    //Label
    JLabel MainTitle;
    //Static Buttons
    JButton QuitButton, ResetBoard;

    private Game Game;

    public TTTJFrame(){

        //Create Window
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Create and Structure Panels
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        add(MainPanel,BorderLayout.CENTER);
        CreateTitlePanel();
        MainPanel.add(MainTitle,BorderLayout.NORTH);
        CreateGamePanel();
        MainPanel.add(GamePanel,BorderLayout.CENTER);
        CreateConsolePanel();
        MainPanel.add(ConsolePanel,BorderLayout.SOUTH);
        Game = new Game(GameTextArea,GamePanel);
        Game.StartGame();


        setVisible(true);
    }

    //methods

    /**
     * Creates and structures the title panel
     */
    public void CreateTitlePanel(){
        TitlePanel = new JPanel();
        TitlePanel.setLayout(new BorderLayout());

        MainTitle = new JLabel("Tic-Tac-Toe",SwingConstants.CENTER);
        MainTitle.setFont(new Font("Serif", Font.BOLD, 22));

        TitlePanel.add(MainTitle,BorderLayout.CENTER);

    }

    /**
     * Creates and structures the gamepanel
     */
    public void CreateGamePanel(){
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridLayout(3,3,10,10));
    }

    /**
     * Creates and structures the gameconsle panel
     */
    public void CreateConsolePanel(){
        ConsolePanel = new JPanel();
        GameTextArea = new JTextArea(10,20);
        GameScrollPane = new JScrollPane(GameTextArea);

        QuitButton = new JButton("Quit");
        ResetBoard = new JButton("Reset Board");

        QuitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});

        ResetBoard.addActionListener((ActionEvent ae) -> {Game.Reset();});

        ConsolePanel.add(GameScrollPane);
        ConsolePanel.add(QuitButton);
        ConsolePanel.add(ResetBoard);
    }

    public static void main(String[] args) {
        new TTTJFrame();
    }
}

