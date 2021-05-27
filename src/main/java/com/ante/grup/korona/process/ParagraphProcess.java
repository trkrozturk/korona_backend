package com.ante.grup.korona.process;

import com.ante.grup.korona.constants.Cities;
import com.ante.grup.korona.constants.ParsePatterns;
import com.ante.grup.korona.constants.NumericKeywords;
import com.ante.grup.korona.model.KoronaData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphProcess {
    String SEHIR = "sehir";
    String TARIH = "tarih";

    public KoronaData getKoronaDataObj(String koronaParagraph) {
        Map<String, Object> koronaObjMap = new HashMap<>();
        String date = setDate(koronaParagraph, koronaObjMap);
        setCity(koronaParagraph, koronaObjMap);
        koronaParagraph = removeDateInformationFromParagraph(koronaParagraph, date);
        List<String> sentencesInParagraph = Arrays.asList(koronaParagraph.split("\\."));
        setNumericInformations(koronaObjMap, sentencesInParagraph);
        return transformMapToKoronaDataObj(koronaObjMap);
    }

    private String removeDateInformationFromParagraph(String koronaParagraph, String date) {
        return date==null?koronaParagraph:koronaParagraph.replace(date, "");
    }

    private String setDate(String koronaParagraph, Map<String, Object> koronaObjMap) {
        String date = getDate(koronaParagraph);
        koronaObjMap.put(TARIH, date);
        return date;
    }
    public String getDate(String paragraph) {
        Matcher matcher = ParsePatterns.DATE_PATTERN.matcher(paragraph);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
    private void setCity(String koronaParagraph, Map<String, Object> koronaObjMap) {
        Optional<String> city = getCity(koronaParagraph);
        city.ifPresent(cityName -> {
            koronaObjMap.put(SEHIR, cityName);
        });
    }

    private void setNumericInformations(Map<String, Object> koronaObjMap, List<String> sentencesInParagraph) {
        sentencesInParagraph.forEach(sentence -> {
            Optional<NumericKeywords> informationSentence = Arrays.stream(NumericKeywords.values()).filter(sentenceKeywords ->
                    Pattern.compile(Pattern.quote(sentenceKeywords.getKeyword()), Pattern.CASE_INSENSITIVE).matcher(sentence).find()
            ).findFirst();

            if (informationSentence.isPresent()) {
                int value = getNumericValue(sentence);
                koronaObjMap.put(informationSentence.get().getKeyword(), value);
            }
        });
    }

    private KoronaData transformMapToKoronaDataObj(Map<String, Object> koronaObjMap) {
        Gson gson = new Gson();
        for (String key : koronaObjMap.keySet()) {
            if (koronaObjMap.get(key)==null) {
                return null;
            }
        }
        JsonElement jsonElement = gson.toJsonTree(koronaObjMap);
        return gson.fromJson(jsonElement, KoronaData.class);
    }

    private int getNumericValue(String sentence) {
        Matcher matcher = ParsePatterns.DIGIT_PATTERN.matcher(sentence);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        }
        return 0;
    }

    private Optional<String> getCity(String koronaSentence) {
        return Cities.cities.stream().filter(koronaSentence::contains).findFirst();
    }
}
