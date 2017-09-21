import java.util.ArrayList;
/**This class is a subclass, Bishop class, of ChessPiece and contains the specific character ID of the piece,
   and only allows.*/
public class Bishop extends ChessPiece{
    /**This is the constructor. It requires the player, the location, and the game the piece will be in.*/
    public Bishop (String owner, ChessLocation loc, ChessGame game){
        super (owner, loc,game);
        
        if (getOwner().equals("P1"))
            charID = "B";
        else
            charID = "b";
        //updateThreateningLocation(loc);
    }
    /**Checks the constraints of the move, and if true will move the piece*/
    public boolean moveTo(ChessLocation newLoc){
        double m=0;
        if ((newLoc.getX()-getLoc().getX())!=0)
             m = Math.abs((newLoc.getY()-getLoc().getY())/(newLoc.getX()-getLoc().getX())); 
        if (m==1){
            if (checkLineOfSight(getLoc(),newLoc))
                super.moveTo(newLoc);
                updateThreateningLocation(newLoc);
                return true;
        }
        else
            System.out.println("You can't move that piece there! Please try again.");
            return false;
    }
    protected void updateThreateningLocation(ChessLocation newLoc){
       ArrayList<ChessLocation> threateningLocations = new ArrayList<ChessLocation>();
        int disL, disR, disF, disB;
        int curRow, curCol;
        curRow = newLoc.getX();
        curCol = newLoc.getY();
        //finding how many space the piece can move on the board.
        disL = curCol;
        disR = 7-curCol;
        disF = 7-curRow ;
        disB = curRow;
        //
        int limitFactor;
        ChessLocation incLoc = new ChessLocation (curRow, curCol);
        if (disL<disF)
            limitFactor=disL;
        else
            limitFactor=disF;
        for (int i=0;i<limitFactor;i++){
            incLoc.setXY(incLoc.getX()+1,incLoc.getY()-1);
            if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())||getGame().getBoard().getPiece(incLoc.getX(),incLoc.getY()).getID().equalsIgnoreCase("k")){
                 threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
            }
            else{break;}
        }
        incLoc.setXY(curRow,curCol);
        if (disR<disF)
            limitFactor=disR;
        else
            limitFactor=disF;
        for (int i=0; i<limitFactor;i++){
            incLoc.setXY(incLoc.getX()+1,incLoc.getY()+1);
            if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())||getGame().getBoard().getPiece(incLoc.getX(),incLoc.getY()).getID().equalsIgnoreCase("k")){
                threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
            }
            else{break;}
        }
        incLoc.setXY(curRow,curCol);
         if (disR<disB)
            limitFactor=disR;
        else
            limitFactor=disB;
        for (int i=0;i<limitFactor;i++){
            incLoc.setXY(incLoc.getX()-1,incLoc.getY()+1);
            if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())||getGame().getBoard().getPiece(incLoc.getX(),incLoc.getY()).getID().equalsIgnoreCase("k")){
                threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
            }
            else{break;}
        }
        incLoc.setXY(curRow,curCol);
         if (disL<disB)
            limitFactor=disL;
        else
            limitFactor=disB;
        for (int i=0; i<limitFactor;i++){
            incLoc.setXY(incLoc.getX()-1,incLoc.getY()-1);
            if (!getGame().getBoard().isPieceAt(incLoc.getX(),incLoc.getY())||getGame().getBoard().getPiece(incLoc.getX(),incLoc.getY()).getID().equalsIgnoreCase("k")){
                threateningLocations.add(new ChessLocation (incLoc.getX(),incLoc.getY()));
            }
            else{break;}
        }
        setThreateningLocations(threateningLocations);
    }
}
