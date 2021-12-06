package e2.workforce;

import e2.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a worker in the workforce. Its always a leaf
 * in the workforce tree. Cannot have children.
 *
 * @see Team
 * @see WorkforceElement
 */
public class Worker extends WorkforceElement {
    private final float hourlyRate;

    /**
     * A map that contains entries for the projects that
     * the worker has spent hours and the hours spent.
     */
    private final Map<Project, Float> hoursMap;

    /**
     * Constructs a Worker with the given name and hourly
     * rate. Creates the empty map.
     *
     * @param name       the name of the element
     * @param hourlyRate the hourly rate of the element
     */
    public Worker(String name, float hourlyRate) {
        super(name);
        this.hourlyRate = hourlyRate;
        hoursMap = new HashMap<>();
    }

    /**
     * Registers a given number of hours spent by the worker
     * in the given project. If the projects already has hours
     * spent, adds the hours.
     *
     * @param project the project to register the hours
     * @param hours   the hours to add to the project
     */
    public void addHours(Project project, float hours) {
        if (!project.hasWorkforceElement(this)) throw new IllegalArgumentException("You don't work here.");
        hoursMap.compute(project, (k, v) -> v == null ? hours : hours + v);
    }

    @Override
    public float getHours(Project project) {
        if (!project.hasWorkforceElement(this)) throw new IllegalArgumentException("You don't work here.");
        return hoursMap.getOrDefault(project, 0f);
    }

    @Override
    public float getCost(Project project) {
        return getHours(project) * hourlyRate;
    }

    @Override
    public void reportOn(Project project, int recurse) {
        if (!project.hasWorkforceElement(this)) throw new IllegalArgumentException("You don't work here.");
        System.out.print(new String(new char[recurse]).replace("\0", "\t"));
        System.out.printf("%s %.2f hours, %.1f €\n", toString(), getHours(project), getCost(project));
    }

    @Override
    public WorkforceElement getChild(int n) {
        return null;
    }

    @Override
    public String toString() {
        return "Worker " + super.getName() + ":";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker that = (Worker) o;
        return Float.compare(that.hourlyRate, hourlyRate) == 0 &&
                Objects.equals(hoursMap, that.hoursMap);
    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * (int) hourlyRate;
        result = 31 * result + super.hashCode();
        result = 31 * result + hoursMap.hashCode();
        return result;
    }
}
