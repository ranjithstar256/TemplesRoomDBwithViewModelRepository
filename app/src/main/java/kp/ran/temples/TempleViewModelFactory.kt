package kp.ran.temples

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TempleViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TempleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TempleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
