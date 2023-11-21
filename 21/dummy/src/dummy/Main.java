
package dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jose
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Getting optional from data source");
        List<Optional<String>> result = getOptionalList();
        result.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
        System.out.println("Getting data from data source");
        List<String> result2 = getObjectList();
        result2.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
        System.out.println("Getting data from data source and converting to optionals");
        List<String> result3 = getObjectList();
        List<Optional<String>> result4 = result3.stream()
                .map(Optional::ofNullable)
                .toList();
        result4.forEach(System.out::println);
    }
    
    private static List<Optional<String>> getOptionalList() {
        List<Optional<String>> data = new ArrayList<>();
        data.add(Optional.of("hello"));
        data.add(Optional.ofNullable(null));
        data.add(Optional.of("everybody"));
        data.add(Optional.of("goodbye"));
        data.add(Optional.ofNullable(null));
        data.add(Optional.of("good morning vietnam"));
        return data;
    }
    
    private static List<String> getObjectList() {
        List<String> data = new ArrayList<>();
        data.add("hello");
        data.add(null);
        data.add("everybody");
        data.add("goodbye");
        data.add(null);
        data.add("good morning vietnam");
        return data;
    }
}
