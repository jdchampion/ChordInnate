package chordinnate.util.nomenclature;

import com.ibm.icu.text.RuleBasedNumberFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

/**
 *
 * @see <a href=http://phrontistery.info/numbers.html>1</a>
 * @see <a href=https://www.georgehart.com/virtual-polyhedra/naming.html>2</a>
 *
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class GreekGrouping {

    private static final String RULE_SET =
            // This rule set shows the normal simple formatting rules for Greek grouping
            "%grouped:\n"
                    // negative numbers are omitted.
                    + "    -x: ;\n"
                    // fractions are omitted.
                    + "    x.x: ;\n"
                    // zero is omitted.
                    + "    0: ;\n"
                    // the rules for the values from 1 to 19 are simply the
                    // words for those numbers
                    + "    mon; di; tri; tetr; pent; hex; hept; oct; enne;\n"
                    + "    dec; hendec; dodec; triskaidec; tetrakaidec; pentakaidec; hexakaidec;\n"
                    + "        heptakaidec; octakaidec; enneakaidec; \n"
                    // beginning at 20, we use the >> to mark the position where
                    // the result of formatting the number's ones digit.  Thus,
                    // we only need a new rule at every multiple of 10.  Text in
                    // brackets is omitted if the value being formatted is an
                    // even multiple of 10.
                    + "    20: icos[i>>];\n"
                    + "    30: tricont[a>>];\n"
                    + "    40: tetracont[a>>];\n"
                    + "    50: pentacont[a>>];\n"
                    + "    60: hexacont[a>>];\n"
                    + "    70: heptacont[a>>];\n"
                    + "    80: octacont[a>>];\n"
                    + "    90: enneacont[a>>];\n"
                    // beginning at 100, we can use << to mark the position where
                    // the result of formatting the multiple of 100 is to be
                    // inserted.  Notice also that the meaning of >> has shifted:
                    // here, it refers to both the ones place and the tens place.
                    // The meanings of the << and >> tokens depend on the base value
                    // of the rule.  A rule's divisor is (usually) the highest
                    // power of 10 that is less than or equal to the rule's base
                    // value.  The value being formatted is divided by the rule's
                    // divisor, and the integral quotient is used to get the text
                    // for <<, while the remainder is used to produce the text
                    // for >>.  Again, text in brackets is omitted if the value
                    // being formatted is an even multiple of the rule's divisor
                    // (in this case, an even multiple of 100)
                    + "    100: hect[o>>];\n"
                    + "    200: <<hect[o>>];\n"
                    + "    400: <<ahect[o>>];\n"
                    // The rules for the higher numbers work the same way as the
                    // rule for 400: Again, the << and >> tokens depend on the
                    // rule's divisor, which for all these rules is also the rule's
                    // base value.  To group by thousand, we simply don't have any
                    // rules between 1,000 and 1,000,000.
                    + "    1000: chili[a>>];\n"
                    + "    2000: <<chili[a>>];\n"
                    + "    4000: <<achili[a>>];\n"
                    // overflow rule.  This rule specifies that values of a
                    // million or more are shown in numerals rather than words.
                    // The == token means to format (with new rules) the value
                    // being formatted by this rule and place the result where
                    // the == is.  The #,##0 inside the == signs is a
                    // DecimalFormat pattern.  It specifies that the value should
                    // be formatted with a DecimalFormat object, and that it
                    // should be formatted with no decimal places, at least one
                    // digit, and a thousands separator.
                    + "    1,000,000: =#,##0=;\n";


    private static RuleBasedNumberFormat FORMAT = new RuleBasedNumberFormat(RULE_SET);

    public static Number parse(@NotNull String text) throws ParseException {
        return FORMAT.parse(text);
    }

    public static String grouping(@NotNull Number item) throws IllegalArgumentException {
        return FORMAT.format(item);
    }

}
