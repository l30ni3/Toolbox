package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import org.junit.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity> (MainActivity.class);

    private MainActivity mActivity = null;
    private String userInput = "Alexa";

    Instrumentation.ActivityMonitor monitorSearch = getInstrumentation().addMonitor(SearchById.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorTools = getInstrumentation().addMonitor(ShowAllTools.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorMachines = getInstrumentation().addMonitor(ShowAllMachines.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorMaterial = getInstrumentation().addMonitor(ShowAllMaterial.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch () {
        View view = mActivity.findViewById(R.id.editText);
        assertNotNull(view);
    }

    @Test
    public void testLaunchSearch () {
        assertNotNull(mActivity.findViewById(R.id.button));
        onView(withId(R.id.button)).perform(click());
        Activity SearchById = getInstrumentation().waitForMonitorWithTimeout(monitorSearch, 5000);
        assertNotNull (SearchById);
        SearchById.finish();
    }

    @Test
    public void testLaunchTools () {
        assertNotNull(mActivity.findViewById(R.id.buttonTools));
        onView(withId(R.id.buttonTools)).perform(click());
        Activity ShowAllTools = getInstrumentation().waitForMonitorWithTimeout(monitorTools, 5000);
        assertNotNull (ShowAllTools);
        ShowAllTools.finish();
    }

    @Test
    public void testLaunchMachines () {
        assertNotNull(mActivity.findViewById(R.id.buttonMachines));
        onView(withId(R.id.buttonMachines)).perform(click());
        Activity ShowAllMachines = getInstrumentation().waitForMonitorWithTimeout(monitorMachines, 5000);
        assertNotNull (ShowAllMachines);
        ShowAllMachines.finish();
    }

    @Test
    public void testLaunchMaterial () {
        assertNotNull(mActivity.findViewById(R.id.buttonMaterial));
        onView(withId(R.id.buttonMaterial)).perform(click());
        Activity ShowAllMaterial = getInstrumentation().waitForMonitorWithTimeout(monitorMaterial, 5000);
        assertNotNull (ShowAllMaterial);
        ShowAllMaterial.finish();
    }

    @Test
    public void testUserInput () {
        //input text in the editText view
        onView(withId(R.id.editText)).perform(replaceText("Alexa"));
        //perform button click
        onView(withId(R.id.button)).perform(click());
        //checking text output
        onView(withId(R.id.searchStringOutput)).check(matches(withText("Alexa")));
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}