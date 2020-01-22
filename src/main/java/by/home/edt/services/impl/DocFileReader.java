package by.home.edt.services.impl;

import by.home.edt.services.interfaces.IFileReader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DocFileReader implements IFileReader {

    public List<String> readFile(FileInputStream inputStream) {
        try {
            XWPFDocument document = new XWPFDocument(OPCPackage.open(inputStream));
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            final String textString = extractor.getText();
            return Arrays.stream(textString.substring(textString.indexOf('\n') + 1).split("\t"))
                    .map(string -> string.replaceAll("\n","").trim())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("ALARM!! EXCEPTION");
        }
        return null;
    }
}
