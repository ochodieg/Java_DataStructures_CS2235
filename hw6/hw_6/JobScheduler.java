package hw_6;

import java.util.PriorityQueue;
import java.util.Random;


public class JobScheduler {

    // method to create a job with random instance variables to add to pq
//    public CPU_Job makeJob(){
//        Random rand = new Random();
//        rand.setSeed(System.currentTimeMillis());
//        int name        = rand.nextInt(20)+1;
//        int priority    = rand.nextInt(20)+1;
//        int length      = rand.nextInt(20)+1;
//
//        return new CPU_Job(name, priority,length);
//    }
    public int jobCount = 0;
    public int masterSliceCount = 0;

    public void initialize(PriorityQueue<CPU_Job> priorityScheduler){
        System.out.println("*** adding ***");
        for (int i = 0; i < 10; i++){
            //priorityScheduler.add(new CPU_Job());
            CPU_Job job = new CPU_Job(i+1);
            System.out.println(job);
            System.out.println(job.getJobLength());
            priorityScheduler.add(job);
        }
        jobCount += 10;

    }



    public static void main(String[] args){

        //JobScheduler simulator = new JobScheduler();
//        CPU_Job j1 = simulator.makeJob();
//        CPU_Job j2 = simulator.makeJob();
//        CPU_Job j3 = simulator.makeJob();
//        CPU_Job j1 = new CPU_Job();
//        CPU_Job j2 = new CPU_Job();
//        CPU_Job j3 = new CPU_Job();
//
//
//        System.out.println(j1);
//        System.out.println(j2);
//        System.out.println(j3);


//        for (int i = 0; i<3; i++){
//            CPU_Job job = new CPU_Job();
//            System.out.println(job);
//            System.out.println(job.runningToString());
//        }

//        CPU_Job j1 = new CPU_Job();
//        CPU_Job j2 = new CPU_Job();
//        CPU_Job j3 = new CPU_Job();
//        System.out.println(j1);
//        System.out.println(j2);
//        System.out.println(j3);
        JobScheduler simulator = new JobScheduler();
        PriorityQueue<CPU_Job> priorityScheduler = new PriorityQueue<>();
//        System.out.println("*** adding ***");
//        for (int i = 0; i < 10; i++){
//            //priorityScheduler.add(new CPU_Job());
//            CPU_Job job = new CPU_Job(i+1);
//            System.out.println(job);
//            System.out.println(job.getJobLength());
//            priorityScheduler.add(job);
//        }
//        System.out.println("\n*** removing ***");
        simulator.initialize(priorityScheduler);






//        while (!priorityScheduler.isEmpty()){
//            CPU_Job job = priorityScheduler.remove();
//            //System.out.println(priorityScheduler.remove());
//            System.out.println(job);
//            System.out.println(job.getJobLength());
//        }

        while(!priorityScheduler.isEmpty()){
            CPU_Job job = priorityScheduler.remove();
            //System.out.println("\n** Starting: " + job.toString() + " **");
           //System.out.printf("job length: %d\n\n",job.getJobLength());

            System.out.printf("""
                    
                    *** Starting: %s ***
                            ** job length: %d **
                        
                    """, job.toString(), job.getJobLength());

            for(int i = 0; i< job.getJobLength(); i++){
                simulator.masterSliceCount += 1;
                if (simulator.masterSliceCount%100 == 0){
                    simulator.jobCount +=1;
                    priorityScheduler.add(new CPU_Job(simulator.jobCount));
                    System.out.println("*** JOB " + simulator.jobCount + " ADDED ***\n");
                }
                int slice = i +1;
                System.out.println("Total time slices used: " + simulator.masterSliceCount);
                System.out.println("Job time slice: " + slice);
                System.out.println(job.runningToString());
            }
            System.out.printf("!! Job %d finished !!\n", job.getJobName());
        }
        System.out.println("\nA TOTAL OF: " + simulator.jobCount + " JOBS WERE RAN");
        System.out.println("A TOTAL OF: " + simulator.masterSliceCount + " TIME SLICES WERE USED");
    }




}

