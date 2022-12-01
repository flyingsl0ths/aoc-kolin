import kotlin.reflect.KClass

inline fun <T : Any> readResource(
                clss: KClass<T>,
                path: String,
                noinline predicate: (String) -> Boolean,
                crossinline onLine: (String) -> Unit,
                crossinline onNotLine: (String) -> Unit
): Unit {
        val file = clss.java.classLoader.getResourceAsStream(path)

        file.use {
                it.bufferedReader().use {
                        it.lines().forEach {
                                if (predicate(it)) {
                                        onLine(it)
                                } else {
                                        onNotLine(it)
                                }
                        }
                }
        }
}
