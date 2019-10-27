package longv.muzik

import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException

class FactoryAnnotatedClass @Throws(IllegalArgumentException::class) constructor(
    /**
     * The original element that was annotated with @Factory
     */
    typeElement: TypeElement
) {
    /**
     * Get the full qualified name of the type specified in  [Factory.clazz].
     *
     * @return qualified name
     */
    var qualifiedSuperClassName: String?
        private set

    /**
     * Get the simple name of the type specified in  [Factory.clazz].
     *
     * @return qualified name
     */
    var simpleTypeName: String?
        private set

    /**
     * Get the id as specified in [Factory.id].
     * return the id
     */
    var id: String
        private set

    init {
        val annotation = typeElement.getAnnotation(Factory::class.java)

        if (annotation.id.isEmpty()) {
            throw IllegalArgumentException("id() in ${Factory::class.java.simpleName} for class ${typeElement.qualifiedName} is null or empty! that's not allowed")
        } else {
            id = annotation.id
        }
        typeElement.superclass
        try {
            annotation.clazz.apply {
                qualifiedSuperClassName = qualifiedName
                simpleTypeName = simpleName
            }
        } catch (mte: MirroredTypeException) {
            val classTypeMirror = mte.typeMirror as DeclaredType
            val classTypeElement = classTypeMirror.asElement() as TypeElement
            qualifiedSuperClassName = classTypeElement.qualifiedName.toString()
            simpleTypeName = classTypeElement.simpleName.toString()
        }
    }
}