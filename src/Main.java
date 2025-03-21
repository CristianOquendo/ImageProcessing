import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Cargar la imagen
        int[][] imageData = ImageProcessing.imgToTwoD("./apple.jpg");

        // Recortar los bordes de la imagen
        int[][] trimmed = ImageProcessing.trimBorders(imageData, 60);
        ImageProcessing.twoDToImage(trimmed, "./trimmed_apple.jpg");

        // Crear una versión negativa de la imagen
        int[][] negative = ImageProcessing.negativeColor(trimmed);
        ImageProcessing.twoDToImage(negative, "./negative_apple.jpg");

        // Estirar la imagen horizontalmente
        assert trimmed != null;
        int[][] stretched = ImageProcessing.stretchHorizontally(trimmed);
        ImageProcessing.twoDToImage(stretched, "./stretched_apple.jpg");

        // Reducir la imagen verticalmente
        int[][] shrunk = ImageProcessing.shrinkVertically(trimmed);
        ImageProcessing.twoDToImage(shrunk, "./shrunk_apple.jpg");

        // Invertir la imagen
        int[][] inverted = ImageProcessing.invertImage(trimmed);
        ImageProcessing.twoDToImage(inverted, "./inverted_apple.jpg");

        // Aplicar un filtro de color
        int[][] filtered = ImageProcessing.colorFilter(trimmed, 50, -30, 20);
        ImageProcessing.twoDToImage(filtered, "./filtered_apple.jpg");

        // Pintar una imagen aleatoria
        int[][] randomImage = ImageProcessing.paintRandomImage(new int[500][500]);
        ImageProcessing.twoDToImage(randomImage, "./random_image.jpg");

        // Pintar rectángulos aleatorios
        int[][] canvas = new int[500][500];
        int[][] rectangles = ImageProcessing.generateRectangles(canvas, 1000);
        ImageProcessing.twoDToImage(rectangles, "./rectangles.jpg");

        System.out.println("Procesamiento de imágenes completado. Verifica los archivos generados.");
    }
}