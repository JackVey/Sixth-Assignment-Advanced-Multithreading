# Advanced Multithreading


## Pi calculation
For this problem, i used several algorithm and ways. those algorithm are as follows👇:

### 1- [Leibniz formula](https://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80#)

<img src="https://d2vlcm61l7u1fs.cloudfront.net/media/0ee/0eeaa57b-570b-4444-93bc-7bc2f4d83931/phpHCRPXf.png" alt="leibniz formula">

The big problem with this formula, as mentioned in [Wikipedia](https://en.wikipedia.org/wiki/Leibniz_formula_for_%CF%80#Unusual_behaviour), is that it generate wrong digits!
so it could not be used for accurate calculation of pi

### 2- Chudnovsky algorithm

<img src="https://www.guinnessworldrecords.com/Images/the-equation-to-calculate-pi_tcm25-694715.jpg" alt="Chudnovsky algorithm">

This algorithm is the most accurate one which is used to calculate billions digits of constant pi.
The problem with this algorithm is, and that is pretty obvious, that it is too **HARD** and **COMPLEX** to implement.
### 3- [BBP formula](https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula)

<img src="http://experimentalmath.info/blog/wp-content/uploads/2013/11/bbp-formula.jpg" alt="BBP formula">

This one saved my day! with a few repetition(by few i mean 10000 terms), we can get accurate digits up to 1000!
I used this algorithm for my final solution to this problem. for more details about this formula, you can take a look on [wikipedia](https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula) page.

Still Chudnovsky algorithm with a recursive implement is the best way to calculate pi, but for multi thread i belive BBP formula is beeter.

## Semaphore
### What is a semaphore?
A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero, then access is allowed. If it is zero, then access is denied. What the counter is counting are permits that allow access to the shared resource. Thus, to access the resource, a thread must be granted a permit from the semaphore.

### How it works?
in general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.
If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
Otherwise, the thread will be blocked until a permit can be acquired.
When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
If there is another thread waiting for a permit, then that thread will acquire a permit at that time.
Java provide Semaphore class in java.util.concurrent package that implements this mechanism, so you don’t have to implement your own semaphores.

### Constructors in Semaphore class
`Semaphore(int num)`

`Semaphore(int num, boolean how)`

Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time. If it is one, then only one thread can access the resource at any one time. By default, all waiting threads are granted a permit in an undefined order. By setting how to true, you can ensure that waiting threads are granted a permit in the order in which they requested access.

### How i solved the problem?
It was quite easy, all i had to do was to create a `semaphore` object in Controller class and pass it to Operator object. then by using `semaphore.acquire()` and lock on of the block of semaphore and by using `semaphore.release()` release it. to test the semaphore, i used `System.out.printf("Operator %s accessed to the Resources.%n", name)` and `System.out.printf("Operator %s released the Resources.%n", name)` to find out when and by who the resource is used.
