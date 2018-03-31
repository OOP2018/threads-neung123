## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |         10,000,000 |        0.022518 |
| Using ReentrantLock     |         10,000,000 |        0.935647 |
| Syncronized method      |         10,000,000 |        0.929326 |
| AtomicLong for total    |         10,000,000 |        0.372653 |

## 1. Using unsynchronized counter object

answer the questions (1.1 - 1.3)

`1.1) I ran the ThreadSum program 5 times using limit = 1,000. I found that the total is not always equals to 0 and it is unstable. 
1.2) Record time using limit = 10,000,000: 0.020980, 0.021543 and 0.025031
1.3) The total is unstable because when run 2 threads, they are running in parallel.  Sometime, both thread can access Counter at the same time and return wrong value.`
## 2. Implications for Multi-threaded Applications

How might this affect real applications?  
`Example: In bank application, if customer access his/her bank account on more than one ATM machine(or online banking) and deposit at the same time. He/she will be able to deposit the money even if it is more than the balance.`

## 3. Counter with ReentrantLock

answer questions 3.1 - 3.4

`3.1) The result is always = 0.
 3.2) The results are different from problem 1 because when use ReentrantLock you will has only one thread running in the application.
 3.3) ReentrantLock is managing the thread by lock another thread and allow only one thread running at that time. It is good to use it when your application have the data that can be overwrite or when you want to have only one thread running at the time.
 3.4) If you don’t write "finally { lock.unlock(); }" in the code and 1 thread throw an error, another thread will not be run because it isn’t unlock yet.`

## 4. Counter with synchronized method

answer question 4

`4.1) The result is always = 0.
 4.2) The results are different from problem 1 because when use synchronized method, it will not let another thread access the resource until on thread finish its work
 4.3) In java, "synchronized" means that only one thread at the time should be able to access the method or resource. It is good to use it when your application have more than one thread that are reading and writing to the same resource.`

## 5. Counter with AtomicLong

answer question 5

`5.1) It still fixes the error in problem 1 because AtomicCounter use AtomicLong to return return the current result.
 5.2) It is good to use AtomicLong (or AtomicDouble, AtomicInteger) when you want to speed up your program.`

## 6. Analysis of Results

answer question 6

`6.1) I got AtomicLong is the fastest and ReentrantLock is the slowest.
 6.2) If my program have many threads that have to access the resource I will use Syncronized method even if it is the slowest but I’m sure that only one thread modifies the resource at any one time.`

## 7. Using Many Threads (optional)

