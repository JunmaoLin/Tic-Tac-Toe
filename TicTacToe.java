public class TicTacToe {
    private static int totalTTTBoardsCreated = 0;
    private int boardNumber; 
    private String[][] TTT;
    private int gamesPlayed;
    private String playerOne;
    private String p1Symbol;
    private int gamesWonByPlayerOne = 0;
    private String playerTwo;
    private String p2Symbol;
    private int gamesWonByPlayerTwo = 0;
    private String winner;

    // Constructor
    public TicTacToe(String[][] Tic){
        TTT = Tic;
        resetGame();
        totalTTTBoardsCreated++;
        boardNumber = totalTTTBoardsCreated;
    }

    //add gamesWonByP1
    public void addGamesWonByP1(){
        gamesWonByPlayerOne++;
    }

    //add gamesWonByP2
    public void addGamesWonByP2(){
        gamesWonByPlayerTwo++;
    }

    //add games played
    public void addgamePlayed(){
        gamesPlayed++;
    }

    // set player move, player will provide row/column > 0.
    public void setMove(int r, int c, String letter){
        TTT[r-1][c-1] = letter;
    }

    // set player names
    public void setPlayerNames(String p1, String p2){
        playerOne = p1;
        playerTwo = p2;
    }

    public void setPlayerNames(String p1){
        this.setPlayerNames(p1, "AI");
    }

    // get player names
    public String getPlayerOneName(){
        return playerOne;
    }

    public String getPlayerTwoName(){
        return playerTwo;
    }

    // set symbols for players
    public void setPlayerOneSymbol(String s){
        p1Symbol = s;
    }

    public void setPlayerTwoSymbol(String s){
        p2Symbol = s;
    }

    // get player symbols
    public String getPlayerOneSymbol(){
        return p1Symbol;
    }

    public String getPlayerTwoSymbol(){
        return p2Symbol;
    }

    // set winner
    public void setWinner(String win){
        winner = win;
    }

    // get winner 
    public String getWinner(){
        return winner;
    }

    // AI fill in a random spot on the board
    public void aiFillin(){
        int randomPosRow = (int)(Math.random()*(TTT.length));
        int randomPosCol = (int)(Math.random()*(TTT.length));
        while(!TTT[randomPosRow][randomPosCol].equals(" ")){
            randomPosRow = (int)(Math.random()*(TTT.length));
            randomPosCol = (int)(Math.random()*(TTT.length));
        }
        TTT[randomPosRow][randomPosCol] = "!";
    }

    //AI fill in for a win
    public boolean aiLookFor3AndPlace(){
        for(int r = 1; r<TTT.length-1; r++){// Check vertical
            for(int c = 0; c < TTT[r].length; c++){
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r+1][c]) && TTT[r-1][c].equals(" ")){//_
                    TTT[r-1][c] = "!";                                                                //!
                    return true;                                                                      //!
                }
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r-1][c]) && TTT[r+1][c].equals(" ")){//!
                    TTT[r+1][c] = "!";                                                                //!
                    return true;                                                                      //_
                }
                if(TTT[r-1][c].equals("!") && TTT[r-1][c].equals(TTT[r+1][c]) && TTT[r][c].equals(" ")){//!
                    TTT[r][c] = "!";                                                                    //_
                    return true;                                                                        //!
                }
            }
        }

        for(int r = 0; r<TTT.length; r++){// Check horizontal 
            for(int c = 1; c < TTT[r].length-1; c++){
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r][c+1]) && TTT[r][c-1].equals(" ")){//_ ! !
                    TTT[r][c-1] = "!";
                    return true;
                }
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r][c-1]) && TTT[r][c+1].equals(" ")){//! ! _
                    TTT[r][c+1] = "!";
                    return true;
                }
                if(TTT[r][c-1].equals("!") && TTT[r][c-1].equals(TTT[r][c+1]) && TTT[r][c].equals(" ")){//! _ !
                    TTT[r][c] = "!";
                    return true;
                }
            }
        }

        for(int r = 1; r<TTT.length-1; r++){// Check for diagonals
            for(int c = 1; c < TTT[r].length-1; c++){
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r-1][c-1]) && TTT[r+1][c+1].equals(" ")){// ! _ _
                    TTT[r+1][c+1] = "!";                                                                  // _ ! _
                    return true;                                                                          // _ _(_)
                }
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r+1][c+1]) && TTT[r-1][c-1].equals(" ")){//(_)_ _
                    TTT[r-1][c-1] = "!";                                                                  // _ ! _
                    return true;                                                                          // _ _ !
                }
                if(TTT[r+1][c+1].equals("!") && TTT[r+1][c+1].equals(TTT[r-1][c-1]) && TTT[r][c].equals(" ")){// ! _ _
                    TTT[r][c] = "!";                                                                          // _(_)_
                    return true;                                                                              // _ _ !
                }

                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r-1][c+1]) && TTT[r+1][c-1].equals(" ")){// _ _ !
                    TTT[r+1][c-1] = "!";                                                                  // _ ! _
                    return true;                                                                          //(_)_ _
                }
                if(TTT[r][c].equals("!") && TTT[r][c].equals(TTT[r+1][c-1]) && TTT[r-1][c+1].equals(" ")){// _ _(_)
                    TTT[r-1][c+1] = "!";                                                                  // _ ! _
                    return true;                                                                          // ! _ _
                }
                if(TTT[r+1][c-1].equals("!") && TTT[r+1][c-1].equals(TTT[r-1][c+1]) && TTT[r][c].equals(" ")){// _ _ !
                    TTT[r][c] = "!";                                                                          // _(_)_
                    return true;                                                                              // ! _ _
                }
            }
        }
        return false;

    }

    //AI block player move
    public boolean aiLookFor3AndBlockPLayer(){
        for(int r = 1; r<TTT.length-1; r++){// Check vertical
            for(int c = 0; c < TTT[r].length; c++){
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r+1][c]) && TTT[r-1][c].equals(" ")){//!
                    TTT[r-1][c] = "!";                                                                     //X
                    return true;                                                                           //X
                }
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r-1][c]) && TTT[r+1][c].equals(" ")){//X
                    TTT[r+1][c] = "!";                                                                     //X
                    return true;                                                                           //!
                }
                if(TTT[r-1][c].equals(p1Symbol) && TTT[r-1][c].equals(TTT[r+1][c]) && TTT[r][c].equals(" ")){//X
                    TTT[r][c] = "!";                                                                         //!
                    return true;                                                                             //X
                }
            }
        }

        for(int r = 0; r<TTT.length; r++){// Check horizontal 
            for(int c = 1; c < TTT[r].length-1; c++){
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r][c+1]) && TTT[r][c-1].equals(" ")){//! X X
                    TTT[r][c-1] = "!";
                    return true;
                }
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r][c-1]) && TTT[r][c+1].equals(" ")){//X X !
                    TTT[r][c+1] = "!";
                    return true;
                }
                if(TTT[r][c-1].equals(p1Symbol) && TTT[r][c-1].equals(TTT[r][c+1]) && TTT[r][c].equals(" ")){//X ! X
                    TTT[r][c] = "!";
                    return true;
                }
            }
        }

        for(int r = 1; r<TTT.length-1; r++){// Check for diagonals
            for(int c = 1; c < TTT[r].length-1; c++){
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r-1][c-1]) && TTT[r+1][c+1].equals(" ")){// X _ _
                    TTT[r+1][c+1] = "!";                                                                       // _ X _
                    return true;                                                                               // _ _(!)
                }
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r+1][c+1]) && TTT[r-1][c-1].equals(" ")){//(!)_ _
                    TTT[r-1][c-1] = "!";                                                                       // _ X _
                    return true;                                                                               // _ _ X
                }
                if(TTT[r+1][c+1].equals(p1Symbol) && TTT[r+1][c+1].equals(TTT[r-1][c-1]) && TTT[r][c].equals(" ")){// X _ _
                    TTT[r][c] = "!";                                                                               // _(!)_
                    return true;                                                                                   // _ _ X
                }

                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r-1][c+1]) && TTT[r+1][c-1].equals(" ")){// _ _ X
                    TTT[r+1][c-1] = "!";                                                                       // _ X _
                    return true;                                                                               //(!)_ _
                }
                if(TTT[r][c].equals(p1Symbol) && TTT[r][c].equals(TTT[r+1][c-1]) && TTT[r-1][c+1].equals(" ")){// _ _(!)
                    TTT[r-1][c+1] = "!";                                                                       // _ X _
                    return true;                                                                               // X _ _
                }
                if(TTT[r+1][c-1].equals(p1Symbol) && TTT[r+1][c-1].equals(TTT[r-1][c+1]) && TTT[r][c].equals(" ")){// _ _ X
                    TTT[r][c] = "!";                                                                               // _(!)_
                    return true;                                                                                   // X _ _
                }
            }
        }
        return false;
    }



    //Check if move is valid
    public boolean checkIfMoveIsValid(int r, int c){
        if(r > TTT.length || r < 1 || c > TTT.length || c < 1){
            return false;
        }
        if(TTT[r-1][c-1].equals(" ")){
            return true;
        }
        return false;
    }

    // check if the board is full
    public boolean checkIfBoardIsFull(){
        for(String[] r : TTT){
            for(String c : r){
                if(c.equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    //check if the board is empty
    public boolean checkIfBoardIsEmpty(){
        boolean empty = true;
        for(String[] r: TTT){
            for(String c : r){
                if(!c.equals(" ")){
                    empty = false;
                }
            }
        }
        return empty;
    }

    // Check if for victory
    public boolean checkForWinner(){
        for(int r = 1; r<TTT.length-1; r++){// Check vertical
            for(int c = 0; c < TTT[r].length; c++){
                if((!TTT[r][c].equals(" ")) && TTT[r][c].equals(TTT[r+1][c]) && TTT[r][c].equals(TTT[r-1][c])){
                winner = TTT[r][c];
                return true;
                }
            }
        }
        for(int r = 0; r<TTT.length; r++){// Check horizontal
            for(int c = 1; c < TTT[r].length-1; c++){
                if((!TTT[r][c].equals(" ")) && TTT[r][c].equals(TTT[r][c+1]) && TTT[r][c].equals(TTT[r][c-1])){
                    winner = TTT[r][c];
                    return true;
                }
            }
        }
        for(int r = 1; r<TTT.length-1; r++){// Check for diagonals
            for(int c = 1; c < TTT[r].length-1; c++){
                if((!TTT[r][c].equals(" ")) && ((TTT[r][c].equals(TTT[r-1][c-1]) && TTT[r][c].equals(TTT[r+1][c+1])) || (TTT[r][c].equals(TTT[r-1][c+1]) && TTT[r][c].equals(TTT[r+1][c-1])))){
                    winner = TTT[r][c];
                    return true;
                }
            }
        }
        return false;    
    }

    // reset the game
    public void resetGame(){
        for(int r = 0; r < TTT.length; r++){
            for(int c = 0; c < TTT[r].length; c++){
                TTT[r][c] = " ";
            }
        }
        winner = "";
    }

    public void printStats(){
        System.out.println("Board Number: " + boardNumber);
        System.out.println("Games played: " + gamesPlayed);
        System.out.println("Games tied: " + (gamesPlayed-gamesWonByPlayerOne-gamesWonByPlayerTwo));
        System.out.println("Player One: " + playerOne);
        System.out.println("Symbol used: " + p1Symbol);
        System.out.println("Games Won By Player One: " + gamesWonByPlayerOne);
        System.out.println("Player Two: " + playerTwo);
        System.out.println("Symbol used: " + p2Symbol);
        System.out.println("Games Won By Player two: " + gamesWonByPlayerTwo);
    }


    public String toString(){ // returns the TTT board of the object
        String board = "";
        board += "+";
        for(int r = 0; r < TTT.length; r++){
            board += "---+";
        }
        board += ("\n");
        for(int row = 0; row < TTT.length; row++){
            board += "|";
            for(int c = 0; c < TTT[row].length; c++){
                board += (" " + TTT[row][c] + " |");
            }
            board += ("\n+");
            for(int r = 0; r < TTT.length; r++){
                board += ("---+");
            }
            board += ("\n");
        }
        return board;
    }

    // +---+---+---+
    // | x | o | x |
    // +---+---+---+
    // | x | ! | x |
    // +---+---+---+
    // | x | o | x |
    // +---+---+---+
    
}
