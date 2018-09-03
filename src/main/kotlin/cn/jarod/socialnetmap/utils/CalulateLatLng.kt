package cn.jarod.socialnetmap.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(value = "classpath:config/application.yml", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "calulate")
class CalulateLatLng
{

    companion object {
        var high:Int = 0
        var wide:Int = 0
        var radius:Int = 0 //地球半径,单位米
    }

    @Value("\${high}")
    fun setHigh(high: Int) {
        CalulateLatLng.high = high
    }

    @Value("\${wide}")
    fun setWide(wide: Int) {
        CalulateLatLng.wide = wide
    }

    @Value("\${radius}")
    fun setRadius(radius: Int) {
        CalulateLatLng.radius = radius
    }

    private fun rad(d: Double): Double {
        return d * Math.PI / 180.0
    }

    /**
     *
     * @param lat1 第一个纬度
     * @param lng1第一个经度
     * @param lat2第二个纬度
     * @param lng2第二个经度
     * @return 两个经纬度的距离
     */
    fun getDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val radLat1 = rad(lat1)
        val radLat2 = rad(lat2)
        val a = radLat1 - radLat2
        val b = rad(lng1) - rad(lng2)

        var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2.0) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2.0)))
        s = s * radius
        s = (Math.round(s * 10000) / 10000).toDouble()

        return s

    }


    fun getLngByDistance(lng: Double, maplevel : Int): Double {


        return 1.0
    }

    fun getLatDistance(lat : Double):Double {
        return 1.0
    }














}