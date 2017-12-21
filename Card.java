	public class Card{
		public enum Suit {
			Club,Diamond,Heart,Spade
		}
		private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
		private int rank; //1~13
		/**
		 * @param suit2 suit
		 * @param r rank
		 */
		//修改Constructor，創建物件時，需要填入參數(Suit s, int value)
		public Card(Suit s,int r){
			suit = s;
			rank=r;
		}	
		//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
		public void printCard(){
			//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
			if (rank ==1)
				System.out.println(suit.toString()+" "+"Ace");
			else
			System.out.println(suit.toString()+" "+rank);
		}
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}