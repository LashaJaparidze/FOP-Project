fun largestDigit(n: Int): Int {
    var num = n
    var largest = 0
    while (num != 0) {
        val digit = num % 10
        if (digit > largest) largest = digit
        num /= 10
    }
    return largest
}