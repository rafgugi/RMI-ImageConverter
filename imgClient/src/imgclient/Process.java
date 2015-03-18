/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgclient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author sg
 */
public class Process implements Runnable {

    public Processor processor;
    private final File file;

    public Process(Processor processor, File file) {
        this.processor = processor;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            String fileName = file.getName();
            String fileExt = Helper.getExtension(file);
            System.out.println(fileName);

            BufferedImage srcBuff = ImageIO.read(file);
            ByteArrayOutputStream bos;
            bos = new ByteArrayOutputStream();
            ImageIO.write(srcBuff, fileExt, bos);
            bos.flush();
            byte[] srcBytes;
            srcBytes = bos.toByteArray();
            bos.close();

            byte[] dstBytes = processor.getImage().toGrayscale(srcBytes);

            InputStream in = new ByteArrayInputStream(dstBytes);
            BufferedImage dstBuff = ImageIO.read(in);
            ImageIO.write(dstBuff, fileExt, new File(Helper.FOLDER_DST + fileName));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
