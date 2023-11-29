package pt.course.airbooking.data.implementation

import pt.course.airbooking.data.db.model.PlaneTypeDbModel

object PlaneTypeDataSource {

    fun getPlaneTypes(): List<PlaneTypeDbModel> {
        val result = mutableListOf<PlaneTypeDbModel>()

        repeat(5) {
            result.add(
                PlaneTypeDbModel(
                    it.toLong(),
                    "Plane ${it + 1}",
                    "Description ${it + 1}",
                    it + 1 * 100
                )
            )
        }
        return result
    }
}