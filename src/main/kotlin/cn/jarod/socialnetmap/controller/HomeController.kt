package cn.jarod.socialnetmap.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
@RequestMapping(value = "/")
class HomeController {
    @RequestMapping(value = "/index", method = arrayOf(RequestMethod.GET))
    fun index(pageModal: ModelMap): String {
        pageModal["data"] = "道可道，非常道；名可名，非常名。\n" +
                "无名，万物之始，有名，万物之母。\n" +
                "故常无欲，以观其妙，常有欲，以观其徼。\n" +
                "此两者，同出而异名，同谓之玄，玄之又玄，众妙之门。"
        return "index"
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun home(pageModal: ModelMap): String {
        pageModal["data"] = "道可道，非常道；名可名，非常名。\n" +
                "无名，万物之始，有名，万物之母。\n" +
                "故常无欲，以观其妙，常有欲，以观其徼。\n" +
                "此两者，同出而异名，同谓之玄，玄之又玄，众妙之门。"
        return "index"
    }
}