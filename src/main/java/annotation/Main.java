package annotation;

public class Main {
    public static void main(String[] args) {
        DefaultValueHandler handler = new DefaultValueHandler();
        Employee employee = new Employee();
        employee.setName("John");

        handler.handle(employee);

        assert employee.getName().equals("John");
        assert employee.getWeekTime().equals(12345L);

        employee.setName(null);

        handler.handle(employee);

        assert employee.getName().equals("some name");
        assert employee.getWeekTime().equals(12345L);
    }
}
