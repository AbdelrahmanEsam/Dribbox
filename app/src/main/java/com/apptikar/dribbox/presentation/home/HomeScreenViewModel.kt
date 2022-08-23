package com.apptikar.dribbox.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.apptikar.dribbox.domain.model.ColorType
import com.apptikar.dribbox.domain.model.Folder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class HomeScreenViewModel: ViewModel() {

    var searchState by mutableStateOf("")
    private set

    var typeState by mutableStateOf(ColorType.Blue)
        private set


    var categoriesState by mutableStateOf(listOf(Folder("Mobile apps","December 20.2020")
    ,Folder("SVG Icons","December 14.2020"),
        Folder("Prototypes","Novemaber 22.2020"),
        Folder("Avatars","Novemaber 10.2020"),
        Folder("Design","December 20.2020")
        ,Folder("SVG Icons","December 14.2020"),
        Folder("Prototypes","Novemaber 22.2020"),
        Folder("Avatars","Novemaber 10.2020"),
        Folder("Design","December 20.2020")
    ))
    private set

    var numberOfColumns =  mutableStateOf(2)
    private set







     fun setNumberOfColumns(columns :Int){
         numberOfColumns.value = columns
     }

    fun setWindowColor(){}

    fun search()
    {

    }

    fun setNewTypeState(color:ColorType){
        typeState = color
    }

    fun setNewSearchState(searchState:String)
    {
      this.searchState = searchState

    }



}