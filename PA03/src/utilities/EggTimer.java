package utilities;
import java.util.Timer;
import java.util.TimerTask;

public class EggTimer extends TimerTask {

      /*  public static attributes
       *****************************/

      /*  private static attributes
       ******************************/

      /*  attributes
       ***************/

   private Timer timer;       // provides one pulse every second
   private int secondsLeft;   // for counting down from whatever 

      /*  constructors
       *****************/

      /**
       *  Creates a one-time EggTimer that starts right away and counts
       *  down from the parameter, in seconds.
       *
       *   @param secondsToCount The value from which it counts down.
       */

   public EggTimer( int secondsToCount ) {

      secondsLeft = secondsToCount;
      timer = (0 < secondsLeft) ? new Timer() : null;
      if ( timer != null ) timer.scheduleAtFixedRate(this,1000,1000);

   } // EggTimer

      /*  public methods
       *******************/

      /**
       *  Description
       *
       *   @param name Description
       *  @return Description
       *  @throws Full.class.name Description
       */

      /**
       *  Respond to an alert from the java.util.Timer object. This is
       *  a method required in extending the java.TimerUser class.
       *
       *  The seconds left counter is decremented. If the EggTimer runs out
       *  the Timer is stopped and forgotten.
       */

   public void run() {

      secondsLeft--;
      if ( secondsLeft <= 0 ) stop();

   } // run

      /**
       *  Fetch the seconds left.
       *  @return How many seconds are left on this EggTimer.
       */

   public int getSecondsLeft() { return secondsLeft; }

      /**
       *  Fetch the time left as a String in the format m:ss.
       *  @return The time left as a String.
       */

   public String getTimeLeft() {

      int minutesPart = secondsLeft / 60;   // minutes portion of String
      int secondsPart = secondsLeft % 60;   // seconds portion of String

      return   String.valueOf( minutesPart )
             + ((secondsPart<10) ? ":0" : ":")
             + String.valueOf( secondsPart );

   } // getTimeLeft

      /**
       *  Stop this EggTimer. It may not be restarted.
       */

   public void stop() {

      if ( timer != null ) {
         timer.cancel();
         timer = null;
      }

   } // stop

} // EggTimer
