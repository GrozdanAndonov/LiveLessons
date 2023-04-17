The folders in this directory demonstrate various Project Reactor Flux
features, as follows:

. ex1 - This example shows how to apply Project Reactor features
        synchronously to perform basic Flux operations, including
        just(), map(), and subscribe().

. ex2 - This example shows how to reduce and/or multiply big fractions
        asynchronously using Flux features in the Reactor framework,
        including create(), interval(), map(), filter(), doOnNext(),
        take(), subscribe(), then(), range(), subscribeOn(),
        publishOn(), and various thread pools.

. ex3 - This example shows how to reduce and/or multiply big fractions
        asynchronously and concurrently using many advanced Flux
        features in the Project Reactor framework, including
        fromIterable(), map(), create(), flatMap(), filter(),
        collectList(), subscribeOn(), take(), and various types of
        thread pools.  It also shows advanced Mono operations, such as
        first(), when(), flatMap(), subscribeOn(), and the parallel
        thread pool.  It also demonstrates how to combine the Java
        streams framework with the Project Reactor framework.

. ex4 - This example shows how to apply Project Reactor features
        asynchronously to perform a range of Flux operations,
        including fromArray(), flatMap(), and subscribe().  It also
        shows various Mono operations, such as fromSupplier(),
        repeat(), flatMap(), flatMapMany(), flatMapIterable(),
        subscribeOn(), and the parallel thread pool.  It also shows
        how to implement a blocking subscriber that uses various type
        of backpressure mechanisms.

. ex5 - This example demonstrates how to use various Flux,
        ParallelFlux, and Mono operators to perform BigFraction
        multiplications and additions in parallel, as well as download
        and store images from remote web servers in parallel.  The
        Flux operators include fromArray(), parallel(),
        doOnComplete(), and collect().  The ParallelFlux operators
        include runOn(), map(), doOnNext(), reduce(), sequential().
        The Mono operators include doOnSuccess() and then().

. ex6 - This example shows how to apply Project Reactor features
        asynchronously to perform a range of Flux operations,
        including flatMap(), collect(), subscribeOn(), and various
        types of thread pools.  It also shows various Mono operations,
        such as when(), firstWithSignal(), materialize(), flatMap(),
        flatMapMany(), flatMapIterable(), subscribeOn(), and the
        parallel thread pool.  In addition, it demonstrates how to
        combine the Java streams framework with the Project Reactor
        framework.
        
