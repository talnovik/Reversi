import java.util.ArrayList;

public class Move {

	private int x, y; // position of the disk
    private ArrayList<int[]> directions = new ArrayList<>(); // the direction to make the move

    public Move(int x, int y)
    {
        this.x = x;
        this.y = y;
        directions.clear();
    }

    public void addDirection (int[] dir){
    	for (int[] d : this.directions)
    	{
    		if(d[0] == dir[0] && d[1] == dir[1])
    		{
    			if(d[2] > dir[2])
    			{
	    			this.directions.remove(d);
	    			directions.add(dir);
	    			return;
    			}
    			else
    			{
    				return;
    			}
    		}
    	}
        directions.add(dir);
    }

    public ArrayList<int[]> getDirections() {
        return directions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
 
}
