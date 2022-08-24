package com.apptikar.dribbox.presentation.profile

import android.content.ContentResolver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.apptikar.dribbox.domain.model.Folder
import dagger.hilt.android.lifecycle.HiltViewModel


class MyProfileViewModel : ViewModel() {
    var categoriesState by mutableStateOf(listOf(
        Folder("Mobile apps ","December 20.2020")
        , Folder("SVG Icons","December 14.2020"),
        Folder("Prototypes","Novemaber 22.2020"),
        Folder("Avatars","Novemaber 10.2020"),
        Folder("Design","December 20.2020")
        , Folder("SVG Icons","December 14.2020"),
        Folder("Prototypes","Novemaber 22.2020"),
        Folder("Avatars","Novemaber 10.2020"),
        Folder("Design","December 20.2020")
    ))
        private set
}