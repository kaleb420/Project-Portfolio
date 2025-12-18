import java.io.*;
import java.util.*;

/**
 * Solutions for Lab 6: recursion over filesystem trees and over strings.
 * .
 * Problem 1: int diskUsage(String path)
 *   - Recursively sums the sizes (in bytes) of a file/directory subtree.
 *   - Uses java.io.File: f.length(), f.isDirectory(), and f.list().
 *   - Directories contribute only the sum of their children (not f.length()).
 * .
 * Problem 2: boolean isShrinkable(String str)
 *   - Returns true iff str can be reduced to a one-letter dictionary word
 *     by repeatedly removing one character and remaining a valid word
 *     at every intermediate step.
 *   - Loads a dictionary from a file named "words" (classpath resource).
 *   - Matching is case-insensitive.
 *   - Note: no memoization is used; the recursion explores all branches.
 */
public class RecursionExamples {

    // -------- Problem 2 support: dictionary (no memoization) --------

    private final Set<String> dictionary;

    /** Use default dictionary loader (file named "words" on the classpath). */
    public RecursionExamples() {
        this(loadDefaultDictionary());
    }

    /** Allow injection of a dictionary (useful for testing). */
    public RecursionExamples(Set<String> dictionary) {
        this.dictionary = (dictionary == null) ? Collections.emptySet() : dictionary;
    }

    // ------------------- Problem 1: disk usage ---------------------

    /**
     * Recursively compute total disk usage, in bytes, for the file/directory at {@code path}.
     * If {@code path} does not exist or cannot be listed, returns 0.
     *
     * Directories: only sum the sizes of their contents (do not add the directory's own length).
     *
     * @param path absolute or relative filesystem path
     * @return total number of bytes used by the entry and all nested contents, clamped to int range
     */
    public int diskUsage(String path) {
        File file = new File(path);
        if (path==null || !file.exists())
            return 0;
        else if (file.isFile()) {
            if (file.length() > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else
                return (int) file.length();
        }
        String[] arr=file.list();
        long sum=0;
        for (int i = 0; i < arr.length; i++) {
            File child = new File(file,arr[i]);
            sum+=diskUsage(child.getPath());
        }
        if (sum > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else
            return (int) sum;
    }

    // ---------------- Problem 2: shrinkable words ------------------

    /**
     * Return true iff {@code str} is shrinkable:
     *  - {@code str} must be a dictionary word, and
     *  - removing one character at a time can eventually reach a one-letter dictionary word,
     *    with every intermediate string also a dictionary word.
     * .
     * Matching is case-insensitive (dictionary and inputs are normalized to lowercase).
     * This implementation does NOT use memoization.
     *
     * @param str candidate word
     * @return true if shrinkable, false otherwise
     */
    public boolean isShrinkable(String str) {
        return isShrinkableRec(str);
    }

    // Recursive helper (no memoization).
    private boolean isShrinkableRec(String s) {
        if (s==null)
            return dictionary.contains(s);
        s=s.toLowerCase();
        boolean solution=false;
        if (s.length()<=1)
            return dictionary.contains(s);
        else if (!dictionary.contains(s))
            return false;
        else {
            for (int i = 0; i < s.length(); i++) {
                solution=isShrinkable(s.substring(0,i) + s.substring(i+1));
                if (solution)
                    return true;
            }
        }
        return solution;
    }

    // ---------------------- Dictionary loading ---------------------

    /**
     * Attempt to load a dictionary from a classpath resource named {@code "words"}.
     * Lines are trimmed and lowercased; empty lines are ignored.
     */
    private static Set<String> loadDefaultDictionary() {
        Set<String> set = new HashSet<>();

        InputStream in = RecursionExamples.class.getClassLoader().getResourceAsStream("words");

        try {
            if (in != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String w = line.trim().toLowerCase(Locale.ROOT);
                        if (!w.isEmpty()) set.add(w);
                    }
                }
            }
        } catch (IOException ignored) {
            // If loading fails, proceed with an empty dictionary.
        }
        return set;
    }
}
