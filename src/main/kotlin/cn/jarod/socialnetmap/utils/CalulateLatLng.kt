package cn.jarod.socialnetmap.utils

import java.math.BigDecimal
import java.math.RoundingMode



object CalulateLatLng
{

    private val R:Int = 6378137 //地球半径,单位米

    private val ACCURACY:Int = 6

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
    fun getDistanceFromTwoPoi(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Double {
        val radLat1 = rad(lat1)
        val radLat2 = rad(lat2)
        val a = radLat1 - radLat2
        val b = rad(lng1) - rad(lng2)

        var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2.0) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2.0)))
        s = s * R
        s = (Math.round(s * 10000) / 10000).toDouble()

        return s

    }


    /**
     * @param maplevel 地图级别
     * @param high 地图高度为比例尺的倍数
     * @return 返回位度变化
     */
    fun getDeltaLatByDistance(maplevel: Int, high: Double):Double {
        var bd = BigDecimal(high * maplevel * 180 /  Math.PI / R)
        bd = bd.setScale(ACCURACY, RoundingMode.HALF_UP)
        return bd.toDouble()
    }

    /**
     * @param lat 维度
     * @param maplevel 地图级别
     * @param wide 地图宽度为比例尺的倍数
     * @return 返回经度变化
     */
    fun getDeltaLngByDistance(lat: Double, maplevel: Int, wide: Double): Double {
        var bd = BigDecimal(wide * maplevel * 180 * Math.cos(lat) / Math.PI / R)
        bd = bd.setScale(ACCURACY, RoundingMode.HALF_UP)
        return bd.toDouble()
    }


}