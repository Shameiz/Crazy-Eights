//ICS Summative
//Shameiz Rangwala
// The "CrazyEights" class.
import java.awt.*;
import hsa.Console;
import javax.imageio.*; // allows image loading
import java.io.*; // allows file access

public class CrazyEights
{
    static Console c;           // The output console
    static int deck[], player1[], player2[], table[], tab = 0; // make global to facilitate easier parameter passing
    static Image imageDeck[], cardBack;
    static String playerName;

    public static Image[] loadCardImages ()  // loads card gif files into array
    {
	Image pictureDeck[] = new Image [52]; // create array of 52 card images

	for (int x = 0 ; x < 52 ; x++)
	    pictureDeck [x] = loadImage ("cards2\\" + (x + 1) + ".gif"); // loads appropriate image from cards2 folder

	cardBack = loadImage ("cards2\\b.gif");
	return pictureDeck;
    }


    public static Image loadImage (String name)  //loads image from file
    {
	Image img = null;
	try
	{
	    img = ImageIO.read (new File (name)); // load file into Image object
	}
	catch (IOException e)
	{
	    System.out.println ("File not found");
	}

	return img;
    }


    public static void delay ()  //provides delay with the nested loop
    {
	for (long x = 0 ; x <= 10999 ; x++)
	{
	    for (long i = 0 ; i <= 10999 ; i++)
	    {
	    }
	}
    }


    public static int[] initDeck ()  // initialize standard deck
    {
	int deck[] = new int [52];
	for (int x = 0 ; x < deck.length ; x++)
	    deck [x] = x; // cards represented by numbers 0-51
	return deck;
    }


    public static int[] resize (int slots, int shorter[])  //makes array shortetr
    {
	int temp[] = new int [slots]; //declare temporary array
	for (int x = 0 ; x < temp.length ; x++)
	{
	    temp [x] = shorter [x + 1]; //temp stores values of shorter
	}
	return temp;

    }



    public static void show (int hand[], int xpos, int ypos, boolean faceup)  //shows the array of cards
    {
	for (int x = 0 ; x < hand.length ; x++)
	{
	    if (faceup)
		c.drawImage (imageDeck [hand [x]], 20 * x + xpos, ypos, null);
	    else
		c.drawImage (cardBack, 20 * x + xpos, ypos, null);
	}
    }


    public static void showCard (int card, int xpos, int ypos, boolean faceup)  //shows a single card
    {

	if (faceup)
	    c.drawImage (imageDeck [card], xpos, ypos, null);
	else
	    c.drawImage (cardBack, xpos, ypos, null);

    }


    public static void numberShow (int hand[], int xpos, int ypos)  //shows numbers under the cards
    {
	for (int x = 1 ; x <= hand.length ; x++)
	{
	    c.drawString ("" + x, 22 * (x - 1) + xpos, ypos);

	}
    }


    public static int[] shuffle (int deck[])  //this shuffles the deck
    {
	int temp, rand1, rand2;
	for (int x = 1 ; x <= 1000 ; x++)
	{
	    rand1 = (int) (Math.random () * (deck.length) + 0); //GENERATE RANDOM NUMBER
	    rand2 = (int) (Math.random () * (deck.length) + 0); //GENERATE RANDOM NUMBER
	    //swap numbers in the random positions
	    temp = deck [rand1];
	    deck [rand1] = deck [rand2];
	    deck [rand2] = temp;
	}
	return deck;


    }


    public static int[] deal (int numCards)  //deals cards to players
    {
	int hand[] = new int [numCards];
	for (int x = 0 ; x < numCards ; x++)
	{
	    hand [x] = deck [x]; // or hand[x] = deal(deck,0);
	    // remove cards from deck if not dealt
	    deck = remove (deck, x);
	}
	return hand;
    }


    public static int[] add (int deck[], int card)  //adds a card at the beginning of the deck
    {
	int x = 0;
	int temp[] = new int [deck.length + 1]; //declare string
	temp [x] = card; //add card to beginning
	for (x = 1 ; x <= deck.length ; x++)
	{
	    temp [x] = deck [x - 1]; //store contents of deck in temp
	}
	return temp;
    }


    public static int[] remove (int deck[], int position)  //removes a card from a position in the deck
    {
	int temp[] = new int [deck.length - 1]; //delcare array
	int x = 0;
	while (x != position)
	{
	    temp [x] = deck [x]; //store contents of deck in temp
	    x++;
	}
	while (x < temp.length)
	{
	    temp [x] = deck [x + 1]; //skip the position specified
	    x++;
	}
	return temp;

    }


    public static int[] insert (int deck[], String card, int position)  //insets a card at a particular position
    {
	int temp[] = new int [deck.length + 1];
	int x = 0;
	while (x != position)
	{
	    temp [x] = deck [x]; //store contents of deck in temp
	    x++;
	}
	temp [x] = name2number (card);
	x++;
	while (x < temp.length)
	{

	    temp [x] = deck [x - 1]; //skip the position specified
	    x++;

	}
	return temp;

    }


    public static int[] sort (int deck[])  //sorts the deck
    {
	int temp;
	for (int x = 0 ; x < deck.length - 1 ; x++) // sort first length-1 values
	{
	    int lowPos = x; // assume first value is lowest
	    for (int y = x + 1 ; y < deck.length ; y++) // check rest of list
	    {
		if (deck [y] < deck [lowPos]) // if you find a lower value
		    lowPos = y; // make it the lowest
	    }

	    temp = deck [x]; // swap low value with value in its proper position
	    deck [x] = deck [lowPos];
	    deck [lowPos] = temp;
	}

	return deck;

    }



    public static int name2number (String card)  //converts the card to an integer
    {
	int rank, suit;
	char rankChar = card.charAt (0); // first character
	char suitChar = card.charAt (1); // second character

	if (rankChar >= '2' && rankChar <= '9')
	    rank = (int) (rankChar - 48 - 2); // convert char digit to integer, subtract 2 to get rank
	else if (rankChar == 't' || rankChar == 'T')
	    rank = 8;
	else if (rankChar == 'j' || rankChar == 'J')
	    rank = 9;
	else if (rankChar == 'q' || rankChar == 'Q')
	    rank = 10;
	else if (rankChar == 'k' || rankChar == 'K')
	    rank = 11;
	else // default to ace
	    rank = 12;

	if (suitChar == 's' || suitChar == 'S')
	    suit = 0;
	else if (suitChar == 'h' || suitChar == 'H')
	    suit = 1;
	else if (suitChar == 'c' || suitChar == 'C')
	    suit = 2;
	else // default to diamonds
	    suit = 3;

	return suit * 13 + rank; // combine to get 0-51 value


    }


    public static String number2name (int cardNumber)  //converts the card number to card name
    {
	int suit, rank;
	char suitChar, rankChar;
	String name;
	suit = cardNumber / 13;
	rank = cardNumber % 13;

	if (suit == 0)
	    suitChar = 's';
	else if (suit == 1)
	    suitChar = 'h';
	else if (suit == 2)
	    suitChar = 'c';
	else // default to diamonds
	    suitChar = 'd';

	if (rank >= 0 && rank <= 7)
	{
	    rank += 2;
	    rankChar = (char) (rank + 48);

	}
	else if (rank == 8)
	    rankChar = 't';
	else if (rank == 9)
	    rankChar = 'j';
	else if (rank == 10)
	    rankChar = 'q';
	else if (rank == 11)
	    rankChar = 'k';
	else // default to ace
	    rankChar = 'a';

	name = " " + rankChar + suitChar;
	return name;


    }


    public static int cardRequirements (int deck[])  //Checks if deck has card with requirements of card on table
    {

	int i = 0;
	for (int x = 0 ; x < deck.length ; x++)
	{
	    String namePlayer = number2name (deck [x]);
	    String nameTable = number2name (table [0]);
	    if ((namePlayer.charAt (1) == nameTable.charAt (1)) || (namePlayer.charAt (1) == '8') || (nameTable.charAt (1) == '8' && table.length == 1) || (namePlayer.charAt (2) == nameTable.charAt (2)))
		i++;


	}
	return i;


    }


    public static int cardAllowed (int player[], int number)  //return the card that is allowed to be played
    {

	int i = -1, stop = -1;
	for (int x = 0 ; x < player.length && i != number ; x++)
	{
	    String namePlayer = number2name (player [x]);
	    String nameTable = number2name (table [0]);
	    if ((namePlayer.charAt (1) == nameTable.charAt (1)) || (namePlayer.charAt (1) == '8') || (nameTable.charAt (1) == '8' && table.length == 1) || (namePlayer.charAt (2) == nameTable.charAt (2)))
		i++;

	    if (i == number)
		stop = x;


	}
	return stop;

    }


    public static void playerTurn ()  //players turn in the game
    {

	int card = 0, counterPlayer, allowed[] = new int [8], i = 0;
	char num = ' ';

	//text box
	c.setColor (Color.yellow);
	c.fillRect (220, 367, 330, 65);
	c.setColor (Color.black);
	c.drawString ("Press number for the corresponding card", 250, 400); //text inside text box


	counterPlayer = cardRequirements (player1); //call cardRequirements()

	if (counterPlayer >= 1)
	{

	    for (int x = 0 ; x < counterPlayer ; x++)
	    {
		allowed [x] = cardAllowed (player1, x); //store all the allowed values

	    }

	    //conditions for accpeting card input
	    while (card < 1 || card > player1.length)
	    {
		num = c.getChar ();
		card = (int) num - 48; //convert char to int
		//makes sure only certain cards can be selected
		while (i == 0)
		{
		    for (int x = 0 ; x < allowed.length && x != card ; x++)
		    {
			if (allowed [x] == card - 1)
			    i++;
		    }

		    if (i == 0)
		    {
			num = c.getChar ();
			card = (int) num - 48; //convert char to num
		    }
		}

	    }

	    //adds card to table and removes from player's deck
	    table = add (table, player1 [card - 1]);
	    tab++;
	    player1 = remove (player1, (card - 1)); //resize player1 deck
	    gameSetUp (); //call set up
	    delay ();
	}

	//draw card
	else
	{
	    //msg box
	    c.setColor (Color.yellow);
	    c.fillRect (220, 367, 330, 65);
	    c.setColor (Color.black);
	    c.drawString ("Press any key to draw card", 300, 400); //msg
	    c.getChar ();
	    player1 = add (player1, deck [deck.length - 1]); //add to player deck
	    deck = remove (deck, (deck.length - 1)); //remove from main deck
	    gameSetUp (); //call set up
	    delay (); //apply delay
	}

    }


    public static void computerTurn ()
    {
	int counterComputer, allowed[] = new int [8];
	//computer's turn
	//Check if deck has card with requirements of card on table
	counterComputer = cardRequirements (player2); //call cardRequirements()

	if (counterComputer >= 1)
	{
	    int allowedComputer = cardAllowed (player2, 0); //card that is allowed to be played
	    table = add (table, player2 [allowedComputer]);
	    tab++;
	    player2 = remove (player2, allowedComputer); //resize player2 deck
	    gameSetUp (); //call set up

	    //text box
	    c.setColor (Color.yellow);
	    c.fillRect (220, 367, 330, 65);
	    c.setColor (Color.black);
	    c.drawString ("Computer plays card", 300, 400); //display text
	    delay ();



	}
	//draw card
	else
	{

	    player2 = add (player2, deck [deck.length - 1]); //add card to computer deck
	    deck = remove (deck, (deck.length - 1)); //remove card from main deck
	    gameSetUp (); //call set up

	    //text box
	    c.setColor (Color.yellow);
	    c.fillRect (220, 367, 330, 65);
	    c.setColor (Color.black);
	    c.drawString ("Computer draws card", 300, 400); //msg
	    delay (); //apply delay
	}
    }


    public static void menu ()
    {
	c.clear ();
	//BACKGROUND COLOUR
	c.setColor (Color.red); // can use pre-defined constants
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	//FONT HEADING AND COLOUR
	c.setColor (Color.blue); // can use pre-defined constants
	Font heading = new Font ("Times New Roman", Font.BOLD, 55); // font name, style, point size
	c.setFont (heading);

	//FONT ON SCREEN
	c.drawString ("CRAZY EIGHTS", 185, 60); // String, x, y

	//Options font set
	c.setColor (Color.yellow); // can use pre-defined constants
	Font point = new Font ("Arial", Font.BOLD, 45); // font name, style, point size
	c.setFont (point);

	//menu options
	c.drawString ("1. GAME RULES", 10, 135);
	c.drawString ("2. HOW TO PLAY?", 10, 235);
	c.drawString ("3. PLAY GAME", 10, 335);
	c.drawString ("4. QUIT", 10, 435);

	//display cards
	int y = 23, x = 7;
	while (y < 400)
	{
	    Image picture = loadImage ("cards2\\" + (x) + ".gif"); // loads appropriate image from cards2 folder
	    c.drawImage (picture, 650, y, null);
	    x = x + 13;
	    y += 125;
	}


    }





    public static void playerName ()
    {
	//BACKGROUND COLOUR
	c.setColor (new Color (255, 51, 153)); // pink
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	//FONT HEADING AND COLOUR
	c.setColor (Color.white); // can use pre-defined constants
	Font nameF = new Font ("CALIBIRI", Font.BOLD, 25); // font name, style, point size
	c.setFont (nameF);

	c.drawString ("ENTER PLAYER NAME AND PRESS ENTER : ", 140, 280); // String, x, y
	c.drawString ("(Name won't appear till you press enter) ", 140, 310); // String, x, y
	c.setCursor (1000, 1000);
	playerName = c.readLine ();
	c.clear ();

	//BACKGROUND COLOUR
	c.setColor (new Color (255, 51, 153)); // pink
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	c.setColor (Color.white);
	c.drawString ("ENTER PLAYER NAME AND PRESS ENTER : ", 140, 280); // String, x, y
	c.drawString (playerName.toUpperCase (), 340, 320); //display player name
	c.drawString ("PRESS ANY KEY TO CONTINUE...", 350, 550);

	c.getChar ();
    }


    public static void rules ()
    {
	c.clear ();
	//BACKGROUND COLOUR
	c.setColor (Color.blue); // can use pre-defined constants
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	//FONT HEADING AND COLOUR
	c.setColor (Color.yellow); // can use pre-defined constants
	Font heading = new Font ("Times New Roman", Font.BOLD, 55); // font name, style, point size
	c.setFont (heading);

	//FONT ON SCREEN
	c.drawString ("GAME RULES", 185, 60); // String, x, y

	//Rules font set
	c.setColor (Color.green); // can use pre-defined constants
	Font point = new Font ("Arial", Font.ITALIC, 25); // font name, style, point size
	c.setFont (point);

	//rules
	c.drawString ("1. The purpose of the  game is to get rid of all the cards.", 10, 100);
	c.drawString ("2. You can play any card of the same suit as the one at the top of the", 10, 150);
	c.drawString ("pile.", 40, 200);
	c.drawString ("3. You can play any card of the same rank as the one at the top of the", 10, 250);
	c.drawString ("pile.", 40, 300);
	c.drawString ("4. If you have no card to play, you can draw a card from the deck.", 10, 350);
	c.drawString ("5. You can always play an eight and the suit is changed to whatever", 10, 400);
	c.drawString ("the suit of the eight is.", 40, 450);
	c.drawString ("6. If the first card on the table is eight when you start, then you", 10, 500);
	c.drawString ("are allowed to put down any card from your hand.", 40, 550);

	c.getChar ();

    }


    public static void howToPlay ()
    {
	c.clear ();
	//BACKGROUND COLOUR
	c.setColor (new Color (255, 51, 153)); // pink
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	//FONT HEADING AND COLOUR
	c.setColor (Color.blue); // can use pre-defined constants
	Font heading = new Font ("Times New Roman", Font.BOLD, 55); // font name, style, point size
	c.setFont (heading);

	//FONT ON SCREEN
	c.drawString ("HOW TO PLAY", 185, 60); // String, x, y

	//Rules font set
	c.setColor (Color.white); // can use pre-defined constants
	Font point = new Font ("Arial", Font.ITALIC, 25); // font name, style, point size
	c.setFont (point);

	//rules
	c.drawString ("First, refer to the Game rules.......", 10, 100);
	c.drawString ("1. Each card in your deck has a number under it.", 10, 150);
	c.drawString ("2. You can play any card as long as it's allowed to be played.", 10, 200);
	c.drawString ("3. This can be done by pressing the number that corresponds", 10, 250);
	c.drawString ("with the card that you want to play.", 40, 300);
	c.drawString ("4. When no cards in the hand can be played, the program will", 10, 350);
	c.drawString ("notify you and you have to press any key to draw a card.", 40, 400);
	c.drawString ("5. Once, either you or the computer has played all the cards in its ", 10, 450);
	c.drawString ("hand, a message declaring the winner will be displayed.", 40, 500);
	c.drawString ("6. After this, press any key to go back to the main menu.", 10, 550);


	c.getChar ();



    }


    public static void gameInitialize ()
    {
	c.clear ();
	char name[] = new char [20], letter = ' ';
	int x;

	playerName ();
	c.clear ();

	//BACKGROUND COLOUR
	c.setColor (Color.green); // can use pre-defined constants
	c.fillRect (0, 0, 850, 1100); // x, y, width, height


	//FONT HEADING AND COLOUR
	c.setColor (Color.black); // can use pre-defined constants
	Font heading = new Font ("CALIBIRI", Font.ITALIC, 55); // font name, style, point size
	c.setFont (heading);

	//FONT ON SCREEN
	c.drawString ("GAME LOADING.......", 140, 280); // String, x, y

	delay ();


	deck = initDeck ();
	deck = shuffle (deck);

	// deal 8 cards to each player
	player1 = deal (8);
	player2 = deal (8);
	table = deal (1);

	// Creation of image deck and integer deck
	imageDeck = loadCardImages ();

    }


    public static void gameSetUp ()
    {
	//BACKGROUND COLOUR
	c.setColor (Color.green); // can use pre-defined constants
	c.fillRect (0, 0, 850, 1100); // x, y, width, height

	//FONT AND COLOUR
	c.setColor (Color.black); // can use pre-defined constants
	Font numbers = new Font ("Times New Roman", Font.BOLD, 15); // font name, style, point size
	c.setFont (numbers);

	//display hands and set up table
	for (int x = 0 ; x <= 10 ; x++)
	{
	    c.drawImage (cardBack, 300, 250 - x * 2, null);
	}

	showCard (table [0], 400, 250, true); // face up
	player2 = sort (player2);
	show (player2, 300, 50, false); // face down
	numberShow (player2, 300, 48);
	player1 = sort (player1);
	show (player1, 300, 450, true); // face up
	numberShow (player1, 300, 560);

	Font box = new Font ("Arial", Font.ITALIC, 25); // font name, style, point size
	c.setFont (box);

	//message box
	c.setColor (Color.yellow);
	c.fillRect (220, 367, 330, 65); //message box
	c.fillRect (547, 475, 250, 65); //player name box
	c.fillRect (35, 75, 250, 65); //computer
	//text
	c.setColor (Color.black); // can use pre-defined constants
	c.drawString (playerName.toUpperCase (), 552, 500); //player
	c.drawString ("COMPUTER", 40, 100);

	c.setFont (numbers);

    }


    public static void gamePlay ()
    {

	//FONT
	Font box = new Font ("Arial", Font.ITALIC, 25); // font name, style, point size

	//FONT AND COLOUR
	c.setColor (Color.black); // can use pre-defined constants
	Font numbers = new Font ("Times New Roman", Font.BOLD, 15); // font name, style, point size
	c.setFont (numbers);

	//message box text
	c.setColor (Color.black);
	c.drawString ("Press any key to start game", 290, 400);
	c.getChar ();

	//main loop
	while (player1.length != 0 && player2.length != 0)
	{
	    if (deck.length == 0)
	    {
		deck = resize (table.length - 1, table); //take cards from table and put in deck
	    }

	    playerTurn (); //let player play

	    if (player1.length != 0)
	    {
		if (deck.length == 0)
		{
		    deck = resize (table.length - 1, table); //take cards from table and put in deck
		}

		computerTurn (); //let computer play
	    }
	}

	Font winner = new Font ("Arial", Font.BOLD, 25); // font name, style, point size
	c.setFont (winner);

	if (player1.length == 0)
	{
	    c.setColor (Color.yellow);
	    c.fillRect (220, 367, 330, 65);
	    c.setColor (Color.black);
	    c.drawString ("YOU WIN!!!!", 320, 405); //display message if player wins
	    c.setFont (box);
	    c.drawString ("WINS!!!", 552, 525);
	    c.drawString ("LOSES", 40, 125);

	}
	else
	{
	    c.setColor (Color.yellow);
	    c.fillRect (220, 367, 330, 65);
	    c.setColor (Color.black);
	    c.setFont (winner);
	    c.drawString ("COMPUTER WINS!!!!", 260, 400); //display message if computer wins
	    c.setFont (box);
	    c.drawString ("LOSES!!!", 552, 525);
	    c.drawString ("WINS!!!", 40, 125);
	}


    }


    public static void gameDriver ()  //combines all aspects of gameplay
    {
	gameInitialize ();
	gameSetUp ();
	gamePlay ();

    }


    public static void main (String[] args)
    {
	c = new Console (30, 100); //resize console
	char choice;
	do
	{
	    menu (); //call menu()
	    choice = c.getChar ();
	    if (choice == '1')
	    {
		rules (); //call rules()

	    }
	    else if (choice == '2')
	    {
		howToPlay (); //call howToPlay()


	    }
	    else if (choice == '3')
	    {
		gameDriver (); //call gameDriver ()
		c.getChar ();

	    }

	}
	while (choice != '4');
	c.close ();






	// Place your program here.  'c' is the output console
    } // main method
} // CrazyEights class


