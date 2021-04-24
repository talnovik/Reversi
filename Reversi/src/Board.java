import java.util.*;

public class Board {
	private static final int SIZE = 8;
	private static final int EMPTY = 0;
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	private int turn;
	private int [][] board = new int[SIZE][SIZE]; // 0 = empty, 1 = black, 2 = white
	public int score;
	
	
	public Board() // board initialize
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
	
	
	
	public int GetPiece(int x, int y)
	{
		return this.board[x][y];
	}
	
	public boolean game_over() // Checking if the is over
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
	
	public int check_winner() // returning winner
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
	
	public int count_black() // returning how many black disks are in the board
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
	

	
	public int count_white() // returning how many white disks are in the board
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
	
	
	public ArrayList<Move> possibleMoves(int curr_color) // returning an Array of possible moves
    {
        ArrayList<Move> a = new ArrayList<>();
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
            	int current[] = new int[3];
            	current[0] = i; // x
            	current[1] = j; // y
            	current[2] = this.GetPiece(i, j); // color
                if (current[2] == EMPTY)
                {
                    for (int x = -1; x <= 1; x++)
                    {
                        for (int y = -1; y <= 1; y++)
                        {
                            if (x != 0 || y != 0)
                            {
                                try
                                {
                                	int[] near = new int[3];
                                	near[0] = current[0] + x;
                                	near[1] = current[1] + y;
                                	near[2] = this.GetPiece(near[0], near[1]);
                                    if (near[2] == 3 - curr_color)
                                    {
                                        int counter = 0;
                                        while (near[2] != EMPTY)
                                        {
                                        	near[0] = near[0] + x;
                                        	near[1] = near[1] + y;
                                        	near[2] = this.GetPiece(near[0], near[1]);
                                            counter++;
                                            if (near[2] == curr_color)
                                            {
                                                boolean found = false;
                                                for (Move move : a)
                                                {
                                                    if (move.getX() == current[0] && move.getY() == current[1])
                                                    {
                                                    		move.addDirection(new int[]{x, y, counter});
                                                            found = true;
                                                            break;
                                                    }
                                                        
                                                }
                                                if (!found)
                                                {
                                                    Move temp_move = new Move(current[0], current[1]);
                                                    temp_move.addDirection(new int[]{x, y, counter});
                                                    a.add(temp_move);
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (Exception e)
                                {
                                    continue;
                                }
                            }
                        }
                    }
                }

            }
        }
        return a;
    }
	
	public Board copyboard() // returning a copy of the board
	{
		Board copy = new Board();
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				copy.PutPiece(i, j, this.GetPiece(i, j));
			}
		}
		return copy;
	}
	
	public Board turn(Board b, Move move, int color) // making the player/AI turn
    {
		
		b.PutPiece(move.getX(), move.getY(), color);

        for (int[] i : move.getDirections())
        {
            int vx = move.getX() + i[0];
            int vy = move.getY() + i[1];

            for (int j = 0; j < i[2]; j++)
            {
            	b.PutPiece(vx, vy, color);

                vx += i[0];
                vy += i[1];
            }
        }
        b.evaluate();
        return b;
    }

	
	public ArrayList<Board> movestoboards(int color) // converting the moves to boards
    {
        ArrayList<Board> boards = new ArrayList<Board>();

        for (Move m : this.possibleMoves(color))
        {
            boards.add(turn(this.copyboard(), m, color));
        }
        return boards;
    }
	
	public void evaluate() // evaluate the board and giving him score
	{
		score = 0;
		int[][] board_value =
            {
                    {20, -3, 11, 8,  8, 11, -3, 20},
                    {-3, -7, -4, 1,  1, -4, -7, -3},
                    {11, -4, 2,  2,  2,  2, -4, 11},
                    { 8,  1, 2, -3, -3,  2,  1,  8},
                    { 8,  1, 2, -3, -3,  2,  1,  8},
                    {11, -4, 2,  2,  2,  2, -4, 11},
                    {-3, -7, -4, 1,  1, -4, -7, -3},
                    {20, -3, 11, 8,  8, 11, -3, 20}
            };

		int b = 0, w = 0;
        for(int i = 0; i < SIZE; i++)
        {
            for(int j = 0; j < SIZE; j++)
            {
                if(this.GetPiece(i, j) == BLACK_DISK)
                {
                    b += board_value[i][j];
                }
                else if(this.GetPiece(i, j) == WHITE_DISK)
                {
                    w += board_value[i][j];
                }
            }
        }
        
        try
        {
            score = (w - b) / (w + b);
        }
        catch (Exception e)
        {
            score = 0;
        }

        
        b += this.possibleMoves(BLACK_DISK).size();
        w += this.possibleMoves(WHITE_DISK).size();
        

        try
        {
            score += (w - b) / (w + b);
        }
        catch (Exception e)
        {
            score = 0;
        }
		
	}

	
}


