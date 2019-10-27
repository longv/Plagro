package longv.muzik

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Factory(val id: String, val clazz: KClass<*>)