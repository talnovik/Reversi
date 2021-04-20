import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;

public class Game {
	static Scanner input = new Scanner(System.in);
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	
	
	public Game()
	{
		Board board = new Board();
		GUI GamePanel = new GUI(board);
		GamePanel.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
		board.setTurn(BLACK_DISK);
		int x;
		int y;
		int winner;
		while(!board.game_over())
		{	
			if(GamePanel.getclick() == true)
			{
				GamePanel.setclick(false);
				x = GamePanel.getposx();
				y = GamePanel.getposy();
				int turn = board.getTurn();
				if (board.vaild_move(x, y))
				{
					board.PutPiece(x, y, turn);
					board.updateBoardFix2(x,y, turn);
					if (board.getTurn() == BLACK_DISK)
						board.setTurn(WHITE_DISK);
					else
						board.setTurn(BLACK_DISK);
					if(board.getTurn() == BLACK_DISK)
						GamePanel.setTitle("TURN: BLACK PLAYER");
					else
						GamePanel.setTitle("TURN: WHITE PLAYER");
				}
			}
			GamePanel.repaint();
		}
		winner = board.check_winner();
		if(winner == BLACK_DISK)
			GamePanel.setTitle("Black Player Wins");
		else if(winner == WHITE_DISK)
			GamePanel.setTitle("White Player wins");
		else
			GamePanel.setTitle("Tie");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
}
