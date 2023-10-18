package kp.ran.temples

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TempleViewModel @Inject constructor (var repository: Repository):ViewModel() {

    val allTemples: LiveData<List<Temple>> = repository.allTemples

    fun insertStudent(temple: Temple) {
        viewModelScope.launch {
            repository.insertTemple((temple))
        }
    }
}


/*
class StudentViewModel(private val repository: Repository) : ViewModel() {

    // Function to insert a student
    fun insertStudent(student: Students) {
        viewModelScope.launch {
            repository.insertUser(student)
        }
    }

    // Function to get all students as a Flow
    suspend fun getAllStudents(): List<Students> {
        return repository.getAllUsers()
    }

    // Function to update a student
    fun updateStudent(student: Students) {
        viewModelScope.launch {
            repository.updateUser(student)
        }
    }

    // Function to delete a student
    fun deleteStudent(student: Students) {
        viewModelScope.launch {
            repository.deleteUser(student)
        }
    }
}*/
