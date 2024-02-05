package edu.vandy.lockmanager.server;

import edu.vandy.lockmanager.common.Callback;
import edu.vandy.lockmanager.common.Lock;
import edu.vandy.lockmanager.common.LockManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static edu.vandy.lockmanager.common.Constants.Endpoints.*;
import static edu.vandy.lockmanager.utils.Utils.log;

/**
 * This Spring {@code @RestController} defines methods that provide a
 * lock manager for a semaphore that can be shared amongst multiple
 * synchronous Spring WebMVC clients.
 */
@RestController
public class LockManagerController {
    /**
     * Auto-wire the {@link LockManagerController} to the {@link
     * LockManagerService}.
     */
    @Autowired
    LockManagerService mService;

    /**
     * Initialize the {@link Lock} manager.
     *
     * @param permits The number of {@link Lock} objects to
     *                    manage
     * @return A {@link LockManager} that is associated with
     *         the state of the semaphore it manages
     */
    @PostMapping(CREATE)
    public LockManager create(@RequestParam Integer permits) {
        log("LockController.create("
            + permits
            + ")");

        return mService
            // Forward to the service.
            .create(permits);
    }

    /**
     * Acquire a {@link Lock}, blocking until one is available.  A
     * {@link DeferredResult} result is used to avoid blocking a
     * thread in the Servlet thread pool.
     *
     * @param lockManager The {@link LockManager} that is associated
     *         with the state of the semaphore it manages
     * @return A {@link DeferredResult} to a {@link Lock} that a
     *         client can acquire and hold during critical sections
     */
    @PostMapping(ACQUIRE_LOCK)
    public DeferredResult<Lock> acquire
    (@RequestParam LockManager lockManager) {
        log("LockController.acquire()");

        // Create a DeferredResult containing a Lock object.
        var deferredResult = new DeferredResult<Lock>();

        // Create a Callback to handle successful or failed lock operations.
        var callback = new Callback() {
            @Override
            public void onSuccess(Lock lock) {
                deferredResult.setResult(lock);
            }

            @Override
            public void onError(Throwable error) {
                deferredResult.setErrorResult(error);
            }
        };

        mService
            // Forward to the service, which runs this method and the
            // associated callback asynchronously.
            .acquire(lockManager, callback);

        log("LockController.acquire() - returning deferredResult");

        // Return the DeferredResult immediately.
        return deferredResult;
    }

    /**
     * Acquire {@code permits} number of {@link Lock} objects.
     *
     * @param lockManager The {@link LockManager} that is associated
     *         with the state of the semaphore it manages
     * @param permits The number of permits to acquire
     * @return A {@link DeferredResult<List>} containing {@code
     *         permits} number of acquired {@link Lock} objects
     */
    @PostMapping(ACQUIRE_LOCKS)
    DeferredResult<List<Lock>> acquire
        (@RequestParam LockManager lockManager,
         @RequestParam Integer permits) {
        log("LockController.acquire("
            + permits
            + ")");

        return mService
            // Forward to the service.
            .acquire(lockManager, permits);
    }

    /**
     * Release the {@link Lock} so other clients can acquire it.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @param lock The {@link Lock} to release
     * @return A {@link Boolean} that's {@link Boolean#TRUE} if the
     *         {@link Lock} was released properly and {@link
     *         Boolean#FALSE} otherwise.
     */
    @PostMapping(RELEASE_LOCK)
    public Boolean release(@RequestParam LockManager lockManager,
                           @RequestParam Lock lock) {
        log("LockController.release("
            + lock
            + ")");

        return mService
            // Forward to the service.
            .release(lockManager, lock);
    }

    /**
     * Release the {@code locks} so other clients can acquire them.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @param locks A {@link List} that contains {@link Lock} objects
     *              to release
     * @return A {@link Boolean} that's {@link Boolean#TRUE} if the
     *         {@link Lock} was released properly and {@link
     *         Boolean#FALSE} otherwise.
     */
    @PostMapping(RELEASE_LOCKS)
    public Boolean release
        (@RequestParam LockManager lockManager,
         @RequestBody List<Lock> locks) {
        log("LockController.release("
            + locks
            + ")");

        return mService
            // Forward to the service.
            .release(lockManager, locks);
    }
}

