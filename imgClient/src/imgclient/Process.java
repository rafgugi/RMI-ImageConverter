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
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.imageio.ImageIO;
import rmi.ImageInterface;

/**
 *
 * @author sg
 */
public class Process implements Runnable {

    public Registry registry;
    public ImageInterface image;

    public Process(String server, int port) {
        try {
            registry = LocateRegistry.getRegistry(server, port);
            image = (ImageInterface) registry.lookup("Image");
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Process(Registry registry, ImageInterface image) {
        this.registry = registry;
        this.image = image;
    }

    public void loop() {
        FileFilter ff;
        ff = new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                String extension = Helper.getExtension(file);
                return ("png".equals(extension) || "jpg".equals(extension));
            }
        };

        File srcFolder = new File(Helper.FOLDER_SRC);
        for (final File file : srcFolder.listFiles(ff)) {
            doTheJob(file);
        }
    }

    public void doTheJob(File file) {
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

            byte[] dstBytes = image.toGrayscale(srcBytes);

            InputStream in = new ByteArrayInputStream(dstBytes);
            BufferedImage dstBuff = ImageIO.read(in);
            ImageIO.write(dstBuff, fileExt, new File(Helper.FOLDER_DST + fileName));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {

    }

}
