package kp.ran.temples

import androidx.lifecycle.LiveData
import javax.inject.Inject

class Repository @Inject constructor(val stdao:TempleDAO) {

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