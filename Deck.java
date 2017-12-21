import java.util.ArrayList;
import java.util.Random;

public  class Deck{
		private static ArrayList<Card> cards;
		private ArrayList<Card> usedCard;
		private ArrayList<Card> openCard = new ArrayList<Card>();

		int  nUsed=-1;
		
		//TODO: Please implement the constructor (30 points)
		public  Deck(int nDeck){
			cards=new ArrayList<Card>();
			for (int i = 0; i < nDeck; i ++)
			{
				for(Card.Suit suit  : Card.Suit.values())
				{
					for(int k = 1; k <= 13; k++)
					{
						Card card = new Card(suit ,k);
						cards.add(card);
					}
				}
			}
			shuffle();
		}
		
		public void printDeck(){
			//Hint: print all items in ArrayList<Card> cards, 
			//TODO: please implement and reuse printCard method in Card class (5 points)
			for (int i = 0; i < cards.size(); i ++)
			{
				cards.get(i).printCard();
			}
		}
		public ArrayList<Card> getAllCards(){
			return cards;
		}
		
	/*	public  void shuffle(){
			Random random = new Random();
			ArrayList<Card> Copycards = new ArrayList<Card>();
			for (int i = 0; i < cards.size(); i ++)
			{

					Copycards.add(cards.get(random.nextInt(cards.size()))) ;
			
			}
			cards.clear();
			for (int i = 0; i < cards.size(); i++)
			{
				cards.add(Copycards.get(i));
			}
		}*/
		
		/*public  void shuffle(){
			Random random = new Random();
			ArrayList<Card> Copycards = new ArrayList<Card>(52);
			while (Copycards.size()<52)
			{
			
					if (Copycards.contains(cards.get(random.nextInt(cards.size()))) == false)
					{
						Copycards.add(cards.get(random.nextInt(cards.size()))) ;
					}
			}
			cards.clear();
			for (int i = 0; i < cards.size(); i++)
			{
				cards.add(Copycards.get(i));
			}
		}*/
		
		public void shuffle() { 
			  ArrayList<Card> scard = new ArrayList<Card>(cards.size()); 
			  Random random = new Random(); 
			  Card copycard; 
			  for (int i = 1; i <= cards.size(); i++) { 
			   do 
			   { 
			    copycard = cards.get(random.nextInt(52)); 
			   } while (scard.contains(copycard)); 
			   scard.add(copycard); 
			  }  
			  
			  cards.clear(); 
			  openCard.clear();
			  cards = scard; 

			 }
			
		public Card getOneCard(boolean isOpened){
			usedCard = new ArrayList<Card>(cards.size());
			nUsed ++;
			if (nUsed == 52)
			{
				shuffle();
				usedCard.clear();
				nUsed = 0;
			}
			usedCard.add(cards.get(nUsed));
			if (isOpened)
			{
				openCard.add(cards.get(nUsed));
			}
			return cards.get(nUsed);
		}
		
		 public ArrayList<Card> getOpenedCard()
		 {
			return openCard;
			 
		 }
		
	}
