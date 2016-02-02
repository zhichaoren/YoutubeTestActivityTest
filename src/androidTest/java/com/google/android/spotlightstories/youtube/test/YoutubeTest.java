package com.google.android.spotlightstories.youtube.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;



@SuppressWarnings("rawtypes")
public class YoutubeTest extends ActivityInstrumentationTestCase2 {
  	private Solo solo;
  	
  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.google.android.spotlightstories.youtube.YoutubeTestActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
  	
  	@SuppressWarnings("unchecked")
    public YoutubeTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.google.android.spotlightstories.youtube.YoutubeTestActivity'
		solo.waitForActivity("YoutubeTestActivity", 2000);
        try {
            Thread.sleep(10000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        //Click on Launch BUGGYNIGHT
        solo.clickOnView(solo.getView(0x2));
        //Wait for activity: 'com.google.android.moxie.common.MoxieActivity'
        assertTrue("MoxieActivity is not found!", solo.waitForActivity("MoxieActivity"));
        dragThroughShow();

	}
    int dragThroughShow() {
        solo.sleep(45000); /* Sleep 45,000 milliseconds before starting test. */

        for(int mainRepetitionCounter = 1; mainRepetitionCounter <= 7; ++ mainRepetitionCounter) {
            for (int dragRepetitionCounter = 1; dragRepetitionCounter <= 36; ++dragRepetitionCounter) {
				/* fromX, toX, fromY, toY, stepCount => a drag operation of 140 pixels to the left.
				*  stepCount represents the number of "move" steps to include in the drag. The less the
				*  number of steps => the faster the drag operation.
				*/
                solo.drag(360, 500, 800, 850, 5);
                solo.sleep(3000); /* Sleep 3000 milliseconds between each swipe. */
            }
        }
        return 0;
    } /* END int loopThroughShow() */
}
