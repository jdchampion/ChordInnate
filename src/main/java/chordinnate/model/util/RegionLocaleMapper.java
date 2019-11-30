package chordinnate.model.util;

import com.ibm.icu.util.Region;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RegionLocaleMapper {

    private static final Map<String, String> CODE_TO_NAME = Collections.unmodifiableMap(initMap1());

    private static final Map<String, String> NAME_TO_CODE = Collections.unmodifiableMap(initMap2());

    private static Map<String, String> initMap1() {
        Map<String, String> map = new HashMap<>();

        // World
        map.put("001", "World");

        // Continent
        map.put("002", "Africa");
        map.put("009", "Oceania");
        map.put("019", "Americas");
        map.put("142", "Asia");
        map.put("150", "Europe");

        // Subcontinent
        map.put("005", "South America");
        map.put("011", "Western Africa");
        map.put("013", "Central America");
        map.put("014", "Eastern Africa");
        map.put("015", "Northern Africa");
        map.put("017", "Middle Africa");
        map.put("018", "Southern Africa");
        map.put("021", "Northern America");
        map.put("029", "Caribbean");
        map.put("030", "Eastern Asia");
        map.put("034", "Southern Asia");
        map.put("035", "South-eastern Asia");
        map.put("039", "Southern Europe");
        map.put("053", "Australia & New Zealand");
        map.put("054", "Melanesia");
        map.put("057", "Micronesia");
        map.put("061", "Polynesia");
        map.put("143", "Central Asia");
        map.put("145", "Western Asia");
        map.put("151", "Eastern Europe");
        map.put("154", "Nothern Europe");
        map.put("155", "Western Europe");
        map.put("QO", "Outlying Oceania");

        // Territory
//        map.put("AC", "");
        map.put("AQ", "Antarctica");
        map.put("BV", "Bouvet Island");
//        map.put("CP", "");
        map.put("GS", "South Georgia and the South Sandwich Islands");
        map.put("HM", "Heard Island & McDonald Islands");
//        map.put("TA", "");

        // Countries supported by Locale (most items in this map)...
        Arrays.stream(Locale.getAvailableLocales())
                .forEach(locale -> {
                    map.put(locale.getCountry(), locale.getDisplayCountry());
                });

        // Grouping
        map.put("003", "North America");
        map.put("202", "Sub-Saharan Africa");
        map.put("419", "Latin America");
        map.put("EU", "European Union");
//        map.put("EZ", "");
//        map.put("UN", "");

        // Deprecated
        map.put("062", "South-Central Asia");
        map.put("172", "Commonwealth of Independent States");
        map.put("200", "Czechoslovakia");
//        map.put("530", "");
        map.put("532", "Netherlands Antilles");
//        map.put("536", "");
        map.put("582", "Pacific Islands (Trust Territory)");
//        map.put("810", "");
        map.put("830", "Channel Islands");
        map.put("890", "Socialist Federal Republic of Yugoslavia");
//        map.put("891", "");
//        map.put("958", "");
//        map.put("959", "");
//        map.put("960", "");
//        map.put("962", "");
//        map.put("963", "");
//        map.put("964", "");
//        map.put("965", "");
//        map.put("966", "");
//        map.put("968", "");
//        map.put("969", "");
//        map.put("970", "");
//        map.put("971", "");
//        map.put("972", "");
//        map.put("973", "");
//        map.put("974", "");
//        map.put("975", "");
//        map.put("976", "");
//        map.put("977", "");
//        map.put("978", "");
//        map.put("979", "");
//        map.put("980", "");
//        map.put("981", "");
//        map.put("982", "");
//        map.put("983", "");
//        map.put("984", "");
//        map.put("985", "");
//        map.put("986", "");
//        map.put("987", "");
//        map.put("988", "");
//        map.put("989", "");
//        map.put("990", "");
//        map.put("991", "");
//        map.put("992", "");
//        map.put("993", "");
//        map.put("994", "");
//        map.put("995", "");
//        map.put("996", "");
//        map.put("997", "");
//        map.put("998", "");
//        map.put("AAA", "");
        map.put("AN", "Netherlands Antilles");
//        map.put("ANT", "");
        map.put("CS", "Serbia and Montenegro");
//        map.put("FQ", "");
        map.put("NT", "Neutral Zone");
//        map.put("NTZ", "");
//        map.put("PC", "");
//        map.put("QMM", "");
//        map.put("QNN", "");
//        map.put("QPP", "");
//        map.put("QQQ", "");
//        map.put("QRR", "");
//        map.put("QSS", "");
//        map.put("QTT", "");
//        map.put("QVV", "");
//        map.put("QWW", "");
//        map.put("QXX", "");
//        map.put("QYY", "");
//        map.put("QZZ", "");
//        map.put("SCG", "");
        map.put("SU", "Union of Soviet Socialist Republics");
//        map.put("SUN", "");
//        map.put("XAA", "");
//        map.put("XBB", "");
//        map.put("XCC", "");
//        map.put("XDD", "");
//        map.put("XEE", "");
//        map.put("XFF", "");
//        map.put("XGG", "");
//        map.put("XHH", "");
//        map.put("XII", "");
//        map.put("XJJ", "");
//        map.put("XLL", "");
//        map.put("XMM", "");
//        map.put("XNN", "");
//        map.put("XOO", "");
//        map.put("XPP", "");
//        map.put("XQQ", "");
//        map.put("XRR", "");
//        map.put("XSS", "");
//        map.put("XTT", "");
//        map.put("XUU", "");
//        map.put("XVV", "");
//        map.put("XWW", "");
//        map.put("XXX", "");
//        map.put("XYY", "");
//        map.put("XZZ", "");
        map.put("YU", "Serbia and Montenegro");
//        map.put("YUG", "");

        // Unknown
        map.put("ZZ", "Unknown");

        return map;
    }

    private static Map<String, String> initMap2() {
        Map<String, String> map = new TreeMap<>();

        for (Map.Entry<String, String> entry : CODE_TO_NAME.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        return map;
    }

    public static String displayName(@NotNull Region region) {
        return CODE_TO_NAME.getOrDefault(region.toString(), "Unknown");
    }

    public static String displayName(@NotNull Locale locale) {
        return CODE_TO_NAME.getOrDefault(locale.getCountry(), "Unknown");
    }

    @Nullable
    public static Region regionWithDisplayName(@NotNull String displayName) {
        String code = NAME_TO_CODE.get(displayName);

        if (code == null) {
            return null;
        }

        return Region.getInstance(code);
    }

    public static Set<Region> regionsWithDisplayNameContaining(@NotNull String displayName) {
        return NAME_TO_CODE.entrySet().stream()
                .filter(es -> es.getKey().contains(displayName))
                .map(es -> Region.getInstance(es.getValue()))
                .collect(Collectors.toSet());
    }

    public static Set<Region> regionsWithDisplayNameMatching(@NotNull String regex) {
        return NAME_TO_CODE.entrySet().stream()
                .filter(es -> es.getKey().matches(regex))
                .map(es -> Region.getInstance(es.getValue()))
                .collect(Collectors.toSet());
    }

}
