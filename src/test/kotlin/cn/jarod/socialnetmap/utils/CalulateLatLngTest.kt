package cn.jarod.socialnetmap.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class CalulateLatLngTest{



    @Test
    @DisplayName("参数为0时情况")
    fun getDeltaLatByDistance_0_all(){
       assertEquals(0.000000, CalulateLatLng.getDeltaLatByDistance(0,0.0))
    }

    @Test
    @DisplayName("地图等级为1，高度为1时情况")
    fun getDeltaLatByDistance_1_0(){
        assertEquals(0.000009, CalulateLatLng.getDeltaLatByDistance(1,1.0))
    }

    @Test
    @DisplayName("赤道上，其他参数为0的情况")
    fun getDeltaLngByDistance_0_all(){
        assertEquals(0.000000, CalulateLatLng.getDeltaLngByDistance(0.0,0,0.0))
    }

    @Test
    @DisplayName("赤道上，地图等级为1，高度为1时情况")
    fun getDeltaLngByDistance_0_1_1(){
        assertEquals(0.000009, CalulateLatLng.getDeltaLngByDistance(0.0,1,1.0))
    }

    @Test
    @DisplayName("30纬度，地图等级为1，高度为1时情况")
    fun getDeltaLngByDistance_30_1_1(){
        assertEquals(0.000001, CalulateLatLng.getDeltaLngByDistance(30.0,1,1.0))
    }


}