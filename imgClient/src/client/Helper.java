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

    public static final FileFilter imageFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            String ext = getExtension(file);
            return ("png".equals(ext) || "jpg".equals(ext));
        }
    };

    public static String getExtension(File file) {
        String extension = "";
        int j = file.getName().lastIndexOf('.');
        if (j > 0) {
            extension = file.getName().substring(j + 1).toLowerCase();
        }
        return extension;
    }

    public static String etaConvert(long l_d) {
        String eta = "";
        l_d /= 1000;
        if (l_d >= 86400) {
            eta += String.valueOf(l_d / 86400) + "d ";
            l_d %= 86400;
        }
        if (l_d >= 3600) {
            eta += String.valueOf(l_d / 3600) + "h ";
            l_d %= 3600;
        }
        if (l_d >= 60) {
            eta += String.valueOf(l_d / 60) + "m ";
            l_d %= 60;
        }
        eta += String.valueOf(l_d) + "s";
        return eta;
    }
}
