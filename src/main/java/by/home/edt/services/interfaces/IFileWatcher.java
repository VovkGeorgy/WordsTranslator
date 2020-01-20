package by.home.edt.services.interfaces;

import java.io.File;

public interface IFileWatcher {
    File[] watchFiles(String watchedDirectoryPath);
}
