package utils

interface SingletonFactory<T> {
    var instance: T?
    fun clear()
}
