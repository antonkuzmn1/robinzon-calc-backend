package cloud.robinzon.backend.data.fm

import cloud.robinzon.backend.data.fm.resources.FmEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/data/fm")
class FmRest(
    private val fmService: FmService
) {

    @GetMapping
    fun getAll(): List<FmEntity> {
        return fmService.all
    }

}