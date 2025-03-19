package kp.ran.temples

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTempleDatabase(@ApplicationContext context: Context): TempleDatabase {
        return TempleDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideTempleDao(database: TempleDatabase): TempleDAO {
        return database.templedao()
    }

    @Singleton
    @Provides
    fun provideRepository(templeDao: TempleDAO): Repository {
        return Repository(templeDao)
    }
}