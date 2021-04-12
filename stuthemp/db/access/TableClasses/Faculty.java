package stuthemp.db.access.TableClasses;

public class Faculty {
    private String id;
    private String name;
    private String dean;
    private String deanery;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getDeanery() {
        return deanery;
    }

    public void setDeanery(String deanery) {
        this.deanery = deanery;
    }
}
