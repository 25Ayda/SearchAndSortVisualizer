// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
public class Main extends PApplet {
    public static Main app;
    private static final int canvasWidth = 1200;
    private static final int canvasHeight = 600;
    private static final ArrayList<Integer> arr = new ArrayList<>();
    public static ArrayList<Art> gallery = new ArrayList<>();
    private static int bottom = 0;
    private static int top = 4;
    private static int mid;
    private int target;
    private String stringTarget = "";
    //public PImage img;
    public boolean keyPress = false;
    String[] imgNames = {"data/angel.jpg", "data/flowers.jpg", "data/wedding.jpg", "data/kermit.jpg", "data/dots.jpg"};
    public static void main(String[] args) {
        PApplet.main("Main");
    }
    public Main(){
        app = this;
    }
    public void settings(){
        size(canvasWidth, canvasHeight);
    }
    public void setup(){
        Art s;
        for(int i = 0; i < 5; i++){
            s = new Art(i, 100 + (canvasWidth)/5 * i , canvasHeight/2, searchState.ALIVE, imgNames[i]);
            gallery.add(s);
        }
        gallery.get(0).setYear(2000);
        gallery.get(1).setYear(100);
        gallery.get(2).setYear(3000);
        gallery.get(3).setYear(400);
        gallery.get(4).setYear(200);

        //gallery.get(2).setImg("data/flowers.jpg");
        /*gallery.get(2).setYear(3000);
        gallery.get(3).setYear(400);
        gallery.get(4).setYear(200);*/
        //img = Main.app.loadImage("data/angel.jpg");

        for(int i = 0; i < 5; i++) {
            System.out.println(gallery.get(i).getYear());
        }
        textSize(20);
        textAlign(CENTER, CENTER);
    }

    public void draw(){
        for(int i = 0; i < 5; i++){
            gallery.get(i).display();
        }
        //image(img, 100, 100, 111, 134);
        fill(0);
        text("INSTRUCTIONS", width/2, height/2 + 50);
        text("BINARY SEARCH VISUALIZER", width/2, 50);
        textSize(15);
        text("Press o to sort art pieces by price", width/2, height/2 + 70);
        text("Input a target price", width/2, height/2 + 90);
        text("Press s key to begin searching" , width/2, height/2 + 110);
        text("Press e key to eliminate half of the gallery" , width/2, height/2 + 130);
        text("Alternate between s and e until you find the desired artwork" , width/2, height/2 + 150);
        text("or determine that no artwork is at your price point" , width/2, height/2 + 170);
        //KEY
        fill(247, 120, 109);
        text("Red represents the middle marker / the price being compared to target price" , width/2, height/2 + 190);
        fill(181, 233, 255);
        text("Blue represents the bottom marker" , width/2, height/2 + 210);
        fill(180, 76, 224);
        text("Purple represents the top marker" , width/2, height/2 + 230);
        fill(138, 138, 138);
        text("Grey represents the section of the art gallery that has been eliminated" , width/2, height/2 + 250);
        fill(247, 244, 151);
        text("Yellow represents the target price that has been found in the gallery" , width/2, height/2 + 270);
        fill(0);
        text("Press r key to reset gallery" , width/2, height/2 + 290);


    }



    private static int binarySearchRecursive(int bottom, int top, int target) {
        if(bottom <= top) {
            if ((gallery.get((bottom + top) / 2)).getYear() == target) {
                return (bottom + top) / 2;
            } else if (gallery.get((bottom + top) / 2).getYear() < target) {
                bottom = (bottom + top) / 2 + 1;
                return binarySearchRecursive(bottom, top, target);
            } else if (gallery.get((bottom + top) / 2).getYear() > target) {
                top = (bottom + top) / 2 - 1;
                return binarySearchRecursive(bottom, top, target);
            }
        }
        return -1;
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
        if(Character.isDigit(key)){
            stringTarget = stringTarget + key;
            fill(204,204,204); //204,204,204
            stroke(204,204,204);
            rect(width/2 - 250, height/2 - 245, 500, 50);
            fill(0);
            text("You are searching for art with price point: $" + stringTarget, width/2, height/2 - 220);
        }
        if(key == 's'){
            target = Integer.parseInt(stringTarget);
            System.out.println("Searching " + key + binarySearchIterative(target));
        }
        if(key == 'e') {
            System.out.println("eliminating " + key + binarySearchIterativeEliminate(target));
        }
        //System.out.println("Recursive" + binarySearchRecursive(0, 8, 7));
            keyPress = true;
        if(key == 'r'){
            reset();
        }

        if(key == 'o'){
            selectionSort(gallery);
        }
    }

    private void reset(){
        for(int i = 0; i < 5; i++){
            gallery.get(i).setSearchState(searchState.ALIVE);
        }
        stringTarget = "";
        bottom = 0;
        top = 4;
    }

    private int binarySearchIterative(int target){
        System.out.println("In function " + target);
        // use a while loop
        if(bottom <= top){
            gallery.get(top).setSearchState(searchState.HIGH);
            gallery.get(bottom).setSearchState(searchState.LOW);
            mid = (bottom + top) / 2;
            gallery.get(mid).setSearchState(searchState.MID);
        }
        keyPress = false;
        return -1;
    }

    private int binarySearchIterativeEliminate(int target){
        fill(204,204,204);
        stroke(204,204,204);
        rect(width/2 - 100 , height/2 -212, 200, 25);
        fill(0);
        if(bottom <= top){
            if((gallery.get(mid)).getYear() == target){
                text("FOUND AT INDEX " + mid, width/2, height/2 - 170);
                gallery.get(mid).setSearchState(searchState.FOUND);
                return mid;
            }else if((gallery.get(mid)).getYear() < target){
                //app.delay(2000);
                for( int i = bottom; i <= mid; i++){
                    gallery.get(i).setSearchState(searchState.DEAD);
                }
                gallery.get(top).setSearchState(searchState.ALIVE);
                text("NOT FOUND YET " + mid, width/2, height/2 - 200);
                bottom = mid +1;
            }else if((gallery.get(mid)).getYear() > target){
                //app.delay(2000);
                for( int i = mid; i <= top; i++){
                    gallery.get(i).setSearchState(searchState.DEAD);
                }
                gallery.get(bottom).setSearchState(searchState.ALIVE);
                text("NOT FOUND YET " + mid, width/2, height/2 - 200);
                top = mid -1;
            }
        }else{
            text(target + " PRICE IS NOT IN GALLERY ", width/2, height/2 - 200);
        }
        keyPress = false;
        return 2;

    }

    private void selectionSort(ArrayList<Art> arr) {
        for (int curIndex = 0; curIndex < arr.size() - 1; curIndex++) {
            int minIndex = findMin(arr, curIndex);
            swapLocation(arr.get(curIndex), arr.get(minIndex));
            swap(arr, curIndex, minIndex);
        }
    }

    private void swapLocation(Art art1, Art art2){
        int tempX = art1.getX();
        int tempY = art1.getY();
        art1.setX(art2.getX());
        art1.setY(art2.getY());
        art2.setX(tempX);
        art2.setY(tempY);
    }
    private int findMin(ArrayList<Art> arr, int startingIndex) {
        int minIndex = startingIndex;
        for (int i = minIndex + 1; i < arr.size(); i++) {
            if (arr.get(i).compareTo(arr.get(minIndex)) == -1) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    private void swap(ArrayList<Art> arr, int x, int y) {
        Art temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }



}