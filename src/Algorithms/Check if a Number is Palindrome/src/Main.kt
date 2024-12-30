fun isPalindrome(n: Int): Boolean {
    var num = n
    var reversed = 0
    var original = n


    while (num != 0) {
        reversed = reversed * 10 + num % 10
        num /= 10
    }

    if (original == reversed) {
        return true
    } else {
        return false
    }
}