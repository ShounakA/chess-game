import java.util.*;
/**This class is a subclass, King class, of ChessPiece and contains the specific character ID of the piece, and only allows.*/
public class King extends ChessPiece{
    /**This is the constructor. It requires the player, the location, and the game the piece will be in.*/
    public King(String owner, ChessLocation loc, ChessGame game){
        super(owner, loc, game);
        if (getOwner().equals("P1"))
            charID = "K";
        else
            charID = "k";
        //game.getBoard().placePieceAt(this,loc);
    }
    /**Checks the constraints of the move, and if true will move the piece*/
   public boolean moveTo(ChessLocation newLoc){
       
       
       // only created to make it easier to debug
       double row, col;
       row = Math.abs(getLoc().getX()-newLoc.getX());
       col = Math.abs(getLoc().getY()-newLoc.getY());
       ChessPiece p = locationInDanger(newLoc);
       if ((getLoc().getX()==newLoc.getX()&&col==1)||(getLoc().getY()==newLoc.getY()&&row==1)){
          
           if (p==null){
               if (checkLineOfSight(getLoc(),newLoc)){
                    super.moveTo(newLoc);
                    updateThreateningLocation(newLoc);
                    return true;
                }
           }
           else
            System.out.println("You can't move there is a piece at "+ p.getLoc().getX() +"," +p.getLoc().getY()+ " that is putting your king in check!");
            
       }
       else if ((row/col ==1)&&(row==1 || col==1)){ //if the x value has a difference of 2 and the Y a difference of 1 then its a valid move(vice versa)
           if (p==null){
               if (checkLineOfSight(getLoc(),newLoc)){
                    super.moveTo(newLoc);
                    updateThreateningLocation(newLoc);
                    return true;
                }
            }
            else
            System.out.println("You can't move there is a piece at "+ p.getLoc().getX() +"," +p.getLoc().getY()+ " that is putting your king in check!");
       }
       
       else//if the move is invalid
        System.out.println("You can't move that piece there!");
        return false;
        
       
   }
   /**Updates all threatening locations that are posisble*/
   protected void updateThreateningLocation(ChessLocation newLoc){
   ArrayList<ChessLocation> threateningLocations = new ArrayList<ChessLocation>();
   int curRow, curCol;
   curRow = newLoc.getX();
   curCol = newLoc.getY();
   
       for (int i=-1;i<2;i++){
        for (int j=-1;j<2;j++){
            if ((curRow+i>=0&&curRow+i<=7)&&(curCol+j>=0&&curCol+j<=7)){
                if (!getGame().getBoard().isPieceAt(curRow+i,curCol+j)){
                    threateningLocations.add(new ChessLocation(curRow+i,curCol+j));
                }
            }
        }
       }
       setThreateningLocations(threateningLocations);
   
   }
   /**Finds first location that puts the king in danger, if any*/
   public ChessPiece locationInDanger(ChessLocation loc){
      
       List<ChessPiece> l = new ArrayList<ChessPiece>();
       if (getOwner().equals("P2"))
        l = getGame().getPlayer1Pieces();
       else
        l = getGame().getPlayer2Pieces();
       int index =-1;
       for (ChessPiece p: l){
            p.updateThreateningLocation(p.getLoc());
            ArrayList<ChessLocation> cl = p.getThreateningLocations();
            for (ChessLocation cl1 : cl){
                if (cl1.equals(loc)){
                    return p;
                }
            }
            
       }
       
       return null;
   }
   /**Checks to see if the king is in check*/
   public ChessPiece check(){
    return locationInDanger(getLoc());
   }
}
