package com.gmail.petrusevich.volha.project

import org.json.JSONArray
import org.json.JSONObject


private const val JSON_ARRAY_NAME = "results"

class ExerciseDataMapper : (String) -> List<ExerciseDataModel> {

    override fun invoke(jsonData: String): List<ExerciseDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArray = jsonObject.getJSONArray(JSON_ARRAY_NAME)
        if (jsonArray.length() != 0) {
            val exerciseList = mutableListOf<ExerciseDataModel>()
            for (index in 0 until jsonArray.length()) {
                val exerciseData = with(jsonArray.getJSONObject(index)) {
                    ExerciseDataModel(
                            categoryExercise = getString("category"),
                            nameExercise = getString("name"),
                            descriptionExercise = getString("description")
                    )
                }
                exerciseList.add(exerciseData)
            }

            return exerciseList
        }
        return emptyList()
    }

}