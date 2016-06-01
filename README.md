Simple application used to test handling of non-compatible serialization code changes with persistent sessions.

Such a change is simulated by adding non-deserializable (but serializable) instance as session attribute;
this instance is correctly marshalled on shutdown, but attempt to use this session laster fails with exception.

There are two cases with different exceptions, used to reproduce bugs in two versions of WildFly AS (9.0.2 and 10.0.0):
* `java.io.InvalidClassException` at `http://localhost:8080/unmarshall-error/bad1.jsp`
  * this is incorrectly handled by WildFly 9.0.2, causing *infinite recursion* that ends up with StackOverflowError
    * each level of recursion also writes stacktrace to log, producing 40MB of log per request (or more)
    * the error is also persistent, each browser request fails this way until session cookie expires (or is deleted)!
  * WildFly 10.0.0 behaves correctly (session is removed and request gets new one)
* `java.lang.IllegalArgumentException` at `http://localhost:8080/unmarshall-error/bad2.jsp`
  * this exception (actually, most runtime exceptions) is not handled at all and request fails, showing error to user
  * unfortunately, the session is not invalidated; user gets errors until session cookie expires or is deleted!

