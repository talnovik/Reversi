
public class Board {
	private static final int SIZE = 8;
	private static final int EMPTY = 0;
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	private int turn;
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
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
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
			/*
			if (x > 0 && x < SIZE - 1 && y > 0 && y < SIZE - 1)
			{
				if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x+1][y+1] != EMPTY
						 || this.board[x+1][y-1] != EMPTY || this.board[x-1][y+1] != EMPTY || this.board[x-1][y-1] != EMPTY)
				{
					return true;
				}
			}
			
			*/
			
			
			
			if(x == 0)
			{
				if(y == 0)
				{
					if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x+1][y+1] != EMPTY)
						return true;
				}
				else
					if(y == SIZE - 1)
					{
						if(this.board[x+1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x+1][y-1] != EMPTY)
							return true;
					}
					else
					{
						if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x+1][y+1] != EMPTY || this.board[x+1][y-1] != EMPTY)
							return true;
					}
			}
			else
			{
				if(x == SIZE - 1)
				{
					if(y == 0)
					{
						if(this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x-1][y+1] != EMPTY)
							return true;
					}
					else
					{
						if(y == SIZE - 1)
						{
							if(this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x-1][y-1] != EMPTY)
								return true;
						}
						else
						{
							if(this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x-1][y+1] != EMPTY || this.board[x-1][y-1] != EMPTY)
								return true;
						}
					}
				}
				else
				{
					if(y == 0)
					{
						if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x+1][y+1] != EMPTY || this.board[x-1][y+1] != EMPTY)
						{
							return true;
						}
					}
					else
					{
						if(y == SIZE - 1)
						{
							if(this.board[x+1][y] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x+1][y-1] != EMPTY|| this.board[x-1][y-1] != EMPTY)
							{
								return true;
							}
						}
						else
						{
							if(this.board[x+1][y] != EMPTY || this.board[x][y+1] != EMPTY || this.board[x-1][y] != EMPTY || this.board[x][y-1] != EMPTY || this.board[x+1][y+1] != EMPTY
									 || this.board[x+1][y-1] != EMPTY || this.board[x-1][y+1] != EMPTY || this.board[x-1][y-1] != EMPTY)
							{
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
			
	}
	
	public int GetPiece(int x, int y)
	{
		return this.board[x][y];
	}
	
	public boolean game_over()
	{
		
		int count_black = 0;
		int count_white = 0;
		for(int i = 0; i<SIZE; i++)
		{
			for(int j = 0; j<SIZE; j++)
			{
				if(board[i][j] == BLACK_DISK)
					count_black++;
				else if(board[i][j] == WHITE_DISK)
					count_white++;
				
			}
		}
			
		if(count_black == 0 || count_white == 0)
			return true;
		
		
		for(int i = 0; i<SIZE; i++)
		{
			for(int j = 0; j<SIZE; j++)
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
	
	public int count_black()
	{
		int count = 0;
		for(int i = 0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if(board[i][j] == BLACK_DISK)
					count++;
				
			}
		}
		return count;
	}
	
	public int count_white()
	{
		int count = 0;
		for(int i = 0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if(board[i][j] == WHITE_DISK)
					count++;
				
			}
		}
		return count;
	}
	
	public boolean block_by_line(int x)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for(int y = 0; y < SIZE; y++)
		{
			if(blocker1 == blocker2  && blocker1 != EMPTY)
				break;
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
		if(blocker1 == blocker2 && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_column(int y)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for (int x = 0; x < SIZE; x++)
		{
			if(blocker1 == blocker2  && blocker1 != EMPTY)
				break;
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_left_down(int x)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for (int y = 0; y < SIZE && x < SIZE; y++, x++)
		{
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_up_right(int y)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for (int x = 0; x < SIZE && y < SIZE; y++, x++)
		{
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_right_down(int x)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for (int y = SIZE - 1; y >= 0 && x < SIZE; y--, x++)
		{
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_up_left(int y)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for (int x = 0; x < SIZE && y >= 0; y--, x++)
		{
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_left_right(int x, int y)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for(; x > 0 && y > 0; x--, y--);
		for(; x < SIZE && y < SIZE; x++, y++)
		{
			if(blocker1 == blocker2  && blocker1 != EMPTY)
				break;
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
			return true;
		return false;
	}
	
	public boolean block_by_diagonal_right_left(int x, int y)
	{
		int blocker1 = EMPTY;
		int blocker2 = EMPTY;
		for(; x > 0 && y < SIZE - 1; x--, y++);
		for(; x < SIZE && y >= 0; x++, y--)
		{
			if(blocker1 == blocker2  && blocker1 != EMPTY)
				break;
			if (blocker1 == EMPTY)
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
		if(blocker1 == blocker2  && blocker1 != EMPTY)
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
						if(GetPiece(i, j) != sign && GetPiece(i, j) != EMPTY)
							PutPiece(i, j, sign);
					}
					
				}
			}
		}
		for(int j = 0; j < SIZE; j++)
		{
			sign = EMPTY;
			flag = block_by_column(j);
			if(flag)
			{
				for(int i = 0; i < SIZE; i++)
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
						if(GetPiece(i, j) != sign && GetPiece(i, j) != EMPTY)
							PutPiece(i, j, sign);
					}
				}
			}
		}
		for(int i = 0; i < SIZE; i++)
		{
			sign = EMPTY;
			flag = block_by_diagonal_left_down(i);
			if(flag)
			{
				for(int j = 0, x = i; j < SIZE && x < SIZE; j++, x++)
				{
					if(sign == EMPTY)
					{
						if(GetPiece(x, j) == WHITE_DISK)
						{
							sign = WHITE_DISK;
						}
						else if(GetPiece(x, j) == BLACK_DISK)
						{
							sign = BLACK_DISK;
						}
					}
					else
					{
						if(GetPiece(x, j) != sign && GetPiece(x, j) != EMPTY)
							PutPiece(x, j, sign);
					}
					
				}
			}
		}
		for(int j = 0; j < SIZE; j++)
		{
			sign = EMPTY;
			flag = block_by_diagonal_up_right(j);
			if(flag)
			{
				for(int i = 0, y = j; i < SIZE && y < SIZE; i++, y++)
				{
					if(sign == EMPTY)
					{
						if(GetPiece(i, y) == WHITE_DISK)
						{
							sign = WHITE_DISK;
						}
						else if(GetPiece(i, y) == BLACK_DISK)
						{
							sign = BLACK_DISK;
						}
					}
					else
					{
						if(GetPiece(i, y) != sign && GetPiece(i, y) != EMPTY)
							PutPiece(i, y, sign);
					}
					
				}
			}
		}
		for(int i = 0; i < SIZE; i++)
		{
			sign = EMPTY;
			flag = block_by_diagonal_right_down(i);
			if(flag)
			{
				for(int j = SIZE - 1, x = i; j >= 0 && x < SIZE; j--, x++)
				{
					if(sign == EMPTY)
					{
						if(GetPiece(x, j) == WHITE_DISK)
						{
							sign = WHITE_DISK;
						}
						else if(GetPiece(x, j) == BLACK_DISK)
						{
							sign = BLACK_DISK;
						}
					}
					else
					{
						if(GetPiece(x, j) != sign && GetPiece(x, j) != EMPTY)
							PutPiece(x, j, sign);
					}
					
				}
			}
		}
		for(int j = 0; j < SIZE; j++)
		{
			sign = EMPTY;
			flag = block_by_diagonal_up_left(j);
			if(flag)
			{
				for(int i = 0, y = j; i < SIZE && y >= 0; i++, y--)
				{
					if(sign == EMPTY)
					{
						if(GetPiece(i, y) == WHITE_DISK)
						{
							sign = WHITE_DISK;
						}
						else if(GetPiece(i, y) == BLACK_DISK)
						{
							sign = BLACK_DISK;
						}
					}
					else
					{
						if(GetPiece(i, y) != sign && GetPiece(i, y) != EMPTY)
							PutPiece(i, y, sign);
					}
					
				}
			}
		}
	}
	
	public void UpdateBoardFix(int x, int y)
	{
		int sign = EMPTY;
		if(block_by_line(x))
		{
			for(int j = 0; j < SIZE; j++)
			{
				if(GetPiece(x, j) == sign && GetPiece(x, j) != EMPTY)
					break;
				if(sign == EMPTY)
				{
					if(GetPiece(x, j) == WHITE_DISK)
					{
						sign = WHITE_DISK;
					}
					else if(GetPiece(x, j) == BLACK_DISK)
					{
						sign = BLACK_DISK;
					}
				}
				else
				{
					if(GetPiece(x, j) == sign)
						break;
					if(GetPiece(x, j) != sign && GetPiece(x, j) != EMPTY)
						PutPiece(x, j, sign);
				}
				
			}
		}
		sign = EMPTY;
		if(block_by_column(y))
		{
			for(int i = 0; i < SIZE; i++)
			{
				if(GetPiece(i, y) == sign && GetPiece(i, y) != EMPTY)
					break;
				if(sign == EMPTY)
				{
					if(GetPiece(i, y) == WHITE_DISK)
					{
						sign = WHITE_DISK;
					}
					else if(GetPiece(i, y) == BLACK_DISK)
					{
						sign = BLACK_DISK;
					}
				}
				else
				{
					if(GetPiece(i, y) != sign && GetPiece(i, y) != EMPTY)
						PutPiece(i, y, sign);
				}
				
			}
		}
		sign = EMPTY;
		if(block_by_diagonal_left_right(x, y))
		{
			int i, j;
			for(i = x, j = y; i > 0 && j > 0; i--, j--);
			for(; j < SIZE && i < SIZE; j++, i++)
			{
				if(GetPiece(i, j) == sign && GetPiece(i, j) != EMPTY)
					break;
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
					if(GetPiece(x, j) == sign)
						break;
					if(GetPiece(i, j) != sign && GetPiece(i, j) != EMPTY)
						PutPiece(i, j, sign);
				}
				
			}
		}
		sign = EMPTY;
		if(block_by_diagonal_right_left(x, y))
		{
			int i, j;
			for(i = x, j = y; i > 0 && j < SIZE - 1; i--, j++);
			for(; i < SIZE && j >= 0; j--, i++)
			{
				if(GetPiece(i, j) == sign && GetPiece(i, j) != EMPTY)
					break;
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
					if(GetPiece(x, j) == sign)
						break;
					if(GetPiece(i, j) != sign && GetPiece(i, j) != EMPTY)
						PutPiece(i, j, sign);
				}
				
			}
		}
	}
	
	
	public void updateBoardFix2(int x, int y, int color)
	{
		int i, j;
		if(x < SIZE - 1)
		{
			for (i = x+1, j = y; i < SIZE-1 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i++)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (; i > x; i--)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(x > 0)
		{
			for (i = x-1, j = y; i > 0 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i--)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (; i < x; i++)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(y < SIZE - 1)
		{
			for (i = x, j = y+1; j < SIZE-1 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; j++)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (; j > y; j--)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(y > 0)
		{
			for (i = x, j = y-1; j > 0 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; j--)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (; j < y; j++)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(x < SIZE - 1 && y < SIZE - 1)
		{
			for (i = x+1, j = y+1; i < SIZE-1 && j < SIZE-1 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i++, j++)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (;i > x && j > y; i--, j--)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(x < SIZE - 1 && y > 0)
		{
			for (i = x+1, j = y-1; i < SIZE-1 && j > 0 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i++, j--)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (;i > x && j < y; i--, j++)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(x > 0 && y < SIZE - 1)
		{
			for (i = x-1, j = y+1; i < SIZE-1 && j > 0 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i--, j++)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (;i < x && j > y; i++, j--)
				{
					PutPiece(i, j, color);
				}
			}
		}
		if(x > 0 && y > 0)
		{
			for (i = x-1, j = y-1; i > 0 && j > 0 && GetPiece(i, j) != color && GetPiece(i, j) != EMPTY; i--, j--)
			{
			}
			if (GetPiece(i, j) == color)
			{
				for (;i < x && j < y; i++, j++)
				{
					PutPiece(i, j, color);
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
