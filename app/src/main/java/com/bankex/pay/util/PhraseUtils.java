package com.bankex.pay.util;

import com.plumillonforge.android.chipview.Chip;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Denis Anisimov.
 */
public class PhraseUtils {

    public static List<Chip> getChips(String text) {
        List<Chip> words = new ArrayList<Chip>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        int position = 0;
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String substring = text.substring(firstIndex, lastIndex);
                words.add(new Tag(substring, position));
                position++;
            }

        }
        return words;
    }

    public static class Tag implements Chip {
        private String mName;
        private int mType = 0;
        private int mPosition = 0;

        public void setType(int type) {
            mType = type;
        }

        public Tag(String name, int type, int position) {
            this(name);
            mType = type;
            mPosition = position;
        }

        public Tag(String name, int position) {
            mName = name;
            mPosition = position;
        }

        public Tag(String name) {
            mName = name;
        }

        public int getPosition() {
            return mPosition;
        }

        @Override
        public String getText() {
            return mName;
        }

        public int getType() {
            return mType;
        }
    }
}
