inline fun <reified T, U> readResource(
                path: String,
                ctx: U,
                crossinline predicate: (String) -> Boolean,
                crossinline onLine: (U, String) -> U,
                crossinline onNotLine: (U, String) -> U
): U {
        val file = T::class.java.classLoader.getResourceAsStream(path)
        var acc = ctx

        file.bufferedReader().use {
                it.forEachLine {
                        if (predicate(it)) {
                                acc = onLine(acc, it)
                        } else {
                                acc = onNotLine(acc, it)
                        }
                }
        }
        return acc
}

inline fun <reified T, U> readLines(
                path: String,
                ctx: U,
                crossinline onReadLine: (U, String) -> U,
): U {
        val file = T::class.java.classLoader.getResourceAsStream(path)

        var acc = ctx

 // it.lines().forEach { acc = onReadLine(acc, it) } 
        file.bufferedReader().use {
		for line in it.lines(){
println(line)
		}
	}

        return acc
}
