import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        String folderPath = "E:/Soft";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable("1000M"));
        //System.out.println(getFolderSize(file));

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
        if (size / 1024 == 0 )
            return String.valueOf(size) + "B";
        size = size / 1024;
        if (size / 1024 == 0 )
            return String.valueOf(size) + "Kb";
        size = size / 1024;
        if (size / 1024 == 0 )
            return String.valueOf(size) + "Mb";
        size = size / 1024;
        if (size / 1024 == 0 )
            return String.valueOf(size) + "Gb";
        size = size / 1024;
        if (size / 1024 == 0 )
            return String.valueOf(size) + "Tb";
        size = size / 1024;
        return String.valueOf(size) + "Pb";
    }

    public static long getSizeFromHumanReadable(String size) {
        if (size.contains("B"))
            return Long.parseLong(size.replaceAll("[^0-9]*", ""));
        if (size.contains("K"))
            return Long.parseLong(size.replaceAll("[^0-9]*", "")) * 1024;
        if (size.contains("M"))
            return Long.parseLong(size.replaceAll("[^0-9]*", "")) * (long) Math.pow(1024, 2);
        if (size.contains("G"))
            return Long.parseLong(size.replaceAll("[^0-9]*", "")) * (long) Math.pow(1024, 3);
        if (size.contains("T"))
            return Long.parseLong(size.replaceAll("[^0-9]*", "")) * (long) Math.pow(1024, 4);
        if (size.contains("P"))
            return Long.parseLong(size.replaceAll("[^0-9]*", "")) * (long) Math.pow(1024, 5);
        return 0;
    }
}