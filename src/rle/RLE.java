package rle;

import java.io.ByteArrayOutputStream;

public class RLE implements AlgorithmOfCompression {

    public byte[] bytesOfOriginImage;
    public byte[] bytesOfCompressImage;

    public RLE() {
    }

    public byte[] getBytesOfOriginImage() {
        return bytesOfOriginImage;
    }

    public byte[] getBytesOfCompressImage() {
        return bytesOfCompressImage;
    }

    public void setBytesOfOriginImage(byte[] bytesOfOriginImage) {
        this.bytesOfOriginImage = bytesOfOriginImage;
    }

    public void setBytesOfCompressImage(byte[] bytesOfCompressImage) {
        this.bytesOfCompressImage = bytesOfCompressImage;
    }

    @Override
    public void compress() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        byte counter = 1;
        byte prev = bytesOfOriginImage[1];

        for (int i = 1; i < bytesOfOriginImage.length; i++) {
            byte current = bytesOfOriginImage[i];
            if (current == prev && counter < 63) {
                counter++;
            } else {
                counter |= 0xc0; //11000000
                byteStream.write(counter);
                byteStream.write(prev);

                counter = 1;
            }
            prev = current;
        }
        counter |= 0xc0; //11000000
        byteStream.write(counter);
        byteStream.write(prev);


        bytesOfOriginImage = byteStream.toByteArray();
    }

    @Override
    public void decompress() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        for (int i = 0; i < bytesOfOriginImage.length; i++) {
            byte counter = (byte) (bytesOfOriginImage[i] & (0x40 - 1)); //111111


            byte value = bytesOfOriginImage[++i];
            for (int j = 0; j < counter; j++) {
                byteStream.write(value);
            }
        }
        bytesOfCompressImage = byteStream.toByteArray();
    }
}
