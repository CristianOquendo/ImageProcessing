import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {

    public static void main(String[] args) {
        // Cargar la imagen
        int[][] imageData = imgToTwoD("./apple.jpg");

        // Ejemplo de uso de los métodos
        int[][] trimmed = trimBorders(imageData, 60);
        twoDToImage(trimmed, "./trimmed_apple.jpg");

        int[][] negative = negativeColor(trimmed);
        twoDToImage(negative, "./negative_apple.jpg");

        int[][] stretched = stretchHorizontally(trimmed);
        twoDToImage(stretched, "./stretched_apple.jpg");

        int[][] shrunk = shrinkVertically(trimmed);
        twoDToImage(shrunk, "./shrunk_apple.jpg");

        int[][] inverted = invertImage(trimmed);
        twoDToImage(inverted, "./inverted_apple.jpg");

        int[][] filtered = colorFilter(trimmed, 50, -30, 20);
        twoDToImage(filtered, "./filtered_apple.jpg");

        // Pintar una imagen aleatoria
        int[][] randomImage = paintRandomImage(new int[500][500]);
        twoDToImage(randomImage, "./random_image.jpg");

        // Pintar rectángulos
        int[][] canvas = new int[500][500];
        int[][] rectangles = generateRectangles(canvas, 1000);
        twoDToImage(rectangles, "./rectangles.jpg");
    }

    public static int[][] trimBorders(int[][] imageData, int i) {
        return imageData;
    }

    // Método para crear una versión negativa de la imagen
    public static int[][] negativeColor(int[][] imageTwoD) {
        // Validar que imageTwoD no sea null
        if (imageTwoD == null) {
            System.out.println("Error: El arreglo de la imagen es null.");
            return null;
        }

        // Validar que imageTwoD tenga al menos una fila y una columna
        if (imageTwoD.length == 0 || imageTwoD[0].length == 0) {
            System.out.println("Error: El arreglo de la imagen está vacío o tiene dimensiones incorrectas.");
            return null;
        }

        // Crear la imagen modificada
        int[][] modifiedImage = new int[imageTwoD.length][imageTwoD[0].length];

        // Recorrer cada píxel de la imagen
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                // Obtener los valores RGBA del píxel actual
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);

                // Validar que rgba no sea null
                if (rgba == null || rgba.length != 4) {
                    System.out.println("Error: No se pudieron obtener los valores RGBA del píxel.");
                    return null;
                }

                // Calcular el negativo de cada componente de color
                rgba[0] = 255 - rgba[0]; // Negativo de Rojo
                rgba[1] = 255 - rgba[1]; // Negativo de Verde
                rgba[2] = 255 - rgba[2]; // Negativo de Azul

                // Convertir los valores RGBA de nuevo a un valor de píxel
                modifiedImage[i][j] = getColorIntValFromRGBA(rgba);

                // Validar que el valor del píxel sea válido
                if (modifiedImage[i][j] == -1) {
                    System.out.println("Error: No se pudo convertir el valor RGBA a un valor de píxel válido.");
                    return null;
                }
            }
        }

        // Devolver la imagen modificada
        return modifiedImage;
    }

    // Método para estirar la imagen horizontalmente
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        int[][] modifiedImage = new int[imageTwoD.length][imageTwoD[0].length * 2];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int it = j * 2;
                modifiedImage[i][it] = imageTwoD[i][j];
                modifiedImage[i][it + 1] = imageTwoD[i][j];
            }
        }
        return modifiedImage;
    }

    // Método para reducir la imagen verticalmente
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int[][] modifiedImage = new int[imageTwoD.length / 2][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD[0].length; i++) {
            for (int j = 0; j < imageTwoD.length - 1; j += 2) {
                modifiedImage[j / 2][i] = imageTwoD[j][i];
            }
        }
        return modifiedImage;
    }

    // Método para invertir la imagen
    public static int[][] invertImage(int[][] imageTwoD) {
        int[][] modifiedImage = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                modifiedImage[i][j] = imageTwoD[imageTwoD.length - 1 - i][imageTwoD[i].length - 1 - j];
            }
        }
        return modifiedImage;
    }

    // Método para aplicar un filtro de color
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        int[][] modifiedImage = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = Math.max(0, Math.min(255, rgba[0] + redChangeValue)); // Ajustar Rojo
                rgba[1] = Math.max(0, Math.min(255, rgba[1] + greenChangeValue)); // Ajustar Verde
                rgba[2] = Math.max(0, Math.min(255, rgba[2] + blueChangeValue)); // Ajustar Azul
                modifiedImage[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return modifiedImage;
    }

    // Método para pintar una imagen con colores aleatorios
    public static int[][] paintRandomImage(int[][] canvas) {
        Random rand = new Random();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                int[] rgba = {rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255};
                canvas[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return canvas;
    }

    // Método para pintar un rectángulo en la imagen
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        for (int i = rowPosition; i < rowPosition + height && i < canvas.length; i++) {
            for (int j = colPosition; j < colPosition + width && j < canvas[i].length; j++) {
                canvas[i][j] = color;
            }
        }
        return canvas;
    }

    // Método para generar rectángulos aleatorios
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        Random rand = new Random();
        for (int i = 0; i < numRectangles; i++) {
            int width = rand.nextInt(canvas[0].length);
            int height = rand.nextInt(canvas.length);
            int rowPosition = rand.nextInt(canvas.length - height);
            int colPosition = rand.nextInt(canvas[0].length - width);
            int[] rgba = {rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255};
            int color = getColorIntValFromRGBA(rgba);
            paintRectangle(canvas, width, height, rowPosition, colPosition, color);
        }
        return canvas;
    }

    // Métodos de utilidad (ya proporcionados)
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }

    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}