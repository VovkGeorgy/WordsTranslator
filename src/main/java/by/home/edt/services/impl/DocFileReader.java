package by.home.edt.services.impl;

import by.home.edt.services.interfaces.IFileReader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

public class DocFileReader implements IFileReader {

    public File readFile(FileInputStream inputStream) {
        try {
            XWPFDocument document = new XWPFDocument(OPCPackage.open(inputStream));

//            final XWPFTable documentTable = document.getBodyElements().stream()
//                    .filter(iBodyElement -> iBodyElement.getElementType().name().equals("TABLE"))
//                    .collect(Collectors.toList())
//                    .get(0).getBody()
//                    .getTables().get(0);

            // todo get text by lines? may be regexp
//            final ArrayList<XWPFTableRow> rowList = documentTable.getRows().stream().map(xwpfTableRow -> xwpfTableRow.get);
//            rowList.forEach(System.out::println);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            final String textString = extractor.getText();
            final String[] sortedStrings = textString.substring(textString.indexOf('\n') + 1).split("\t");
            System.out.println(textString);
        } catch (Exception e) {
            System.out.println("ALARM!! EXCEPTION");
        }
        return null;
    }
}
