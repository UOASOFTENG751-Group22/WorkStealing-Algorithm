package uoa.se751.group22.sample;//####[1]####
//####[1]####
import java.util.ArrayList;//####[3]####
import uoa.se751.group22.utils.Work;//####[5]####
//####[5]####
//-- ParaTask related imports//####[5]####
import pt.runtime.*;//####[5]####
import java.util.concurrent.ExecutionException;//####[5]####
import java.util.concurrent.locks.*;//####[5]####
import java.lang.reflect.*;//####[5]####
import pt.runtime.GuiThread;//####[5]####
import java.util.concurrent.BlockingQueue;//####[5]####
import java.util.ArrayList;//####[5]####
import java.util.List;//####[5]####
//####[5]####
public class ParaTaskTest {//####[7]####
    static{ParaTask.init();}//####[7]####
    /*  ParaTask helper method to access private/protected slots *///####[7]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[7]####
        if (m.getParameterTypes().length == 0)//####[7]####
            m.invoke(instance);//####[7]####
        else if ((m.getParameterTypes().length == 1))//####[7]####
            m.invoke(instance, arg);//####[7]####
        else //####[7]####
            m.invoke(instance, arg, interResult);//####[7]####
    }//####[7]####
//####[9]####
    public static void main(String[] args) {//####[9]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[10]####
        int numItems = 20;//####[12]####
        int granularityOfTask = 100;//####[13]####
        TaskIDGroup group = new TaskIDGroup(numItems);//####[15]####
        for (int i = 0; i < numItems; i++) //####[17]####
        {//####[17]####
            itemsToProcess.add(granularityOfTask);//####[18]####
            TaskID id = oneOffDoingWork(granularityOfTask);//####[19]####
            group.add(id);//####[20]####
        }//####[21]####
        System.out.println("Will process " + numItems + " items with granularity of " + granularityOfTask);//####[23]####
        long startTime = System.currentTimeMillis();//####[25]####
        try {//####[27]####
            group.waitTillFinished();//####[28]####
        } catch (Exception e) {//####[29]####
            e.printStackTrace();//####[30]####
        }//####[31]####
        long totalTime = System.currentTimeMillis() - startTime;//####[33]####
        System.out.println("Time: " + totalTime + " milliseconds");//####[34]####
    }//####[35]####
//####[37]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[37]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[37]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[37]####
            try {//####[37]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[37]####
                    int.class//####[37]####
                });//####[37]####
            } catch (Exception e) {//####[37]####
                e.printStackTrace();//####[37]####
            }//####[37]####
        }//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[37]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[37]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[37]####
        // ensure Method variable is set//####[37]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[37]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[37]####
        }//####[37]####
        taskinfo.setParameters(granularity);//####[37]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[37]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[37]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[37]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[37]####
        // ensure Method variable is set//####[37]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[37]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[37]####
        }//####[37]####
        taskinfo.setTaskIdArgIndexes(0);//####[37]####
        taskinfo.addDependsOn(granularity);//####[37]####
        taskinfo.setParameters(granularity);//####[37]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[37]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[37]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[37]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[37]####
    }//####[37]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[37]####
        // ensure Method variable is set//####[37]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[37]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[37]####
        }//####[37]####
        taskinfo.setQueueArgIndexes(0);//####[37]####
        taskinfo.setIsPipeline(true);//####[37]####
        taskinfo.setParameters(granularity);//####[37]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[37]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[37]####
    }//####[37]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[37]####
        doWork(granularity);//####[38]####
    }//####[39]####
//####[39]####
//####[41]####
    private static void doWork(int granularity) {//####[41]####
        Work.computeWork(granularity);//####[42]####
    }//####[43]####
}//####[43]####
