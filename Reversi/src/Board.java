
public class Board {
	private static final int SIZE = 8;
	private static final int EMPTY = 0;
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
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
		
		this.PutPiece(3, 3, BLACK_DISK);
		this.PutPiece(3, 4, WHITE_DISK);
		this.PutPiece(4, 3, WHITE_DISK);
		this.PutPiece(4, 4, BLACK_DISK);

	}
	
	public void print_board()
	{
		for(int i = 0; i < SIZE; i++)
		{
			System.out.println("");
			for(int j = 0; j < SIZE; j++)
			{
				if(board[i][j] == BLACK_DISK)
					System.out.print(" B");
				else if(board[i][j] == WHITE_DISK)
					System.out.print(" W");
				else
					System.out.print(" E");
			}
		}
	}
	
	public void PutPiece(int x, int y, int color)
	{
		this.board[x][y] = color;
	}
	
	public boolean vaild_move(int x, int y)
	{
		if(this.board[x][y] == EMPTY)
		{
			if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY)
				return true;
		}
		return false;
	}
	
	public int GetPiece(int x, int y)
	{
		return this.board[x][y];
	}
	
	public boolean game_over()
	{
		for(int i = 0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if(GetPiece(i, j) == EMPTY)
					return false;
			}
		}
		return true;
				
	}
	
	public int check_winner()
	{
		int count_black = 0;
		int count_white = 0;
		for(int i = 0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if(board[i][j] == BLACK_DISK)
					count_black++;
				else if(board[i][j] == WHITE_DISK)
					count_white++;
				
			}
		}
		if(count_black > count_white)
			return BLACK_DISK;
		else if(count_black < count_white)
			return WHITE_DISK;
		else
			return EMPTY;
	}
	
	public boolean block_by_line(int x)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for(int y = 0; y < SIZE; y++)
		{
			if(blocker1 == EMPTY)
			{
				if(GetPiece(x, y) == WHITE_DISK)
				{
					blocker1 = WHITE_DISK;
				}
				else if(GetPiece(x, y) == BLACK_DISK)
				{
					blocker1 = BLACK_DISK;
				}
			}
			else
			{
				if(GetPiece(x, y) == WHITE_DISK)
				{
					blocker2 = WHITE_DISK;
				}
				else if(GetPiece(x, y) == BLACK_DISK)
				{
					blocker2 = BLACK_DISK;
				}
			}
		}
		if(blocker1 == blocker2)
			return true;
		return false;
	}
	
	public void UpdateBoard()
	{
		int sign;
		boolean flag;
		for(int i = 0; i < SIZE; i++)
		{
			sign = EMPTY;
			flag = block_by_line(i);
			if(flag)
			{
				for(int j = 0; j < SIZE; j++)
				{
					if(sign == EMPTY)
					{
						if(GetPiece(i, j) == WHITE_DISK)
						{
							sign = WHITE_DISK;
						}
						else if(GetPiece(i, j) == BLACK_DISK)
						{
							sign = BLACK_DISK;
						}
					}
					else
					{
						if(GetPiece(i, j) != sign)
							PutPiece(i, j, sign);
					}
					
				}
			}
		}
	}
}
