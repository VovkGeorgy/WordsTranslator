package by.home.edt;

import by.home.edt.classes.Words;
import by.home.edt.services.impl.DocFileReader;
import by.home.edt.services.impl.DocFileWriter;
import by.home.edt.services.impl.FileTranslator;
import by.home.edt.utils.FileReadUtils;
import by.home.edt.utils.PropertiesService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(final String[] args) throws IOException {
        final DocFileReader docFileReader = new DocFileReader();
        final FileTranslator fileTranslator = new FileTranslator();
        final DocFileWriter fileWriter = new DocFileWriter();

        final Properties props = PropertiesService.getProperties();
        final String inputFolderPath = props.getProperty("input.folder.path");
        final String outputFolderPath = props.getProperty("output.folder.path");
        final String[] fileExtension = {props.getProperty("file.extension")};
        final int maxReadFiles = Integer.parseInt(props.getProperty("max.read.files"));

        final List<File> fileList = FileReadUtils.getFilesByExtensions(inputFolderPath, fileExtension, maxReadFiles);
        for (File file : fileList) {
            System.out.println("Reading file - " + file.getName());
            List<String> stringsWithNumbers;
            try (FileInputStream inputStream = new FileInputStream(file)) {
                stringsWithNumbers = docFileReader.readFile(inputStream);
            }
            final List<String> translatedStrings = fileTranslator.translate(stringsWithNumbers);
            final Words words = new Words("Test title", translatedStrings);

            final String fileName = file.getName().replace(".docx", " - out.docx");
            final String filePath = outputFolderPath + fileName;

            fileWriter.writeFile(words, filePath);
        }
    }
}
