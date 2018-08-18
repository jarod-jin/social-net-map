package cn.jarod.socialnetmap.service.impl


import cn.jarod.socialnetmap.service.ISignatureService
import cn.jarod.socialnetmap.utils.HashUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SignatureService : ISignatureService {
    private val logger: Logger = LoggerFactory.getLogger(SignatureService::class.java)

    // 将Token放到一个map中，以后需要从数据库中获取
    private var tokenMap = mapOf(pair = "fix" to "********************")

    override fun checkSignature(signature: String, timestamp: String, nonce: String, type: String): Boolean {
        // 从参数中获取type，并在Map中得到token
        val token: String = tokenMap[type].toString()
        // 将参数放入一个数组中
        val arr: Array<String> = arrayOf(token, timestamp, nonce)
        // 将数组进行排序
        arr.sort()
        // 再将排序好的数据依次拼接成一个字符串
        val content = StringBuilder()
        for (str: String in arr) {
            content.append(str)
        }
        logger.debug(content.toString())
        // 将字符串进行SHA-1加密，得出结果
        val result = HashUtils.sha1(content.toString())
        logger.debug("Result String:" + result)
        logger.debug("Result:" + result.equals(signature.toUpperCase(), false))
        // 将结果与传进来的签名进行比对，并返回结果
        return result.equals(signature.toUpperCase(), false)
    }
}