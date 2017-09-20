package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import net.ddns.endercrypt.library.cards.entities.Card;
import net.ddns.endercrypt.library.cards.entities.Suite;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestCard
{
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void throwsIllegalArgumentOnNegativeNumber()
	{
		new Card(Suite.CLUBS, -1);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void throwsIllegalArgumentOnZeroNumber()
	{
		new Card(Suite.CLUBS, 0);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void throwsIllegalArgumentOnHighNumber()
	{
		new Card(Suite.CLUBS, 14);
	}

	@Test
	public void cardReturnsCorrectNumber()
	{
		for (Suite suite : Suite.values())
		{
			for (int number = 1; number < 13; number++)
			{
				Card card = new Card(suite, number);

				assertEquals(suite, card.getSuite());
				assertEquals(number, card.getNumber());
			}
		}
	}

	@Test
	public void cardEquals()
	{
		for (Suite suite : Suite.values())
		{
			for (int number = 1; number < 13; number++)
			{
				Card card1 = new Card(suite, number);
				Card card2 = new Card(suite, number);

				assertEquals(card1, card2);
			}
		}
	}
}
