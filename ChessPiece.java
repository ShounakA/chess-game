import java.util.ArrayList;
/**
 * This class provides the super class methods to move and check the line of sight of a chess piece.
   */
public abstract class ChessPiece implements ChessPieceInterface{
   
   private ChessGame game;
   private String owner;
   private ChessLocation loc;
   protected String charID;
   private ArrayList<ChessLocation> threateningLocations;
   /**
      This is the constructor. It requires the player, the location, and the game the piece will be in.
      */
   public ChessPiece(String owner, ChessLocation loc, ChessGame game){
        //inits the variables
        this.owner=owner;
        this.loc=loc;
        this.game=game;
        this.charID=" ";
        game.getBoard().placePieceAt(this,loc);
        threateningLocations = new ArrayList<ChessLocation>();
    }
  /**Returns the owner string.*/
  public String getOwner(){ return this.owner;}
  /**Returns the chess location.*/
  public ChessLocation getLoc(){ return this.loc;}
  /**Returns the current game.*/
  public ChessGame getGame(){ return this.game;}
  /**Returns the ID of the piece*/
  public String getID(){
   return this.charID; 
  }
  /**Returns all the threatening locations of the chess piece*/
  public ArrayList<ChessLocation> getThreateningLocations(){
   return  threateningLocations;
  }
  /**Changes the ArrayList of ChessLocations*/
  public void setThreateningLocations(ArrayList<ChessLocation> l){
      this.threateningLocations = l;
  }
  /**Changes the location of the piece and removes the piece at the old location.*/
  public boolean moveTo(ChessLocation newLoc){
    if ((newLoc.getX()<8||newLoc.getX()>=0)&&(newLoc.getY()<8||newLoc.getY()>=0)){
        ChessBoard board = this.game.getBoard();
      if ((board.isPieceAt(newLoc.getX(), newLoc.getY()))){
          if (!board.getPiece(newLoc.getX(), newLoc.getY()).getOwner().equals(getOwner())){
                board.removePiece(getLoc());
                board.placePieceAt(this, newLoc);
                this.loc.setXY(newLoc.getX(),newLoc.getY()); 
                return true;
          }
          else{
            System.out.println("You can't kill your own piece!");
            return false;
          }
       }
       else{
           board.removePiece(getLoc());
           board.placePieceAt(this, newLoc);
           this.loc.setXY(newLoc.getX(),newLoc.getY()); 
           return true;
        }
    }
    else{
        System.out.println("There is no such tile!");
        return false;
    }
  }
  /**Checks the type of move that was made and increments up to the new location to find a piece.*/
  protected boolean checkLineOfSight(ChessLocation start, ChessLocation end){
   int lenY = end.getY()-start.getY();
   int lenX = end.getX()-start.getX();
   int incX = 1;
   int incY = 1;
   int len = lenY;
   /*these statements check to see what kind of move it was, so it doesnt have to check for both diagonal
    *and straight moves
      */
   if (lenY<0){
    incY =-1;
   }
   if (lenX<0){
    incX=-1;
   }
   if (lenY==0){
    incY=0;
    len=lenX;
   }
   if (lenX==0){ 
    incX=0;
   }
   
   int x = start.getX()+incX;
   int y = start.getY()+incY;
   for (int i=0;i<Math.abs(len)-1;i++){
       boolean check = this.game.getBoard().isPieceAt(x,y);//&&this.game.getBoard().getPiece(x,y).getOwner().equals(getOwner());
       if (check){
           System.out.println("You have a piece in the way!");
           return false;
       }
       x+=incX;
       y+=incY;
        
   }
   return true;
  }
  protected abstract void updateThreateningLocation(ChessLocation newLocation);
 
}
