public class Course {
    private String name;
    private String id;
    private int credits;

    public Course(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course other = (Course) obj;
            return this.id.equals(other.id);
        }
        return false;
    }
}
