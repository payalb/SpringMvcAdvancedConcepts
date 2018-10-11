1) asynchronous call in mvc:

controller: async supported to be true for dispatcher servlet
<async-supported>true</async-supported>
in web.xml


The interface HandlerExceptionResolver has only one method
The 'handler' parameter contains the Controller's handler method information where exception occurred. This parameter normally is the instance of HandlerMethod in Spring MVC. The method resolveException() will be invoked even if the exception is thrown outside of the target handler method, before or after handler's invocation but still in the course of target handler method invocation, for example in a handler interceptor.

diff contexts
async support
filter in spring


DeferredResult processing works as follows:

The controller returns a DeferredResult and saves it in some in-memory queue or list where it can be accessed.

Spring MVC calls request.startAsync().

Meanwhile, the DispatcherServlet and all configured filters exit the request processing thread, but the response remains open.

The application sets the DeferredResult from some thread, and Spring MVC dispatches the request back to the Servlet container.

The DispatcherServlet is invoked again, and processing resumes with the asynchronously produced return value.

Callable processing works as follows:

The controller returns a Callable.

Spring MVC calls request.startAsync() and submits the Callable to a TaskExecutor for processing in a separate thread.

Meanwhile, the DispatcherServlet and all filters exit the Servlet container thread, but the response remains open.

Eventually the Callable produces a result, and Spring MVC dispatches the request back to the Servlet container to complete processing.

The DispatcherServlet is invoked again, and processing resumes with the asynchronously produced return value from the Callable.

HandlerInterceptor instances can be of type AsyncHandlerInterceptor, to receive the afterConcurrentHandlingStarted callback on the initial request that starts asynchronous processing (instead of postHandle and afterCompletion).

HandlerInterceptor implementations can also register a CallableProcessingInterceptor or a DeferredResultProcessingInterceptor, to integrate more deeply with the lifecycle of an asynchronous request (for example, to handle a timeout event). See AsyncHandlerInterceptor for more details.

DeferredResult provides onTimeout(Runnable) and onCompletion(Runnable) callbacks. See the Javadoc of DeferredResult for more details. Callable can be substituted for WebAsyncTask that exposes additional methods for timeout and completion callbacks.