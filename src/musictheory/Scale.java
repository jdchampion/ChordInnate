package musictheory;

/**
 * Created by Joseph on 1/1/16.
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;

    Scale(Note root, ScaleType scaleType) {
        this.root = root;
        this.scaleType = scaleType;
    }

    public String getName() {
        return root.getName() + " " + scaleType.name;
    }

    public Note getRoot() {
        return root;
    }
}
