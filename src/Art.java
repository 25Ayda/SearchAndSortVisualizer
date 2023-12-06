import processing.core.PImage;
public class Art implements Comparable {
    private int artYear;
    private int x;
    private int y;
    private PImage img;
    private searchState state;
    private int size = 40;

    public Art(int year, int x, int y, searchState state, String imgName){
        artYear = year;
        this.x = x;
        this.y = y;
        this.state = state;
        this.img = Main.app.loadImage(imgName);
    }

    public void setSearchState(searchState state){
        this.state = state;
    }

    public void setMid(){
        Main.app.fill(50, 30, 20);
        Main.app.circle(x, y, size);
        Main.app.textSize(20);
        Main.app.textAlign(Main.app.CENTER, Main.app.CENTER);
        Main.app.fill(0);
        Main.app.text(artYear, x, y);
    }

    public int getYear(){
        return artYear;
    }

    public void setYear(int year){
        artYear = year;
    }

    public void setImg(String imgName){
        this.img = Main.app.loadImage(imgName);
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void display(){
        Main.app.stroke(0);
        if(state == searchState.MID){
            Main.app.fill(247, 120, 109);
        }else if(state == searchState.LOW) {
            Main.app.fill(181, 233, 255);
        }else if (state == searchState.HIGH) {
            Main.app.fill(228, 181, 255);
        }else if (state == searchState.DEAD) {
            Main.app.fill(138, 138, 138);
        }else if (state == searchState.FOUND) {
            Main.app.fill(247, 244, 151);
        }else{
            Main.app.fill(255, 255, 255);
        }
        Main.app.circle(x, y, size);
        Main.app.textSize(15);
        Main.app.textAlign(Main.app.CENTER, Main.app.CENTER);
        Main.app.fill(0);
        Main.app.text(artYear, x, y);
        Main.app.image(img, x - 68, y - 160, 111, 136);
    }

    @Override
    public int compareTo(Object o) {
        if(getYear() > ((Art)(o)).getYear()){
            return 1;
        } else if (getYear() < ((Art)(o)).getYear()){
            return -1;
        }else{
            return 0;
        }
    }
    //private void Art(int year, String artName, int image){

    //}
}
