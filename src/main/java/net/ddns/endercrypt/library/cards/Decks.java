package net.ddns.endercrypt.library.cards;

import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Deck;
import net.ddns.endercrypt.library.cards.entities.Suite;

/**
 * @author EnderCrypt
 * static utility class for returning diffrent kinds of decks
 * @see Deck
 */
public class Decks
{
	/**
	 * simply retunrns an empty deck
	 * @return a deck
	 */
	public static Deck empty()
	{
		return new Deck();
	}

	/**
	 * returns a standard deck, that is a deck filled with 52 cards, 1 to 13 in all 4 suites
	 * 
	 * @return a deck
	 * @see Deck
	 * @see Card
	 * @see Suite
	 */
	public static Deck standard()
	{
		Deck deck = Decks.empty();

		for (Suite suite : Suite.values())
		{
			for (Card card : suite.suiteCards())
			{
				deck.add(card);
			}
		}

		return deck;
	}

	/**
	 * works very much like {@link #standard()} but also shuffles the deck
	 * 
	 * @return a deck
	 * @see #standard()
	 * @see Deck
	 * @see Card
	 * @see Suite
	 */
	public static Deck standardShuffled()
	{
		Deck deck = standard();
		deck.shuffle();
		return deck;
	}
}
