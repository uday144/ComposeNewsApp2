package com.example.mvvmcomposeapp2.domain.usecases.app_entry

import com.example.mvvmcomposeapp2.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}