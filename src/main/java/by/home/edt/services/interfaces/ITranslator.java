package by.home.edt.services.interfaces;

import java.io.IOException;
import java.util.List;

public interface ITranslator {

    List<String> translate(List<String> stringList) throws IOException;
}
