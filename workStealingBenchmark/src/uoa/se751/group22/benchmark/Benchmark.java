package uoa.se751.group22.benchmark;//####[1]####
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
public class Benchmark {//####[9]####
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
//####[11]####
    private static final String EXECUTION_TYPE_SEQ = "SEQUENTIAL";//####[11]####
//####[12]####
    private static final String EXECUTION_TYPE_SHARE = "SHARE";//####[12]####
//####[13]####
    private static final String EXECUTION_TYPE_STEAL = "STEAL";//####[13]####
//####[14]####
    private static final String EXECUTION_TYPE_MIXED = "MIXED";//####[14]####
//####[16]####
    static {//####[16]####
        setParaTaskScheduling(EXECUTION_TYPE_STEAL);//####[17]####
    }//####[18]####
//####[29]####
    /**
	 * Call with the Execution type and the number of items to process.
	 * Execution types can be the following:
	 * - sequential
	 * - work stealing
	 * - work sharing 
	 * - mixed
	 *///####[29]####
    public static void main(String[] args) {//####[29]####
        if (args.length != 2) //####[30]####
        {//####[30]####
            System.out.println("Please check the parameters of your execution command, 2 parameters are needed!");//####[31]####
            System.out.println("param 1 : [sequential|share|steal|mixed]");//####[32]####
            System.out.println("param 2 : no of tasks");//####[33]####
            return;//####[34]####
        }//####[35]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[37]####
        final String executionType = args[0].toUpperCase();//####[39]####
        final int problemSize = Integer.parseInt(args[1]);//####[41]####
        int granularityOfTask = 20;//####[42]####
        for (int i = 0; i < problemSize; i++) //####[45]####
        {//####[45]####
            itemsToProcess.add(granularityOfTask);//####[46]####
        }//####[47]####
        if (EXECUTION_TYPE_SEQ.equals(executionType)) //####[49]####
        {//####[49]####
            long startTime = System.currentTimeMillis();//####[50]####
            for (int workItem : itemsToProcess) //####[51]####
            {//####[51]####
                doWork(workItem);//####[52]####
            }//####[53]####
            long totalTime = System.currentTimeMillis() - startTime;//####[54]####
            System.out.println(totalTime);//####[55]####
        } else {//####[56]####
            TaskIDGroup group = new TaskIDGroup(problemSize);//####[58]####
            for (int i = 0; i < problemSize; i++) //####[59]####
            {//####[59]####
                TaskID id = oneOffDoingWork(granularityOfTask);//####[60]####
                group.add(id);//####[61]####
            }//####[62]####
            long startTime = System.currentTimeMillis();//####[63]####
            try {//####[64]####
                group.waitTillFinished();//####[65]####
                long totalTime = System.currentTimeMillis() - startTime;//####[66]####
                System.out.println(totalTime);//####[67]####
            } catch (Exception e) {//####[68]####
                e.printStackTrace();//####[69]####
            }//####[70]####
        }//####[71]####
    }//####[73]####
//####[75]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[75]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[75]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[75]####
            try {//####[75]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[75]####
                    int.class//####[75]####
                });//####[75]####
            } catch (Exception e) {//####[75]####
                e.printStackTrace();//####[75]####
            }//####[75]####
        }//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[75]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[75]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[75]####
        // ensure Method variable is set//####[75]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[75]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[75]####
        }//####[75]####
        taskinfo.setParameters(granularity);//####[75]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[75]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[75]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[75]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[75]####
        // ensure Method variable is set//####[75]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[75]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[75]####
        }//####[75]####
        taskinfo.setTaskIdArgIndexes(0);//####[75]####
        taskinfo.addDependsOn(granularity);//####[75]####
        taskinfo.setParameters(granularity);//####[75]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[75]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[75]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[75]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[75]####
    }//####[75]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[75]####
        // ensure Method variable is set//####[75]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[75]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[75]####
        }//####[75]####
        taskinfo.setQueueArgIndexes(0);//####[75]####
        taskinfo.setIsPipeline(true);//####[75]####
        taskinfo.setParameters(granularity);//####[75]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[75]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[75]####
    }//####[75]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[75]####
        doWork(granularity);//####[76]####
    }//####[77]####
//####[77]####
//####[79]####
    private static void doWork(int granularity) {//####[79]####
        Work.computeWork(granularity);//####[80]####
    }//####[81]####
//####[83]####
    private static boolean setParaTaskScheduling(String executionType) {//####[83]####
        boolean success = false;//####[84]####
        if (EXECUTION_TYPE_SHARE.equals(executionType)) //####[85]####
        {//####[85]####
            success = ParaTask.setScheduling(ScheduleType.WorkSharing);//####[86]####
        } else if (EXECUTION_TYPE_STEAL.equals(executionType)) //####[89]####
        {//####[89]####
            success = ParaTask.setScheduling(ScheduleType.WorkStealing);//####[90]####
        } else {//####[93]####
            success = ParaTask.setScheduling(ScheduleType.MixedSchedule);//####[94]####
        }//####[96]####
        return success;//####[98]####
    }//####[99]####
}//####[99]####
