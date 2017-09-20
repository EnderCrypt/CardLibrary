package net.ddns.endercrypt.library.cards.entities;

import java.awt.Color;
import java.util.stream.IntStream;

/**
 * @author EnderCrypt
 * class representing all the Suite's of a playing card, these include
 * 	CLUBS
 *	DIAMOND
 *	HEART
 *	SPADES
 *
 * @see Card
 */
public enum Suite
{
	CLUBS('♣', Color.BLACK),
	DIAMOND('♦', Color.RED),
	HEART('♥', Color.RED),
	SPADES('♠', Color.BLACK);

	private char character;
	private Color color;

	private Suite(char character, Color color)
	{
		this.character = character;
		this.color = color;
	}

	/**
	 * returns an ascii character representing this suite
	 * @return a char
	 */
	public char getCharacter()
	{
		return character;
	}

	/**
	 * returns the color of this suite (Color.BLACK/Color.RED)
	 * @return a Color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * returns an ordered array of the cards 1 to 13 (king) in this suite
	 * @return an array of cards
	 */
	public Card[] suiteCards()
	{
		return IntStream.range(1, 14).mapToObj(i -> new Card(this, i)).toArray(Card[]::new);
	}

	/**
	 * returns a randomly choosen suite
	 * @return a suite
	 */
	public static Suite random()
	{
		Suite[] suites = values();
		int index = (int) Math.floor(Math.random() * suites.length);
		return suites[index];
	}

	@Override
	public String toString()
	{
		String name = super.toString().toLowerCase();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
}
