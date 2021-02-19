import java.util.Scanner;

public class Game {
	static Scanner input = new Scanner(System.in);
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	private static final int BLACK_PLAYER = 0;
	private static final int WHITE_PLAYER = 1;
	private int turn;
	
	
	public Game()
	{
		Board board = new Board();
		
		turn = BLACK_PLAYER;
		int x;
		int y;
		boolean vaild_move;
		int winner;
		while(!board.game_over())
		{
			if(turn == BLACK_PLAYER)
			{
				board.print_board();
				System.out.println();
				System.out.println("Black player: type x: ");
				x = input.nextInt();
				System.out.println("Black player: type y: ");
				y = input.nextInt();
				do
				{
					vaild_move = board.vaild_move(x, y);
					if(vaild_move)
						board.PutPiece(x, y, BLACK_DISK);
				}
				while(vaild_move);
				board.UpdateBoard();
				board.print_board();
			}
			else
			{
				board.print_board();
				System.out.println();
				System.out.println("White player: type x: ");
				x = input.nextInt();
				System.out.println("White player: type y: ");
				y = input.nextInt();
				do
				{
					vaild_move = board.vaild_move(x, y);
					if(vaild_move)
						board.PutPiece(x, y, BLACK_DISK);
				}
				while(vaild_move);
				board.UpdateBoard();
				board.print_board();
			}
		}
		winner = board.check_winner();
		if(winner == BLACK_PLAYER)
			System.out.println("Black Player wins");
		else if(winner == WHITE_PLAYER)
			System.out.println("White Player wins");
		else
			System.out.println("Tie");
	}
}
