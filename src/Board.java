import javax.swing.*;
import java.awt.event.ActionEvent;

public class Board {
    private TicTacToeButton[][] Board;
    private Game Game;
    public Board(Game Game){
        this.Game = Game;
        Board = new TicTacToeButton[3][3];
    }

    public void CreateBoard(JPanel BoardPanel){
        for(int row = 0; row < 3; ++row){
            for(int col = 0; col < 3; ++col){
                Board[row][col] = new TicTacToeButton(row,col," ");
                Board[row][col].addActionListener((ActionEvent ae) -> {
                    TicTacToeButton clickedButton = (TicTacToeButton) ae.getSource();
                    Game.MakeMove(clickedButton);});
                BoardPanel.add(Board[row][col]);
            }
        }
    }
    public void ResetBoard(){
            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    Board[row][col].setTakenBy(" ");
                    Board[row][col].setIconBlank();
                }
            }

    }

    public TicTacToeButton[][] getBoard() {
        return this.Board;
    }
}
