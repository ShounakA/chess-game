
/**
 *This is the main class. This where the classes are put together to make the game.
 */
import javax.swing.*;
import java.io.*;

public class PlayGame
{
    public static String[] commands;
    
    public static void main(String[] args) throws IOException {
      //lets us access the pieces, board, and the location, 
      //they all now point to the same address as the ones created in ChessGame
      boolean hasMoved= false;
      ChessGame game= new ChessGame("P1", "P2");
      ChessBoard board= game.getBoard();
      
      ChessLocation newLoc = new ChessLocation (0,0);//temporary location
      //keep track of king
      ChessLocation k1Loc = new ChessLocation (0,4);
      ChessLocation k2Loc = new ChessLocation (7,3);
      ChessPiece check1 = null;
      ChessPiece check2 = null;
      boolean whoTurn = true;
      //Declare and Init variables
      String comLine;
      String instructions = "\n\n\nWelcome to my version of Chess! \nTo interact with the game you must learn the commands."
                                +"\nYou must type these exactly. Must include a space in between every new parameter."
                                +"\nType 'move' to move, then type the piece you want to move ex. 'N' to move the Knight"
                                + "\nType x coordinate followed by a space and then the y coordinate.\nHere is an example command"
                                + "\nmove 0 1 - 2 2 "
                                + "\n type 'help' if you need to view the list of commands\n";
                                
      String help = "Move Instruction: move row col - row col\nRestart Instruction: restart\nQuit Instruction: quit\nHelp Instruction: help\n";
       
      System.out.println(instructions);//Displays the Instructions
      System.out.println("Player 1's Move\n\n\n");
      board.createBoard();
      do{//do while loop when user enters "quit" the games exits
          /*Player Moves!*/
          //Sets up the terminal to take input
          BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));
          comLine = lineOfText.readLine();//takes the input from the terminal and stores 
          getCommands(comLine);//splits the input and stores into an array
          // this checks for the command in the terminal.
          //There will really only be 4 columns to check for which is why I hard coded the size of the array
          if (commands[0].equals("move")){//checks the command (so far only has the Knight piece)
            if (board.isPieceAt(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]))){
                //Player 1 Turn
                if (whoTurn){
                    if (board.getPiece(Integer.parseInt(commands[1]),Integer.parseInt(commands[2])).getOwner().equals("P1")){
                        if ((Integer.parseInt(commands[1])>=0)&&(Integer.parseInt(commands[2])>=0)&&(Integer.parseInt(commands[4])>=0)&&((Integer.parseInt(commands[5])>=0))){
                                hasMoved = board.getPiece(Integer.parseInt(commands[1]),Integer.parseInt(commands[2])).moveTo(new ChessLocation (Integer.parseInt(commands[4]), Integer.parseInt(commands[5])));
                            
                            if (hasMoved && board.getPiece(Integer.parseInt(commands[4]),Integer.parseInt(commands[5])).getID().equals("K")){//if the king has moved update the location
                                k1Loc.setXY(Integer.parseInt(commands[4]),Integer.parseInt(commands[5]));
                            }
                            if (hasMoved && board.getPiece(Integer.parseInt(commands[4]),Integer.parseInt(commands[5])).getLoc().equals(k2Loc)){//if player one kills the other king you win
                                System.out.println("Player 1 wins! He has captured Player 2's King!");
                                break;
                            }
                            check2 = ((King)board.getPiece(k2Loc.getX(),k2Loc.getY())).check();//if the king is in check 
                            if (check2!=null)
                                System.out.println("Player 2's king is in check by a piece at "+ check2.getLoc().getX() +","+check2.getLoc().getY()); 
                            if (hasMoved)
                                whoTurn = false;
                        }
                        else
                            System.out.println("The entry you entered was out of bound of the board. \nPlease make sure your entry is not less than 0 or greator than 8");
                    }
                    else
                        System.out.println("That isn't your piece to move! You are Player 1, all the Capital pieces are yours!");
                }
                else{
                    if (board.getPiece(Integer.parseInt(commands[1]),Integer.parseInt(commands[2])).getOwner().equals("P2")){
                            if ((Integer.parseInt(commands[1])>=0)&&(Integer.parseInt(commands[2])>=0)&&(Integer.parseInt(commands[4])>=0)&&((Integer.parseInt(commands[5])>=0))){
                               
                                hasMoved = board.getPiece(Integer.parseInt(commands[1]),Integer.parseInt(commands[2])).moveTo(new ChessLocation (Integer.parseInt(commands[4]), Integer.parseInt(commands[5])));
                            if (hasMoved && board.getPiece(Integer.parseInt(commands[4]),Integer.parseInt(commands[5])).getID().equals("k")){
                                k2Loc.setXY(Integer.parseInt(commands[4]),Integer.parseInt(commands[5]));
                            }
                            if (hasMoved && board.getPiece(Integer.parseInt(commands[4]),Integer.parseInt(commands[5])).getLoc().equals(k1Loc)){
                                System.out.println("Player 2 wins! He has captured Player 1's King!");
                                break;
                            }
                            check1 = ((King) board.getPiece(k1Loc.getX(),k1Loc.getY())).check();
                            if (check1!=null)
                                System.out.println("Player 1's king is in check by a piece at "+ check1.getLoc().getX() +","+check1.getLoc().getY());
                                if (hasMoved)
                                    whoTurn=true;
                            }
                            else
                        
                                System.out.println("The entry you entered was out of bound of the board. \nPlease make sure your entry is not less than 0 or greator than 8");
                        }
                        else
                            System.out.println("That isn't your piece to move! You are Player 2, all the lower case pieces are yours!");
                }
            }
              
              else{//if user types in random string for second parameter
                  System.out.println("Piece doesn't exist!");
              }            
          }
          else if (commands[0].equals("quit"))//when user quits display this message
              System.out.println("Thank You for Playing!");
          else if (commands[0].equals("restart")){
              whoTurn =true;
              game= new ChessGame("P1", "P2");
              board = game.getBoard();
              newLoc = new ChessLocation (0,0);//temporary location
              //keep track of king
              k1Loc= new ChessLocation (0,4);
              k2Loc = new ChessLocation (7,3);
          }
          else if (commands[0].equals("help")){
              System.out.println(help);
          }
          else
            System.out.println("Enter a valid command. Type 'help' to view commands.");//if there is no such command that is displayed
          if (whoTurn)
            System.out.println("Player 1's Move\n\n\n"); 
          else
            System.out.println("Player 2's Move\n\n\n");
          board.createBoard();//displays the board
        
      } while (!comLine.equals("quit")) ;
    }
    
    public static void getCommands(String command){//splits the terminal command and places it in an array of size 4
        commands= command.split(" "); 
        
    }
}
