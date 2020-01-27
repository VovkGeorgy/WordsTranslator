package by.home.edt.services.impl;

import by.home.edt.classes.Words;
import by.home.edt.services.interfaces.IFileWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocFileWriter implements IFileWriter {

    @Override
    public boolean writeFile(Words words, String path) {
        try {
            System.out.println("Writing file....");
            final XWPFDocument document = new XWPFDocument();
            final XWPFParagraph tmpParagraph = document.createParagraph();
            final XWPFRun tmpRun = tmpParagraph.createRun();
            tmpRun.setFontSize(13);
            words.getWords().forEach(word -> {
                tmpRun.setText(word);
                tmpRun.addBreak();
            });
            document.write(new FileOutputStream(new File(path)));
            document.close();
            System.out.println("File has been successfully written");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
