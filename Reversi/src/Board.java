
public class Board {
	private static final int SIZE = 8;
	private static final int EMPTY = 0;
	private static final int BLACK = 1;
	private static final int WHITE = 2;
	private int [][] board = new int[SIZE][SIZE]; // 0 = empty, 1 = black, 2 = white
	
	
	public Board()
	{
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				board[i][j] = EMPTY;
			}
		}
	}
	
	public void PutPiece(int x, int y, int color)
	{
		this.board[x][y] = color;
	}
	
	public void UpdateBoard()
	{
		
	}
}
