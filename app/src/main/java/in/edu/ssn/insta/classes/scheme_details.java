package in.edu.ssn.insta.classes;

public class scheme_details {
    int Name;
    int desc;
    String link;

    public scheme_details(int name, int desc, String link) {
        Name = name;
        this.desc = desc;
        this.link = link;
    }

    public int getName() {
        return Name;
    }

    public void setName(int name) {
        Name = name;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
