/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;
import rmi.ImageInterface;

/**
 *
 * @author sg
 */
public class Image extends UnicastRemoteObject implements ImageInterface {

    public Image() throws RemoteException {
    }

    @Override
    public byte[] toGrayscale(byte[] src) throws RemoteException {
        System.out.print(".");
        try {
            InputStream in = new ByteArrayInputStream(src);
            BufferedImage image = ImageIO.read(in);
            byte[] imageByte;
            ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            colorConvert.filter(image, image);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", bos);
            bos.flush();
            imageByte = bos.toByteArray();
            return imageByte;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
