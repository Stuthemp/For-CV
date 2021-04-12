package stuthemp.db.access.TableClasses;

import javax.validation.constraints.*;
import java.sql.Date;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 20, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 1, max = 20, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotEmpty(message = "Middle name should not be empty")
    @Size(min = 1, max = 20, message = "Middle name should be between 2 and 30 characters")
    private String middlename;

    @NotNull
    @Past
    private Date wasBorn;

    @NotEmpty(message = "Address should not be empty")
    @Size(min = 1, max = 20, message = "Address should be between 2 and 30 characters")
    private String address;

    @NotEmpty(message = "gender should not be empty")
    @javax.validation.constraints.Pattern(regexp = ("(Муж\\.|Жен\\.)"))
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getWasBorn() {
        return wasBorn;
    }

    public void setWasBorn(Date wasBorn) {
        this.wasBorn = wasBorn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
