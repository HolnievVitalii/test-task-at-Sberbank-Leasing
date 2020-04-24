import java.util.*;

/**
 * @author Holniev Vitalii.
 * <p>
 * There is a line consisting of words. All words in it are separated by a single space.
 * You need to convert the string to a data structure that groups words by the first letter in the word.
 * Then print only groups containing more than one element.
 * Groups must be sorted alphabetically. Words within a group must be sorted in descending order of characters;
 * if the number of characters is equal, then sort in alphabetical order.
 * Example line: “boot shed watermelon bolt boxing exchange” (Пример строки: «сапог сарай арбуз болт бокс биржа»
 * Отсортированная строка: [б=[биржа, бокс, болт], c=[caпог, сарай]])
 * Sorted string: [b = [exchange, boxing, bolt], c = [boot, shed]]
 */
public class Grouping {
    private final static String testString = "сапог cарай арбуз болт бокс биржа";
    public String sourceString;
    private final Map<String, List<String>> words = new TreeMap<>();

    public Grouping(String source) {
        sourceString = source;
        if (source == null || source.isEmpty()) return;
        Arrays.stream(source.trim().split("\\PL+"))
                .map(String::toLowerCase)
                .distinct()
                .forEach(s -> {
                    String letter = s.substring(0, 1);
                    if (!words.containsKey(letter)) {
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        words.put(letter, list);
                    } else {
                        words.get(letter).add(s);
                    }
                });
    }

    public static void main(String[] args) {
        String source = testString;
        Grouping grouping = new Grouping(source);
        System.out.println("Source string -> " + grouping.sourceString);
        System.out.println("Grouped and Sorted string - > " + grouping.toString());
        System.out.println("Groups containing more than one element -> " + grouping.moreThanOneItem());
    }


    /**
     * source string sorting method
     *
     * @param source for sorting
     * @return list of sorted data
     */
    private List<String> sortTheSourceString(List<String> source) {
        List<String> result = new ArrayList<>();
        result.addAll(source);
        result.sort((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            } else {
                return s2.length() - s1.length();
            }

        });
        return result;
    }

    /**
     * only groups containing more than one item are displayed
     *
     * @return group string
     */
    public String moreThanOneItem() {
        Map<String, List<String>> result = new TreeMap<>();
        words.forEach((k, v) -> {
            if (v.size() > 1) {
                result.put(k, sortTheSourceString(v));
            }
        });
        return result.toString();
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
