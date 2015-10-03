package wszolek.lauren.abusylist;

public class TodoItem {
    public Long _id;
    public String title;
    public String priority;
    public Boolean completed;

    public TodoItem() {
        this.title = "defaults item";
        this.priority = "low";
        this.completed = false;
    }

    public TodoItem(String title, String priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

}
