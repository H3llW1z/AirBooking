package pt.course.airbooking.data.implementation

import pt.course.airbooking.data.db.model.PlaneTypeDbModel

object PlaneTypeDataSource {

    fun getPlaneTypes(): List<PlaneTypeDbModel> {
        val result = mutableListOf<PlaneTypeDbModel>()

        repeat(5) {
            result.add(PlaneTypeDbModel(it.toLong(), "Description $it", it * 100))
        }
        return result
    }
}