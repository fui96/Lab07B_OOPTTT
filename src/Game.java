import javax.swing.*;

public class Game implements GameLogic {

    //Fields
    private boolean isXTurn;
    private int TurnCount;
    public Board TTTBoard;
    private boolean isWin;
    private boolean isTie;
    private TicTacToeButton[][] Board;
    private JTextArea output;
    private JPanel BoardPanel;

    //Constructor
    public Game(JTextArea output,JPanel BoardPanel) {
        this.TTTBoard = new Board(this);
        Board = TTTBoard.getBoard();
        this.BoardPanel = BoardPanel;
        this.isXTurn = true;
        this.TurnCount = 0;
        this.output = output;
        this.isWin = false;
        this.isTie = false;

    }

    //Setters and Getters

    public boolean isXTurn() {
        return isXTurn;
    }

    public void setXTurn(boolean XTurn) {
        isXTurn = XTurn;
    }

    public int getTurnCount() {
        return TurnCount;
    }

    public void setTurnCount(int turnCount) {
        TurnCount = turnCount;
    }

    public JPanel getBoardPanel() {
        return BoardPanel;
    }


    //Methods
    public void CreateBoard() {
        TTTBoard.CreateBoard((this.getBoardPanel()));
    }

    public void ResetBoard() {
        TTTBoard.ResetBoard();
        this.isXTurn = true;
        this.TurnCount = 0;
        this.isWin = false;
        this.isTie = false;
    }

    public void StartGame() {
        CreateBoard();
    }
    public void Reset(){
        ResetBoard();
    }
    public void EndGame() {
        if (isWin) {
            int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION) {
                ResetBoard();
            }
            else if (playAgain == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
    }

    public void MakeMove(TicTacToeButton Source){
        if(!isWin && !isTie) {
            if (!Source.isTaken()) {
                if (isXTurn) {
                    Source.setIconX();
                } else {
                    Source.setIconO();
                }
                TurnCount++;
                if (TurnCount > 4) {
                    CheckWinner(isXTurn);
                }
                if (TurnCount > 6) {
                    CheckTie();
                }
                isXTurn = !isXTurn;
            }
            else {
                JOptionPane.showMessageDialog(null, "This Space is Already Taken", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void CheckWinner(boolean isXTurn){
        if(RowCheck() || ColCheck() || DiagCheck()){
            if(isXTurn){
                output.append("X Wins\n");
            }
            else{
                output.append("O Wins\n");
            }
            isWin = true;
            EndGame();
        }
    }

    public void CheckTie(){
        boolean rowtie = false;
        boolean coltie = false;
        String Diag1 = Board[0][0].getTakenBy() + Board[1][1].getTakenBy() + Board[2][2].getTakenBy();
        String Diag2 = Board[0][2].getTakenBy() + Board[1][1].getTakenBy() + Board[2][0].getTakenBy();
        boolean Diagonal1Tie = false;
        Diagonal1Tie = Diag1.contains("X") && Diag1.contains("O");
        boolean Diagonal2Tie = false;
        Diagonal2Tie = Diag2.contains("X") && Diag2.contains("O");
        if(Diagonal1Tie && Diagonal2Tie){
            isTie = true;
        }
        else if(!Diagonal1Tie || !Diagonal2Tie){
            isTie = false;
            return;
        }
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(Board[row][col].isTaken()){
                    String rowContains = Board[row][0].getTakenBy() + Board[row][1].getTakenBy() + Board[row][2].getTakenBy();
                    String colContains = Board[0][col].getTakenBy() + Board[1][col].getTakenBy() + Board[2][col].getTakenBy();
                    rowtie = (rowContains.contains("X") && rowContains.contains("O"));
                    coltie = (colContains.contains("X") && colContains.contains("O"));
                    if(rowtie && coltie){
                        isTie = true;
                    }
                    else if(!rowtie || !coltie){
                        isTie = false;
                        return;
                    }
                }
            }
        }
        if(isTie){
            output.append("Game is a tie!\n");
        }
    }
    /**
     *checks if there is a win state in any of the three rows
     * @return a win state found in the rows
     */
    public boolean RowCheck(){
        for(int row = 0; row < 3; row++) {
            if(Board[row][0].getTakenBy().equals(Board[row][1].getTakenBy())
                    && Board[row][1].getTakenBy().equals(Board[row][2].getTakenBy())
                    && !Board[row][0].getTakenBy().equals(" ")){
                return true;
            }
        }
        return false;

    }

    /**
     *checks if there is a win state in any of the three cols
     * @return a win state found in the cols
     */
    public boolean ColCheck(){
        for(int col = 0; col < 3; col++) {
            if(Board[0][col].getTakenBy().equals(Board[1][col].getTakenBy())
                    && Board[1][col].getTakenBy().equals(Board[2][col].getTakenBy())
                    && !Board[0][col].getTakenBy().equals(" ")){
                return true;
            }
        }

        return false;
    }

    /**
     * checks if there is a win state in either of the diagonals
     * @return a win state in the diagonals
     */
    public boolean DiagCheck(){
        if(Board[0][0].getTakenBy().equals(Board[1][1].getTakenBy())
                && Board[1][1].getTakenBy().equals(Board[2][2].getTakenBy()) && !Board[0][0].getTakenBy().equals(" ")){
            return true;
        }
        else if(Board[0][2].getTakenBy().equals(Board[1][1].getTakenBy())
                && Board[1][1].getTakenBy().equals(Board[2][0].getTakenBy()) && !Board[0][2].getTakenBy().equals(" ")){
            return true;
        }

        return false;
    }
}

