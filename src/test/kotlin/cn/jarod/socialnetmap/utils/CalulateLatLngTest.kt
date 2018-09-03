package cn.jarod.socialnetmap.utils

import cn.jarod.socialnetmap.SocialNetMapApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(SocialNetMapApplication::class))
class CalulateLatLngTest{


    @Test
    fun getLngByDistance(){

        println(CalulateLatLng)
        println(CalulateLatLng.wide)
        println(CalulateLatLng.wide)
    }

}