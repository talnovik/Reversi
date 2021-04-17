import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLClientInfoException;

public class GUI extends JFrame  implements MouseListener{
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 1000;
	private static final int SQUARE = 100;
	private static final int EMPTY = 0;
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	private static JPanel TitlePanel = new JPanel();
	private static PaintBoard game;
	private static JPanel bottomPanel = new JPanel();
	private static Board board;
	private JLabel title;
	

	public GUI(Board b)
	{
		board = b;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		title = new JLabel("TURN: BLACK PLAYER");
		Font font = new Font("TURN: BLACK PLAYER", Font.BOLD, 25);
		title.setFont(font);
		TitlePanel.add(title, BorderLayout.CENTER);
		TitlePanel.setSize(1200, 50);
		TitlePanel.setVisible(true);
		game = new PaintBoard(board);
		game.setSize(1200, 800);
		add(TitlePanel);
		add(game);
		setVisible(true);
		this.addMouseListener(this);
		pack();
	}
	
	public Dimension getPreferredSize() { 
 	   return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		int turn = board.getTurn();
		x = x/SQUARE - 2;
		y = (y - 80)/SQUARE;
		if (board.GetPiece(x, y) == EMPTY)
		{
			board.PutPiece(x, y, turn);
			board.updateBoardFix2(x,y, turn);
			if (board.getTurn() == BLACK_DISK)
				board.setTurn(WHITE_DISK);
			else
				board.setTurn(BLACK_DISK);
			if(board.getTurn() == BLACK_DISK)
				title.setText("TURN: BLACK PLAYER");
			else
				title.setText("TURN: WHITE PLAYER");
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


class PaintBoard extends JPanel {
	private static final int SIZE = 8;
	private static final int SQUARE = 100;
	private static final int START = SQUARE/2;
	private static final int EMPTY = 0;
	private static final int BLACK_DISK = 1;
	private static final int WHITE_DISK = 2;
	private static Board board;
	
	public PaintBoard(Board board)
	{
		this.board = board;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i <= SIZE; i++)
		{
			g.drawLine(2*SQUARE, i*SQUARE+START, SIZE*SQUARE+2*SQUARE, i*SQUARE+START);
		}
		for(int j = 0; j <= SIZE; j++)
		{
			g.drawLine(j*SQUARE+2*SQUARE, START, j*SQUARE+2*SQUARE, SIZE*SQUARE+START);
		}
		
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				if(board.GetPiece(i, j) == BLACK_DISK)
				{
					g.setColor(Color.black);
					g.fillOval(i*SQUARE+2*SQUARE+SQUARE/4, j*SQUARE+SQUARE-25, 50, 50);
				}
				if(board.GetPiece(i, j) == WHITE_DISK)
				{
					g.setColor(Color.white);
					g.fillOval(i*SQUARE+2*SQUARE+SQUARE/4, j*SQUARE+SQUARE-25, 50, 50);
				}
			}
		}
		
	}

	
	
}











