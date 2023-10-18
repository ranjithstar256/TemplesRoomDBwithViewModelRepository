package kp.ran.temples

import androidx.lifecycle.LiveData

class Repository(val stdao:TempleDAO) {

    suspend fun insertTemple(temple: Temple) {
        stdao.insert(temple)
    }

    val allTemples: LiveData<List<Temple>> = stdao.getAllLiveTemples()

    /*suspend fun getAllUsers() = stdao.getAllStudents()

    suspend fun updateUser(user: Students) {
        stdao.update(user)
    }

    suspend fun deleteUser(user: Students) {
        stdao.delete(user)
    }*/
}