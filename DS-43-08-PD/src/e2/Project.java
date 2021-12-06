package e2;

import e2.workforce.Team;
import e2.workforce.WorkforceElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a project which the workforce elements spend the hours.
 * The project is related to certain teams that are working on it. Only
 * the workers of these teams are allowed to spend hours in the project.
 *
 * @see Team
 */
public class Project {
    /**
     * The list that contains the names of all the projects.
     * Is considered to never be allowed to remove projects
     * from this list. Once a project is created with a given
     * name, this name is blocked always.
     */
    private static final List<String> NAMES = new ArrayList<>();

    private final String name;

    /**
     * The list of the teams working in the project.
     */
    private final List<Team> teams;

    /**
     * Constructs a new project with the given name and with
     * a first team working on it.
     *
     * @param name the name of the project to be created
     * @param team the first team added to the list of teams
     */
    public Project(String name, Team team) {
        for (String n : NAMES)
            if (n.equals(name)) throw new IllegalArgumentException("Name already exists.");
        NAMES.add(name);
        this.name = name;
        teams = new ArrayList<>();
        teams.add(team);
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a team to the allowed teams checking that the
     * teams are not related to avoid duplicities.
     *
     * @param nt the team to be added
     */
    public void addTeam(Team nt) {
        if (teams.stream().anyMatch(t -> Team.areRelated(t, nt)))
            throw new IllegalArgumentException("Teams are related.");
        teams.add(nt);
    }

    /**
     * Checks if the given element is an allowed element
     * in the project.
     *
     * @param we the element to check
     * @return <code>true</code> if is allowed, or
     * <code>false</code> otherwise
     */
    public boolean hasWorkforceElement(WorkforceElement we) {
        for (Team t : teams) {
            for (WorkforceElement w : t)
                if (w.equals(we)) return true;
        }
        return false;
    }

    /**
     * Prints a full report of the project. Total hours
     * spent by all workers in allowed teams and total cost.
     * Detailing later by each element.
     */
    public void report() {
        float hours, cost;
        hours = (float) teams.stream().mapToDouble(t -> t.getHours(this)).sum();
        cost = (float) teams.stream().mapToDouble(t -> t.getCost(this)).sum();
        System.out.printf("Project %s: %.1f hours, %.1f €%n", name, hours, cost);
        teams.forEach(t -> t.reportOn(this, 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                Objects.equals(teams, project.teams);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}