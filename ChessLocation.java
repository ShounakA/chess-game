
/**
 * This class is there to store the coordinates of the piece. 
 */
public class ChessLocation
{
    private int x,y;//only variables that we need
    /**This constructor requires the x and y coordinate of the piece.*/
    public ChessLocation(int x,int y)//Only Constructor, you must have x,y.
    {
        this.x=x;
        this.y=y;
    }
    
    /**Returns the X coordinate.*/
    public int getX(){
        return this.x;
    }
     /**Returns the Y coordinate.*/
    public int getY(){
        return this.y;
    }
     /**Changes the X coordinate.*/
    public void setX(int x){
        this.x=x;
    }
    /**Changes the Y coordinate.*/
    public void setY(int y){
        this.y=y;
    }
    /**Changes the X and Y coordinates at the same time.*/
    public void setXY (int x, int y){
        //This is to set both x and y at the same time
        this.x=x;
        this.y=y;
    }
    /**Provides a string representation of the ChessLocation*/
    public String toString(){
        return this.x+","+this.y;
    }
    /**Checks if the given location has the same row and column as that of itself*/
    @Override
    public boolean equals (Object o){
        boolean sameSame = false;
       
        if (o != null && o instanceof ChessLocation)
        { 
            ChessLocation cp = (ChessLocation) o;
                sameSame = getX()==cp.getX()&&getY()==cp.getY();
        }
        return sameSame; 
    }
   
}
