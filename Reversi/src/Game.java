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
		boolean vaild_move;
		int winner;
		while(!board.game_over())
		{	
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
