package rle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

public class CompressionImage {

    public BufferedImage originImage, compressionImage;

    public CompressionImage() {
    }

    public void compression() {
        Raster raster = originImage.getRaster();
        DataBufferByte bufferByte = (DataBufferByte) raster.getDataBuffer();
        byte[] source = bufferByte.getData();

        RLE rle = new RLE();
        rle.setBytesOfOriginImage(source);
        rle.compress();
        rle.decompress();
        byte[] dBytes = rle.getBytesOfCompressImage();
        compressionImage = new BufferedImage(originImage.getWidth(), originImage.getHeight(), originImage.getType());
        DataBufferByte dBufferByte = new DataBufferByte(dBytes, dBytes.length);
        Raster dRaster = Raster.createRaster(raster.getSampleModel(), dBufferByte, new Point());
        compressionImage.setData(dRaster);
    }

    public BufferedImage getOriginImage() {
        return originImage;
    }

    public BufferedImage getCompressionImage() {
        return compressionImage;
    }

    public void setOriginImage(BufferedImage originImage) {
        this.originImage = originImage;
    }

    public void setCompressionImage(BufferedImage compressionImage) {
        this.compressionImage = compressionImage;
    }
}
