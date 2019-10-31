package me.longv.muzik

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.Messager
import javax.annotation.processing.Filer
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.annotation.processing.ProcessingEnvironment
import javax.tools.Diagnostic
import java.nio.file.Files.size
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror
import java.lang.reflect.Modifier.ABSTRACT
import javax.lang.model.SourceVersion
import javax.lang.model.element.*


class FactoryProcessor: AbstractProcessor() {
    private lateinit var typeUtils: Types
    private lateinit var elementUtils: Elements
    private lateinit var filer: Filer
    private lateinit var messager: Messager
    //private val factoryClasses = LinkedHashMap<String, FactoryGroupedClasses>()

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        typeUtils = processingEnv.typeUtils
        elementUtils = processingEnv.elementUtils
        filer = processingEnv.filer
        messager = processingEnv.messager
    }

    override fun getSupportedAnnotationTypes(): Set<String> = LinkedHashSet<String>().apply {
        add(Factory::class.java.canonicalName)
    }

    @Suppress("UNCHECKED_CAST")
    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        env.getElementsAnnotatedWith(Factory::class.java).apply{
//            forEach {
//                if (!isElementValid(it)) {
//                    return@apply
//                }
//            }

            //groupBy { it.getAnnotation(Factory::class.java).clazz }
                //.mapValues { it.value as List<TypeElement> }
                //.map { FactoryGenerator(it.key, it.value) }
                //.forEach { it.generateCode(elementUtils, filer) }
        }
        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    private fun isElementValid(element: Element): Boolean {
        // Element must be a class
        if (element.kind != ElementKind.CLASS) {
            notifyError("Only classes can be annotated with ${element.simpleName}", element)
            return false
        }

        // Cast for further usage
        element as TypeElement

        // Class must be public
        if (!element.modifiers.contains(Modifier.PUBLIC)) {
            notifyError("The class ${element.qualifiedName} is not public", element)
            return false
        }

        // Class cannot be an abstract class
        if (element.modifiers.contains(Modifier.ABSTRACT)) {
            notifyError("The class ${element.qualifiedName} is abstract. You can't annotate abstract classes with ${Factory::class.java.simpleName}", element)
            return false
        }

//        // Check inheritance: Class must be childclass as specified in @Factory.type();
//        val superClassElement = elementUtils.getTypeElement(item.getQualifiedFactoryGroupName())
//        if (superClassElement.kind == ElementKind.INTERFACE) {
//            // Check interface implemented
//            if (!classElement.getInterfaces().contains(superClassElement.asType())) {
//                error(
//                    classElement, "The class %s annotated with @%s must implement the interface %s",
//                    classElement.getQualifiedName().toString(), Factory::class.java.simpleName,
//                    item.getQualifiedFactoryGroupName()
//                )
//                return false
//            }
//        } else {
//            // Check subclassing
//            var currentClass = classElement
//            while (true) {
//                val superClassType = currentClass.getSuperclass()
//
//                if (superClassType.getKind() == TypeKind.NONE) {
//                    // Basis class (java.lang.Object) reached, so exit
//                    error(
//                        classElement, "The class %s annotated with @%s must inherit from %s",
//                        classElement.getQualifiedName().toString(), Factory::class.java.simpleName,
//                        item.getQualifiedFactoryGroupName()
//                    )
//                    return false
//                }
//
//                if (superClassType.toString() == item.getQualifiedFactoryGroupName()) {
//                    // Required super class found
//                    break
//                }
//
//                // Moving up in inheritance tree
//                currentClass = typeUtils.asElement(superClassType) as TypeElement
//            }
//        }

        // Check if an empty public constructor is given
        val emptyConstructorElement = element.enclosedElements.find {
            (it as? ExecutableElement)?.run { parameters.size == 0 && modifiers.contains(Modifier.PUBLIC)
            } ?: false
        }
        return if (emptyConstructorElement == null) {
            notifyError("The class ${element.qualifiedName} must provide an public empty default constructor", element)
            false
        } else {
            true
        }
    }

    private fun notifyError(message: String, element: Element) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element)
    }
}
