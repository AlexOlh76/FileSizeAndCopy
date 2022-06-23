import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

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

    //TODO: 24B, 24Kb, 36Mb, 34Gb, 42Tb
    public static String getHumanReadableSize(long size) {
        for(int i = 0; i < sizeMultipliers.length; i++) {
            double value = size / Math.pow(1024, i);
            if(value<1024) {
                return  Math.round(value) + "" +
                        sizeMultipliers[i] +
                        (i>0 ? "b" : "");
            }
        }
        return "Very big";
    }

    //TODO: 24B, 24Kb, 36Mb, 34Gb, 42Tb
    // 24B, 24Kb, 36Mb, 34Gb, 42Tb
    // 235 => 240640
    public static long getSizeFromHumanReadable(String size){
        HashMap<Character, Integer> char2multiplier = getSizeMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+","")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", "")
        );
        return length;
    }

    private static HashMap<Character, Integer> getSizeMultipliers() {

        HashMap<Character,Integer> char2multiplier = new HashMap<>();
        for(int i = 0; i< sizeMultipliers.length; i++) {
            char2multiplier.put(
                sizeMultipliers[i],
                (int) Math.pow(1024,i)
            );
        }
        return char2multiplier;
    }
}


