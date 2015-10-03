package wszolek.lauren.abusylist;

import android.os.Parcel;
import android.os.Parcelable;

//parceling taken from here: http://sohailaziz05.blogspot.com/2012/04/passing-custom-objects-between-android.html

public class TodoItem implements Parcelable{
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

    public TodoItem(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.title = data[0];
        this.priority = data[2];
        this.completed = Boolean.parseBoolean(data[2]);
    }
    @Override
    public int describeContents() {
        // auto generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.title, this.priority, String.valueOf(this.completed)});
    }

    public static final Parcelable.Creator<TodoItem> CREATOR= new Parcelable.Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source); //using parcelable constructor
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };
}
