import java.util.*;
/**
 * This class places the board and the pieces together.

 */
public class ChessGame
{
    
    private ChessBoard board;
    private ChessPiece[] knight = new Knight[4];
    private ChessPiece[] bishop = new Bishop[4];
    private ChessPiece[] rook = new Rook[4];
    private ChessPiece[] king = new King[2];
    private ChessPiece[] queen = new Queen[2];
    private ChessPiece[] pawn = new Pawn[16];
    private String p1, p2;
    /**Constructor requires the players Names, and will create the pieces for player.*/
    public ChessGame(String p1, String p2){
       this.board = new ChessBoard ();
       this.knight[0] = new Knight (p1, new ChessLocation(0,1), this);
       this.knight[1] = new Knight (p1, new ChessLocation (0,6), this);
       this.bishop[0] = new Bishop (p1, new ChessLocation (0,2), this);
       this.bishop[1] = new Bishop (p1, new ChessLocation (0,5), this);
       this.rook[0] = new Rook (p1, new ChessLocation (0,0), this);
       this.rook[1] = new Rook (p1, new ChessLocation(0,7), this);
       this.king[0] = new King(p1, new ChessLocation(0,4), this);
       this.queen[0] = new Queen(p1, new ChessLocation(0,3), this);
       for (int i=0;i<pawn.length/2;i++){
        this.pawn[i]=new Pawn(p1, new ChessLocation (1,i),this);
       }
       this.knight[2] = new Knight (p2, new ChessLocation(7,1), this);
       this.knight[3] = new Knight (p2, new ChessLocation (7,6), this);
       this.bishop[2] = new Bishop (p2, new ChessLocation (7,2), this);
       this.bishop[3] = new Bishop (p2, new ChessLocation (7,5), this);
       this.rook[2] = new Rook (p2, new ChessLocation (7,0), this);
       this.rook[3] = new Rook (p2, new ChessLocation(7,7), this);
       this.king[1] = new King(p2, new ChessLocation(7,3), this);
       this.queen[1] = new Queen(p2, new ChessLocation(7,4), this);
       
       for (int i=pawn.length/2;i<(pawn.length);i++){
        this.pawn[i]= new Pawn(p2, new ChessLocation (6,pawn.length-i-1),this);
       }
       this.p1 = p1;
       this.p2 = p2;
       
    }
    
    /**Returns the board.*/
    public ChessBoard getBoard(){
        return this.board;
    }
    /**Changes the board.*/
    public void setBoard(ChessBoard board){
        this.board=board;
    }
    public List<ChessPiece> getPlayer1Pieces(){
        List<ChessPiece> p1 = new ArrayList<ChessPiece>();
        p1.add(bishop[0]);
        p1.add(bishop[1]);
        p1.add(rook[0]);
        p1.add(rook[1]);
        p1.add(knight[0]);
        p1.add(knight[1]);
        p1.add(queen[0]);
        p1.add(king[0]);
       for (int i=0;i<pawn.length/2;i++){
        p1.add(this.pawn[i]);
       }
        return p1;
    }
    public List<ChessPiece> getPlayer2Pieces(){
        List<ChessPiece> p2 = new ArrayList<ChessPiece>();
        p2.add(bishop[2]);
        p2.add(bishop[3]);
        p2.add(rook[2]);
        p2.add(rook[3]);
        p2.add(knight[2]);
        p2.add(knight[3]);
        p2.add(queen[1]);
        p2.add(king[1]);
        for (int i=pawn.length/2;i<(pawn.length);i++){
           p2.add(this.pawn[i]);
        }
        return p2;
    }
    
    
    
}
