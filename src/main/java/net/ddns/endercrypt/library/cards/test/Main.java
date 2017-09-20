package net.ddns.endercrypt.library.cards.test;

import net.ddns.endercrypt.library.cards.Decks;
import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Deck;

public class Main
{
	public static void main(String[] args)
	{
		Deck deck = Decks.standardShuffled();

		deck.pull().stream(5).map(Card::fineName).forEach(System.out::println);
	}
}
