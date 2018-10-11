1) asynchronous call in mvc:

controller: async supported to be true for dispatcher servlet
<async-supported>true</async-supported>
in web.xml


The interface HandlerExceptionResolver has only one method
The 'handler' parameter contains the Controller's handler method information where exception occurred. This parameter normally is the instance of HandlerMethod in Spring MVC. The method resolveException() will be invoked even if the exception is thrown outside of the target handler method, before or after handler's invocation but still in the course of target handler method invocation, for example in a handler interceptor.

diff contexts
async support
filter in spring
