package net.ddns.endercrypt.library.cards.pull;

import java.util.Iterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;

/**
 * @author EnderCrypt
 * represents an implementation of a spliterator that spliteratess through an iterator untill a null is encountered
 * @param <T>
 */
public class NullTerminatedSpliterator<T> extends AbstractSpliterator<T>
{
	private Iterator<T> iterator;

	/**
	 * creates a NullTerminatedSpliterator with the given iterator
	 * @param iterator
	 * @param additionalCharacteristics
	 */
	public NullTerminatedSpliterator(Iterator<T> iterator, int additionalCharacteristics)
	{
		super(Long.MAX_VALUE, additionalCharacteristics);
		this.iterator = iterator;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action)
	{
		if (iterator.hasNext() == false)
		{
			return false;
		}
		action.accept(iterator.next());
		return true;
	}

}
