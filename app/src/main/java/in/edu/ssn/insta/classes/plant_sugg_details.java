package in.edu.ssn.insta.classes;

public class plant_sugg_details {

    String name;
    int Image ;

    public plant_sugg_details(String name, int image) {
        this.name = name;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
