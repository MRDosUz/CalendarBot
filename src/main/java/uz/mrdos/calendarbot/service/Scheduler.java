package uz.mrdos.calendarbot.service;


// Importing required classes

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.mrdos.calendarbot.enums.EntryExitEnum;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j

// Class
public class Scheduler {





    @Scheduled(cron = "0 */30 * * * *")
    public void cronEntryExit() throws InterruptedException {



    }



    // Method
    // To trigger the scheduler every 3 seconds with
    // an initial delay of 5 seconds.
//    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
//    public void scheduleTask()
//    {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "dd-MM-yyyy HH:mm:ss.SSS");
//
//        String strDate = dateFormat.format(new Date());
//
//        System.out.println(
//                "Fixed Delay Scheduler: Task running at - "
//                        + strDate);
//    }



//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)



//    @Scheduled(cron = "0 */30 * * * *")
//
//    public void cronJobSch() throws InterruptedException {
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        String s = "Aka Nurik soat "  + strDate + " bo'ldi";
////        telegramBotService.sendMessage(1055894713, s);
////        telegramBotService.sendMessage(823411392, s);
//        System.out.println("Java cron job expression:: " + strDate);
//
//        //Thread.sleep(1000);
//    }

}



// Annotation
//9 211 368
//1 625 535