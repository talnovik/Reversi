import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;

public class Game {
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	
	
	public Game()
	{
		Board board = new Board(); // initialize of the board
		GUI GamePanel = new GUI(board); // Initialize of the GUI
		GamePanel.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
		board.setTurn(BLACK_DISK); // black player first
		int x;
		int y;
		int winner;
		while(GamePanel.getvp() == false && GamePanel.getvai() == false) // waiting for choosing a mode
		{
			try {
				TimeUnit.NANOSECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(GamePanel.getvp()) // in case of picking Player VS Player
		{
			GamePanel.countBlack.setText("Black Disks: " + board.count_black());
			GamePanel.countWhite.setText("White Disks: " + board.count_white());
			while(!board.game_over())
			{
				if(board.getTurn() == BLACK_DISK)
				{
					while(GamePanel.getclick() == false) // waiting for a mouse click
					{
						try {
							TimeUnit.NANOSECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					x = GamePanel.getposx();
					y = GamePanel.getposy();
					int turn = board.getTurn();
					ArrayList<Move> moves = board.possibleMoves(turn); // getting all of the possible moves
					for(int i = 0; i < moves.size(); i++)
					{
						if(moves.get(i).getX() == x && moves.get(i).getY() == y) // checking for valid move
						{
							board.turn(board, moves.get(i), turn); // making the turn
							board.setTurn(3 - turn);
						}
					}
					if(board.getTurn() == BLACK_DISK)
						GamePanel.setTitle("TURN: BLACK PLAYER");
					else
						GamePanel.setTitle("TURN: WHITE PLAYER");
					GamePanel.setclick(false);
					GamePanel.countBlack.setText("Black Disks: " + board.count_black());
					GamePanel.countWhite.setText("White Disks: " + board.count_white());
					GamePanel.repaint();
				}
				else
				{
					while(GamePanel.getclick() == false) // waiting for a mouse click
					{
						try {
							TimeUnit.NANOSECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					x = GamePanel.getposx();
					y = GamePanel.getposy();
					int turn = board.getTurn();
					ArrayList<Move> moves = board.possibleMoves(turn);
					for(int i = 0; i < moves.size(); i++)
					{
						if(moves.get(i).getX() == x && moves.get(i).getY() == y)
						{
							board.turn(board, moves.get(i), turn);
							board.setTurn(3 - turn);
						}
					}
					if(board.getTurn() == BLACK_DISK)
						GamePanel.setTitle("TURN: BLACK PLAYER");
					else
						GamePanel.setTitle("TURN: WHITE PLAYER");
					GamePanel.setclick(false);
					GamePanel.countBlack.setText("Black Disks: " + board.count_black());
					GamePanel.countWhite.setText("White Disks: " + board.count_white());
					GamePanel.repaint();
				}
			}
			
		}
		else // in case of picking Player VS AI
		{
			AI ai = new AI(WHITE_DISK); // initialize the AI
			GamePanel.countBlack.setText("Black Disks: " + board.count_black());
			GamePanel.countWhite.setText("White Disks: " + board.count_white());
			while(!board.game_over())
			{
				if(board.getTurn() == BLACK_DISK) // Player turn
				{
					while(GamePanel.getclick() == false) // waiting for mouse click
					{
						try {
							TimeUnit.NANOSECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					x = GamePanel.getposx();
					y = GamePanel.getposy();
					int turn = board.getTurn();
					ArrayList<Move> moves = board.possibleMoves(turn);
					for(int i = 0; i < moves.size(); i++)
					{
						if(moves.get(i).getX() == x && moves.get(i).getY() == y)
						{
							board.turn(board, moves.get(i), turn);
							board.setTurn(3 - turn);
						}
					}
					if(board.getTurn() == BLACK_DISK)
						GamePanel.setTitle("TURN: BLACK PLAYER");
					else
						GamePanel.setTitle("TURN: WHITE PLAYER");
					GamePanel.setclick(false);
					GamePanel.countBlack.setText("Black Disks: " + board.count_black());
					GamePanel.countWhite.setText("White Disks: " + board.count_white());
					GamePanel.repaint();
				}
				else // AI turn
				{
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					board = ai.MiniMax(board); // getting the AI move
					GamePanel.setboard(board); // making the AI move
					board.setTurn(BLACK_DISK);
					if(board.getTurn() == BLACK_DISK)
						GamePanel.setTitle("TURN: BLACK PLAYER");
					else
						GamePanel.setTitle("TURN: WHITE PLAYER");
					GamePanel.repaint();
					GamePanel.countBlack.setText("Black Disks: " + board.count_black());
					GamePanel.countWhite.setText("White Disks: " + board.count_white());
				}
			}
		}
		winner = board.check_winner(); // checking for a winner and putting him on the screen
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
