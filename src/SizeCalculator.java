import java.util.HashMap;

public class SizeCalculator {
    private static char[] multipliers = {'B', 'K', 'M', 'G', 'T', 'P'};
    private static char[] rusMultipliers = {'Б', 'К', 'М', 'Г', 'Т', 'П'};
    private static HashMap<Character, Integer> char2multiplier = getMultipliers();
    public static String getHumanReadableSize(long size) {
        int i = 0;
        double value = 0;
        for (; i < multipliers.length; i++) {
            value = size / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100) / 100. + "" +
                        multipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return Math.round(value * 100) / 100 + "" +
                multipliers[multipliers.length - 1];
    }
    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = 'B';
        if (!size.replaceAll("[0-9\\s+]*[^BKMGTPБКМГТП]*", "").isEmpty()) {
            sizeFactor = size
                    .replaceAll("[0-9\\s+]+", "")
                    .charAt(0);
        }
        int multiplier = char2multiplier.get(sizeFactor);
        long length = 1;
        if (!size.replaceAll("[^0-9\\s+]", "").isEmpty()) {
            length = multiplier * Long.parseLong(size.replaceAll("[^0-9]", ""));
        }
        return length;
    }
    private static HashMap<Character, Integer> getMultipliers() {
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(multipliers[i], (int) Math.pow(1024, i));
        }
        for (int i = 0; i < rusMultipliers.length; i++) {
            char2multiplier.put(rusMultipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}
