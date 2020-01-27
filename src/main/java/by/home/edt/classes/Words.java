package by.home.edt.classes;

import java.util.List;

public class Words {
    private String wordsTitle;
    private List<String> words;

    public Words(String wordsTitle, List<String> words) {
        this.wordsTitle = wordsTitle;
        this.words = words;
    }

    public String getWordsTitle() {
        return wordsTitle;
    }

    public void setWordsTitle(String wordsTitle) {
        this.wordsTitle = wordsTitle;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
