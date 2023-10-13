package kp.ran.temples

class Repository(val stdao:TempleDAO) {
    suspend fun insertTemple(temple: Temple) {
        stdao.insert(temple)
    }

    /*suspend fun getAllUsers() = stdao.getAllStudents()

    suspend fun updateUser(user: Students) {
        stdao.update(user)
    }

    suspend fun deleteUser(user: Students) {
        stdao.delete(user)
    }*/
}