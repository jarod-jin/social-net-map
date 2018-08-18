package cn.jarod.socialnetmap.controller

import cn.jarod.socialnetmap.service.ISignatureService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@ResponseBody
open class SignatureController {
    private var logger: Logger = LoggerFactory.getLogger(SignatureController::class.java)

    @Autowired
    lateinit var signatureService: ISignatureService


    @GetMapping("/tokenSign/{type}")
    open fun sign(@PathVariable type: String,
                  @RequestParam signature: String,
                  @RequestParam nonce: String,
                  @RequestParam timestamp: String,
                  @RequestParam echostr: String): String {
        val valid = signatureService.checkSignature(signature, timestamp, nonce, type)
        when (valid) {
            true -> return echostr
        }
        logger.error("Signature invalid!")
        return ""
    }
}