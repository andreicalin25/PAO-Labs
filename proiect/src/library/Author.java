package library;

import java.util.Objects;

public class Author {
    private String name;
    private String date_of_birth;
    private String date_of_death;

    public Author(String name, String date_of_birth, String date_of_death) {
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.date_of_death = date_of_death;
    }

    public Author(String name, String date_of_birth) {
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.date_of_death = null;
    }

    public String  getName() {
        return name;
    }

    public void setName(String  name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDate_of_death() {
        return date_of_death;
    }

    public void setDate_of_death(String date_of_death) {
        this.date_of_death = date_of_death;
    }

    @Override
    public String toString() {
        return  name + ", date_of_birth=" + date_of_birth + ", date_of_death=" + date_of_death;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getName(), author.getName()) && Objects.equals(getDate_of_birth(), author.getDate_of_birth()) && Objects.equals(getDate_of_death(), author.getDate_of_death());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate_of_birth(), getDate_of_death());
    }
}
