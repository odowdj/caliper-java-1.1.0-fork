package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.actions.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class NavigationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubVolume epub;
    private DigitalResource fromResource;
    private EpubSubChapter target;
    private NavigationEvent event;
    private static final Logger log = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Build epub
        epub = TestUtils.buildEpubVolume43();

        // Build previous location
        fromResource = TestUtils.buildAmRev101LandingPage();

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Build event
        event = TestUtils.buildEpubNavigationEvent(learningContext, epub, Action.NAVIGATED_TO, fromResource, target);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Navigation event is serialized to JSON with expected values",
        jsonFixture("fixtures/caliperNavigationEvent.json"), serialize(event));
    }
}