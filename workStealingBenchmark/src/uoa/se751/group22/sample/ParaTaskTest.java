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
    private static final ScheduleType SCHEDULE_TYPE = ScheduleType.MixedSchedule;//####[12]####
//####[14]####
    public static void main(String[] args) {//####[14]####
        ParaTask.setScheduling(SCHEDULE_TYPE);//####[15]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[16]####
        int numItems = 20;//####[18]####
        int granularityOfTask = 100;//####[19]####
        TaskIDGroup group = new TaskIDGroup(numItems);//####[21]####
        for (int i = 0; i < numItems; i++) //####[23]####
        {//####[23]####
            itemsToProcess.add(granularityOfTask);//####[24]####
            TaskID id = oneOffDoingWork(granularityOfTask);//####[25]####
            group.add(id);//####[26]####
        }//####[27]####
        System.out.println("Will process " + numItems + " items with granularity of " + granularityOfTask);//####[29]####
        System.out.println("Using ScheduleType: " + SCHEDULE_TYPE.toString());//####[30]####
        long startTime = System.currentTimeMillis();//####[32]####
        try {//####[34]####
            group.waitTillFinished();//####[35]####
        } catch (Exception e) {//####[36]####
            e.printStackTrace();//####[37]####
        }//####[38]####
        long totalTime = System.currentTimeMillis() - startTime;//####[40]####
        System.out.println("Time: " + totalTime + " milliseconds");//####[41]####
    }//####[42]####
//####[44]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[44]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[44]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[44]####
            try {//####[44]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[44]####
                    int.class//####[44]####
                });//####[44]####
            } catch (Exception e) {//####[44]####
                e.printStackTrace();//####[44]####
            }//####[44]####
        }//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[44]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[44]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[44]####
        // ensure Method variable is set//####[44]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[44]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[44]####
        }//####[44]####
        taskinfo.setParameters(granularity);//####[44]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[44]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[44]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[44]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[44]####
        // ensure Method variable is set//####[44]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[44]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[44]####
        }//####[44]####
        taskinfo.setTaskIdArgIndexes(0);//####[44]####
        taskinfo.addDependsOn(granularity);//####[44]####
        taskinfo.setParameters(granularity);//####[44]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[44]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[44]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[44]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[44]####
    }//####[44]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[44]####
        // ensure Method variable is set//####[44]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[44]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[44]####
        }//####[44]####
        taskinfo.setQueueArgIndexes(0);//####[44]####
        taskinfo.setIsPipeline(true);//####[44]####
        taskinfo.setParameters(granularity);//####[44]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[44]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[44]####
    }//####[44]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[44]####
        doWork(granularity);//####[45]####
    }//####[46]####
//####[46]####
//####[48]####
    private static void doWork(int granularity) {//####[48]####
        Work.computeWork(granularity);//####[49]####
    }//####[50]####
}//####[50]####
