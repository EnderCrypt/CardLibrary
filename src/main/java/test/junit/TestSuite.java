package test.junit;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Suite;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestSuite
{
	@Test
	public void suiteHas4()
	{
		assertSame(Suite.values().length, 4);
	}

	@Test
	public void suiteHasThe4Suites()
	{
		List<String> suiteNames = Arrays.asList(Suite.values()).stream().map(s -> s.toString()).collect(Collectors.toList());

		Predicate<String> containsEqualsIgnoreCase = new Predicate<String>()
		{
			@Override
			public boolean test(String name)
			{
				return suiteNames.stream().filter(s -> s.equalsIgnoreCase(name)).findAny().isPresent();
			}
		};

		assertTrue(containsEqualsIgnoreCase.test("CLUBS"));
		assertTrue(containsEqualsIgnoreCase.test("DIAMOND"));
		assertTrue(containsEqualsIgnoreCase.test("HEART"));
		assertTrue(containsEqualsIgnoreCase.test("SPADES"));
	}

	@Test
	public void suiteReturnsTheCorrect13Cards()
	{
		for (Suite suite : Suite.values())
		{
			Card[] cards = suite.suiteCards();
			assertSame(13, cards.length);
			for (int i = 0; i < cards.length; i++)
			{
				Card card = cards[i];
				assertEquals(suite, card.getSuite());
				assertEquals(i + 1, card.getNumber());
			}
		}
	}

	@Test
	public void suiteHasCorrectCharacters()
	{
		assertEquals(Suite.CLUBS.getCharacter(), '♣');
		assertEquals(Suite.DIAMOND.getCharacter(), '♦');
		assertEquals(Suite.HEART.getCharacter(), '♥');
		assertEquals(Suite.SPADES.getCharacter(), '♠');
	}

	@Test
	public void suiteHasCorrectColors()
	{
		assertEquals(Suite.CLUBS.getColor(), Color.BLACK);
		assertEquals(Suite.DIAMOND.getColor(), Color.RED);
		assertEquals(Suite.HEART.getColor(), Color.RED);
		assertEquals(Suite.SPADES.getColor(), Color.BLACK);
	}
}
