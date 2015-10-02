package wszolek.lauren.abusylist;

public class TodoItem {
    public String title;
    public String priority;
    public Boolean completed;

    public TodoItem(String title, String priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

}
