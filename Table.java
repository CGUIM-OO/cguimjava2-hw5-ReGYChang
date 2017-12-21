import java.util.ArrayList;

public class Table {
	static final int MAXPLAYER = 4;
	private Deck StoredCards;
	private Player[] StoredPlayers;
	private Dealer Storedealer;
	private int[] pos_betArray;
	private ArrayList<Card> oneRoundCard = new ArrayList<Card>();
	
	public Table (int nDeck){
		 StoredCards = new Deck(nDeck);
		 StoredPlayers = new Player[MAXPLAYER];
	}
	
	public void set_player(int pos, Player p){
			StoredPlayers [pos] = p;		
	}
	

	
	public void set_dealer(Dealer d) {
		Storedealer = d;
	}
	
	public Card get_face_up_card_of_dealer(){
		Card dealersfaceupCard = Storedealer.getOneRoundCard().get(0);
		return dealersfaceupCard;
	}

	public Player[] get_player() {
		return StoredPlayers;
	}
	
	private void ask_each_player_about_bets(){
		pos_betArray=new int[StoredPlayers.length];
		for(int i = 0; i < StoredPlayers.length; i++){
				StoredPlayers[i].sayHello();
				int bet=StoredPlayers[i].makeBet();
				if(bet>StoredPlayers[i].getCurrentChips()){
					if(StoredPlayers[i].getCurrentChips()==0){
						StoredPlayers[i].setBet(0);
						pos_betArray[i]=0;
					}else{
						StoredPlayers[i].setBet(StoredPlayers[i].getCurrentChips());
						pos_betArray[i]=StoredPlayers[i].getCurrentChips();
					}
				}else{
					pos_betArray[i]=bet;
				}
			
		}
	}
	
	private void distribute_cards_to_dealer_and_players(){
		/*for (Player p : StoredPlayers ){
			oneRoundCard.add(StoredCards.getOneCard(true));
			oneRoundCard.add(StoredCards.getOneCard(true));
			p.setOneRoundCard(oneRoundCard);
			oneRoundCard.clear();
		}
			oneRoundCard.add(StoredCards.getOneCard(true));
			oneRoundCard.add(StoredCards.getOneCard(false));
			Storedealer.setOneRoundCard(oneRoundCard);
			System.out.println("Dealer's face up card is + oneRoundCard.get(1).printCard();");
			oneRoundCard.clear();
		*/
		for(int i = 0; i < StoredPlayers.length; i++){
				ArrayList<Card> playersCard=new ArrayList<Card>();
					playersCard.add(StoredCards.getOneCard(true));
					playersCard.add(StoredCards.getOneCard(true));
					StoredPlayers[i].setOneRoundCard(playersCard);
		}
			ArrayList<Card> dealersCard=new ArrayList<Card>();
			dealersCard.add(StoredCards.getOneCard(true));
			dealersCard.add(StoredCards.getOneCard(false));
			Storedealer.setOneRoundCard(dealersCard);
			System.out.print("Dealer's face up card is ");
			Card dealers_face_up_card=get_face_up_card_of_dealer();
			dealers_face_up_card.printCard();
		
	}

	private void ask_each_player_about_hits(){
		for(int i = 0; i < StoredPlayers.length; i++){
				System.out.print(StoredPlayers[i].getName()+"'s Cards now:");
				StoredPlayers[i].printAllCard();
				hit_process(i,StoredPlayers[i].getOneRoundCard());
				System.out.println(StoredPlayers[i].getName()+"'s hit is over!");
			
		}
	}
	
	private void hit_process(int pos,ArrayList<Card> cards){
		boolean hit;
		do{
			hit=StoredPlayers[pos].hitMe(); //this
			if(hit){
				cards.add(StoredCards.getOneCard(true));
				StoredPlayers[pos].setOneRoundCard(cards);
				System.out.print("Hit! ");
				System.out.print(StoredPlayers[pos].getName()+"'s Cards now:");
				StoredPlayers[pos].printAllCard();
				if (StoredPlayers[pos].getTotalValue()>21){
					hit=false;
				}
			}
			else{
				System.out.println("Pass hit!");
			}

		}while(hit);
	}

	private void ask_dealer_about_hits(){
		ArrayList<Card> cards=Storedealer.getOneRoundCard();
		boolean hit;
		do{
			hit=Storedealer.hit_me(this);
			if (hit){
				cards.add(StoredCards.getOneCard(true));
				Storedealer.setOneRoundCard(cards);
			}
			if (Storedealer.getTotalValue()>21){
				hit=false;
			}
		}
		while(hit);
		System.out.println("Dealer's hit is over!");	
	}
	
	private void calculate_chips(){
		int dealersCradValue=Storedealer.getTotalValue();
		System.out.print("Dealer's card value is "+dealersCradValue+" ,Cards:");
		Storedealer.printAllCard();
		for(int i=0;i<StoredPlayers.length;i++){

				System.out.print(StoredPlayers[i].getName()+"'s Cards: ");
				StoredPlayers[i].printAllCard();
				System.out.print(StoredPlayers[i].getName()+" card value is "+StoredPlayers[i].getTotalValue());
				if (StoredPlayers[i].getTotalValue()>21){
					if(Storedealer.getTotalValue()>21){
						System.out.println(", chips have no change!, the Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
					else{
						StoredPlayers[i].increaseChips(-pos_betArray[i]);
						System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
				}
				else if (StoredPlayers[i].getTotalValue()==21){
					if(StoredPlayers[i].getOneRoundCard().size()==2 && StoredPlayers[i].hasAce()){
						if(Storedealer.getTotalValue()!=21){
							StoredPlayers[i].increaseChips(pos_betArray[i]*2);
							System.out.println(",Black jack!!! Get "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
						}
						else{
							if(Storedealer.getOneRoundCard().size()==2 && Storedealer.hasAce()){
								System.out.println(",Black Jack!!!! But chips have no change!, the Chips now is: "+StoredPlayers[i].getCurrentChips());
							}
							else{
								StoredPlayers[i].increaseChips(pos_betArray[i]*2);
								System.out.println(",Black jack!!! Get "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
							}
						}
					}
					else{
						if(Storedealer.getTotalValue()!=21){
							StoredPlayers[i].increaseChips(pos_betArray[i]*2);
							System.out.println(",Get "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
						}
						else{
							System.out.println(",chips have no change!The Chips now is: "+StoredPlayers[i].getCurrentChips());
						}
					}
				}
				else{
					if(Storedealer.getTotalValue()>21){
						StoredPlayers[i].increaseChips(pos_betArray[i]);
						System.out.println(", Get "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
					else if (Storedealer.getTotalValue()<StoredPlayers[i].getTotalValue()){
						StoredPlayers[i].increaseChips(pos_betArray[i]);
						System.out.println(", Get "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
					else if(Storedealer.getTotalValue()>StoredPlayers[i].getTotalValue()){
						StoredPlayers[i].increaseChips(-pos_betArray[i]);
						System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
					else{
						System.out.println(", chips have no change! The Chips now is: "+StoredPlayers[i].getCurrentChips());
					}
				}	

			}
	}
	
	

	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
