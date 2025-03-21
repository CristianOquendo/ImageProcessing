import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // The provided images are apple.jpg, flower.jpg, and kitten.jpg
        int[][] imageData = ImageProcessing.imgToTwoD("./apple.jpg");
        //ImagenProcessing.viewImageData (I
        // Or load your own image using a URL!
        //int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
        //viewImageData(imageData);
        int[][] trimmed = ImageProcessing.trimBorders(imageData, 200);

        int[][] negative = ImageProcessing.negativeColor(imageData);

        int [][] stretch = ImageProcessing.stretchHorizontally(imageData);

        int [][] shrink = ImageProcessing.shrinkVertically(imageData);

        int [][] invert = ImageProcessing.invertImage(imageData);

       //int [][] color = ImageProcessing.colorFilter();

        System.out.println(paintRectangle);



        ImageProcessing.twoDToImage(trimmed, "./trimmed_apple.jpg");
        ImageProcessing.twoDToImage(negative, "./negative_apple.png");
        ImageProcessing.twoDToImage(stretch, "./stretch_apple.png");
        ImageProcessing.twoDToImage(shrink, "./shrink_apple.png");
        ImageProcessing.twoDToImage(invert, "./invert_apple.png");
        //ImageProcessing.twoDToImage(color, "./color_filter_apple.png");


        }

        // int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
        // Painting with pixels
    }
