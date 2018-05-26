package uoa.se751.group22.benchmark;//####[1]####
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
public class Benchmark {//####[7]####
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
        try {//####[32]####
            group.waitTillFinished();//####[33]####
        } catch (Exception e) {//####[34]####
            e.printStackTrace();//####[35]####
        }//####[36]####
        long totalTime = System.currentTimeMillis() - startTime;//####[38]####
        System.out.println("Time: " + totalTime + " milliseconds");//####[39]####
    }//####[40]####
//####[42]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[42]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[42]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[42]####
            try {//####[42]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[42]####
                    int.class//####[42]####
                });//####[42]####
            } catch (Exception e) {//####[42]####
                e.printStackTrace();//####[42]####
            }//####[42]####
        }//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[42]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setParameters(granularity);//####[42]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[42]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setTaskIdArgIndexes(0);//####[42]####
        taskinfo.addDependsOn(granularity);//####[42]####
        taskinfo.setParameters(granularity);//####[42]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[42]####
    }//####[42]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[42]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setQueueArgIndexes(0);//####[42]####
        taskinfo.setIsPipeline(true);//####[42]####
        taskinfo.setParameters(granularity);//####[42]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[42]####
        doWork(granularity);//####[43]####
    }//####[44]####
//####[44]####
//####[46]####
    private static void doWork(int granularity) {//####[46]####
        Work.computeWork(granularity);//####[47]####
    }//####[48]####
}//####[48]####
