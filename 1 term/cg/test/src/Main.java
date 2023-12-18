import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder extraBytes = new StringBuilder();
        File image = readImage("C:\\Users\\Danila\\Downloads\\Мы.png");
        byte[] imageBytes = Files.readAllBytes(image.toPath());
        for (byte imageByte : imageBytes) {
            extraBytes.append(((short) imageByte + 128) % 2);
        }
        System.out.println(extraBytes);
    }
    public static File readImage(String path){
        return new File(path);
    }
}