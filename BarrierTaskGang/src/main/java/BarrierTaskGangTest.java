import tasks.CyclicSearchWithCyclicBarrier;
import tasks.CyclicSearchWithPhaser;
import tasks.OneShotSearchWithCountDownLatch;
import utils.Options;
import utils.TaskGang;


/**
 * This test driver showcases how various subclasses of the {@link
 * TaskGang} framework use different Java barrier synchronizers to
 * implement an "embarrassingly parallel" app that concurrently
 * searches for words in one or more lists of input {@link String}
 * objects.
 */
public class BarrierTaskGangTest {
    /**
     * Enumerate the tests to run.
     */
    enum TestsToRun {
        COUNT_DOWN_LATCH,
        CYCLIC_BARRIER,
        PHASER
    }

    /**
     * This is the entry point into the test program.  
     */
    static public void main(String[] args) {
        Options.instance().parseArgs(args);

        Options.printDebugging("Starting BarrierTaskGangTest");

        new CyclicSearchWithPhaser
            (Options.sWordList,
                Options.sVariableNumberOfInputStrings).run();

        /*
        // Iterate through all the tests.
        for (TestsToRun test : TestsToRun.values()) {
            Options.print("Starting " + test);


            // Create/run appropriate type of utils.TaskGang to search for
            // words concurrently.
            // makeTaskGang(Options.sWordList, test).run();

            Options.print("Ending " + test);
        } */

        Options.printDebugging("Ending BarrierTaskGangTest");
    }

    /**
     * Factory method that creates the desired type of utils.TaskGang
     * subclass implementation.
     */
    @SuppressWarnings("SameParameterValue")
    private static Runnable makeTaskGang(String[] wordList,
                                         TestsToRun choice) {
        return switch (choice) {
            case COUNT_DOWN_LATCH -> new OneShotSearchWithCountDownLatch
                (wordList,
                 Options.sOneShotInputStrings);
            case CYCLIC_BARRIER -> new CyclicSearchWithCyclicBarrier
                (wordList,
                 Options.sFixedNumberOfInputStrings);
            case PHASER -> new CyclicSearchWithPhaser
                (wordList,
                 Options.sVariableNumberOfInputStrings);
        };
    }
}
