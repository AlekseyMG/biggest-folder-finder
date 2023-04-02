import java.util.ArrayList;
import java.io.File;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }
    public long getFolderSize(File folder) {
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
    public File getFolder() {
        return folder;
    }
    public void addChild(Node node) {
        node.setLevel(level + 1);
        children.add(node);
    }
    private void setLevel(int level) {
        this.level = level;
    }
    public ArrayList<Node> getChildren() {
        return children;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children) {
            builder.append("| ".repeat(level) + child.toString());
        }
        return builder.toString();
    }
}
