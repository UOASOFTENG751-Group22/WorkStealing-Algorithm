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
//####[25]####
    /**
	 * Call with the Execution type and the number of items to process.
	 * Execution types can be the following:
	 * - sequential
	 * - work stealing
	 * - work sharing 
	 * - mixed
	 *///####[25]####
    public static void main(String[] args) {//####[25]####
        if (args.length != 2) //####[26]####
        {//####[26]####
            System.out.println("Please check the parameters of your execution command, 2 parameters are needed!");//####[27]####
            System.out.println("param 1 : [sequential|share|steal|mixed]");//####[28]####
            System.out.println("param 2 : no of tasks");//####[29]####
            return;//####[30]####
        }//####[31]####
        ArrayList<Integer> itemsToProcess = new ArrayList<Integer>();//####[33]####
        final String executionType = args[0].toUpperCase();//####[35]####
        final int problemSize = Integer.parseInt(args[1]);//####[37]####
        int granularityOfTask = 200;//####[38]####
        for (int i = 0; i < problemSize; i++) //####[41]####
        {//####[41]####
            itemsToProcess.add(granularityOfTask);//####[42]####
        }//####[43]####
        if (EXECUTION_TYPE_SEQ.equals(executionType)) //####[45]####
        {//####[45]####
            long startTime = System.currentTimeMillis();//####[46]####
            for (int workItem : itemsToProcess) //####[47]####
            {//####[47]####
                doWork(workItem);//####[48]####
            }//####[49]####
            long totalTime = System.currentTimeMillis() - startTime;//####[50]####
            System.out.println(totalTime);//####[51]####
        } else {//####[52]####
            setParaTaskScheduling(executionType);//####[53]####
            TaskIDGroup group = new TaskIDGroup(problemSize);//####[55]####
            for (int i = 0; i < problemSize; i++) //####[56]####
            {//####[56]####
                TaskID id = oneOffDoingWork(granularityOfTask);//####[57]####
                group.add(id);//####[58]####
            }//####[59]####
            long startTime = System.currentTimeMillis();//####[60]####
            try {//####[61]####
                group.waitTillFinished();//####[62]####
                long totalTime = System.currentTimeMillis() - startTime;//####[63]####
                System.out.println(totalTime);//####[64]####
            } catch (Exception e) {//####[65]####
                e.printStackTrace();//####[66]####
            }//####[67]####
        }//####[68]####
    }//####[70]####
//####[72]####
    private static volatile Method __pt__oneOffDoingWork_int_method = null;//####[72]####
    private synchronized static void __pt__oneOffDoingWork_int_ensureMethodVarSet() {//####[72]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[72]####
            try {//####[72]####
                __pt__oneOffDoingWork_int_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneOffDoingWork", new Class[] {//####[72]####
                    int.class//####[72]####
                });//####[72]####
            } catch (Exception e) {//####[72]####
                e.printStackTrace();//####[72]####
            }//####[72]####
        }//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(int granularity) {//####[72]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[72]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(int granularity, TaskInfo taskinfo) {//####[72]####
        // ensure Method variable is set//####[72]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[72]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[72]####
        }//####[72]####
        taskinfo.setParameters(granularity);//####[72]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[72]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity) {//####[72]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[72]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(TaskID<Integer> granularity, TaskInfo taskinfo) {//####[72]####
        // ensure Method variable is set//####[72]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[72]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[72]####
        }//####[72]####
        taskinfo.setTaskIdArgIndexes(0);//####[72]####
        taskinfo.addDependsOn(granularity);//####[72]####
        taskinfo.setParameters(granularity);//####[72]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[72]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity) {//####[72]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[72]####
        return oneOffDoingWork(granularity, new TaskInfo());//####[72]####
    }//####[72]####
    private static TaskID<Void> oneOffDoingWork(BlockingQueue<Integer> granularity, TaskInfo taskinfo) {//####[72]####
        // ensure Method variable is set//####[72]####
        if (__pt__oneOffDoingWork_int_method == null) {//####[72]####
            __pt__oneOffDoingWork_int_ensureMethodVarSet();//####[72]####
        }//####[72]####
        taskinfo.setQueueArgIndexes(0);//####[72]####
        taskinfo.setIsPipeline(true);//####[72]####
        taskinfo.setParameters(granularity);//####[72]####
        taskinfo.setMethod(__pt__oneOffDoingWork_int_method);//####[72]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[72]####
    }//####[72]####
    public static void __pt__oneOffDoingWork(int granularity) {//####[72]####
        doWork(granularity);//####[73]####
    }//####[74]####
//####[74]####
//####[76]####
    private static void doWork(int granularity) {//####[76]####
        Work.computeWork(granularity);//####[77]####
    }//####[78]####
//####[80]####
    private static boolean setParaTaskScheduling(String executionType) {//####[80]####
        boolean success = false;//####[81]####
        if (EXECUTION_TYPE_SHARE.equals(executionType)) //####[82]####
        {//####[82]####
            success = ParaTask.setScheduling(ScheduleType.WorkSharing);//####[83]####
            System.out.println("Set scheduling type: " + EXECUTION_TYPE_SHARE);//####[84]####
        } else if (EXECUTION_TYPE_STEAL.equals(executionType)) //####[86]####
        {//####[86]####
            success = ParaTask.setScheduling(ScheduleType.WorkStealing);//####[87]####
            System.out.println("Set scheduling type: " + EXECUTION_TYPE_STEAL);//####[88]####
        } else {//####[90]####
            success = ParaTask.setScheduling(ScheduleType.MixedSchedule);//####[91]####
            System.out.println("Set scheduling type: " + EXECUTION_TYPE_MIXED);//####[92]####
        }//####[93]####
        System.out.println("Successfully set scheduling type: " + success);//####[94]####
        return success;//####[95]####
    }//####[96]####
}//####[96]####
