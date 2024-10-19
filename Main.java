import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("This is a Tic Tac Toe game. You know the rules. Win by having 3 of the same symbols in a row/column/diagonal.");
        String startFresh = "yes";
        while(startFresh.equalsIgnoreCase("yes")){

            // Getting user data
            System.out.println("Please enter the size of your board: 3 for 3-by-3, 4 for 4-by-4, or 5 for 5-by-5");
            int boardSize = scan.nextInt();// Creating board size
            while(boardSize != 3 && boardSize != 4 && boardSize != 5){
                System.out.println("Please enter one of the given number: 3, 4, 5");
                boardSize = scan.nextInt();
            }
            scan.nextLine();
            TicTacToe gameOne = new TicTacToe(new String[boardSize][boardSize]);

            System.out.println("Enter a mode: Single Player, One VS One");//Creating the mode
            String mode = scan.nextLine();
            while(!mode.equalsIgnoreCase("One vs One") && !mode.equalsIgnoreCase("Single Player")){
                System.out.println("Please enter one of the given mode: Single Player, One vs One");
                mode = scan.nextLine();
            }
            if(mode.equalsIgnoreCase("One vs One")){// Setting the up 1v1 mode
                System.out.print("Please enter a name for player one: ");//Ask for p1 name
                String p1 = scan.nextLine();
                System.out.println("Are you sure?");
                String yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a name for player one: ");
                    p1 = scan.nextLine();
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }
                System.out.print("Please enter a symbol for player one: ");//Ask for p1 symbol
                String p1Symbol = scan.nextLine();
                while(p1Symbol.length() != 1){// check for 1 character symbol.
                    System.out.println("Please enter a symbol of one character.");
                    p1Symbol = scan.nextLine(); 
                }
                System.out.println("Are you sure?");
                yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a symbol for player one: ");
                    p1Symbol = scan.nextLine();
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }

                System.out.print("Please enter a name for player two: ");//Ask for p2 name
                String p2 = scan.nextLine();
                System.out.println("Are you sure?");
                yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a name for player two: ");
                    p2 = scan.nextLine();
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }
                System.out.print("Please enter a symbol for player Two: ");//Ask for p2 symbol
                String p2Symbol = scan.nextLine();
                while(p2Symbol.length() != 1 || p2Symbol.equals(p1Symbol)){// check for 1 character symbol.
                    System.out.println("Please enter a symbol of one character or a different symbol.");
                    p2Symbol = scan.nextLine(); 
                }
                System.out.println("Are you sure?");
                yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a symbol for player Two: ");
                    p2Symbol = scan.nextLine();
                    while(p2Symbol.length() != 1 || p2Symbol.equals(p1Symbol)){// check for 1 character symbol.
                        System.out.println("Please enter a symbol of one character or a different symbol.");
                        p2Symbol = scan.nextLine(); 
                    }
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }
                gameOne.setPlayerNames(p1, p2);
                gameOne.setPlayerOneSymbol(p1Symbol);
                gameOne.setPlayerTwoSymbol(p2Symbol);
            }
            else{//setting up single player mode
                System.out.print("Please enter a name for player one: ");
                String p1 = scan.nextLine();
                System.out.println("Are you sure?");
                String yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a name for player one: ");
                    p1 = scan.nextLine();
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }
                System.out.print("Please enter a symbol: ");//Ask for p1 symbol
                String p1Symbol = scan.nextLine();
                while(p1Symbol.length() != 1){// check for 1 character symbol.
                    System.out.println("Please enter a symbol of one character.");
                    p1Symbol = scan.nextLine(); 
                }
                System.out.println("Are you sure?");
                yesOrNo = scan.nextLine();
                while(!yesOrNo.equalsIgnoreCase("yes")){
                    System.out.print("Please enter a symbol: ");
                    p1Symbol = scan.nextLine();
                    System.out.println("Are you sure?");
                    yesOrNo = scan.nextLine();
                }
                gameOne.setPlayerNames(p1);
                gameOne.setPlayerOneSymbol(p1Symbol);
                gameOne.setPlayerTwoSymbol("!");
            }
            //End of getting user data

            System.out.println("Are you ready?");// Check to see if the player is ready.
            String continuePlaying = scan.nextLine();
            while(!continuePlaying.equalsIgnoreCase("yes")){
                System.out.println("Type yes when you are ready.");
                continuePlaying = scan.nextLine();
            }

            //1V1 mode
            while(continuePlaying.equalsIgnoreCase("yes") && mode.equalsIgnoreCase("One vs One")){
                if(gameOne.checkIfBoardIsEmpty()){//print the empty board once
                    System.out.println(gameOne);//print the board
                }

                //get positions from p1
                System.out.print("Player one please enter the row you want to place your " + gameOne.getPlayerOneSymbol() + ": "); 
                int row = scan.nextInt();
                System.out.print("Player one please enter the column you want to place your " + gameOne.getPlayerOneSymbol() + ": ");
                int column = scan.nextInt();
                while(!gameOne.checkIfMoveIsValid(row, column)){//check if move is valid
                    System.out.print("Please enter a valid row: ");
                    row = scan.nextInt();
                    System.out.print("Please enter a valid column: ");
                    column = scan.nextInt();
                }
                gameOne.setMove(row, column, gameOne.getPlayerOneSymbol());//set the move
                System.out.println(gameOne);//print the board
                if(gameOne.checkForWinner()){//check for victory
                    gameOne.addGamesWonByP1();
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }
                if(gameOne.checkIfBoardIsFull()){//check for tie
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }

                //get position from p2
                System.out.print("Player two please enter the row you want to place your " + gameOne.getPlayerTwoSymbol() + ": ");
                row = scan.nextInt();
                System.out.print("Player one please enter the column you want to place your " + gameOne.getPlayerTwoSymbol() + ": ");
                column = scan.nextInt();
                while(!gameOne.checkIfMoveIsValid(row, column)){//check if move is valid
                    System.out.print("Please enter a valid row: ");
                    row = scan.nextInt();
                    System.out.print("Please enter a valid column: ");
                    column = scan.nextInt();
                }
                gameOne.setMove(row, column, gameOne.getPlayerTwoSymbol());//set the move
                System.out.println(gameOne);//print the board
                if(gameOne.checkForWinner()){//check for victory
                    gameOne.addGamesWonByP2();
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }
            }
            //end of code for 1v1 mode

            //Single player mode
            while(continuePlaying.equalsIgnoreCase("yes") && mode.equalsIgnoreCase("Single Player")){
                if(gameOne.checkIfBoardIsEmpty()){//print the empty board once
                    System.out.println(gameOne);//print the board
                }
                //get position from player
                System.out.print("Player one please enter the row you want to place your " + gameOne.getPlayerOneSymbol() + ": "); 
                int row = scan.nextInt();
                System.out.print("Player one please enter the column you want to place your " + gameOne.getPlayerOneSymbol() + ": ");
                int column = scan.nextInt();
                while(!gameOne.checkIfMoveIsValid(row, column)){//check if move is valid
                    System.out.print("Please enter a valid row: ");
                    row = scan.nextInt();
                    System.out.print("Please enter a valid column: ");
                    column = scan.nextInt();
                }
                gameOne.setMove(row, column, gameOne.getPlayerOneSymbol());//set the move
                System.out.println(gameOne);//print the board
                if(gameOne.checkForWinner()){//check for victory
                    gameOne.addGamesWonByP1();
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }
                if(gameOne.checkIfBoardIsFull()){//check for tie
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }

                //AI turn
                if(gameOne.aiLookFor3AndPlace()){
                    ;
                }
                else if(gameOne.aiLookFor3AndBlockPLayer()){
                    ;
                }
                else{
                    gameOne.aiFillin();
                }
                System.out.println(gameOne);//print the board
                if(gameOne.checkForWinner()){//check for victory
                    gameOne.addGamesWonByP2();
                    gameOne.addgamePlayed();
                    gameOne.printStats();
                    gameOne.resetGame();
                    System.out.println("Would you like to play again?");
                    scan.nextLine();
                    continuePlaying = scan.nextLine();
                    continue;
                }
            }

            //ask player if they want to try the other mode.
            System.out.println("Would you like to switch mode or start fresh?");
            startFresh = scan.nextLine();
            if(startFresh.equalsIgnoreCase("yes")){
                continue;
            }
        }
    }
}
