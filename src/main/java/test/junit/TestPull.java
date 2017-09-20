package test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import net.ddns.endercrypt.library.cards.pull.ElementPuller;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestPull
{
	private List<String> sampleList = Collections.unmodifiableList(Arrays.asList("one", "two", "three"));

	private ElementPuller<String> samplePuller;

	@Before
	public void init()
	{
		samplePuller = new ElementPuller<>(new ArrayList<>(sampleList).iterator());
	}

	@Test
	public void pullNullableWorks()
	{
		assertEquals("one", samplePuller.nullable());
		assertEquals("two", samplePuller.nullable());
		assertEquals("three", samplePuller.nullable());
		assertEquals(null, samplePuller.nullable());
	}

	@Test
	public void pullOptionalWorksWorks()
	{
		assertEquals(Optional.of("one"), samplePuller.optional());
		assertEquals(Optional.of("two"), samplePuller.optional());
		assertEquals(Optional.of("three"), samplePuller.optional());
		assertEquals(Optional.empty(), samplePuller.optional());
	}

	@Test
	public void pullDemandWorks()
	{
		assertEquals("one", samplePuller.demand());
		assertEquals("two", samplePuller.demand());
		assertEquals("three", samplePuller.demand());
	}

	@Test(expected = NoSuchElementException.class)
	public void pullDemandException()
	{
		samplePuller.list(3);

		samplePuller.demand();
	}

	@Test
	public void pullListWorks()
	{
		assertEquals(sampleList, samplePuller.list());
	}

	@Test
	public void pullIteratorWorks()
	{
		Iterator<String> iterator = samplePuller.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("one", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("two", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("three", iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void pullStreamWorks()
	{
		List<String> newList = samplePuller.stream().collect(Collectors.toList());
		assertEquals(sampleList, newList);
	}

	@Test
	public void pullStreamLimitedWorks()
	{
		List<String> newList = samplePuller.stream(2).collect(Collectors.toList());
		assertEquals(Arrays.asList("one", "two"), newList);
	}
}
