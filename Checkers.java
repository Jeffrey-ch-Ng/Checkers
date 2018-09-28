// The "NewestCPT" class.
import java.awt.*;
import hsa.Console;

public class Checkers
{
    static Console c;           // The output console
    //Set Colors
    static Color goodBrown = new Color (138, 71, 20);
    static Color lightBrown = new Color (168, 101, 49);
    static Color cyan = new Color (107, 231, 255);
    static Color magenta = new Color (204, 0, 204);
    static Color orange = new Color (255, 128, 0);
    static Color player1Colour = Color.white;
    static Color player2Colour = Color.red;

    //Set Turns
    static int playerTurn = 1;
    static int turnNumber = 0;
    static int pieceRegular = 0, pieceKing = 0;

    //Set Board variables
    static int rowI = 0, rowF = 0, columnI = 0, columnF = 0, eatRow = 0, eatColumn = 0, eatRowSecond = 0, eatColumnSecond = 0;
    static int[] [] board = new int [8] [8];
    static int choice;
    static int eatenNum = 0;
    static int player1Win = 0, player2Win = 0;
    static int totalPlayer1Wins = 0, totalPlayer2Wins = 0;
    static int colourChoice1 = 0, colourChoice2 = 0;
    static String player1Name, player2Name;
    static int menuSelection = 0;


    //Set Font
    static Font a = new Font ("Arial", 2, 66);
    static Font Instructions = new Font ("Franklin Gothic", 3, 20);
    static Font Titles = new Font ("Tahoma", 4, 50);

    //Main Program
    public static void main (String[] args)
    {
	c = new Console (40, 180);
	//Menu
	c.drawRect (350, 300, 800, 75);
	c.setColour (Color.red);
	int x = 350;
	do
	{
	    c.fillRect (x, 300, 5, 75);
	    x = x + 1;
	    int generator = (int) (Math.random () * 10);
	    delay (generator);
	}
	while (x < 1150);
	delay (500);

	displayWelcome ();

	do
	{
	    //Game Menu Screen




	    menu ();


	    //Setup board
	    createBoard ();
	    drawBackground ();

	    //Game loop
	    while (true)
	    {

		drawBoard ();
		determineTurnNumber ();
		//check if game is won
		if (checkWin ())
		{
		    break;
		}
		promptMove ();
		updateBoard ();
		checkKings ();

	    }

	    if (!winScreen ())
	    {
		System.exit (1);
	    }
	}
	while (true);
    }


    public static void displayWelcome ()
    {
	drawGraphics ();
	c.setColor (Color.red);
	Font a = new Font ("Arial", 3, 50);
	c.setFont (a);
	c.drawString ("Welcome to Ultimate Checkers", 350, 350);
	delay (2500);

    }


    public static void drawGraphics ()
    {
	Color darkBlue = new Color (0, 0, 175);
	c.setColor (darkBlue);
	c.fillRect (0, 0, 1800, 800);
	c.setColor (Color.yellow);
	int x = 0;
	do
	{
	    int starx = (int) (Math.random () * 1750 + 1);
	    int stary = (int) (Math.random () * 750 + 1);
	    c.fillOval (starx, stary, 3, 3);
	    delay (2);
	    x = x + 1;
	}
	while (x <= 2000);
	delay (1000);
    }





    //create menu screen
    public static void menu ()
    {
	do
	{
	    c.clear ();
	    c.setColor (Color.black);
	    c.fillRect (0, 0, 1500, 800);
	    c.setColor (Color.yellow);
	    c.fillOval (10, 10, 200, 200);
	    Color mercury = new Color (64, 64, 64);
	    c.setColor (mercury);
	    delay (100);
	    c.fillOval (100, 250, 25, 25);
	    Color venus = new Color (255, 178, 102);
	    c.setColor (venus);
	    delay (100);
	    c.fillOval (110, 300, 35, 35);
	    c.setColor (Color.blue);
	    delay (100);
	    c.fillOval (150, 350, 40, 40);
	    c.setColor (Color.red);
	    delay (100);
	    c.fillOval (200, 400, 20, 20);
	    delay (100);
	    Color rocks = new Color (160, 160, 160);
	    c.setColor (rocks);
	    int rockx = 50;
	    int rocky = 540;
	    do
	    {
		c.fillOval (rockx, rocky, 10, 10);
		int generator = (int) (Math.random () * 20 + 10);
		rockx = rockx + generator;
		rocky = rocky - 5;
		delay (50);
	    }
	    while (rocky >= 440);
	    Color jupiter = new Color (204, 102, 0);
	    c.setColor (jupiter);
	    delay (200);
	    c.fillOval (400, 550, 80, 80);
	    Color saturn = new Color (255, 153, 51);
	    c.setColor (saturn);
	    delay (200);
	    c.fillOval (650, 600, 70, 70);
	    Color uranus = new Color (102, 178, 255);
	    c.setColor (uranus);
	    delay (200);
	    c.fillOval (900, 630, 60, 60);
	    Color neptune = new Color (0, 153, 153);
	    c.setColor (neptune);
	    delay (200);
	    c.fillOval (1200, 655, 50, 50);
	    c.setColor (Color.white);
	    delay (1000);
	    Font b = new Font ("Arial", 1, 30);
	    c.setFont (b);




	    //user make their menu selection
	    do
	    {
		c.drawString ("Enter 1 to play game.", 550, 180);
		c.drawString ("Enter 2 to see intructions.", 520, 240);
		c.drawString ("Enter 3 to see credits.", 550, 300);
		delay (500);
		c.drawString ("Enter your choice:", 550, 400);
		c.setCursor (20, 105);
		menuSelection = c.readInt ();
		if (menuSelection < 1 || menuSelection > 3)
		{
		    c.drawString ("Enter a valid choice.", 550, 350);
		}
	    }
	    while (menuSelection < 1 || menuSelection > 3);

	    c.clear ();

	    if (menuSelection == 1)
	    {
		chooseName ();
		chooseColour ();
		break;

	    }
	    else if (menuSelection == 2)
	    {
		instructions ();
	    }
	    else if (menuSelection == 3)
	    {
		displayCredits ();
	    }

	}
	while (menuSelection != 1);

    }


    //Players can enter their names
    public static void chooseName ()
    {
	c.clear ();
	c.setColour (Color.black);
	c.fillRect (0, 0, 2000, 2000);
	c.setColour (Color.white);
	c.setFont (Titles);
	c.drawString ("Player 1, what is your preferred name?", 50, 150);
	c.drawString ("Player 1 enter name: ", 50, 250);
	c.setCursor (12, 70);
	player1Name = c.readLine ();

	c.clear ();
	c.setColour (Color.black);
	c.fillRect (0, 0, 2000, 2000);
	c.setColour (Color.white);
	c.setFont (Titles);
	c.drawString ("Player 2, what is your preferred name?", 50, 150);
	c.drawString ("Player 2 enter name: ", 50, 250);
	c.setCursor (12, 70);
	player2Name = c.readLine ();
    }


    //Players can customize their colours
    public static void chooseColour ()
    {
	//Prompt for colour choice

	c.clear ();

	c.setColor (Color.black);
	c.fillRect (0, 0, 2000, 2000);
	c.setFont (Instructions);
	c.setColor (Color.white);

	c.drawString ("Pick Your Colour!", 600, 50);
	delay (1000);

	c.drawString ("Enter 1 for white!", 50, 200);
	c.fillOval (350, 120, 100, 100);
	delay (800);
	c.setColor (cyan);

	c.drawString ("Enter 2 for cyan!", 50, 350);
	c.fillOval (350, 270, 100, 100);
	delay (800);
	c.setColor (Color.red);

	c.drawString ("Enter 3 for red!", 50, 500);
	c.fillOval (350, 420, 100, 100);
	delay (800);
	c.setColor (Color.yellow);

	c.drawString ("Enter 4 for yellow!", 50, 650);
	c.fillOval (350, 570, 100, 100);
	delay (800);
	c.setColor (Color.green);

	c.drawString ("Enter 5 for green!", 550, 200);
	c.fillOval (850, 120, 100, 100);
	delay (800);
	c.setColor (Color.blue);

	c.drawString ("Enter 6 for blue!", 550, 350);
	c.fillOval (850, 270, 100, 100);
	delay (800);
	c.setColor (magenta);

	c.drawString ("Enter 7 for magenta!", 550, 500);
	c.fillOval (850, 420, 100, 100);
	delay (800);
	c.setColor (orange);

	c.drawString ("Enter 8 for orange!", 550, 650);
	c.fillOval (850, 570, 100, 100);
	c.setColor (Color.white);

	c.drawString (player1Name + " enter colour choice: ", 50, 750);


	do
	{
	    c.setCursor (38, 50);
	    colourChoice1 = c.readInt ();
	    if (colourChoice1 < 1 || colourChoice1 > 8)
	    {
		c.drawString ("Invalid Entry", 50, 775);
	    }

	}
	while (colourChoice1 < 1 || colourChoice1 > 8);

	c.clear ();

	switch (colourChoice1)
	{
	    case 1:
		player1Colour = Color.white;
		break;
	    case 2:
		player1Colour = cyan;
		break;
	    case 3:
		player1Colour = Color.red;
		break;
	    case 4:
		player1Colour = Color.yellow;
		break;
	    case 5:
		player1Colour = Color.green;
		break;
	    case 6:
		player1Colour = Color.blue;
		break;
	    case 7:
		player1Colour = magenta;
		break;
	    case 8:
		player1Colour = orange;
		break;
	}

	c.setColor (Color.black);
	c.fillRect (0, 0, 2000, 2000);
	c.setFont (Instructions);
	c.setColor (Color.white);
	c.drawString ("Pick Your Colour!", 600, 50);
	c.drawString ("Enter 1 for white!", 50, 200);
	c.fillOval (350, 120, 100, 100);
	c.setColor (cyan);
	c.drawString ("Enter 2 for cyan!", 50, 350);
	c.fillOval (350, 270, 100, 100);
	c.setColor (Color.red);
	c.drawString ("Enter 3 for red!", 50, 500);
	c.fillOval (350, 420, 100, 100);
	c.setColor (Color.yellow);
	c.drawString ("Enter 4 for yellow!", 50, 650);
	c.fillOval (350, 570, 100, 100);
	c.setColor (Color.green);
	c.drawString ("Enter 5 for green!", 550, 200);
	c.fillOval (850, 120, 100, 100);
	c.setColor (Color.blue);
	c.drawString ("Enter 6 for blue!", 550, 350);
	c.fillOval (850, 270, 100, 100);
	c.setColor (magenta);
	c.drawString ("Enter 7 for magenta!", 550, 500);
	c.fillOval (850, 420, 100, 100);
	c.setColor (orange);
	c.drawString ("Enter 8 for orange!", 550, 650);
	c.fillOval (850, 570, 100, 100);
	c.setColor (Color.white);
	c.drawString (player2Name + " enter colour choice: ", 50, 750);


	do
	{
	    c.setCursor (38, 50);
	    colourChoice2 = c.readInt ();
	    if (colourChoice2 < 1 || colourChoice2 > 8 || colourChoice2 == colourChoice1)
	    {
		c.drawString ("Invalid Entry", 50, 775);
	    }

	}
	while (colourChoice2 < 1 || colourChoice2 > 8 || colourChoice2 == colourChoice1);

	switch (colourChoice2)
	{
	    case 1:
		player2Colour = Color.white;
		c.clear ();
		break;
	    case 2:
		player2Colour = cyan;
		c.clear ();
		break;
	    case 3:
		player2Colour = Color.red;
		c.clear ();
		break;
	    case 4:
		player2Colour = Color.yellow;
		c.clear ();
		break;
	    case 5:
		player2Colour = Color.green;
		c.clear ();
		break;
	    case 6:
		player2Colour = Color.blue;
		c.clear ();
		break;
	    case 7:
		player2Colour = magenta;
		c.clear ();
		break;
	    case 8:
		player2Colour = orange;
		c.clear ();
		break;
	}
	c.clear ();
    }



    //Credits screen
    public static void displayCredits ()
    {
	int creditSelection = 0;

	c.clear ();
	c.setColor (Color.black);
	Font g = new Font ("Jokerman", 1, 30);
	c.setFont (g);
	c.drawString ("Made by Alex Liu, Jeffrey Ng, and Andrew Zhao.", 210, 250);
	Font h = new Font ("Edwardian Script ITC", 1, 30);
	c.setFont (h);
	c.drawString ("Sponsored by Mr. Ganuelas.", 430, 350);
	c.drawString ("Enter 1 to return to menu", 430, 450);
	c.setCursor (23, 100);


	do
	{

	    creditSelection = c.readInt ();
	    if (creditSelection != 1)
	    {
		c.setCursor (23, 100);
		c.drawString ("Invalid Entry", 430, 500);
	    }
	}
	while (creditSelection != 1);



    }


    public static void instructions ()
    {
	c.clear ();
	c.setColor (Color.black);
	Font f = new Font ("Arial", 2, 30);
	c.setFont (f);
	c.drawString ("Welcome to Ultimate Checkers, the best online two-player checkers game ever!", 10, 30);
	delay (100);
	c.drawString ("To play this game, you need to have an opponent (obviously, since this is a two-player game)", 10, 60);
	c.drawString ("Player2 side goes first, and white goes second.", 10, 90);
	c.drawString ("After the starting moves, the two sides will take alternate turns until one side wins.", 10, 120);
	c.drawString ("Pieces can only move to adjacent diagonal squares in front of it.", 10, 150);
	c.drawString ("If no squares are available, then the piece cannot be moved that turn.", 10, 180);
	c.drawString ("A king piece is achieved when your piece reaches your opponent's board edge.", 10, 210);
	c.drawString ("A king piece can also move backwards, but also only on adjacent diagonal squares.", 10, 240);
	c.drawString ("To kill a piece, you must leap over the enemy piece into an empty space one diagonal square behind it.", 10, 270);
	c.drawString ("If no such space exists, the piece cannot be killed.", 10, 300);
	c.drawString ("To do multiple jumps, input the final destination.", 10, 330);
	int x = 0;
	c.drawString ("Go to next page for an animated demonstration when you finish reading.", 10, 360);
	do
	{
	    c.drawString ("Enter 1 when you have finished reading: ", 10, 400);
	    c.setCursor (20, 70);
	    x = c.readInt ();
	    if (x == 1)
	    {
		c.clear ();
		createBoard ();
		drawBoard ();
		movePiece ();
		break;
	    }
	    else
	    {
		c.drawString ("Invalid input. Please enter again.", 10, 420);
	    }
	}
	while (true);

	c.clear ();
    }


    //aniamted demonstration
    public static void movePiece ()
    {
	Font f = new Font ("Arial", 3, 12);
	c.setFont (f);
	c.setColor (Color.black);
	c.drawString ("Player1 goes first.", 750, 120);
	delay (1000);
	c.fillOval (182, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (107, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player2 now moves for the second turn.", 750, 140);
	delay (1000);
	c.fillOval (107, 482, 60, 60);
	c.setColor (Color.red);
	c.fillOval (182, 407, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player1 moves up.", 750, 160);
	delay (1000);
	c.fillOval (107, 182, 60, 60);
	c.setColor (Color.white);
	c.fillOval (182, 257, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player2 mirrors player 1.", 750, 180);
	delay (1000);
	c.fillOval (182, 557, 60, 60);
	c.setColor (Color.red);
	c.fillOval (107, 482, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player1 moves to block.", 750, 200);
	delay (1000);
	c.fillOval (182, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (257, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player2 takes the edge.", 750, 220);
	delay (1000);
	c.fillOval (557, 482, 60, 60);
	c.setColor (Color.red);
	c.fillOval (632, 407, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player1 advances.", 750, 240);
	delay (1000);
	c.fillOval (332, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (407, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Bad move for player 2.", 750, 260);
	delay (1000);
	c.fillOval (407, 482, 60, 60);
	c.setColor (Color.red);
	c.fillOval (482, 407, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Taking a risk?", 750, 280);
	delay (1000);
	c.fillOval (632, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (557, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("TAKEDOWN!", 750, 300);
	delay (1000);
	c.fillOval (482, 407, 60, 60);
	c.fillOval (407, 332, 60, 60);
	c.setColor (Color.red);
	c.fillOval (332, 257, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Counter-TAKEDOWN!", 750, 320);
	delay (1000);
	c.fillOval (257, 182, 60, 60);
	c.fillOval (332, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (407, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Massacre!", 750, 340);
	delay (1000);
	c.fillOval (182, 407, 60, 60);
	c.fillOval (257, 332, 60, 60);
	c.setColor (Color.red);
	c.fillOval (332, 257, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Counter-MASSACRE!", 750, 360);
	delay (1000);
	c.fillOval (407, 182, 60, 60);
	c.fillOval (332, 257, 60, 60);
	c.setColor (Color.white);
	c.fillOval (257, 332, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Blood drenches the battlefield.", 750, 380);
	delay (1000);
	c.fillOval (332, 557, 60, 60);
	c.setColor (Color.red);
	c.fillOval (407, 482, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Renewed war!.", 750, 400);
	delay (1000);
	c.fillOval (407, 332, 60, 60);
	c.setColor (Color.white);
	c.fillOval (482, 407, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Is player 2 sacrificing its soldiers to the gods?", 750, 420);
	delay (1000);
	c.fillOval (632, 557, 60, 60);
	c.setColor (Color.red);
	c.fillOval (557, 482, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("Player1 slashes down!", 750, 440);
	delay (1000);
	c.fillOval (482, 407, 60, 60);
	c.fillOval (407, 482, 60, 60);
	c.setColor (Color.white);
	c.fillOval (332, 557, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("WHAAAAAAAAAAAAT!", 750, 460);
	delay (1000);
	c.fillOval (257, 632, 60, 60);
	c.setColor (Color.red);
	c.fillOval (182, 557, 60, 60);
	delay (1000);
	c.setColor (Color.black);
	c.drawString ("PLAYER 1 IS CROWNED!", 750, 480);
	delay (1000);
	c.fillOval (332, 557, 60, 60);
	c.setColor (Color.white);
	c.fillOval (257, 632, 60, 60);
	c.setColor (Color.black);
	c.fillStar (262, 637, 50, 50);
	delay (1000);
	c.drawString ("*The game goes on until one side loses all pieces or cannot move anymore.", 750, 500);
	delay (1000);
	int x = 0;
	c.drawString ("Enter 1 to go back to menu: ", 750, 520);
	c.setCursor (28, 100);

	do
	{
	    x = c.readInt ();
	    if (x != 1)
	    {
		c.drawString ("Invalid input. Please re-enter.", 750, 560);
	    }
	}
	while (x != 1);

    }



    public static void createBoard ()
    {
	//Create white pieces
	for (int i = 0 ; i < 3 ; i++)
	{
	    if (i % 2 == 0)
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{
		    if (j % 2 == 1)
		    {
			board [i] [j] = 1;
		    }
		}
	    }
	    else
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{
		    if (j % 2 == 0)
		    {
			board [i] [j] = 1;
		    }
		}
	    }
	}


	//Create red pieces
	for (int i = 5 ; i < 8 ; i++)
	{
	    if (i % 2 == 0)
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{
		    if (j % 2 == 1)
		    {
			board [i] [j] = 2;
		    }
		}
	    }
	    else
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{
		    if (j % 2 == 0)
		    {
			board [i] [j] = 2;
		    }
		}
	    }
	}



    }


    public static void drawBackground ()
    {
	//Draw Background
	c.setColor (Color.black);
	c.fillRect (0, 0, 2000, 2000);
	c.setColor (Color.white);
	c.fillRect (27, 27, 751, 751);
	c.setColor (goodBrown);
	c.fillRect (30, 30, 745, 745);
	c.setColor (Color.white);
	c.fillRect (98, 98, 604, 604);

	//Draw row numbers
	c.setFont (a);

	for (int i = 1 ; i <= 8 ; i++)
	{
	    c.drawString ("" + i, (75 * i) + 40, 85);
	}


	for (int i = 1 ; i <= 8 ; i++)
	{
	    c.drawString ("" + i, 50, (75 * i) + 80);
	}
    }


    public static void drawBoard ()
    {

	//Draw squares
	for (int i = 0 ; i < board.length ; i++)
	{
	    if (i % 2 == 0)
	    {

		for (int j = 0 ; j < board [i].length ; j++)
		{


		    if (j % 2 == 0)
		    {
			c.setColor (lightBrown);
		    }
		    else
		    {
			c.setColor (Color.black);
		    }
		    c.fillRect (100 + (i * 75), 100 + (j * 75), 75, 75);
		}

	    }
	    else
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{
		    if (j % 2 == 0)
		    {
			c.setColor (Color.black);

		    }
		    else
		    {
			c.setColor (lightBrown);
		    }
		    c.fillRect (100 + (i * 75), 100 + (j * 75), 75, 75);
		}
	    }
	}


	//Draw pieces
	for (int i = 0 ; i < board.length ; i++)
	{
	    for (int j = 0 ; j < board [i].length ; j++)
	    {
		if (board [i] [j] == 1)
		{
		    c.setColor (player1Colour);
		    c.fillOval (107 + (j * 75), 107 + (i * 75), 60, 60);
		}
		else if (board [i] [j] == 2)
		{
		    c.setColor (player2Colour);
		    c.fillOval (107 + (j * 75), 107 + (i * 75), 60, 60);
		}
		else if (board [i] [j] == 3)
		{
		    c.setColor (player1Colour);
		    c.fillOval (107 + (j * 75), 107 + (i * 75), 60, 60);
		    c.setColor (Color.black);
		    c.fillStar (112 + (j * 75), 112 + (i * 75), 50, 50);
		}
		else if (board [i] [j] == 4)
		{
		    c.setColor (player2Colour);
		    c.fillOval (107 + (j * 75), 107 + (i * 75), 60, 60);
		    c.setColor (Color.black);
		    c.fillStar (112 + (j * 75), 112 + (i * 75), 50, 50);
		}

	    }
	}
    }



    //Move the pieces
    public static void updateBoard ()
    {

	//Check if piece is eaten
	if (eatenNum == 1)
	{
	    board [eatRow] [eatColumn] = 0;
	}
	else if (eatenNum == 2)
	{
	    board [eatRow] [eatColumn] = 0;
	    board [eatRowSecond] [eatColumnSecond] = 0;
	}

	eatenNum = 0;

	//Move the pieces
	board [rowI] [columnI] = 0;
	board [rowF] [columnF] = choice;


    }


    //Check the turn number and print
    public static void determineTurnNumber ()
    {
	c.setFont (Titles);

	if (turnNumber % 2 == 0)
	{
	    playerTurn = 1;
	    pieceRegular = 2;
	    pieceKing = 4;
	}


	else
	{
	    playerTurn = 2;
	    pieceRegular = 1;
	    pieceKing = 3;
	}


	turnNumber += 1;

    }


    public static void displayPlayerTurn ()
    {
	c.setFont (Titles);
	if (playerTurn == 1)
	{

	    c.setColor (player1Colour);
	    c.drawString (player1Name + "'s turn", 850, 200);
	}
	else
	{
	    c.setColor (player2Colour);
	    c.drawString (player2Name + "'s turn", 850, 200);
	}
    }




    //Prompt user for move
    public static void promptMove ()
    {
	do
	{
	    do
	    {
		displayPlayerTurn ();

		c.drawString ("Enter the initial row:", 850, 350);
		c.setCursor (20, 110);
		rowI = (c.readInt () - 1);

		if (rowI > 7 || rowI < 0)
		{
		    c.clear ();
		    drawBackground ();
		    drawBoard ();
		    displayPlayerTurn ();
		    c.drawString ("Invalid entry", 850, 275);
		}

	    }
	    while (rowI > 7 || rowI < 0);

	    drawBackground ();
	    drawBoard ();

	    do
	    {
		displayPlayerTurn ();

		c.drawString ("Enter the initial column:", 850, 350);
		c.setCursor (20, 110);
		columnI = (c.readInt () - 1);

		if (columnI > 7 || columnI < 0)
		{
		    c.clear ();
		    drawBackground ();
		    drawBoard ();
		    displayPlayerTurn ();
		    c.drawString ("Invalid entry", 850, 275);
		}

	    }
	    while (columnI > 7 || columnI < 0);

	    drawBackground ();
	    drawBoard ();

	    do
	    {
		displayPlayerTurn ();

		c.drawString ("Enter the final row: ", 850, 350);
		c.setCursor (20, 110);
		rowF = (c.readInt () - 1);


		if (rowF > 7 || rowF < 0)
		{
		    c.clear ();
		    drawBackground ();
		    drawBoard ();
		    displayPlayerTurn ();
		    c.drawString ("Invalid entry", 850, 275);
		}

	    }
	    while (rowF > 7 || rowF < 0);

	    drawBackground ();
	    drawBoard ();

	    do
	    {
		displayPlayerTurn ();

		c.drawString ("Enter the final column: ", 850, 350);
		c.setCursor (20, 110);
		columnF = (c.readInt () - 1);

		if (columnF > 7 || columnF < 0)
		{
		    c.clear ();
		    drawBackground ();
		    drawBoard ();
		    displayPlayerTurn ();
		    c.drawString ("Invalid entry", 850, 275);
		}

	    }
	    while (columnF > 7 || columnF < 0);

	    drawBackground ();
	    drawBoard ();


	    if (determineType ())
	    {
		break;
	    }

	    displayPlayerTurn ();
	    c.drawString ("Invalid Move", 850, 275);
	}


	while (true);
    }


    //Determine character type
    public static boolean determineType ()
    {
	choice = board [rowI] [columnI];

	//Check piece is valid for white pieces
	if (playerTurn == 1)
	{
	    if (choice == 1 || choice == 3)
	    {
		switch (choice)
		{
		    case 1:
			if (checkPlayer1Normal ())
			{
			    return true;
			}
			else
			{
			    return false;
			}
		    case 3:
			if (checkPlayerKing ())
			{
			    return true;
			}
			else
			{
			    return false;
			}
		}
	    }

	    else
	    {
		return false;
	    }
	}

	//check piece is valid for red pieces
	else
	{
	    if (choice == 2 || choice == 4)
	    {
		switch (choice)
		{
		    case 2:
			if (checkPlayer2Normal ())
			{
			    return true;
			}
			else
			{
			    return false;
			}
		    case 4:
			if (checkPlayerKing ())
			{
			    return true;
			}
			else
			{
			    return false;
			}
		}

	    }
	    else
	    {
		return false;
	    }
	}


	return false;

    }


    //Move down single
    public static boolean moveDownSingle ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI + 1)
	    {
		if (columnF == columnI + 1 || columnF == columnI - 1)
		{
		    return true;
		}
		else
		{
		    return false;
		}
	    }
	}
	return false;
    }


    //Move up single
    public static boolean moveUpSingle ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI - 1)
	    {
		if (columnF == columnI + 1 || columnF == columnI - 1)
		{
		    return true;
		}
		else
		{
		    return false;
		}
	    }
	}
	return false;
    }


    public static boolean moveDownRightEat ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI + 2)
	    {
		if (columnF == columnI + 2)
		{
		    if (board [rowI + 1] [columnI + 1] == pieceRegular || board [rowI + 1] [columnI + 1] == pieceKing)
		    {
			eatRow = rowI + 1;
			eatColumn = columnI + 1;
			eatenNum = 1;
			return true;
		    }
		}
	    }
	}
	return false;
    }


    public static boolean moveDownLeftEat ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI + 2)
	    {
		if (columnF == columnI - 2)
		{
		    if (board [rowI + 1] [columnI - 1] == pieceRegular || board [rowI + 1] [columnI - 1] == pieceKing)
		    {
			eatRow = rowI + 1;
			eatColumn = columnI - 1;
			eatenNum = 1;
			return true;
		    }
		}
	    }
	}
	return false;
    }


    public static boolean moveUpLeftEat ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI - 2)
	    {
		if (columnF == columnI - 2)
		{
		    if (board [rowI - 1] [columnI - 1] == pieceRegular || board [rowI - 1] [columnI - 1] == pieceKing)
		    {
			eatRow = rowI - 1;
			eatColumn = columnI - 1;
			eatenNum = 1;
			return true;
		    }
		}
	    }
	}
	return false;
    }


    public static boolean moveUpRightEat ()
    {
	if (board [rowF] [columnF] == 0)
	{
	    if (rowF == rowI - 2)
	    {
		if (columnF == columnI + 2)
		{
		    if (board [rowI - 1] [columnI + 1] == pieceRegular || board [rowI - 1] [columnI + 1] == pieceKing)
		    {
			eatRow = rowI - 1;
			eatColumn = columnI + 1;
			eatenNum = 1;
			return true;
		    }
		}
	    }
	}
	return false;
    }


    //Double eat move down and right
    public static boolean moveDownRightDoubleEat ()
    {
	if (rowI + 2 <= 7 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI + 4)
		{
		    if (columnF == columnI + 4)
		    {
			if ((board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 3] [columnI + 3] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 3] [columnI + 3] == pieceKing) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 3] [columnI + 3] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 3] [columnI + 3] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI + 3;
			    eatColumnSecond = columnI + 3;
			    eatenNum = 2;
			    return true;
			}
		    }

		}
	    }
	}
	return false;
    }


    //Double eat move down and straight
    public static boolean moveDownStraightDoubleEat ()
    {
	if (rowI + 2 <= 7 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI + 4)
		{
		    if (columnF == columnI)
		    {

			if ((board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 3] [columnI + 1] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 3] [columnI + 1] == pieceKing) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 3] [columnI + 1] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 3] [columnI + 1] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI + 3;
			    eatColumnSecond = columnI + 1;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	if (rowI + 2 <= 7 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI - 2] == 0)
	    {
		if (rowF == rowI + 4)
		{
		    if (columnF == columnI)
		    {

			if ((board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 3] [columnI - 1] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 3] [columnI - 1] == pieceKing) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 3] [columnI - 1] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 3] [columnI - 1] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI + 3;
			    eatColumnSecond = columnI - 1;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	return false;
    }


    //Double eat move left and straight
    public static boolean moveLeftStraightDoubleEat ()
    {
	if (rowI + 2 <= 7 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI - 2] == 0)
	    {
		if (rowF == rowI)
		{
		    if (columnF == columnI - 4)
		    {

			if ((board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 1] [columnI - 3] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 1] [columnI - 3] == pieceKing) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 1] [columnI - 3] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 1] [columnI - 3] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI + 1;
			    eatColumnSecond = columnI - 3;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	if (rowI - 2 >= 0 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI - 2] == 0)
	    {
		if (rowF == rowI)
		{
		    if (columnF == columnI - 4)
		    {

			if ((board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 1] [columnI - 3] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 1] [columnI - 3] == pieceKing) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 1] [columnI - 3] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 1] [columnI - 3] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI - 1;
			    eatColumnSecond = columnI - 3;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	return false;
    }


    //Double eat move right and straight
    public static boolean moveRightStraightDoubleEat ()
    {
	if (rowI + 2 <= 7 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI)
		{
		    if (columnF == columnI + 4)
		    {

			if ((board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 1] [columnI + 3] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceRegular && board [rowI + 1] [columnI + 3] == pieceKing) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 1] [columnI + 3] == pieceRegular) || (board [rowI + 1] [columnI + 1] == pieceKing && board [rowI + 1] [columnI + 3] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI + 1;
			    eatColumnSecond = columnI + 3;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	if (rowI - 2 >= 0 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI)
		{
		    if (columnF == columnI + 4)
		    {

			if ((board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 1] [columnI + 3] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 1] [columnI + 3] == pieceKing) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 1] [columnI + 3] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 1] [columnI + 3] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI - 1;
			    eatColumnSecond = columnI + 3;
			    eatenNum = 2;
			    return true;

			}
		    }
		}
	    }
	}
	return false;
    }




    // Double eat move down and left
    public static boolean moveDownLeftDoubleEat ()
    {
	if (rowI + 2 <= 7 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI + 2] [columnI - 2] == 0)
	    {
		if (rowF == rowI + 4)
		{
		    if (columnF == columnI - 4)
		    {
			if ((board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 3] [columnI - 3] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceRegular && board [rowI + 3] [columnI - 3] == pieceKing) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 3] [columnI - 3] == pieceRegular) || (board [rowI + 1] [columnI - 1] == pieceKing && board [rowI + 3] [columnI - 3] == pieceKing))
			{
			    eatRow = rowI + 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI + 3;
			    eatColumnSecond = columnI - 3;
			    eatenNum = 2;
			    return true;
			}
		    }
		}
	    }
	}


	return false;
    }


    // Double eat move up and Left
    public static boolean moveUpLeftDoubleEat ()
    {
	if (rowI - 2 >= 0 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI - 2] == 0)
	    {
		if (rowF == rowI - 4)
		{
		    if (columnF == columnI - 4)
		    {
			if ((board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 3] [columnI - 3] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 3] [columnI - 3] == pieceKing) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 3] [columnI - 3] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 3] [columnI - 3] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI - 3;
			    eatColumnSecond = columnI - 3;
			    eatenNum = 2;
			    return true;
			}
		    }
		}
	    }
	}


	return false;
    }


    // Double eat move up and right
    public static boolean moveUpRightDoubleEat ()
    {
	if (rowI - 2 >= 0 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI - 4)
		{
		    if (columnF == columnI + 4)
		    {
			if ((board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 3] [columnI + 3] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 3] [columnI + 3] == pieceKing) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 3] [columnI + 3] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 3] [columnI + 3] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI - 3;
			    eatColumnSecond = columnI + 3;
			    eatenNum = 2;
			    return true;
			}
		    }
		}
	    }
	}


	return false;
    }


    // Double eat moving up and straight
    public static boolean moveUpStraightDoubleEat ()
    {
	if (rowI - 2 >= 0 && columnI + 2 <= 7)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI + 2] == 0)
	    {
		if (rowF == rowI - 4)
		{
		    if (columnF == columnI)
		    {

			if ((board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 3] [columnI + 1] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceRegular && board [rowI - 3] [columnI + 1] == pieceKing) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 3] [columnI + 1] == pieceRegular) || (board [rowI - 1] [columnI + 1] == pieceKing && board [rowI - 3] [columnI + 1] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI + 1;
			    eatRowSecond = rowI - 3;
			    eatColumnSecond = columnI + 1;
			    eatenNum = 2;
			    return true;
			}

		    }
		}
	    }

	}


	if (rowI - 2 >= 0 && columnI - 2 >= 0)
	{
	    if (board [rowF] [columnF] == 0 && board [rowI - 2] [columnI - 2] == 0)
	    {

		if (rowF == rowI - 4)
		{
		    if (columnF == columnI)
		    {

			if ((board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 3] [columnI - 1] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceRegular && board [rowI - 3] [columnI - 1] == pieceKing) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 3] [columnI - 1] == pieceRegular) || (board [rowI - 1] [columnI - 1] == pieceKing && board [rowI - 3] [columnI - 1] == pieceKing))
			{
			    eatRow = rowI - 1;
			    eatColumn = columnI - 1;
			    eatRowSecond = rowI - 3;
			    eatColumnSecond = columnI - 1;
			    eatenNum = 2;
			    return true;
			}

		    }
		}
	    }
	}


	return false;
    }



    //Check the white pieces normal
    public static boolean checkPlayer1Normal ()
    {
	if (moveDownRightDoubleEat () || moveDownLeftDoubleEat () || moveDownStraightDoubleEat () || moveDownSingle () || moveDownRightEat () || moveDownLeftEat ())
	{
	    return true;
	}


	return false;

    }


    //Check the red pieces normal
    public static boolean checkPlayer2Normal ()
    {
	if (moveUpRightDoubleEat () || moveUpLeftDoubleEat () || moveUpStraightDoubleEat () || moveUpSingle () || moveUpRightEat () || moveUpLeftEat ())
	{
	    return true;
	}


	return false;
    }





    //Check the kings
    public static boolean checkPlayerKing ()
    {
	if (moveRightStraightDoubleEat () || moveLeftStraightDoubleEat () || moveDownRightDoubleEat () || moveDownLeftDoubleEat () || moveDownStraightDoubleEat () || moveUpRightDoubleEat () || moveUpLeftDoubleEat () || moveUpStraightDoubleEat () || moveDownSingle () || moveUpSingle () || moveUpRightEat () || moveUpLeftEat () || moveDownRightEat () || moveDownLeftEat ())
	{
	    return true;
	}


	return false;
    }



    //Check if piece has reached other side to become king
    public static void checkKings ()
    {
	for (int i = 0 ; i < board.length ; i++)
	{
	    if (board [0] [i] == 2)
	    {
		board [0] [i] = 4;
	    }
	}


	for (int i = 0 ; i < board.length ; i++)
	{
	    if (board [7] [i] == 1)
	    {
		board [7] [i] = 3;
	    }
	}
    }


    //Check if the game is won
    public static boolean checkWin ()
    {

	int player1Checker = 0, player2Checker = 0, player1NotWon = 0, player2NotWon = 0, savedPieceRegular = pieceRegular, savedPieceKing = pieceKing;

	//Check for white and red pieces
	for (int i = 0 ; i < board.length ; i++)
	{
	    for (int j = 0 ; j < board [i].length ; j++)
	    {

		if (board [i] [j] == 1 || board [i] [j] == 3)
		{
		    player1Checker += 1;
		}

		if (board [i] [j] == 2 || board [i] [j] == 4)
		{
		    player2Checker += 1;
		}
	    }
	}

	if (player1Checker == 0)
	{
	    player2Win = 1;
	    return true;
	}
	else if (player2Checker == 0)
	{
	    player1Win = 1;
	    return true;
	}


	//Check if they have any moves left
	if (playerTurn == 1 && player1Checker > 0 && player1NotWon == 0)
	{

	    for (int i = 0 ; i < board.length ; i++)
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{

		    rowI = i;
		    columnI = j;

		    //Check moves for red pieces regular
		    if (board [i] [j] == 1)
		    {

			if (i + 1 <= 7 && j - 1 >= 0)
			{
			    if (board [i + 1] [j - 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}
			if (i + 1 <= 7 && j + 1 <= 7 && player1NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}

			if (i + 2 <= 7 && j - 2 >= 0 && player1NotWon == 0)
			{

			    if (board [i + 1] [j - 1] == 2 || board [i + 1] [j - 1] == 4)
			    {
				rowF = i + 2;
				columnF = j - 2;

				if (moveDownLeftEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i + 2 <= 7 && j + 2 <= 7 && player1NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 2 || board [i + 1] [j + 1] == 4)
			    {

				rowF = i + 2;
				columnF = j + 2;
				if (moveDownRightEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
		    }


		    //Check moves for red kings
		    if (board [i] [j] == 3 && player1NotWon == 0)
		    {
			if (i + 1 <= 7 && j - 1 >= 0 && player1NotWon == 0)
			{
			    if (board [i + 1] [j - 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}
			if (i + 1 <= 7 && j + 1 <= 7 && player1NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}
			if (i - 1 >= 0 && j - 1 >= 0 && player1NotWon == 0)
			{
			    if (board [i - 1] [j - 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}
			if (i - 1 >= 0 && j + 1 <= 7 && player1NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 0)
			    {
				player1NotWon = 1;
			    }
			}
			if (i + 2 <= 7 && j - 2 >= 0 && player1NotWon == 0)
			{
			    if (board [i + 1] [j - 1] == 2 || board [i + 1] [j - 1] == 4)
			    {
				rowF = i + 2;
				columnF = j - 2;
				if (moveDownLeftEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i + 2 <= 7 && j + 2 <= 7 && player1NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 2 || board [i + 1] [j + 1] == 4)
			    {

				rowF = i + 2;
				columnF = j + 2;
				if (moveDownRightEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i - 2 >= 0 && j - 2 >= 0 && player1NotWon == 0)
			{
			    if (board [i - 1] [j - 1] == 2 || board [i - 1] [j - 1] == 4)
			    {

				rowF = i - 2;
				columnF = j - 2;
				if (moveUpLeftEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i - 2 >= 0 && j + 2 <= 7 && player1NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 2 || board [i - 1] [j + 1] == 4)
			    {

				rowF = i - 2;
				columnF = j + 2;
				if (moveUpRightEat ())
				{
				    player1NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
		    }
		}
	    }

	    if (player1Checker == 0 || player1NotWon == 0)
	    {
		player2Win = 1;
		pieceRegular = savedPieceRegular;
		pieceKing = savedPieceKing;
		return true;

	    }
	}



	if (playerTurn == 2 && player2Checker > 0 && player2NotWon == 0)
	{
	    for (int i = 0 ; i < board.length ; i++)
	    {
		for (int j = 0 ; j < board [i].length ; j++)
		{

		    rowI = i;
		    columnI = j;

		    if (board [i] [j] == 2)
		    {
			if (i - 1 <= 7 && j - 1 >= 0)
			{
			    if (board [i - 1] [j - 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i - 1 >= 0 && j + 1 <= 7 && player2NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i - 2 >= 0 && j - 2 >= 0 && player2NotWon == 0)
			{
			    if (board [i - 1] [j - 1] == 1 || board [i - 1] [j - 1] == 3)
			    {
				rowF = i - 2;
				columnF = j - 2;
				if (moveUpLeftEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i - 2 >= 0 && j + 2 <= 7 && player2NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 1 || board [i - 1] [j + 1] == 3)
			    {

				rowF = i - 2;
				columnF = j + 2;
				if (moveUpRightEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
		    }

		    if (board [i] [j] == 4)
		    {

			if (i + 1 <= 7 && j - 1 >= 0 && player2NotWon == 0)
			{
			    if (board [i + 1] [j - 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i + 1 <= 7 && j + 1 <= 7 && player2NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i - 1 >= 0 && j - 1 >= 0 && player2NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i - 1 >= 0 && j + 1 <= 7 && player2NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 0)
			    {
				player2NotWon = 1;
			    }
			}
			if (i - 2 >= 0 && j - 2 >= 0 && player2NotWon == 0)
			{
			    if (board [i - 1] [j - 1] == 1 || board [i - 1] [j - 1] == 3)
			    {
				rowF = i - 2;
				columnF = j - 2;
				if (moveUpLeftEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i - 2 >= 0 && j + 2 <= 7 && player2NotWon == 0)
			{
			    if (board [i - 1] [j + 1] == 1 || board [i - 1] [j + 1] == 3)
			    {

				rowF = i - 2;
				columnF = j + 2;
				if (moveUpRightEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i + 2 <= 7 && j - 2 >= 0 && player2NotWon == 0)
			{
			    if (board [i + 1] [j - 1] == 1 || board [i + 1] [j - 1] == 3)
			    {

				rowF = i + 2;
				columnF = j - 2;
				if (moveDownLeftEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
			if (i + 2 <= 7 && j + 2 <= 7 && player2NotWon == 0)
			{
			    if (board [i + 1] [j + 1] == 1 || board [i + 1] [j + 1] == 3)
			    {

				rowF = i + 2;
				columnF = j + 2;
				if (moveDownRightEat ())
				{
				    player2NotWon = 1;
				    eatenNum = 0;
				}
			    }
			}
		    }
		}
	    }

	    if (player2Checker == 0 || player2NotWon == 0)
	    {
		player1Win = 1;
		pieceRegular = savedPieceRegular;
		pieceKing = savedPieceKing;
		return true;

	    }
	}

	return false;
    }


    //Create win screen
    public static boolean winScreen ()
    {
	c.setFont (Instructions);

	int selection = 0;

	//Print congragulations
	if (player1Win == 1)
	{
	    totalPlayer1Wins += 1;
	    player1Win = 0;
	    c.clear ();
	    drawBackground ();
	    drawBoard ();
	    c.setColor (player1Colour);
	    c.setFont (Instructions);
	    c.drawString ("Congragulations " + player1Name + "!!!", 850, 275);
	    c.drawString ("You Just Won!!!", 850, 325);
	}


	else
	{
	    totalPlayer2Wins += 1;
	    player2Win = 0;
	    c.clear ();
	    drawBackground ();
	    drawBoard ();
	    c.setColor (player2Colour);
	    c.setFont (Instructions);
	    c.drawString ("Congragulations " + player2Name + "!!!", 850, 275);
	    c.drawString ("You Just Won!!!", 850, 325);
	}


	do
	{
	    c.drawString ("Would you like to play again?", 850, 400);
	    c.drawString ("1 - Yes", 850, 450);
	    c.drawString ("2 - No", 850, 500);
	    c.setCursor (30, 110);
	    selection = c.readInt ();

	    if (selection > 2 || selection < 1)
	    {
		c.drawString ("Invalid Entry", 850, 550);
	    }
	    else
	    {
		break;
	    }

	}
	while (true);


	//Return selection
	if (selection == 1)
	{
	    c.clear ();
	    return true;
	}


	return false;
    }


    public static void delay (int x)
    {
	try
	{
	    Thread.sleep (x);

	}


	catch (InterruptedException ie)
	{

	}
    }


    // Place your program here.  'c' is the output console
    // main method
} // NewestCPT class
