package imageform;

import rle.CompressionImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageForm extends JFrame{
    private JButton buttonLoadOriginalImage;
    private JButton buttonSaveCompressedImage;
    private JButton buttonCompress;
    private JLabel labelSizeCompressedImage;
    private JLabel labelSizeOriginImage;
    private JPanel panelOriginImage;
    private JPanel panelCompressedImage;
    private JPanel panel;

    BufferedImage originalImage;
    BufferedImage outputImage;

    File originalFile;
    File outputFile;

    public ImageForm() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setLocation(10,10);
        this.setSize(new Dimension(1200, 700));

//        labelSizeOriginImage.setText("image size");

        buttonCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (originalFile != null) {
                    try {
                        CompressionImage compression = new CompressionImage();
                        originalImage = ImageIO.read(originalFile);
                        compression.setOriginImage(originalImage);
                        compression.compression();
                        outputImage = compression.getCompressionImage();
                        ((ImagePanel) panelCompressedImage).setImage(outputImage);
                        panelCompressedImage.repaint();
                        String extension = originalFile.getName().substring(originalFile.getName().indexOf(".") + 1);

                        ImageIO.write(outputImage, extension, new File("compressImage." + extension) );
                        outputFile = new File("compressImage." + extension);

                        Double size = (double) (outputFile.length() / 1024);
                        labelSizeCompressedImage.setText(String.format("%f Kb", size));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        buttonLoadOriginalImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int ret = fileOpen.showOpenDialog(ImageForm.this);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    originalFile = fileOpen.getSelectedFile();
                    ((ImagePanel) panelOriginImage).setFile(originalFile);
                    panelOriginImage.repaint();
                }
                Double size = (double) (originalFile.length() / 1024);
                labelSizeOriginImage.setText(String.format("%f Kb", size));
            }
        });


    }

    private void createUIComponents() {
        panelOriginImage = new ImagePanel();
        panelCompressedImage = new ImagePanel();
    }
}
