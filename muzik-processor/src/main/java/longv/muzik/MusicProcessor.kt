package longv.muzik

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

class MusicProcessor: AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>?, env: RoundEnvironment?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
