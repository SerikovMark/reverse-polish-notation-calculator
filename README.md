# calculator-application
This is calculator application with implementation of reverse polish notation calculator. Now supported only command line mode.
Or you can just build a project and include it as a maven dependency or add jar file to class path of your application

###Requirements
```
maven
java 1.8 or above
``` 

###Building an application
In order to build application just run
`mvn clean package` or `mvn clean install` if you want to install jar file in .m2 directory (maven required)

###Run the application
In order to run application just type in command line `java -jar calculator-application.jar`

###Usage
You can pass all your input in one line, for example `3 1 * 1 / 5 - 8 +`, `5 3 2 + /`, or separate it as a different inputs
```
>5
>3
>2
>+
>/
8.0
4.0
>1
>/
4.0
```  

If you want to exit in any time of execution just type char `q` and press enter or close std input.

###Maven dependency
If you installed application in .m2 directory or your nexus or analogue artifacts repository you can add it as maven 
dependency in your project 
```
    <dependency>
        <groupId>com.calculator</groupId>
        <artifactId>calculator-application</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

###Using API
If you include this application as a dependency in your project you can use
`Calculator` interface and `ReversePolishNotationCalculator` class as it implementation.

Before using it you should initialize possible arithmetic operations by calling 

`OperationService.init()`

From the box supported four operation such as `+, -, * , /`

But you can add your own operation by implementing `OperationExecutor` interface and register it in `OperationService` by calling 
next static method 

```OperationService.register("operation signature", OperationExecutorImpl)```,

but use it with care because you can override existing logic of operations.

For calculation you should use method 
`public List<CalculationResult> calculate(Deque<Double> numbers, String userInput)` and pass to it two parameters.

###Architecture solutions
I decided use pure java because of I wanted it easy to use and run without unnecessary libs.
Of course we could use spring for dependencies injection, but on this stage of development it would be overhead, moreover if application will grows
it is not to hard add spring support because I used modular approach and injected my dependencies by constructor.

For operations I used separated service. So in future we could easy change implementation and for example added support for
hot load new operations(For example via class load).

For now application supports only command line interface, but it is easy to add new user interface (UI), all that we need
implement `UserInterface` interface and of course use inside of implementation `Calculator` interface.
I decided start user interface in new thread because if in future we will add new UI in order to UIs not block each other 
(waiting of user inputs blocks execution of application) all of them should be run in different threads. In future we can use some 
Thread pool executor instead of run threads directly.

Also I added some wrapper for response from `calculate` method from `ReversePolishNotationCalculator`, because I wanted that user of this methods
could decide witch output he would show to user of his UI and in that way `ReversePolishNotationCalculator` doesn't know anything about its user
and only provide results of his actions inside.

I covered most used operating by unit tests, but of course there is lot to cover else.

###Possible improvements
    Functionality:
        -Added logging in order to know what happens in application and if users catch
         some bugs we could understand what happened.
        -Added hot load of new operations
        -Added more test cases.
        
    UX:
        -Added user help
        -Mayby add more details hints to user and output of the operation results and process.
    
