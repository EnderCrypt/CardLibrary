package net.ddns.endercrypt.library.cards.pull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author EnderCrypt
 * represents a class that allows pulling instances from an iterator and return that in any form choosen, such as an optional, a list, a stream.
 * @param <T>
 */
public class ElementPuller<T>
{
	private Iterator<T> iterator;

	public ElementPuller(Iterator<T> iterator)
	{
		this.iterator = iterator;
	}

	/* SINGLE */

	/**
	 * returns 1 element or null if unavailable
	 * @return an instance or null
	 */
	public T nullable()
	{
		if (iterator.hasNext())
		{
			return iterator.next();
		}
		return null;
	}

	/**
	 * returns an optional that will contain an element if available
	 * @return an optional
	 */
	public Optional<T> optional()
	{
		return Optional.ofNullable(nullable());
	}

	/**
	 * returns an element or throws a NoSuchElementException exception if no element is available
	 * @return an instance
	 */
	public T demand()
	{
		return optional().orElseThrow(NoSuchElementException::new);
	}

	/* LIST */

	/**
	 * returns a list of up to {@code count} elements, if that many elements are available if available, if not the list will just be smaller
	 * @param count
	 * @return a limited list
	 */
	public List<T> list(long count)
	{
		return stream(count).collect(Collectors.toList());
	}

	/**
	 * returns a list of all available elements
	 * @param count
	 * @return a list
	 */
	public List<T> list()
	{
		return stream().collect(Collectors.toList());
	}

	/* ITERATE */

	public Iterator<T> iterator(int count)
	{
		return stream(count).iterator();
	}

	/**
	 * returns an iterator which allows iterating through all elements available
	 * @return an iterator
	 */
	public Iterator<T> iterator()
	{
		return iterator;
	}

	/* STREAM */

	/**
	 * returns a stream of up to {@code count} elements, if that many elements are available if available, if not the stream will just be smaller
	 * @param count
	 * @return a limited stream
	 */
	public Stream<T> stream(long count)
	{
		return stream().limit(count);
	}

	/**
	 * returns a stream of all available elements
	 * @param count
	 * @return a stream
	 */
	public Stream<T> stream()
	{
		return StreamSupport.stream(new NullTerminatedSpliterator<>(iterator, Spliterator.ORDERED), false);
	}
}
