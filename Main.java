import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Random;
public class Main {
    //Defining our variables
    static SingleLinkedList Player1Numbers = new SingleLinkedList();
    static SingleLinkedList Player2Numbers = new SingleLinkedList();
    static int Turn = 1;
    static int Player1Score = 0;
    static int Player2Score = 0;
    static int Player1TempScore = 0;
    static int Player2TempScore = 0;
    static SingleLinkedList HighScore =  new SingleLinkedList();
    static SingleLinkedList PlayerNames= new SingleLinkedList();
    static SingleLinkedList PlayerScores= new SingleLinkedList();
static SingleLinkedList Reading(String txt)
{
	//Reading files,putting and returning them in the Single linked list.
    SingleLinkedList HighScoreTable = new SingleLinkedList();
    int counter=0;
	try 
	{      
	File file = new File(txt);
	Scanner push=new Scanner(file);
	while(push.hasNextLine())
	{
    String line=push.nextLine();
    if(line.equals("Player1") || line.equals("Player2")||counter!=0)
    {
        if(counter==0)
        {
         counter=2;
        }
        counter--;
    }
    else
    {
        HighScoreTable.add(line);
    }
    
	}
    push.close();	
	return HighScoreTable;
	} catch (FileNotFoundException e) {
	      System.out.println("The file couldn't found.");
		  return null;
	    }
	
}
static void PlayersAndScores(SingleLinkedList HighScore)
{ 
  //Separating highscore to names and scores.
  Node temp = HighScore.getHead();
  int counter = 0;
  while(temp!=null)
  {
    if(counter %2==0)
    {
    PlayerNames.add(temp.getData());
    }
    else
    {
      PlayerScores.add(temp.getData());

    }
    temp = temp.getLink();
    counter++;
  }

}
static SingleLinkedList ControllingNumbers(SingleLinkedList PlayerNumbers)
{   
    //Controlling if it has 6 consecutive numbers.
    SingleLinkedList PlayerNumbersTempIndex = new SingleLinkedList();
    //Keeping matched numbers in PlayerNumbersIndex linked list.
    SingleLinkedList PlayerNumbersIndex = new SingleLinkedList();
    int counter = 0;
    for (int i = 1; i < 7; i++) {
        boolean breaking =false;
         Node temp = PlayerNumbers.getHead();
         for (int j = 0; j < PlayerNumbers.size(); j++) {
             if((Integer)temp.getData()==i&&!PlayerNumbersIndex.search(j))
             {
                 counter++;
                 PlayerNumbersTempIndex.add(j);
                 breaking=true;
             }
             temp = temp.getLink();
             if(breaking)
             break;
         }
            
        }
        if(counter !=0 && counter>=6)
            {
                Node tempassign = PlayerNumbersTempIndex.getHead();
              for (int j = 0; j < 6; j++) {
                  PlayerNumbersIndex.orderAndAdd(tempassign.getData());
                  tempassign = tempassign.getLink();
                  
              }
              if(PlayerNumbers.equals(Player1Numbers))
              //Adding temporary scores.
              Player1TempScore+=30;
              else
              Player2TempScore+=30;
            }
            PlayerNumbersTempIndex.setHead(null);
            counter = 0;
    //Controlling if it has 4 same numbers.
    for (int i = 0; i <PlayerNumbers.size(); i++) {
        Node temp = PlayerNumbers.getHead();
        if(counter !=0 && counter>=4 )
        {
            Node tempassign = PlayerNumbersTempIndex.getHead();
          for (int j = 0; j < 4; j++) {
              PlayerNumbersIndex.orderAndAdd(tempassign.getData());
              tempassign = tempassign.getLink();
              
          }
          if(PlayerNumbers.equals(Player1Numbers))
          //Adding temporary scores.
          Player1TempScore+=10;
          else
          Player2TempScore+=10;

          
        }
        PlayerNumbersTempIndex.setHead(null);
        counter = 0;     
        for (int j = 0; j < i; j++) {
            temp = temp.getLink();
        } 
        int number = (Integer)temp.getData();
        int indexcounter=0;
        while(temp!=null){
            if((Integer)temp.getData()==number && !PlayerNumbersIndex.search(i+indexcounter))
            {
              counter++;
              if(counter<=4)
              {
              PlayerNumbersTempIndex.add(i+indexcounter);
              }
            }
            indexcounter++;
            temp = temp.getLink();
        }
    }
    PlayerNumbersTempIndex.setHead(null);
    return PlayerNumbersIndex;

    
}
static void SortingPlayersAndScores()
{
    //Sorting players and scores highest to lowest.
    SingleLinkedList OrderedPlayerNames = new SingleLinkedList();
    SingleLinkedList OrderedPlayerScores = new SingleLinkedList();
    SingleLinkedList TempHighScore = new SingleLinkedList();

    Node ForTemp = PlayerScores.getHead();
    Node ForTempName = PlayerNames.getHead();
    
    for (int i = 0; i < PlayerNames.size(); i++) {
        Node newNode = new Node(ForTemp.getData());
        Node newNodeName = new Node(ForTempName.getData());
        boolean flag =false;
    
        if(OrderedPlayerScores.getHead()==null)
        {
            flag=true;
            OrderedPlayerScores.setHead(newNode);
            OrderedPlayerNames.setHead(newNodeName);
        }
        if (Integer.parseInt(String.valueOf(ForTemp.getData())) > Integer.parseInt(String.valueOf(OrderedPlayerScores.getHead().getData()))){
          
           newNode.setLink(OrderedPlayerScores.getHead());
           newNodeName.setLink(OrderedPlayerNames.getHead());
           OrderedPlayerScores.setHead(newNode);
           OrderedPlayerNames.setHead(newNodeName);
           }
           Node temp = OrderedPlayerScores.getHead();
           Node tempName = OrderedPlayerNames.getHead();
           Node previous =null;
           Node previousName =null;
           while(flag==false &&temp!=null&&Integer.parseInt(String.valueOf(ForTemp.getData()))<=Integer.parseInt(String.valueOf(temp.getData())))
           {
               previous=temp;
               previousName=tempName;
               temp = temp.getLink();
               tempName = tempName.getLink();
           }
           if(temp==null)
           {
               
               previous.setLink(newNode);
               previousName.setLink(newNodeName);
           }
           else{
               if(previous!=null)
               {
               
               newNode.setLink(temp);
               newNodeName.setLink(tempName);
               previous.setLink(newNode);
               previousName.setLink(newNodeName);
               }
           }
       ForTemp = ForTemp.getLink();
       ForTempName = ForTempName.getLink();
    }
    PlayerScores = OrderedPlayerScores;
    PlayerNames = OrderedPlayerNames;
    Node TempName = PlayerNames.getHead();
    Node TempScore = PlayerScores.getHead();
    //Merging them on the one Single linked list with two datas.
    for (int i = 0; i < PlayerNames.size(); i++) {
        
        TempHighScore.addTwoData(TempName.getData(),TempScore.getData());
        TempName = TempName.getLink();
        TempScore = TempScore.getLink();
        
    }
    HighScore = TempHighScore;
}
static int RandomNumber(int range)

{
	// Create random number and return it.
	Random Random = new Random();
	return (Random.nextInt(range)*1 + 1);

}
static void RandomDices()
{
    //Play dice three times for each player.
    for (int i = 0; i < 3; i++) {
        Player1Numbers.add(RandomNumber(6));
        Player2Numbers.add(RandomNumber(6));
    }
    
}
static void PrintingScreen(SingleLinkedList color1,SingleLinkedList color2,SingleLinkedList PreviousPlayer1Numbers,SingleLinkedList PreviousPlayer2Numbers)
{ 
    //Printing screen with right colors.
    System.out.print("\u001B[32m"+"Player1: "+"\u001B[0m");
    Player1Numbers.display(color1);
    for (int i = 0; i < 3; i++) {
        System.out.print(" ");
    }
    if(Player2Numbers.size()>Player1Numbers.size())
    {
        for (int i = 0; i < Player2Numbers.size()-Player1Numbers.size(); i++) 
        {
            System.out.print("  ");
        }
    }
    System.out.print("\u001B[36m"+"score: "+"\u001B[0m"+Player1Score);;
    System.out.println();
    System.out.print("\u001B[34m"+"Player2: "+"\u001B[0m");
    Player2Numbers.display(color2);
    for (int i = 0; i < 3; i++) {
        System.out.print(" ");
    }
    if(Player1Numbers.size()>Player2Numbers.size())
    {
        for (int i = 0; i < Player1Numbers.size()-Player2Numbers.size(); i++) 
        {
            System.out.print("  ");
        }
    }
    System.out.print("\u001B[36m"+"score: "+"\u001B[0m"+Player2Score);
    System.out.println();
    System.out.println();
    //Filling PreviousPlayerNumbers linked lists for ExtraPrintingScreen() procedure.
    Node Temprev1 = Player1Numbers.getHead();
    for (int i = 0; i < Player1Numbers.size(); i++) {
        PreviousPlayer1Numbers.add(Temprev1.getData());
        Temprev1 = Temprev1.getLink();
    }
    Node Temprev2 = Player2Numbers.getHead();
    for (int i = 0; i < Player2Numbers.size(); i++) {
        PreviousPlayer2Numbers.add(Temprev2.getData());
        Temprev2 = Temprev2.getLink();
    }
    
    
    

}
static void ExtraPrintingScreen(SingleLinkedList color1,SingleLinkedList color2,SingleLinkedList PreviousPlayer1Numbers,SingleLinkedList PreviousPlayer2Numbers)
{ 
    //If it requires, printing screen again with alignment in a single turn.
    System.out.print("\u001B[32m"+"Player1: "+"\u001B[0m");
    Player1Numbers.display(color1);
    for (int i = 0; i < 3; i++) {
        System.out.print(" ");
    }
    if(Player2Numbers.size()>Player1Numbers.size())
    {
        for (int i = 0; i < Player2Numbers.size()-Player1Numbers.size(); i++) 
        {
            System.out.print("  ");
        }
    }
    if(PreviousPlayer1Numbers.size()>=PreviousPlayer2Numbers.size()&&Player1Numbers.size()>=Player2Numbers.size())
    for (int i = 0; i < PreviousPlayer1Numbers.size()-Player1Numbers.size(); i++) {
        System.out.print("  ");
    }
    else if(PreviousPlayer1Numbers.size()>=PreviousPlayer2Numbers.size()&&Player2Numbers.size()>=Player1Numbers.size())
    for (int i = 0; i < PreviousPlayer1Numbers.size()-Player2Numbers.size(); i++) {
        System.out.print("  ");
    }
    else if(PreviousPlayer2Numbers.size()>=PreviousPlayer1Numbers.size()&&Player1Numbers.size()>=Player2Numbers.size())
    for (int i = 0; i < PreviousPlayer2Numbers.size()-Player1Numbers.size(); i++) {
        System.out.print("  ");
    }
    else if(PreviousPlayer2Numbers.size()>=PreviousPlayer1Numbers.size()&&Player2Numbers.size()>=Player1Numbers.size())
    for (int i = 0; i < PreviousPlayer2Numbers.size()-Player2Numbers.size(); i++) {
        System.out.print("  ");
    }
    System.out.print("\u001B[36m"+"score: "+"\u001B[0m"+Player1Score);;
    System.out.println();
    System.out.print("\u001B[34m"+"Player2: "+"\u001B[0m");
    Player2Numbers.display(color2);
    for (int i = 0; i < 3; i++) {
        System.out.print(" ");
    }
    if(Player1Numbers.size()>Player2Numbers.size())
    {
        for (int i = 0; i < Player1Numbers.size()-Player2Numbers.size(); i++) 
        {
            System.out.print("  ");
        }
    }
    if(PreviousPlayer1Numbers.size()>=PreviousPlayer2Numbers.size()&&Player1Numbers.size()>=Player2Numbers.size())
    for (int i = 0; i < PreviousPlayer1Numbers.size()-Player1Numbers.size(); i++) {
        System.out.print("  ");
    }
  
    else if(PreviousPlayer1Numbers.size()>=PreviousPlayer2Numbers.size()&&Player2Numbers.size()>=Player1Numbers.size())
    for (int i = 0; i < PreviousPlayer1Numbers.size()-Player2Numbers.size(); i++) {
        System.out.print("  ");
    }

    else if(PreviousPlayer2Numbers.size()>=PreviousPlayer1Numbers.size()&&Player1Numbers.size()>=Player2Numbers.size())
    for (int i = 0; i < PreviousPlayer2Numbers.size()-Player1Numbers.size(); i++) {
        System.out.print("  ");
    }

    else if(PreviousPlayer2Numbers.size()>=PreviousPlayer1Numbers.size()&&Player2Numbers.size()>=Player1Numbers.size())
    for (int i = 0; i < PreviousPlayer2Numbers.size()-Player2Numbers.size(); i++) {
        System.out.print("  ");
    }

    System.out.print("\u001B[36m"+"score: "+"\u001B[0m"+Player2Score);
    System.out.println();
    System.out.println();
    

}
static SingleLinkedList DeletingNumbers(SingleLinkedList PlayerNumber ,SingleLinkedList PlayerNumbersIndex)
{   //Deleting numbers which are matched.
    Node temp = PlayerNumbersIndex.getHead();
    int deletecounter =0;
    for (int i = 0; i < PlayerNumbersIndex.size(); i++) {
        PlayerNumber.deleteAtIndex((Integer)temp.getData()-deletecounter);
        temp = temp.getLink();
        deletecounter++;
    }
   return PlayerNumber;
    
}
static void Scoring(SingleLinkedList Player1NumbersIndex,SingleLinkedList Player2NumbersIndex,SingleLinkedList PreviousPlayer1Numbers, SingleLinkedList PreviousPlayer2Numbers)
{
   //Giving score to the player and using ExtraPrintingScreen() procedure.
   Player1Score += Player1TempScore;
   Player2Score += Player2TempScore;
   if(Player1TempScore>0 ||Player2TempScore>0)
   ExtraPrintingScreen(Player1NumbersIndex,Player2NumbersIndex,PreviousPlayer1Numbers,PreviousPlayer2Numbers);
   PreviousPlayer1Numbers.clearList();
   PreviousPlayer2Numbers.clearList();
   Player1TempScore=0;
   Player2TempScore=0;
}
static void AddingPlayersToHighScore()
{
    //Adding players to the highscore table.
    if(Player1Score>=Player2Score)
    {
    PlayerNames.add("Player1");
    PlayerScores.add(Player1Score);
    }
    else
    {
    PlayerNames.add("Player2");
    PlayerScores.add(Player2Score);
    }
}
static void PrintingHighScoreTable()
{
 //Printing highscore table.
 System.out.println("Game is over.");
 if(Player1Score>Player2Score)
 {
   System.out.println("The winner is player 1."); 
 }
 else if(Player1Score==Player2Score)
 {
   System.out.println("Game is draw."); 
 }
 else
 {
   System.out.println("The winner is player 2."); 
 }
 System.out.println();
 System.out.println("\u001B[33m"+"High Score Table");
 HighScore.displayfirstTen();
}
static void WritingHighScore()
{
    try {
        FileWriter myWriter = new FileWriter("HighScoreTable.txt");
        Node Temp = HighScore.getHead();
        for (int i = 0; i < HighScore.size(); i++) {
                myWriter.write(Temp.getData()+"\n"+Temp.getSeconddata()+"\n");
                Temp = Temp.getLink();
            }
        myWriter.close();
        System.out.println("Successfully wrote highscore table to the file.");
      } catch (IOException e) {
        System.out.println("The file couldn't found.");
      }
}
    public static void main(String[] args) throws InterruptedException
    {
        //Defining variables.
        SingleLinkedList Player1NumbersIndex = new SingleLinkedList();
        SingleLinkedList Player2NumbersIndex = new SingleLinkedList();
        SingleLinkedList PreviousPlayer1Numbers = new SingleLinkedList();
        SingleLinkedList PreviousPlayer2Numbers = new SingleLinkedList(); 
        
        //Our game loop.
        for (int i = 0; i < 10; i++) {
            System.out.println("Turn: "+Turn);
            RandomDices();
            Player1NumbersIndex = ControllingNumbers(Player1Numbers);
            Player2NumbersIndex = ControllingNumbers(Player2Numbers);
            PrintingScreen(Player1NumbersIndex, Player2NumbersIndex,PreviousPlayer1Numbers,PreviousPlayer2Numbers);
            Player1Numbers=DeletingNumbers(Player1Numbers,Player1NumbersIndex);
            Player2Numbers=DeletingNumbers(Player2Numbers,Player2NumbersIndex);
            Player1NumbersIndex.clearList();
            Player2NumbersIndex.clearList();  
            Scoring(Player1NumbersIndex,Player2NumbersIndex,PreviousPlayer1Numbers,PreviousPlayer2Numbers);         
            Turn++;
            Thread.sleep(1000);
           
        }
        //Calculating and printing highscore table.
        HighScore = Reading("HighScoreTable.txt");
        PlayersAndScores(HighScore);
        AddingPlayersToHighScore();
        SortingPlayersAndScores();
        PrintingHighScoreTable();
        WritingHighScore();
    }
    
}
