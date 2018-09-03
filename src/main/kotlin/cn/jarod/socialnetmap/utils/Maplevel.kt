package cn.jarod.socialnetmap.utils

object Maplevel {
    enum class MapLevel(var level: Int, var scale: Int) {

        three(3, 2000000),

        four(4, 1000000),

        five(5, 1000000),

        six(6, 200000),

        seven(7, 100000),

        eight(8, 50000),

        nine(9, 25000),

        ten(10, 20000),

        eleven(11, 10000),

        twelve(12, 5000),

        thirteen(13, 2000),

        fourteen(14, 1000),

        fifteen(15, 500),

        sixteen(16, 200),

        seventeen(17, 100),

        eighteen(18, 50),

        nineteen(19, 20);

    }


  fun getScaleFromlevel(level: Int) = when (level) {
        MapLevel.three.level -> MapLevel.three.scale
        MapLevel.four.level -> MapLevel.four.scale
        MapLevel.five.level -> MapLevel.five.scale
        MapLevel.six.level -> MapLevel.six.scale
        MapLevel.seven.level -> MapLevel.seven.scale
        MapLevel.eight.level -> MapLevel.eight.scale
        MapLevel.nine.level -> MapLevel.nine.scale
        MapLevel.ten.level -> MapLevel.ten.scale
        MapLevel.eleven.level -> MapLevel.eleven.scale
        MapLevel.twelve.level -> MapLevel.twelve.scale
        MapLevel.thirteen.level -> MapLevel.thirteen.scale
        MapLevel.fourteen.level -> MapLevel.fourteen.scale
        MapLevel.fifteen.level -> MapLevel.fifteen.scale
        MapLevel.sixteen.level -> MapLevel.sixteen.scale
        MapLevel.seventeen.level -> MapLevel.seventeen.scale
        MapLevel.eighteen.level -> MapLevel.eighteen.scale
        MapLevel.nineteen.level -> MapLevel.nineteen.scale
        else -> MapLevel.five.scale
    }


    fun main(args: Array<String>) {
        var mapLevel: MapLevel = MapLevel.nineteen

        println(mapLevel.name)

        println(mapLevel.ordinal)

        println(MapLevel.valueOf("eighteen"))

        println(MapLevel.values()[14])

        println(getScaleFromlevel(3))

    }
}


