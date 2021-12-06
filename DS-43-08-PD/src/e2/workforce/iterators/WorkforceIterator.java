package e2.workforce.iterators;

import e2.workforce.WorkforceElement;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * An iterator that travers a tree using the preorder
 * algorithm.
 *
 * @see Iterator
 */
public class WorkforceIterator implements Iterator<WorkforceElement> {
    /**
     * The list with all the elements in order to be served.
     */
    private final LinkedList<WorkforceElement> queue;

    /**
     * Constructs the iterator to traverse the tree starting
     * in the given element and going down.
     * Calls <code>addToQueue</code> to start filling the queue.
     *
     * @param we the element to start traversing the tree
     * @throws IllegalArgumentException if the element is null
     */
    public WorkforceIterator(WorkforceElement we) {
        queue = new LinkedList<>();
        if (we == null) throw new IllegalArgumentException("Parent node cannot be null");
        addToQueue(we);
    }

    /**
     * Adds the given element to the queue, determines with
     * element has to be next and calls recursively with the
     * element.
     *
     * @param we the element to be added to the queue
     */
    private void addToQueue(WorkforceElement we) {
        queue.add(we);
        int i = 0;
        while (we.getChild(i) != null) {
            addToQueue(we.getChild(i++));
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public WorkforceElement next() {
        return queue.pop();
    }
}
