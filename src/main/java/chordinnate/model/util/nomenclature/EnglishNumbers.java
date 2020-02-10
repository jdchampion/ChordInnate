package chordinnate.model.util.nomenclature;

import com.ibm.icu.text.RuleBasedNumberFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

@NoArgsConstructor(access = AccessLevel.NONE)
public class EnglishNumbers {

    private static final String RULE_SET =
            // This rule set shows the normal simple formatting rules for English
            "%simplified:\n"
                    // negative number rule.  This rule is used to format negative
                    // numbers.  The result of formatting the number's absolute
                    // value is placed where the >> is.
                    + "    -x: minus >>;\n"
                    // faction rule.  This rule is used for formatting numbers
                    // with fractional parts.  The result of formatting the
                    // number's integral part is substituted for the <<, and
                    // the result of formatting the number's fractional part
                    // (one digit at a time, e.g., 0.123 is "zero point one two
                    // three") replaces the >>.
                    + "    x.x: << point >>;\n"
                    // the rules for the values from 0 to 19 are simply the
                    // words for those numbers
                    + "    zero; one; two; three; four; five; six; seven; eight; nine;\n"
                    + "    ten; eleven; twelve; thirteen; fourteen; fifteen; sixteen;\n"
                    + "        seventeen; eighteen; nineteen;\n"
                    // beginning at 20, we use the >> to mark the position where
                    // the result of formatting the number's ones digit.  Thus,
                    // we only need a new rule at every multiple of 10.  Text in
                    // backets is omitted if the value being formatted is an
                    // even multiple of 10.
                    + "    20: twenty[->>];\n"
                    + "    30: thirty[->>];\n"
                    + "    40: forty[->>];\n"
                    + "    50: fifty[->>];\n"
                    + "    60: sixty[->>];\n"
                    + "    70: seventy[->>];\n"
                    + "    80: eighty[->>];\n"
                    + "    90: ninety[->>];\n"
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
                    + "    100: << hundred[ >>];\n"
                    // The rules for the higher numbers work the same way as the
                    // rule for 100: Again, the << and >> tokens depend on the
                    // rule's divisor, which for all these rules is also the rule's
                    // base value.  To group by thousand, we simply don't have any
                    // rules between 1,000 and 1,000,000.
                    + "    1000: << thousand[ >>];\n"
                    + "    1,000,000: << million[ >>];\n"
                    + "    1,000,000,000: << billion[ >>];\n"
                    + "    1,000,000,000,000: << trillion[ >>];\n"
                    // overflow rule.  This rule specifies that values of a
                    // quadrillion or more are shown in numerals rather than words.
                    // The == token means to format (with new rules) the value
                    // being formatted by this rule and place the result where
                    // the == is.  The #,##0 inside the == signs is a
                    // DecimalFormat pattern.  It specifies that the value should
                    // be formatted with a DecimalFormat object, and that it
                    // should be formatted with no decimal places, at least one
                    // digit, and a thousands separator.
                    + "    1,000,000,000,000,000: =#,##0=;\n"

                    // This rule set formats numbers between 1,000 and 9,999 somewhat
                    // differently: If the hundreds digit is not zero, the first two
                    // digits are treated as a number of hundreds.  For example, 2,197
                    // would come out as "twenty-one hundred ninety-seven."
                    + "%alt-teens:\n"
                    // just use %simplified to format values below 1,000
                    + "    =%simplified=;\n"
                    // values between 1,000 and 9,999 are delegated to %%alt-hundreds
                    // for formatting.  The > after "1000" decreases the exponent
                    // of the rule's radix by one, causing the rule's divisor
                    // to be 100 instead of 1,000.  This causes the first TWO
                    // digits of the number, instead of just the first digit,
                    // to be sent to %%alt-hundreds
                    + "    1000>: <%%alt-hundreds<[ >>];\n"
                    // for values of 10,000 and more, we again just use %simplified
                    + "    10,000: =%simplified=;\n"
                    // This rule set uses some obscure voodoo of the description language
                    // to format the first two digits of a value in the thousands.
                    // The rule at 10 formats the first two digits as a multiple of 1,000
                    // and the rule at 11 formats the first two digits as a multiple of
                    // 100.  This works because of something known as the "rollback rule":
                    // if the rule applicable to the value being formatted has two
                    // substitutions, the value being formatted is an even multiple of
                    // the rule's divisor, and the rule's base value ISN'T an even multiple
                    // if the rule's divisor, then the rule that precedes this one in the
                    // list is used instead.  (The [] notation is implemented internally
                    // using this notation: a rule containing [] is split into two rules,
                    // and the right one is chosen using the rollback rule.) In this case,
                    // it means that if the first two digits are an even multiple of 10,
                    // they're formatted with the 10 rule (containing "thousand"), and if
                    // they're not, they're formatted with the 11 rule (containing
                    // "hundred").  %%empty is a hack to cause the rollback rule to be
                    // invoked: it makes the 11 rule have two substitutions, even though
                    // the second substitution (calling %%empty) doesn't actually do
                    // anything.
                    + "%%alt-hundreds:\n"
                    + "    0: SHOULD NEVER GET HERE!;\n"
                    + "    10: <%simplified< thousand;\n"
                    + "    11: =%simplified= hundred>%%empty>;\n"
                    + "%%empty:\n"
                    + "    0:;"

                    // this rule set is the same as %simplified, except that it formats
                    // the value as an ordinal number: 234 is formatted as "two hundred
                    // thirty-fourth".  Notice the calls to ^simplified: we have to
                    // call %simplified to avoid getting "second hundred thirty-fourth."
                    + "%ordinal:\n"
                    + "    zeroth; first; second; third; fourth; fifth; sixth; seventh;\n"
                    + "        eighth; ninth;\n"
                    + "    tenth; eleventh; twelfth; thirteenth; fourteenth;\n"
                    + "        fifteenth; sixteenth; seventeenth; eighteenth;\n"
                    + "        nineteenth;\n"
                    + "    twentieth; twenty->>;\n"
                    + "    30: thirtieth; thirty->>;\n"
                    + "    40: fortieth; forty->>;\n"
                    + "    50: fiftieth; fifty->>;\n"
                    + "    60: sixtieth; sixty->>;\n"
                    + "    70: seventieth; seventy->>;\n"
                    + "    80: eightieth; eighty->>;\n"
                    + "    90: ninetieth; ninety->>;\n"
                    + "    100: <%simplified< hundredth; <%simplified< hundred >>;\n"
                    + "    1000: <%simplified< thousandth; <%simplified< thousand >>;\n"
                    + "    1,000,000: <%simplified< millionth; <%simplified< million >>;\n"
                    + "    1,000,000,000: <%simplified< billionth;\n"
                    + "        <%simplified< billion >>;\n"
                    + "    1,000,000,000,000: <%simplified< trillionth;\n"
                    + "        <%simplified< trillion >>;\n"
                    + "    1,000,000,000,000,000: =#,##0=;"

                    // %default is a more elaborate form of %simplified;  It is basically
                    // the same, except that it introduces "and" before the ones digit
                    // when appropriate (basically, between the tens and ones digits) and
                    // separates the thousands groups with commas in values over 100,000.
                    + "%default:\n"
                    // negative-number and fraction rules.  These are the same
                    // as those for %simplified, but ave to be stated here too
                    // because this is an entry point
                    + "    -x: minus >>;\n"
                    + "    x.x: << point >>;\n"
                    // just use %simplified for values below 100
                    + "    =%simplified=;\n"
                    // for values from 100 to 9,999 use %%and to decide whether or
                    // not to interpose the "and"
                    + "    100: << hundred[ >%%and>];\n"
                    + "    1000: << thousand[ >%%and>];\n"
                    // for values of 100,000 and up, use %%commas to interpose the
                    // commas in the right places (and also to interpose the "and")
                    + "    100,000>>: << thousand[>%%commas>];\n"
                    + "    1,000,000: << million[>%%commas>];\n"
                    + "    1,000,000,000: << billion[>%%commas>];\n"
                    + "    1,000,000,000,000: << trillion[>%%commas>];\n"
                    + "    1,000,000,000,000,000: =#,##0=;\n"
                    // if the value passed to this rule set is greater than 100, don't
                    // add the "and"; if it's less than 100, add "and" before the last
                    // digits
                    + "%%and:\n"
                    + "    and =%default=;\n"
                    + "    100: =%default=;\n"
                    // this rule set is used to place the commas
                    + "%%commas:\n"
                    // for values below 100, add "and" (the apostrophe at the
                    // beginning is ignored, but causes the space that follows it
                    // to be significant: this is necessary because the rules
                    // calling %%commas don't put a space before it)
                    + "    ' and =%default=;\n"
                    // put a comma after the thousands (or whatever preceded the
                    // hundreds)
                    + "    100: , =%default=;\n"
                    // put a comma after the millions (or whatever precedes the
                    // thousands)
                    + "    1000: , <%default< thousand, >%default>;\n"
                    // and so on...
                    + "    1,000,000: , =%default=;"
                    // %%lenient-parse isn't really a set of number formatting rules;
                    // it's a set of collation rules.  Lenient-parse mode uses a Collator
                    // object to compare fragments of the text being parsed to the text
                    // in the rules, allowing more leeway in the matching text.  This set
                    // of rules tells the formatter to ignore commas when parsing (it
                    // already ignores spaces, which is why we refer to the space; it also
                    // ignores hyphens, making "twenty one" and "twenty-one" parse
                    // identically)
                    + "%%lenient-parse:\n"
                    + "    & ' ' , ',' ;\n";

    private static RuleBasedNumberFormat FORMAT = new RuleBasedNumberFormat(RULE_SET);

    public static Number parse(@NotNull String text) throws ParseException {
        return FORMAT.parse(text);
    }

    public static String spellOut(@NotNull Number item) throws IllegalArgumentException {
        return FORMAT.format(item);
    }

    public static String ordinal(@NotNull Number item) throws IllegalArgumentException {
        return FORMAT.format(item.longValue(), "%ordinal");
    }

}
