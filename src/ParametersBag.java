import java.io.File;

public class ParametersBag {
    private long sizeLimit;
    private String path;
    public ParametersBag(String args[]) {

        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Укажите два параметра: -l (лимит по объему) и -d (путь к папке)\n" +
                            "К примеру: \"BiggetFolderFinder.jar -l 200M -d C:/Users\"" +
                            " чтобы вывести папки более 200 мегабайт");
        }
        sizeLimit = 0;
        path = "";
        for (int i = 0; i < 4; i++) {
            if (args[i].equals("-l")) {
                sizeLimit = SizeCalculator.getSizeFromHumanReadable(args[i + 1]);
            } else if (args[i].equals("-d")) {
                path = args[i + 1];
            }
        }
        if (sizeLimit <= 0) {
            throw new IllegalArgumentException("Лимит не указан или указан не верно");
        }
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Путь к папке не указан или указан не верно");
        }
    }
    public long getSizeLimit() {
        return sizeLimit;
    }
    public String getPath() {
        return path;
    }
}
