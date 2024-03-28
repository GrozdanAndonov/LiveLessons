package edu.vandy.lockmanager.client;

import edu.vandy.lockmanager.common.Lock;
import edu.vandy.lockmanager.common.LockManager;
import edu.vandy.lockmanager.server.LockManagerApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static edu.vandy.lockmanager.common.Constants.Endpoints.*;

/**
 * An auto-generated proxy used by asynchronous WebFlux clients to
 * access the capabilities of the {@link LockManagerApplication}
 * microservice.
 */
public interface LockAPI {
    /**
     * Initialize the {@link Lock} manager.
     *
     * @param permitCount The total number of {@link Lock}
     *                    objects to create
     * @return A {@link Mono} that emits a {@link LockManager}
     *         associated with the state of the semaphore it manages
     */
    @PostExchange(CREATE)
    Mono<LockManager> create(@RequestParam Integer permitCount);

    /**
     * Acquire a {@link Lock}, blocking until one is available.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @return A {@link Mono} that emits a newly acquired {@link Lock}
     */
    @PostExchange(ACQUIRE_LOCK)
    Mono<Lock> acquire(@RequestParam LockManager lockManager);

    /**
     * Acquire {@code permits} number of {@link Lock} objects.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @param permits The number of permits to acquire
     * @return A {@link Flux} that emits a {@link List} containing
     *         {@code permits} newly acquired {@link Lock} objects
     */
    @PostExchange(ACQUIRE_LOCKS)
    Flux<Lock> acquire(@RequestParam LockManager lockManager,
                       @RequestParam Integer permits);

    /**
     * Release the lock back to the {@link Lock} manager.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @param lock The {@link Lock} to release
     * @return A {@link Mono} that emits a {@link Boolean} containing
     *         {@link Boolean#TRUE} if the {@link Lock} was released
     *         properly and {@link Boolean#FALSE} otherwise
     */
    @PostExchange(RELEASE_LOCK)
    Mono<Boolean> release(@RequestParam LockManager lockManager,
                          @RequestParam Lock lock);

    /**
     * Release the {@code locks} back to the {@link Lock} manager.
     *
     * @param lockManager The {@link LockManager} that is associated
     *                    with the state of the semaphore it manages
     * @param locks A {@link List} containing {@link Lock} objects to
     *              release
     * @return A {@link Mono} that emits a {@link Boolean} containing
     *         {@link Boolean#TRUE} if the {@link Lock} was released
     *         properly and {@link Boolean#FALSE} otherwise
     */
    @PostExchange(RELEASE_LOCKS)
    Mono<Boolean> release(@RequestParam LockManager lockManager,
                          @RequestBody List<Lock> locks);
}
