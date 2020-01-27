package by.home.edt;

import by.home.edt.classes.Words;
import by.home.edt.services.impl.DocFileReader;
import by.home.edt.services.impl.DocFileWriter;
import by.home.edt.services.impl.FileTranslator;
import by.home.edt.utils.FileUtils;

import java.io.FileInputStream;
import java.util.List;

public class Main {
    private static final String inputFilePath = "D:\\Prog\\TEMP\\EDT\\inputDocHere\\The Wire 102.docx";
    private static final String outputFilePath = "D:\\Prog\\TEMP\\EDT\\outputDocs\\The Wire 102 - out.docx";

    public static void main(String[] args) {
        final DocFileReader docFileReader = new DocFileReader();
        final FileTranslator fileTranslator = new FileTranslator();

        FileInputStream inputStream = FileUtils.getFileStream(inputFilePath);
        final List<String> stringsWithNumbers = docFileReader.readFile(inputStream);
        List<String> translatedStrings = fileTranslator.translate(stringsWithNumbers);
        DocFileWriter fileWriter = new DocFileWriter();
        Words words = new Words("Test title", translatedStrings);
        fileWriter.writeFile(words, outputFilePath);
    }
}
