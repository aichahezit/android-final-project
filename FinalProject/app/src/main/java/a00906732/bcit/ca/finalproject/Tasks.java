package a00906732.bcit.ca.finalproject;

/**
 * Created by Aicha on 15-11-28.
 */
public class Tasks {

    public String   taskname;
    public String   course;
    public int      weight;
    public String   repeat;
    public String   duedate;

    public Tasks(){}

    public Tasks(String taskname, String course, int weight, String repeat, String duedate) {
        this.taskname   = taskname;
        this.course     = course;
        this.weight     = weight;
        this.repeat     = repeat;
        this.duedate    = duedate;
    }

    public String getTaskname(){
        return taskname;
    }

    public void setTaskname(String task){
        taskname = task;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course = course;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getRepeat(){
        return repeat;
    }

    public void setRepeat(String repeat){
        this.repeat = repeat;
    }

    public String getDuedate(){
        return duedate;
    }

    public void setDuedate(String date){
        duedate = date;
    }

}
