package stuthemp.db.access.TableClasses;

public class StudentsRelatives {
    private int id;
    private int relativeTypeId;
    private int personId;
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelativeTypeId() {
        return relativeTypeId;
    }

    public void setRelativeTypeId(int relativeTypeId) {
        this.relativeTypeId = relativeTypeId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
