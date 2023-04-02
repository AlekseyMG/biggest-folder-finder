import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        String folderPath = "E:/PB Download";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable("235K"));
        System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(long size) {
        int i = 0;
        String[] multipliers = {"B", "Kb", "Mb", "Gb", "Tb", "Pb"};
        while (true) {
            if (size / 1024 == 0) {
                break;
            }
            i++;
            size = Math.round((double) size / 1024);
        }
        return String.valueOf(size) + multipliers[i];
    }

    public static long getSizeFromHumanReadable(String size) {
        HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));
        return length;
    }
    private static HashMap<Character, Integer> getMultipliers() {
        char[] multipliers = {'B', 'K', 'M', 'G', 'T', 'P'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(multipliers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}