package uoa.se751.group22.sample;//####[1]####
//####[1]####
import java.util.ArrayList;//####[3]####
import pt.runtime.ParaTask;//####[5]####
import pt.runtime.ParaTask.ScheduleType;//####[6]####
import uoa.se751.group22.utils.Work;//####[7]####
//####[7]####
//-- ParaTask related imports//####[7]####
import pt.runtime.*;//####[7]####
import java.util.concurrent.ExecutionException;//####[7]####
import java.util.concurrent.locks.*;//####[7]####
import java.lang.reflect.*;//####[7]####
import pt.runtime.GuiThread;//####[7]####
import java.util.concurrent.BlockingQueue;//####[7]####
import java.util.ArrayList;//####[7]####
import java.util.List;//####[7]####
//####[7]####
public class ParaTaskTest {//####[9]####
    static{ParaTask.init();}//####[9]####
    /*  ParaTask helper method to access private/protected slots *///####[9]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[9]####
        if (m.getParameterTypes().length == 0)//####[9]####
            m.invoke(instance);//####[9]####
        else if ((m.getParameterTypes().length == 1))//####[9]####
            m.invoke(instance, arg);//####[9]####
        else //####[9]####
            m.invoke(instance, arg, interResult);//####[9]####
    }//####[9]####
//####[12]####
    /** Sets schedule type for program *///####[12]####
    private static final ScheduleType SCHEDULE_TYPE = ScheduleType.WorkStealing;//####[12]####
//####[14]####
    static {//####[14]####
        ParaTask.setScheduling(SCHEDULE_TYPE);//####[15]####
    }//####[16]####
//####[18]####
    public static void main(String[] args) {//####[18]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[19]####
        int numItems = 10000;//####[21]####
        int granularityOfTask = 20;//####[22]####
        TaskIDGroup group = new TaskIDGroup(numItems);//####[24]####
        for (int i = 0; i < numItems; i++) //####[26]####
        {//####[26]####
            itemsToProcess.add(granularityOfTask);//####[27]####
            TaskID id = oneOffDoingWork(granularityOfTask);//####[28]####
            group.add(id);//####[29]####
        }//####[30]####
        System.out.println("Will process " + numItems + " items with granularity of " + granularityOfTask);//####[32]####
        System.out.println("Using ScheduleType: " + SCHEDULE_TYPE.toString());//####[33]####
        long startTime = System.currentTimeMillis();//####[35]####
        try {//####[37]####
            group.waitTillFinished();//####[38]####
        } catch (Exception e) {//####[39]####
            e.printStackTrace();//####[40]####
        }//####[41]####
        long totalTime = System.currentTimeMillis() - startTime;//####[43]####
        System.out.println("Time: " + totalTime + " milliseconds");//####[44]####
    }//####[45]####
//####[47]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[47]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[47]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[47]####
            try {//####[47]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[47]####
                    int.class//####[47]####
                });//####[47]####
            } catch (Exception e) {//####[47]####
                e.printStackTrace();//####[47]####
            }//####[47]####
        }//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[47]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[47]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[47]####
        // ensure Method variable is set//####[47]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[47]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[47]####
        }//####[47]####
        taskinfo.setParameters(granularity);//####[47]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[47]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[47]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[47]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[47]####
        // ensure Method variable is set//####[47]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[47]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[47]####
        }//####[47]####
        taskinfo.setTaskIdArgIndexes(0);//####[47]####
        taskinfo.addDependsOn(granularity);//####[47]####
        taskinfo.setParameters(granularity);//####[47]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[47]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[47]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[47]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[47]####
    }//####[47]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[47]####
        // ensure Method variable is set//####[47]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[47]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[47]####
        }//####[47]####
        taskinfo.setQueueArgIndexes(0);//####[47]####
        taskinfo.setIsPipeline(true);//####[47]####
        taskinfo.setParameters(granularity);//####[47]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[47]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[47]####
    }//####[47]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[47]####
        doWork(granularity);//####[48]####
    }//####[49]####
//####[49]####
//####[51]####
    private static void doWork(int granularity) {//####[51]####
        Work.computeWork(granularity);//####[52]####
    }//####[53]####
}//####[53]####
