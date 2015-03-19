/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author sg
 */
public class Process extends Thread implements Runnable {

    public Processor processor;
    private final File file;

    public Process(Processor processor, File file) {
        this.processor = processor;
        this.file = file;
        if (!processor.isReady) {
            System.out.println("Overlap!");
        }
        processor.isReady = false;
    }

    @Override
    public void run() {
        String fileName = file.getName();
        String fileExt = Helper.getExtension(file);
        Date date = new Date();
        try {
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

            long diff = new Date().getTime() - date.getTime();
            String msg = "";
            msg += processor + "> Processing " + fileName;
            msg += " (time: " + Helper.etaConvert(diff) + ")";
            System.out.println(msg);
        } catch (IOException | NullPointerException ex) {
            System.out.println("Process: " + processor + "> " + ex.getMessage());
        }
        processor.isReady = true;
    }

}
