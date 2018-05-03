package uoa.se751.group22.sample;//####[1]####
//####[1]####
//-- ParaTask related imports//####[1]####
import pt.runtime.*;//####[1]####
import java.util.concurrent.ExecutionException;//####[1]####
import java.util.concurrent.locks.*;//####[1]####
import java.lang.reflect.*;//####[1]####
import pt.runtime.GuiThread;//####[1]####
import java.util.concurrent.BlockingQueue;//####[1]####
import java.util.ArrayList;//####[1]####
import java.util.List;//####[1]####
//####[1]####
public class HelloWorld {//####[3]####
    static{ParaTask.init();}//####[3]####
    /*  ParaTask helper method to access private/protected slots *///####[3]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[3]####
        if (m.getParameterTypes().length == 0)//####[3]####
            m.invoke(instance);//####[3]####
        else if ((m.getParameterTypes().length == 1))//####[3]####
            m.invoke(instance, arg);//####[3]####
        else //####[3]####
            m.invoke(instance, arg, interResult);//####[3]####
    }//####[3]####
//####[5]####
    private void notifyFunc() {//####[5]####
        System.out.println("in notifyFunc");//####[6]####
    }//####[7]####
//####[9]####
    public static void main(String[] args) {//####[9]####
        System.out.println("(1)");//####[11]####
        hello("Sequential");//####[13]####
        System.out.println("(2)");//####[15]####
        TaskID id1 = oneoff_hello();//####[17]####
        System.out.println("(3)");//####[19]####
        HelloWorld hw = new HelloWorld();//####[21]####
        TaskInfo __pt__id2 = new TaskInfo();//####[23]####
//####[23]####
        boolean isEDT = GuiThread.isEventDispatchThread();//####[23]####
//####[23]####
//####[23]####
        /*  -- ParaTask notify clause for 'id2' -- *///####[23]####
        try {//####[23]####
            Method __pt__id2_slot_0 = null;//####[23]####
            __pt__id2_slot_0 = ParaTaskHelper.getDeclaredMethod(hw.getClass(), "notifyFunc", new Class[] {});//####[23]####
            if (false) hw.notifyFunc(); //-- ParaTask uses this dummy statement to ensure the slot exists (otherwise Java compiler will complain)//####[23]####
            __pt__id2.addSlotToNotify(new Slot(__pt__id2_slot_0, hw, false));//####[23]####
//####[23]####
        } catch(Exception __pt__e) { //####[23]####
            System.err.println("Problem registering method in clause:");//####[23]####
            __pt__e.printStackTrace();//####[23]####
        }//####[23]####
        TaskIDGroup id2 = multi_hello(__pt__id2);//####[23]####
        System.out.println("(4)");//####[25]####
        TaskID id3 = interactive_hello();//####[27]####
        System.out.println("(5)");//####[29]####
        TaskID id4 = new HelloWorld().oneoff_hello2();//####[31]####
        System.out.println("(6)");//####[33]####
        TaskIDGroup g = new TaskIDGroup(4);//####[35]####
        g.add(id1);//####[36]####
        g.add(id2);//####[37]####
        g.add(id3);//####[38]####
        g.add(id4);//####[39]####
        System.out.println("** Going to wait for the tasks to execute... ");//####[40]####
        try {//####[41]####
            g.waitTillFinished();//####[42]####
        } catch (ExecutionException e) {//####[43]####
            e.printStackTrace();//####[44]####
        } catch (InterruptedException e) {//####[45]####
            e.printStackTrace();//####[46]####
        }//####[47]####
        System.out.println("** Done! All tasks have now completed.");//####[48]####
    }//####[49]####
//####[51]####
    private static void hello(String name) {//####[51]####
        System.out.println("Hello from " + name);//####[52]####
    }//####[53]####
//####[55]####
    private static volatile Method __pt__oneoff_hello__method = null;//####[55]####
    private synchronized static void __pt__oneoff_hello__ensureMethodVarSet() {//####[55]####
        if (__pt__oneoff_hello__method == null) {//####[55]####
            try {//####[55]####
                __pt__oneoff_hello__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneoff_hello", new Class[] {//####[55]####
                    //####[55]####
                });//####[55]####
            } catch (Exception e) {//####[55]####
                e.printStackTrace();//####[55]####
            }//####[55]####
        }//####[55]####
    }//####[55]####
    private static TaskID<Void> oneoff_hello() {//####[55]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[55]####
        return oneoff_hello(new TaskInfo());//####[55]####
    }//####[55]####
    private static TaskID<Void> oneoff_hello(TaskInfo taskinfo) {//####[55]####
        // ensure Method variable is set//####[55]####
        if (__pt__oneoff_hello__method == null) {//####[55]####
            __pt__oneoff_hello__ensureMethodVarSet();//####[55]####
        }//####[55]####
        taskinfo.setParameters();//####[55]####
        taskinfo.setMethod(__pt__oneoff_hello__method);//####[55]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[55]####
    }//####[55]####
    public static void __pt__oneoff_hello() {//####[55]####
        hello("One-off Task");//####[56]####
    }//####[57]####
//####[57]####
//####[59]####
    private static volatile Method __pt__oneoff_hello2__method = null;//####[59]####
    private synchronized static void __pt__oneoff_hello2__ensureMethodVarSet() {//####[59]####
        if (__pt__oneoff_hello2__method == null) {//####[59]####
            try {//####[59]####
                __pt__oneoff_hello2__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__oneoff_hello2", new Class[] {//####[59]####
                    //####[59]####
                });//####[59]####
            } catch (Exception e) {//####[59]####
                e.printStackTrace();//####[59]####
            }//####[59]####
        }//####[59]####
    }//####[59]####
    private TaskID<Void> oneoff_hello2() {//####[59]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[59]####
        return oneoff_hello2(new TaskInfo());//####[59]####
    }//####[59]####
    private TaskID<Void> oneoff_hello2(TaskInfo taskinfo) {//####[59]####
        // ensure Method variable is set//####[59]####
        if (__pt__oneoff_hello2__method == null) {//####[59]####
            __pt__oneoff_hello2__ensureMethodVarSet();//####[59]####
        }//####[59]####
        taskinfo.setParameters();//####[59]####
        taskinfo.setMethod(__pt__oneoff_hello2__method);//####[59]####
        taskinfo.setInstance(this);//####[59]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[59]####
    }//####[59]####
    public void __pt__oneoff_hello2() {//####[59]####
        System.out.println("Hello from oneoff_hello2");//####[60]####
    }//####[61]####
//####[61]####
//####[63]####
    private static volatile Method __pt__multi_hello__method = null;//####[63]####
    private synchronized static void __pt__multi_hello__ensureMethodVarSet() {//####[63]####
        if (__pt__multi_hello__method == null) {//####[63]####
            try {//####[63]####
                __pt__multi_hello__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__multi_hello", new Class[] {//####[63]####
                    //####[63]####
                });//####[63]####
            } catch (Exception e) {//####[63]####
                e.printStackTrace();//####[63]####
            }//####[63]####
        }//####[63]####
    }//####[63]####
    private static TaskIDGroup<Void> multi_hello() {//####[63]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[63]####
        return multi_hello(new TaskInfo());//####[63]####
    }//####[63]####
    private static TaskIDGroup<Void> multi_hello(TaskInfo taskinfo) {//####[63]####
        // ensure Method variable is set//####[63]####
        if (__pt__multi_hello__method == null) {//####[63]####
            __pt__multi_hello__ensureMethodVarSet();//####[63]####
        }//####[63]####
        taskinfo.setParameters();//####[63]####
        taskinfo.setMethod(__pt__multi_hello__method);//####[63]####
        return TaskpoolFactory.getTaskpool().enqueueMulti(taskinfo, 8);//####[63]####
    }//####[63]####
    public static void __pt__multi_hello() {//####[63]####
        hello("Multi-Task [subtask " + CurrentTask.relativeID() + "]  [thread " + CurrentTask.currentThreadID() + "]  [globalID " + CurrentTask.globalID() + "]  [mulTaskSize " + CurrentTask.multiTaskSize() + "]  [TaskID " + CurrentTask.currentTaskID() + "]  [ISinside? " + CurrentTask.insideTask() + "]  [progress " + CurrentTask.getProgress() + "]");//####[64]####
    }//####[65]####
//####[65]####
//####[67]####
    private static volatile Method __pt__interactive_hello__method = null;//####[67]####
    private synchronized static void __pt__interactive_hello__ensureMethodVarSet() {//####[67]####
        if (__pt__interactive_hello__method == null) {//####[67]####
            try {//####[67]####
                __pt__interactive_hello__method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__interactive_hello", new Class[] {//####[67]####
                    //####[67]####
                });//####[67]####
            } catch (Exception e) {//####[67]####
                e.printStackTrace();//####[67]####
            }//####[67]####
        }//####[67]####
    }//####[67]####
    public static TaskID<Void> interactive_hello() {//####[67]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[67]####
        return interactive_hello(new TaskInfo());//####[67]####
    }//####[67]####
    public static TaskID<Void> interactive_hello(TaskInfo taskinfo) {//####[67]####
        // ensure Method variable is set//####[67]####
        if (__pt__interactive_hello__method == null) {//####[67]####
            __pt__interactive_hello__ensureMethodVarSet();//####[67]####
        }//####[67]####
        taskinfo.setParameters();//####[67]####
        taskinfo.setMethod(__pt__interactive_hello__method);//####[67]####
        taskinfo.setInteractive(true);//####[67]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[67]####
    }//####[67]####
    public static void __pt__interactive_hello() {//####[67]####
        hello("Interactive Task");//####[68]####
    }//####[69]####
//####[69]####
}//####[69]####
