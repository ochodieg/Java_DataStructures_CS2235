package hw_6;

import java.util.Objects;
import java.util.Random;


public class CPU_Job implements Comparable<CPU_Job> {
    private int name;
    private int priority;
    private int jobLength;


    // constructor
    public CPU_Job(int name){
        try
        {Thread.sleep(1); }
        catch(InterruptedException ex)
        {Thread.currentThread().interrupt();}
        Random rand = new Random();
        // methods that call this multiple times run too quickly[?]
        // to let seed change effectively. With a 1ms delay, the seed changes
        // drastically enough to allow for different values
        rand.setSeed(System.currentTimeMillis());
        //int name        = rand.nextInt(20)+1;
        int priority    = rand.nextInt(20)+1;
        int length      = rand.nextInt(100)+1;

        this.name       = name;
        this.priority   = priority;
        this.jobLength  = length; // keep between 1 - 100
    }

    // getter setters
    public int getJobName()     {return name;}
    public int getJobPriority() {return priority;}
    public int getJobLength()   {return jobLength;}

    public void setName(int name)           {this.name      = name;}
    public void setPriority(int priority)   {this.priority  = priority;}
    public void setJobLength(int jobLength) {this.jobLength = jobLength;}

    @Override // override Comparable's compareTo

    public int compareTo(CPU_Job job){ // compares this jobs priority to other jobs priority
        if (this.getJobPriority() - job.getJobPriority()!=0){       // if priorities are not the same
            return this.getJobPriority() - job.getJobPriority();}   // return highest priority
        else if (this.getJobLength() - job.getJobLength() !=0)      // else if job lengths aren't the same
            {return this.getJobLength() - job.getJobLength();}      // return lowest job length
        else
            {return this.getJobName()-job.getJobName();}            // else return lowest job name
    } // make sure, when producing a new job, no two jobs of the same name exist

    public String toString()
    {return String.format("Job %d: Priority %d", this.getJobName(), this.getJobPriority());}

    public String runningToString()
    {return String.format("Running Job %d...\n", this.getJobName());}

}


