package com.wizeek.task1;

/**
 * Created by Artur on 6/4/2018.
 *
 * Task: given two Strings find out if the second one can be constructed from the first one with no more than 3 moves,
 * where 1 move is either deletion of a character or a change for another character.
 * Additions are not allowed.
 * Return of the method is boolean.
 *
 * Example:
 * team -> tream: false (no addition is allowed)
 * tream -> team: true (1 deletion)
 * team -> teem: true (1 change)
 * team -> t: true (3 deletions)
 * team -> r: false
 */
public class WordsTransform {
    boolean convertable(String word1, String word2) {
        if (word2.length() > word1.length()) {
            return false;
        }
        if (word1.length() - word2.length() > 3) {
            return false;
        }
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int changes = recursion(c1, c2, 0, 0, 0);

        return changes < 4;
    }

    Integer recursion(char[] c1, char[] c2, int i, int j, int changes) {
        while(i != c1.length && j != c2.length && c1[i] == c2[j]) {
            i++;
            j++;
        }
        if (j != c2.length) {
            changes++;
            if (i != c1.length) {
                i++;
                int r1 = recursion(c1, c2, i, j, changes);
                int r2 = recursion(c1, c2, i, ++j, changes);
                return Math.min(r1, r2);
            }
        }
        return changes;
    }
}
