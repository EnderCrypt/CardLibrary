package net.ddns.endercrypt.library.cards.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.ddns.endercrypt.library.cards.pull.ElementPuller;

/**
 * @author EnderCrypt
 * class representing a playing deck, these can be empty or contain cards, theres no limit to the amount of cards that can be put in a deck
 * 
 * @see {@link #add(Card)}
 * @see {@link #pull()}
 */
public class Deck
{
	private final List<Card> list = new ArrayList<>();

	/**
	 * generates a new empty deck
	 */
	public Deck()
	{

	}

	/**
	 * creats a copy of all cards in a deck and puts it in this deck
	 * @param deck
	 */
	public Deck(Deck deck)
	{
		deck.peek().stream().map(Card::new).forEach(this::add);
	}

	/**
	 * adds a card to this deck
	 * a word of warning: multiple decks can have the same card in them
	 * @param card
	 */
	public void add(Card card)
	{
		list.add(card);
	}

	/**
	 * returns true if theres atleast 1 card available in the deck 
	 * @return boolean
	 */
	public boolean hasCards()
	{
		return count() > 0;
	}

	/**
	 * moves all cards from this deck to the destination deck
	 * @param destination deck
	 */
	public void transferCards(Deck destination)
	{
		list.stream().forEach(destination::add);
		empty();
	}

	/**
	 * returns how many cards are in this deck
	 * @return a number
	 */
	public int count()
	{
		return list.size();
	}

	/**
	 * checks if this deck contains the specific card
	 * @param targetCard
	 * @return true/false
	 */
	public boolean contains(Card targetCard)
	{
		return list.contains(targetCard);
	}

	/**
	 * clears the deck from all cards
	 */
	public void empty()
	{
		list.clear();
	}

	/**
	 * randomly shuffles all the cards in this deck
	 */
	public void shuffle()
	{
		Collections.shuffle(list);
	}

	/**
	 * generates a pull object, this allows you to check cards from this deck in many diffrent ways for example
	 * {@code deck.pull.nullable()} will return a card OR null if none is available
	 * {@code deck.pull.stream()} will return a stream of cards
	 * {@code deck.pull.list()} will return a list of all cards, this cards will be the same as in the deck
	 * 
	 * this function will NEVER remove cards from the deck
	 * @return ElementPuller of card
	 * @see {@link #Deck.pull()}
	 */
	public ElementPuller<Card> peek()
	{
		return new ElementPuller<>(new Iterator<Card>()
		{
			private int index = list.size();

			@Override
			public boolean hasNext()
			{
				return (index >= 0);
			}

			@Override
			public Card next()
			{
				index--;
				return list.get(index);
			}
		});
	}

	/**
	 * generates a pull object, this allows you to pull cards from this deck in many diffrent ways for example
	 * {@code deck.pull.nullable()} will return a card OR null if none is available
	 * {@code deck.pull.stream()} will return a stream of cards, each card you take from the stream will be removed from the deck
	 * {@code deck.pull.list()} will return a list of all cards, all cards WILL be removed from this deck
	 * 
	 * this function WILL remove cards from the deck
	 * @return ElementPuller of card
	 * @see {@link #Deck.peek()}
	 */
	public ElementPuller<Card> pull()
	{
		return new ElementPuller<>(new Iterator<Card>()
		{
			@Override
			public boolean hasNext()
			{
				return (list.size() > 0);
			}

			@Override
			public Card next()
			{
				return list.remove(list.size() - 1);
			}
		});
	}

	/**
	 * attempts to pull a card thats identical to {@code targetCard} from the deck
	 * if the deck contains multiple cards that are similar, all of them will be returned
	 * @param targetCard
	 * @return ElementPuller of card
	 * @see {@link #Deck.pull()}
	 */
	public ElementPuller<Card> pull(Card targetCard)
	{
		return new ElementPuller<>(new Iterator<Card>()
		{
			private int locate()
			{
				for (int i = list.size() - 1; i > 0; i--)
				{
					if (list.get(i).equals(targetCard))
					{
						return i;
					}
				}
				return -1;
			}

			@Override
			public boolean hasNext()
			{
				return locate() >= 0;
			}

			@Override
			public Card next()
			{
				return list.remove(locate());
			}
		});
	}
}