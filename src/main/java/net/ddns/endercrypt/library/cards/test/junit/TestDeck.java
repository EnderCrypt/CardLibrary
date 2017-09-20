package net.ddns.endercrypt.library.cards.test.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import net.ddns.endercrypt.library.cards.Decks;
import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Deck;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestDeck
{
	@Test
	public void deckStartsEmpty()
	{
		Deck deck = new Deck();
		assertSame(0, deck.count());
	}

	@Test
	public void deckCanAddAndRemoveCard()
	{
		Deck deck = new Deck();

		Card card = Card.random();

		assertEquals(null, deck.pull().nullable());

		deck.add(card);

		assertSame(1, deck.count());

		assertEquals(card, deck.pull().nullable());

		assertEquals(null, deck.pull().nullable());
	}

	@Test
	public void deckCanTransfer()
	{
		Card card = Card.random();

		Deck deckSrc = Decks.empty();
		deckSrc.add(card);

		Deck deckDest = Decks.empty();
		deckSrc.transferCards(deckDest);

		assertSame(0, deckSrc.count());
		assertSame(1, deckDest.count());
	}

	@Test
	public void deckContains()
	{
		Deck deck = Decks.empty();

		Card card = Card.random();

		assertFalse(deck.contains(card));

		deck.add(card);

		assertTrue(deck.contains(card));
	}
}
