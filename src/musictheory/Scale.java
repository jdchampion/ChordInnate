package musictheory;

import javax.xml.bind.ValidationException;

/**
 * Created by Joseph on 1/1/16.
 */
public class Scale {
    private Note root;
    private ScaleType scaleType;

    Scale(Note root, ScaleType scaleType) throws ValidationException{
        /*
         * TODO Maybe convert the natural Notes into their non-accidental enharmonic equivalent,
         *      instead of throwing an Exception? Would reduce the number of try-catches.
         */
        if (root.isNatural()) {
            throw new ValidationException("Scale constructor called with Natural Note root.");
        }
        this.root = root;
        this.scaleType = scaleType;
    }

    public String getName() {
        return root.getName() + " " + scaleType.name;
    }

    public Note getRoot() {
        return root;
    }

    private Note getNote(int relativePitch) {

        // TODO return the correct note w.r.t. KeySignature and relativePitch
        return null;
    }
}
