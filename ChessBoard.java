
/**This is the chess board, it will display the board, and the pieces on the board.*/
public class ChessBoard
{
    
    private ChessPiece[][] tiles=new ChessPiece[8][8];
    /**This constructor makes the "tiles" of the board.*/
    public ChessBoard(){
        for (int j=0;j<8;j++){
            for (int i=0;i<8;i++){
                this.tiles[i][j]=null;
            }
        }
        
    }
    /**This prints the board onto the terminal with all the pieces that were created.*/
    public void createBoard(){//prints the tiles and pieces.
        
        for (int x=0;x<8;x++){
            
            for (int y =0;y<8;y++){
                if(tiles[y][x] != null)
                System.out.print(this.tiles[y][x].getID() + " ");
                else
                System.out.print("_ ");
            }
            System.out.println("");
        }
        
        System.out.println("\n\n\n");
    }
    /**Returns true if there is a piece at the provided tile coordinates.*/
    public boolean isPieceAt(int x,int y){
        //checks to see if tiles is "__" if it isnt then there is a piece there
        if (this.tiles[y][x]==null)
            return false;
        return true;
        
    }
    /**Places piece on the board.*/
    public void placePieceAt(ChessPiece piece, ChessLocation loc){
        this.tiles[loc.getY()][loc.getX()]=piece;//pieces are represented by first letter of there name (EXCEPT THE KNIGHT PIECE, BECUASE OF KING)
        
    }
    /**Removes piece from the board.*/
    public void removePiece(ChessLocation loc){
        this.tiles[loc.getY()][loc.getX()]= null ;//an empty tile is presented as "_"
    }
    /**Returns the specified piece*/
    public ChessPiece getPiece(int x,int y){ 
        return tiles[y][x];
    }
    
}
