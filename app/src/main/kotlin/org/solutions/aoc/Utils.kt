import kotlin.reflect.KClass

inline fun <T : Any, U> readResource(
                clss: KClass<T>,
                path: String,
                ctx: U,
                crossinline predicate: (String) -> Boolean,
                crossinline onLine: (U, String) -> U,
                crossinline onNotLine: (U, String) -> U
): U {
        val file = clss.java.classLoader.getResourceAsStream(path)
        var acc = ctx

        file.bufferedReader().use {
                it.lines().forEach {
                        if (predicate(it)) {
                                acc = onLine(acc, it)
                        } else {
                                acc = onNotLine(acc, it)
                        }
                }
        }
        return acc
}
