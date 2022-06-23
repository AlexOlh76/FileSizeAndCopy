import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "D:/Мои документы/Доки по обучению JAVA";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        Long size = pool.invoke(calculator);
        System.out.println(size);

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }
}
