package com.epam.mjc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringJoiner regex = new StringJoiner("|");
        for (String delimiter : delimiters) {
            regex.add(delimiter);
        }
        return Arrays.asList(source.split(regex.toString()));
    }
}
