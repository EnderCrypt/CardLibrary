package net.ddns.endercrypt.library.cards.test.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import net.ddns.endercrypt.library.cards.Decks;
import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Deck;
import net.ddns.endercrypt.library.cards.entities.Suite;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestDecks
{
	private static final int EXPECTED_ENTROPY = 50;

	@Test
	public void decksEmptyGivesAnEmptyDeck()
	{
		assertSame(Decks.empty().count(), 0);
	}

	@Test
	public void decksStandardGives52Cards()
	{
		assertSame(Decks.standard().count(), 52);
	}

	@Test
	public void decksStandardShuffledGives52Cards()
	{
		assertSame(Decks.standardShuffled().count(), 52);
	}

	@Test
	public void decksStandardGivesAll52Cards()
	{
		Deck deck = Decks.standard();

		List<Card> cards = deck.pull().list();

		for (Suite suite : Suite.values())
		{
			List<Card> expectedSuiteCards = Arrays.asList(suite.suiteCards());
			assertTrue(cards.containsAll(expectedSuiteCards));
		}
	}

	@Test
	public void decksStandardShuffledGivesRandomlyOrdered()
	{
		Iterator<Card> iterator1 = Decks.standard().pull().list().iterator();
		Iterator<Card> iterator2 = Decks.standardShuffled().pull().list().iterator();

		int entropy = 0;

		for (int i = 0; i < 52; i++)
		{
			assertTrue(iterator1.hasNext());
			assertTrue(iterator2.hasNext());

			Card card1 = iterator1.next();
			Card card2 = iterator2.next();

			if (card1.equals(card2) == false)
			{
				entropy++;
			}
		}

		assertFalse(iterator1.hasNext());
		assertFalse(iterator2.hasNext());

		assertTrue(entropy <= 52);
		assertTrue("entropy was expected to be" + EXPECTED_ENTROPY + " but was " + entropy, entropy >= EXPECTED_ENTROPY);
	}
}
