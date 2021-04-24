import java.util.*;

public class AI {
	private int maxDepth = 5;
	public int color;
	
	public AI(int color)
	{
		this.color = color;
	}
	
	public Board MiniMax(Board board) // initializing the minimax
	{
		
		return max(board.copyboard(), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
	}
	
	
	public Board max(Board board, int a, int b, int depth) // for the White disk
	{
		if((board.game_over()) || (depth == maxDepth))
        {
            return board;
        }
        ArrayList<Board> nodes = board.movestoboards(2);
        if (nodes.isEmpty()) return board;
        Board maxBoard = nodes.get(0);
        for (Board node : nodes)
        {
            Board oppBoard = min(node, a, b, depth + 1);
            if(oppBoard.score > maxBoard.score)
            {
                maxBoard = node;
            }
            else if(oppBoard.score == maxBoard.score && Math.random() < .5f)
            {
                maxBoard = node;
            }
            
            a = Integer.max(a, oppBoard.score);
            if(b <= a)
            {
                break;
            }
        }
        
        return maxBoard;
	}
	
	public Board min(Board board, int a, int b, int depth) // for the Black disk
    {
        if((board.game_over()) || (depth == maxDepth))
        {
            return board;
        }
        ArrayList<Board> nodes = board.movestoboards(1);
        if (nodes.isEmpty()) return board;

        Board minBoard = nodes.get(0);
        for (Board node : nodes)
        {
            Board oppBoard = max(node, a, b, depth + 1);
            if(oppBoard.score < minBoard.score)
            {
                minBoard = node;
            }
            else if(oppBoard.score == minBoard.score && Math.random() < .5f)
            {
                minBoard = node;
            }

            b = Integer.min(b, oppBoard.score);
            if(b <= a)
            {
                break;
            }
        }
        return minBoard;
    }
	
	
}
