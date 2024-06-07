package com.example.mvvmcomposeapp2.domain.usecases.app_entry

import com.example.mvvmcomposeapp2.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}