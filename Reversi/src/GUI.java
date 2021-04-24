import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	private static Board board;
	private static int posx;
	private static int posy;
	private JLabel title;
	private static JButton vsplayer = new JButton();
	private static JButton vsAI = new JButton();
	private static JButton exit = new JButton();
	private static JLabel menuTitle = new JLabel();
	public static JLabel countBlack = new JLabel();
	public static JLabel countWhite = new JLabel();
	private static boolean click;
	private static boolean vp = false;
	private static boolean vai = false;
	

	public GUI(Board b) //Initialize GUI
	{
		board = b;
		menu();
	}
	
	public Dimension getPreferredSize() { 
 	   return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void menu() // initialize the menu
	{
		setSize(DEFAULT_WIDTH/3, DEFAULT_HEIGHT/3);
		
		
		Container mainP = getContentPane();
        mainP.setLayout(null);

        menuTitle = new JLabel("Reversi");
        vsplayer = new JButton("Player VS Player");
        vsAI = new JButton("Player VS COM");
        exit = new JButton("Exit");


        mainP.add(menuTitle);
        menuTitle.setFont(new Font("Chiller",Font.BOLD,50));
        menuTitle.setBounds(140, 30, 200, 50);

        mainP.add(vsplayer);
        vsplayer.setMnemonic(KeyEvent.VK_S);
        vsplayer.setBounds(100, 80, 200, 30);

        mainP.add(vsAI);
        vsAI.setMnemonic(KeyEvent.VK_H);
        vsAI.setBounds(100, 110, 200, 30);


        mainP.add(exit);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setBounds(100, 140, 200, 30);


        vsplayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainP.removeAll();
				gamePVP();
			}
		});
        
        vsAI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainP.removeAll();
				gamePVAI();
				
			}
		});
        
		exit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(1);
					}
				});
		        
        

        setResizable(false);
		setVisible(true);
	}
	
	
	public void gamePVP() // initialize the game of Player VS Player
	{
		this.vp = true;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		title = new JLabel("TURN: BLACK PLAYER");
		Font font = new Font("TURN: BLACK PLAYER", Font.BOLD, 25);
		title.setFont(font);
		TitlePanel.add(title, BorderLayout.CENTER);
		TitlePanel.setSize(1200, 50);
		TitlePanel.setVisible(true);
		game = new PaintBoard(board);
		game.setSize(1200, 860);
		add(TitlePanel);
		add(game);
		Container gameP = getContentPane();
        gameP.setLayout(null);
        gameP.add(countBlack);
        countBlack.setFont(new Font("Arial",Font.BOLD,35));
        countWhite.setFont(new Font("Arial",Font.BOLD,35));
        countBlack.setText("Black Disks: " + board.count_black());
		countWhite.setText("White Disks: " + board.count_white());
        countBlack.setBounds(200, 880, 300, 50);
        countWhite.setBounds(800, 880, 300, 50);
        gameP.add(countWhite);
        countBlack.setVisible(true);
		setVisible(true);
		this.addMouseListener(this);
		pack();
	}
	
	public void gamePVAI() // initialize the game of Player VS AI
	{
		this.vai = true;
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		title = new JLabel("TURN: BLACK PLAYER");
		Font font = new Font("TURN: BLACK PLAYER", Font.BOLD, 25);
		title.setFont(font);
		TitlePanel.add(title, BorderLayout.CENTER);
		TitlePanel.setSize(1200, 50);
		TitlePanel.setVisible(true);
		game = new PaintBoard(board);
		game.setSize(1200, 860);
		add(TitlePanel);
		add(game);
		Container gameP = getContentPane();
        gameP.setLayout(null);
        gameP.add(countBlack);
        countBlack.setFont(new Font("Arial",Font.BOLD,35));
        countWhite.setFont(new Font("Arial",Font.BOLD,35));
        countBlack.setBounds(200, 880, 300, 50);
        countWhite.setBounds(800, 880, 300, 50);
        gameP.add(countWhite);
        countBlack.setVisible(true);
		setVisible(true);
		this.addMouseListener(this);
		pack();
	}
	
	public void setboard(Board b) // painting the board on the screen
	{
		this.board = b;
		game = new PaintBoard(b);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { // returning the position of the mouse click
		
		int x = e.getX(), y = e.getY();
		if(x >= 210 && y >= 80 && x <= 1010 && y <= 880)
		{
			this.posx = x/SQUARE - 2;
			this.posy = (y - 80)/SQUARE;
			this.click = true;
		}
	}
	
	public void setTitle(String s)
	{
		title.setText(s);
	}
	
	public int getposx()
	{
		return this.posx;
	}
	
	public int getposy()
	{
		return this.posy;
	}
	
	public boolean getclick()
	{
		return this.click;
	}
	
	public void setclick(boolean c)
	{
		this.click = false;
	}
	
	public boolean getvp()
	{
		return this.vp;
	}
	
	public boolean getvai()
	{
		return this.vai;
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


class PaintBoard extends JPanel { // a class that painting the board on the screen
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
		g.setColor(Color.green);
		g.fillRect(2*SQUARE, START, 8*SQUARE, 8*SQUARE);
		g.setColor(Color.black);
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











