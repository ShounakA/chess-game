import java.util.ArrayList;
/**This class is a subclass, Pawn class, of ChessPiece and contains the specific character ID of the piece, and only allows.*/
public class Pawn extends ChessPiece{
   /** This entry represents the first move.*/
   public boolean firstMove;
   /**This is the constructor. It requires the player, the location, and the game the piece will be in.*/
   public Pawn(String owner, ChessLocation loc, ChessGame game){
        super(owner, loc, game);
        
        if (getOwner().equals("P1"))
            charID = "P";
        else
            charID = "p";
        firstMove=false;
        updateThreateningLocation(loc);
    }
   /**Checks the constraints of the move, and if true will move the piece*/
   public boolean moveTo(ChessLocation newLoc){
       // only created to make it easier to debug
       int len, ofst;
       if (getOwner().equals("P2")){
           len = getLoc().getX()-newLoc.getX();
           
       }
       else{
           len = newLoc.getX()-getLoc().getX();
          
        }
       double row, col;
       row = Math.abs(getLoc().getX()-newLoc.getX());
       col = Math.abs(getLoc().getY()-newLoc.getY());
       if ((getLoc().getY()==newLoc.getY()&&len==2)&&firstMove==false){
          if ((!getGame().getBoard().isPieceAt(newLoc.getX(),newLoc.getY())&&(!getGame().getBoard().isPieceAt(newLoc.getX(),newLoc.getY())))){
              if (checkLineOfSight(getLoc(),newLoc)){
                  super.moveTo(newLoc);
                  firstMove=true;
                  updateThreateningLocation(newLoc);
                  return true;
              }
            }
            else{
               if(getGame().getBoard().getPiece(newLoc.getX(),newLoc.getY()).getOwner().equals("P1")||getGame().getBoard().getPiece(newLoc.getX(),newLoc.getY()).getOwner().equals("P2")){ 
                  System.out.println("You can't move that piece there!");
                  return false;
               } 
           }
       }
       else if (getLoc().getY()==newLoc.getY()&&len==1){
           if ((!getGame().getBoard().isPieceAt(newLoc.getX(),newLoc.getY()))){
              if (checkLineOfSight(getLoc(),newLoc)){
                  super.moveTo(newLoc);
                  firstMove=true;
                  updateThreateningLocation(newLoc);
                  return true;
              }
            }
            else{
               if(getGame().getBoard().getPiece(newLoc.getX(),newLoc.getY()).getOwner().equals("P1")||getGame().getBoard().getPiece(newLoc.getX(),newLoc.getY()).getOwner().equals("P2")){ 
                  System.out.println("You can't move that piece there!");
                  return false;
               } 
           }
       }
       else if (((row/col ==1)&&(row==1 || col==1))&&(getGame().getBoard().isPieceAt(newLoc.getX(),newLoc.getY())&&!getGame().getBoard().getPiece(newLoc.getX(),newLoc.getY()).getOwner().equals(getOwner()))){ //if the x value has a difference of 2 and the Y a difference of 1 then its a valid move(vice versa)
           super.moveTo(newLoc);
              firstMove=true;
              updateThreateningLocation(newLoc);
              return true;
       }
       else
           System.out.println("You can't move that piece there!");
           return false;
           
     
      
       
        
       
   }
   protected void updateThreateningLocation(ChessLocation newLoc){
       ArrayList<ChessLocation> threateningLocations = new ArrayList<ChessLocation>();
        int curRow, curCol;
        curRow = newLoc.getX();
        curCol = newLoc.getY();
        
        ChessLocation incLoc = new ChessLocation (curRow, curCol);
        if (getOwner().equals("P1")){
            if (curCol == 0){
                incLoc.setXY(incLoc.getX()+1,incLoc.getY()+1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
            else if (curCol == 7){
                incLoc.setXY(incLoc.getX()+1,incLoc.getY()-1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
            else if (curRow == 7){
                //do nothing there are not thretening locations
            }
            else{
                incLoc.setXY(incLoc.getX()+1,incLoc.getY()+1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
                incLoc.setXY(curRow,curCol);
                incLoc.setXY(incLoc.getX()+1,incLoc.getY()-1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
        }
        else{
            if (curCol == 0){
                incLoc.setXY(incLoc.getX()-1,incLoc.getY()+1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
            else if (curCol == 7){
                incLoc.setXY(incLoc.getX()-1,incLoc.getY()-1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
            else if (curRow ==0){
                //do nothing there are no threatening locations
            }
            else{
                incLoc.setXY(incLoc.getX()-1,incLoc.getY()+1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
                incLoc.setXY(curRow,curCol);//back to its position
                incLoc.setXY(incLoc.getX()-1,incLoc.getY()-1);
                if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())){
                    threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
                }
            }
        }
       setThreateningLocations(threateningLocations);
       
           
   }
}
 