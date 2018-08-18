package cn.jarod.socialnetmap.service

interface ISignatureService {

    fun checkSignature(signature: String, timestamp: String, nonce: String, type: String): Boolean

}