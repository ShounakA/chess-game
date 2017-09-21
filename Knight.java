import java.util.ArrayList;
/**
  This class is a subclass, Knight class, of ChessPiece and contains the specific character ID of the piece,
   and only allows.*/
  
public class Knight extends ChessPiece
{
    
    
    /**This is the constructor. It requires the player, the location, and the game the piece will be in.*/
    public Knight(String owner, ChessLocation loc, ChessGame game){
        super(owner, loc, game);
        if (getOwner().equals("P1"))
            charID = "N";
        else
            charID = "n";
        updateThreateningLocation(loc);
        
    }
    /**Checks the constraints of the move, and if true will move the piece*/
   public boolean moveTo(ChessLocation newLoc){
       
       
       // only created to make it easier to debug
       int row, col;
       row = Math.abs(getLoc().getX()-newLoc.getX());
       col = Math.abs(getLoc().getY()-newLoc.getY());
       
       if ((row==2 && col==1)||(row==1 && col==2)){ //if the x value has a difference of 2 and the Y a difference of 1 then its a valid move(vice versa)
                super.moveTo(newLoc);
                updateThreateningLocation(newLoc);
                return true;
       }
       else//if the move is invalid
        System.out.println("You can't move that piece there!");
            return true;
        
       
   }
   protected void updateThreateningLocation(ChessLocation newLoc){
       ArrayList<ChessLocation> threateningLocations = new ArrayList<ChessLocation>();
       int curRow, curCol;
       curRow = newLoc.getX();
       curCol = newLoc.getY();
      
       if ((curRow+2<=7)&&(curCol+1<=7)){
           if (!getGame().getBoard().isPieceAt(curRow+2, curCol+1)){//|_ v
                        threateningLocations.add(new ChessLocation(curRow+2, curCol+1));
                        
           }
        }
  
       if ((curRow+2<=7)&&(curCol-1>=0)){
           if (!getGame().getBoard().isPieceAt(curRow+2, curCol-1)){//_| v
                        threateningLocations.add(new ChessLocation(curRow+2, curCol-1));
                        
           }
        }
    
       if ((curRow-2>=0)&&(curCol+1<=7)){
           if (!getGame().getBoard().isPieceAt(curRow-2, curCol+1)){ // |_ ^
                        threateningLocations.add(new ChessLocation(curRow-2, curCol+1));
                        
           }
       }
    
       if ((curRow-2>=0)&&(curCol-1>=0)){
           if (!getGame().getBoard().isPieceAt(curRow-2, curCol-1)){// _| ^
                    threateningLocations.add(new ChessLocation(curRow-2, curCol-1));
                    
           }
       }
        
       if ((curRow+1<=7)&&(curCol+2<=7)){
           if (!getGame().getBoard().isPieceAt(curRow+1, curCol+2)){ //|__ v
                    threateningLocations.add(new ChessLocation(curRow+1, curCol+2));
                   
           }
        }
       if ((curRow-1>=0)&&(curCol+2<=7)){
           if (!getGame().getBoard().isPieceAt(curRow-1, curCol+2)){//|__ ^
                    threateningLocations.add(new ChessLocation(curRow-1, curCol+2));
                    
           }
       }
        
       if ((curRow+1<=7)&&(curCol-2>=0)){ 
           if (!getGame().getBoard().isPieceAt(curRow+1, curCol-2)){// __| v
                    threateningLocations.add(new ChessLocation(curRow+1, curCol-2));
                    
           }
       }
        
       if ((curRow-1>=0)&&(curCol-2>=0)){ 
           if (!getGame().getBoard().isPieceAt(curRow-1, curCol-2)){ // |__ ^
                    threateningLocations.add(new ChessLocation(curRow-1, curCol-2));
                    
           }
       }
       setThreateningLocations(threateningLocations);
      
   
    }
}
   
 
