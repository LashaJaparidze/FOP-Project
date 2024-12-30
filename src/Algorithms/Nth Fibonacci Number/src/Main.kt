fun nthFibonacci(n: Int): Int {
    if (n <= 0) return 0
    if (n == 1) return 1
    var a = 0
    var b = 1
    var fib = 0
    for (i in 2..n) {
        fib = a + b
        a = b
        b = fib
    }
    return fib
}