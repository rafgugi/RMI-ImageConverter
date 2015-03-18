/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author sg
 */
public class Helper {

    public static final String FOLDER_SRC = "D:/Pictures/src/";
    public static final String FOLDER_DST = "D:/Pictures/dst/";

    public static String getExtension(File file) {
        String extension = "";
        int j = file.getName().lastIndexOf('.');
        if (j > 0) {
            extension = file.getName().substring(j + 1).toLowerCase();
        }
        return extension;
    }

    public static final FileFilter imageFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            String ext = getExtension(file);
            return ("png".equals(ext) || "jpg".equals(ext));
        }
    };
}
