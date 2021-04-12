package stuthemp.db.access.TableClasses;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Group {
    private int id;

    private int groupNumber;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @NotNull
    private String facultyId;

    @NotNull
    @Range(min = 0, max = 50)
    private int studentsQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public int getStudentsQuantity() {
        return studentsQuantity;
    }

    public void setStudentsQuantity(int studentsQuantity) {
        this.studentsQuantity = studentsQuantity;
    }
}
