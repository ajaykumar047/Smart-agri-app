package in.edu.ssn.insta.classes;

public class event_item_details {
    String name, date,obj,desc;

    public event_item_details(String name, String date, String obj, String desc) {
        this.name = name;
        this.date = date;
        this.obj = obj;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
