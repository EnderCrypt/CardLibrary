package net.ddns.endercrypt.library.cards.entities;

/**
 * @author EnderCrypt
 * this class representing a standard playing card
 * cards can be stored in decks
 * @see Deck
 */
public class Card
{
	private Suite suite;
	private int number;

	/**
	 * creates a copy of a card
	 * @param card
	 */
	public Card(Card card)
	{
		this(card.getSuite(), card.getNumber());
	}

	/**
	 * creates a new card with the specific suite and number
	 * @param suite
	 * @param number
	 * @throws IllegalArgumentException if the number is below 1 or above 13
	 */
	public Card(Suite suite, int number)
	{
		if ((number < 1) || (number > 13))
			throw new IllegalArgumentException("Number must be 1 to 13");
		this.suite = suite;
		this.number = number;
	}

	/**
	 * returns the suite of this card
	 * @return the suite
	 */
	public Suite getSuite()
	{
		return suite;
	}

	/**
	 * returns the number on this card
	 * @return the number
	 */
	public int getNumber()
	{
		return number;
	}

	/**
	 * returns a string version of {@code getNumber()} but with 1, 11, 12, 13 renamed into ace, jack, queen, king
	 * @return a string representing the number
	 */
	public String getNamedNumber()
	{
		switch (number)
		{
		case 1:
			return "Ace";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return String.valueOf(number);
		}
	}

	/**
	 * returns a string with both the suite and number
	 * @return a string
	 */
	public String name()
	{
		return getSuite() + " " + getNumber();
	}

	/**
	 * returns a string with the suite character and the named number
	 * @return a fine string
	 */
	public String fineName()
	{
		return getSuite().getCharacter() + " " + getNamedNumber();
	}

	/**
	 * generates a random card
	 * @return a card
	 */
	public static Card random()
	{
		Suite suite = Suite.random();
		int number = (int) Math.ceil(Math.random() * 13);

		return new Card(suite, number);
	}

	@Override
	public String toString()
	{
		return getClass().getName() + "[" + fineName() + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((suite == null) ? 0 : suite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Card other = (Card) obj;
		if (number != other.number) return false;
		if (suite != other.suite) return false;
		return true;
	}
}
