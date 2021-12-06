package e2.workforce;

import e2.Project;
import e2.workforce.iterators.WorkforceIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * An element of the workforce of the company to be represented.
 * Forms a tree representation using the Composite Pattern.
 * The nodes are objects of Team. The leaves are objects of Worker.
 * Teams can be empty, so sometimes can be leaves too.
 *
 * @see Team
 * @see Worker
 */
public abstract class WorkforceElement implements Iterable<WorkforceElement> {
    /**
     * Array used to check the non-duplicity of the names.
     */
    private static final List<String> NAMES = new ArrayList<>();

    private final String name;

    /**
     * Constructs a WorkforceElement with the unique given name.
     *
     * @param name the name of the element to be added
     * @throws IllegalArgumentException if the name already exists
     */
    public WorkforceElement(String name) {
        for (String n : NAMES)
            if (n.equals(name)) throw new IllegalArgumentException("Name already exists.");
        NAMES.add(name);
        this.name = name;
    }

    /**
     * Removes a name from the NAMES list. Allows the name to
     * be used again by a new element.
     *
     * @param name the name to be removed
     */
    public static void freeName(String name) {
        NAMES.removeIf(n -> n.equals(name));
    }

    /**
     * Returns the name of the WorkforceElement.
     *
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hours spent by the element. If the element
     * is a Team, returns all the hours spent by its children.
     *
     * @param project the project to search for spent hours
     * @return the number of hours spent in the given project
     */
    public abstract float getHours(Project project);

    /**
     * Returns the cost from the hours spent by the element.
     * If the element is a Team, returns the cost of the hours
     * spent by its children.
     *
     * @param project the project to search for cost
     * @return the cost of the hours spent in the given project
     */
    public abstract float getCost(Project project);

    /**
     * Prints a report about the spent hours from a element
     * in a given project. Goes recursive until the last element.
     * Uses tabulation to represent the hierarchy.
     *
     * @param project the project to generate the report
     * @param recurse represents the level of hierarchy
     */
    public abstract void reportOn(Project project, int recurse);

    /**
     * Prints a report about the spent hours from a element
     * in a given project. Goes recursive until the last element.
     * Uses tabulation to represent the hierarchy.
     * Defaults the hierarchy level to the first.
     *
     * @param project the project to generate the report
     */
    public void reportOn(Project project) {
        reportOn(project, 0);
    }

    /**
     * Returns the children of the element in position n.
     *
     * @param n the position of the children to return
     * @return return the children in the position n, or
     * <code>null</code> if there's no children
     * in the given position
     */
    public abstract WorkforceElement getChild(int n);

    @Override
    public Iterator<WorkforceElement> iterator() {
        return new WorkforceIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkforceElement that = (WorkforceElement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
