import imageform.ImageForm;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        Composer comp = new Composer();
//        BufferedImage bf = ImageIO.read(new File("image1.jpeg"));
//        comp.compose(bf);
//        bf = comp.getDecomposed();
//        ImageIO.write(bf, "png", new File("41.jpeg") );
//        System.out.println("image created");

        ImageForm mainForm = new ImageForm();
        mainForm.setVisible(true);
    }
}