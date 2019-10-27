package longv.muzik

import java.io.IOException
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import kotlin.reflect.KClass
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.Filer

class FactoryGenerator(private val clazz: KClass<*>, private val elements: List<TypeElement>) {
    @Throws(IOException::class)
    fun generateCode(elementUtils: Elements, filer: Filer) {
//        val createMethod = MethodSpec.methodBuilder("create")
//            .addModifiers(Modifier.PUBLIC)
//            .returns(clazz.java)
//            .addParameter(Any::class.java, "clazz")
//            .addStatement("\$T.out.println(\$S)", System::class.java, "Hello, JavaPoet!")
//            .build()

//        val factoryClass = TypeSpec.classBuilder("TestFactory")
////            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//            //.addMethod(createMethod)
//            .build()

//        val classElement = elementUtils.getTypeElement(clazz.qualifiedName)
//        val packageElement = elementUtils.getPackageOf(classElement)
//        val javaFile = JavaFile.builder("longv.muzik", factoryClass)
//            .build()
//
//        javaFile.writeTo(filer)
    }
}