

public interface GameLogic {
    void StartGame();
    void EndGame();
    void MakeMove(TicTacToeButton Source);
    void Reset();
    void CheckWinner(boolean player);
    void CheckTie();
}
