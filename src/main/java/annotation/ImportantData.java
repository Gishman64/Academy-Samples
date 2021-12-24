package annotation;

/**
 * Employee important data
 */
public class ImportantData {
    @DefaultValue("SUPER VAJNO")
    String nothing;

    // isn`t a constructor
    public static ImportantData of(String s) {
        ImportantData data = new ImportantData();
        data.nothing = s;
        return data;
    }
}
