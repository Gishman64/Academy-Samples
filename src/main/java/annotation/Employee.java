package annotation;

public class Employee {

    @DefaultValue(value = "some name")
    private String name;
    @DefaultValue(value = "12345")
    private Long weekTime;
    @DefaultValue("TOP SECRET")
    private ImportantData data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(Long weekTime) {
        this.weekTime = weekTime;
    }
}
