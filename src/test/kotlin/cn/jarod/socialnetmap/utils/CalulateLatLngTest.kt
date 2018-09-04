package cn.jarod.socialnetmap.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test




class CalulateLatLngTest{



    @Test
    fun getLngByDistance1(){


    }

    @Test
    @DisplayName("测试在北纬30度，其他参数为0时情况")
    fun getMaxLatByDistance_0_all(){
       assertEquals(30.000000, CalulateLatLng.getMaxLatByDistance(30.000000,0,0.0))
    }

    @Test
    @DisplayName("测试在北纬30度，地图等级为1，高度为1时情况")
    fun getMaxLatByDistance_1_0(){
        assertEquals(30.000009, CalulateLatLng.getMaxLatByDistance(30.000000,1,1.0))
    }

}