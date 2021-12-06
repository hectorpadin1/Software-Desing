package e2.workforce;

import e2.Project;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A node of the tree representing the workforce. A team
 * can contain more teams and workers.
 *
 * @see WorkforceElement
 * @see Worker
 */
public class Team extends WorkforceElement {
    /**
     * The list that contains all the children.
     */
    private final List<WorkforceElement> elements;

    /**
     * Constructs a empty Team with the given name.
     *
     * @param name the name of the team
     */
    public Team(String name) {
        super(name);
        this.elements = new LinkedList<>();
    }

    /**
     * Check's if an Team is related to another team. A team is
     * related to another team if they have common elements.
     *
     * @param t1 the first team of the couple to test
     * @param t2 the second team of the couple to test
     * @return <code>true</code> if the teams are related,
     * <code>false</code> otherwise.
     */
    public static boolean areRelated(Team t1, Team t2) {
        return t1.contains(t2) || t2.contains(t1);
    }

    /**
     * Adds the given element as a children to the node.
     *
     * @param we the element to be added as a children
     */
    public void addChildren(WorkforceElement we) {
        elements.add(we);
    }

    /**
     * Removes the given element from the node's children, if
     * present.
     *
     * @param we the element to be removed
     */
    public void removeChildren(WorkforceElement we) {
        elements.remove(we);
        WorkforceElement.freeName(we.getName());
    }

    /**
     * Checks, recursively, if the given element is a children,
     * or a children of a children, and so on.
     *
     * @param we the element to search
     * @return <code>true</code> if the element if a children, or
     * <code>false</code> otherwise
     */
    public boolean contains(WorkforceElement we) {
        for (WorkforceElement w : this) {
            if (w.equals(we)) return true;
        }
        return false;
    }

    @Override
    public void reportOn(Project project, int recurse) {
        if (!project.hasWorkforceElement(this)) throw new IllegalArgumentException("You don't work here.");
        System.out.print(new String(new char[recurse]).replace("\0", "\t"));
        System.out.printf("%s %.2f hours, %.1f €\n", toString(), getHours(project), getCost(project));
        for (WorkforceElement we : elements)
            we.reportOn(project, recurse + 1);
    }

    @Override
    public WorkforceElement getChild(int n) {
        try {
            return elements.get(n);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public float getHours(Project project) {
        return (float) elements.stream().mapToDouble(e -> e.getHours(project)).sum();
    }

    @Override
    public float getCost(Project project) {
        return (float) elements.stream().mapToDouble(e -> e.getCost(project)).sum();
    }

    @Override
    public String toString() {
        return "Team " + super.getName() + ":";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Team that = (Team) o;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * super.hashCode();
        result = 31 * result + elements.hashCode();
        return result;
    }
}
