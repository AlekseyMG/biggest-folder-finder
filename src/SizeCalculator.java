import java.util.HashMap;

public class SizeCalculator {
    private static char[] multipliers = {'B', 'K', 'M', 'G', 'T', 'P'};
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
                multipliers[multipliers.length - 1] +
                (i > 0 ? "b" : "");
    }
    public static long getSizeFromHumanReadable(String size) {
        //HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return length;
    }
    private static HashMap<Character, Integer> getMultipliers() {
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(multipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}
