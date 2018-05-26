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
    private static final String EXECUTION_TYPE_SEQ = "SEQUENTIAL";//####[9]####
//####[11]####
    public static void main(String[] args) {//####[11]####
        if (args.length != 2) //####[12]####
        {//####[12]####
            System.out.println("Please check the parameters of your execution command, 2 parameters are needed!");//####[13]####
            return;//####[14]####
        }//####[15]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[17]####
        final String executionType = args[0].toUpperCase();//####[19]####
        final int problemSize = Integer.parseInt(args[1]);//####[21]####
        int granularityOfTask = 200;//####[22]####
        for (int i = 0; i < problemSize; i++) //####[25]####
        {//####[25]####
            itemsToProcess.add(granularityOfTask);//####[26]####
        }//####[27]####
        if (EXECUTION_TYPE_SEQ.equals(executionType)) //####[29]####
        {//####[29]####
            long startTime = System.currentTimeMillis();//####[30]####
            for (int workItem : itemsToProcess) //####[31]####
            {//####[31]####
                doWork(workItem);//####[32]####
            }//####[33]####
            long totalTime = System.currentTimeMillis() - startTime;//####[34]####
            System.out.println(totalTime);//####[35]####
        } else {//####[36]####
            TaskIDGroup group = new TaskIDGroup(problemSize);//####[37]####
            for (int i = 0; i < problemSize; i++) //####[38]####
            {//####[38]####
                TaskID id = oneOffDoingWork(granularityOfTask);//####[39]####
                group.add(id);//####[40]####
            }//####[41]####
            long startTime = System.currentTimeMillis();//####[42]####
            try {//####[43]####
                group.waitTillFinished();//####[44]####
                long totalTime = System.currentTimeMillis() - startTime;//####[45]####
                System.out.println(totalTime);//####[46]####
            } catch (Exception e) {//####[47]####
                e.printStackTrace();//####[48]####
            }//####[49]####
        }//####[50]####
    }//####[52]####
//####[54]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[54]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[54]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[54]####
            try {//####[54]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[54]####
                    int.class//####[54]####
                });//####[54]####
            } catch (Exception e) {//####[54]####
                e.printStackTrace();//####[54]####
            }//####[54]####
        }//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[54]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[54]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[54]####
        // ensure Method variable is set//####[54]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[54]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[54]####
        }//####[54]####
        taskinfo.setParameters(granularity);//####[54]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[54]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[54]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[54]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[54]####
        // ensure Method variable is set//####[54]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[54]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[54]####
        }//####[54]####
        taskinfo.setTaskIdArgIndexes(0);//####[54]####
        taskinfo.addDependsOn(granularity);//####[54]####
        taskinfo.setParameters(granularity);//####[54]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[54]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[54]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[54]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[54]####
    }//####[54]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[54]####
        // ensure Method variable is set//####[54]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[54]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[54]####
        }//####[54]####
        taskinfo.setQueueArgIndexes(0);//####[54]####
        taskinfo.setIsPipeline(true);//####[54]####
        taskinfo.setParameters(granularity);//####[54]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[54]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[54]####
    }//####[54]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[54]####
        doWork(granularity);//####[55]####
    }//####[56]####
//####[56]####
//####[58]####
    private static void doWork(int granularity) {//####[58]####
        Work.computeWork(granularity);//####[59]####
    }//####[60]####
}//####[60]####
