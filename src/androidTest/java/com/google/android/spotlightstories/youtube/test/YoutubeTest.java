package com.google.android.spotlightstories.youtube.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;



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
        // Wait for activity: 'com.google.android.spotlightstories.youtube.YoutubeTestActivity'
        // check text "YouTubeTest"
		solo.waitForActivity("YoutubeTestActivity", 2000);
        assertTrue(solo.searchText("YouTubeTest"));

        try {
            Thread.sleep(10000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // Click on Launch the BUGGYNIGHT/HELP/ON-ICE/SPECIAL-DELIVERY/Pearl
        solo.clickOnButton("Launch HELP");
        solo.sleep(10000);
        // Wait for activity: 'com.google.android.moxie.common.MoxieActivity'
        // search test "YouTubeTest" on launch page to verify if show start playing
        assertFalse(solo.searchText("YouTubeTest"));
        dragThroughShow();

	}
    int dragThroughShow() {
        solo.sleep(30000);/* Sleep 30,000 milliseconds before starting test. */

        for(int mainRepetitionCounter = 1; mainRepetitionCounter <= 1000; ++mainRepetitionCounter) {
            for (int dragRepetitionCounter = 1; dragRepetitionCounter <= 7 ; ++dragRepetitionCounter) {
				/* fromX, toX, fromY, toY, stepCount => a drag operation of 140 pixels to the left.
				*  stepCount represents the number of "move" steps to include in the drag. The less the
				*  number of steps => the faster the drag operation.
				*/
                solo.drag(100, 400, 1050, 750, 5);
                solo.sleep(2000); /* Sleep 2000 milliseconds between each swipe. */
            }
            // check if show played to the end
            boolean out = solo.searchText("YouTubeTest");
            if ( out == true ) {
                break;
            }
            // System.out.println("printing the output of "+ out);
        }
        return 0;
    } /* END int loopThroughShow() */
}
