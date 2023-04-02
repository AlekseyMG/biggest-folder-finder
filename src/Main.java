import java.util.concurrent.ForkJoinPool;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        String folderPath = "E:/PB Download";
        File file = new File(folderPath);
        Node root = new Node(file);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }
}