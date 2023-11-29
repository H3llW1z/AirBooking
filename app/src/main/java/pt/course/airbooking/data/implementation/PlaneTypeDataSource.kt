package pt.course.airbooking.data.implementation

import pt.course.airbooking.data.db.model.PlaneTypeDbModel

object PlaneTypeDataSource {

    fun getPlaneTypes(): List<PlaneTypeDbModel> {

        val planeType1 = PlaneTypeDbModel(
            1,
            "Boeing 737",
            "Компактный узкофюзеляжный самолет, предназначенный для коротких и средних дистанций. Один из самых массовых самолетов в мире.",
            215
        )
        val planeType2 = PlaneTypeDbModel(
            2,
            "Airbus A320",
            "Узкофюзеляжный самолет, предназначенный для коротких и средних дистанций. Один из самых популярных самолетов в мире.",
            150
        )
        val planeType3 = PlaneTypeDbModel(
            3,
            "Boeing 787 Dreamliner",
            "Среднефюзеляжный самолет, предназначенный для средних и дальних дистанций. Один из самых экономичных и комфортабельных самолетов в мире.",
            336
        )
        val planeType4 = PlaneTypeDbModel(
            4,
            "Airbus A330",
            "Среднефюзеляжный самолет, предназначенный для средних и дальних дистанций. Один из самых популярных самолетов в мире.",
            440
        )
        val planeType5 = PlaneTypeDbModel(
            5,
            "Boeing 777",
            "Широфюзеляжный самолет, предназначенный для дальних дистанций. Один из самых популярных самолетов в мире.",
            451
        )

        return listOf(planeType1, planeType2, planeType3, planeType4, planeType5)
    }
}