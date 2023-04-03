import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        ParametersBag bag = new ParametersBag(args);
        String folderPath = bag.getPath();
        long sizeLimit = bag.getSizeLimit();
//        String folderPath = "E:/PB Download";
//        long sizeLimit = 1024 * 1024 * 50; // выводить только файлы более 50Mb

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }
}