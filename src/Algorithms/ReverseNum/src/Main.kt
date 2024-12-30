fun reverseNumber(n: Int): Int {
    var num = n
    var reversed = 0
    while (num != 0) {
        reversed = reversed * 10 + num % 10
        num /= 10
    }
    return reversed
}