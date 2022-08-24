package com.apptikar.dribbox.presentation.storage

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.os.StatFs
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.apptikar.dribbox.data.ItemDetail
import com.apptikar.dribbox.presentation.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File




class StorageViewModel constructor(private val context: Application)  : AndroidViewModel(context) {

    var totalMemorySize = MutableStateFlow(0L)
        private set

    var freeMemorySize = MutableStateFlow(0L)
        private set

    var other = MutableStateFlow(0L)
    private set

    var otherPercentage = MutableStateFlow(0.0)
        private set

    val listOfDataRows = MutableStateFlow(mutableListOf<ItemDetail>())


   private fun getTotalInternalMemorySize() {
        val path: File = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val totalBlocks = stat.blockCountLong
        this.totalMemorySize.value =  totalBlocks * blockSize
    }

    private  fun getAvailableInternalMemorySize() {
        val path = Environment.getDataDirectory()
        val stat = StatFs(path.path)
        val blockSize = stat.blockSizeLong
        val availableBlocks = stat.availableBlocksLong
        freeMemorySize.value =  availableBlocks * blockSize
    }

    @SuppressLint("QueryPermissionsNeeded")
   private fun getAllApps() : Long{
        val infos: List<ApplicationInfo> =  context.packageManager.getInstalledApplications(
            PackageManager.GET_META_DATA)
        var appsSize = 0L
        var installedApps = 0

        for (info in infos) {
            if (info.flags and ApplicationInfo.FLAG_SYSTEM != 1)  {
                appsSize+=File(info.sourceDir).length()
                installedApps++
            }
        }
        return appsSize
    }


 private fun loadVideosFromSDCard() : Long {
        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
        var videosSize = 0L
        val cursor  = context.contentResolver.query(uri, projection, null, null, null)
        val  columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor.moveToNext()) {
            val absolutePathOfVideo =   cursor.getString(columnIndexData)
            videosSize += File(absolutePathOfVideo).length()
        }
        cursor.close()
       return videosSize
    }


   private fun loadAudioFromSDCard() : Long
    {
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
        var audiosSize = 0L
        val cursor  = context.contentResolver.query(uri, projection, null, null, null)
        val  columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor.moveToNext()) {
            val absolutePathOfVideo =   cursor.getString(columnIndexData)
            audiosSize += File(absolutePathOfVideo).length()
        }
        cursor.close()
       return  audiosSize
    }


    @SuppressLint("Recycle")
    private fun loadImagesFromSDCard() : Long{
        var imagesSize = 0L
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null,null)
        val  columnIndexData = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor.moveToNext()) {
            val  absolutePathOfImage = cursor.getString(columnIndexData)
            imagesSize+= File(absolutePathOfImage).length()
        }
       return imagesSize
    }

    init {
        viewModelScope.launch {
          val audioSize =  loadAudioFromSDCard()
          val videosSize = loadVideosFromSDCard()
          val imagesSize =  loadImagesFromSDCard()
          val appsSize =  getAllApps()
          getTotalInternalMemorySize()
          getAvailableInternalMemorySize()
           val  imagesPercentage = (imagesSize.toDouble()/(totalMemorySize.value.toDouble()-freeMemorySize.value.toDouble()))
           val appsPercentage = (appsSize.toDouble()/(totalMemorySize.value.toDouble()-freeMemorySize.value.toDouble()))
           val   videosPercentage = (videosSize.toDouble()/(totalMemorySize.value.toDouble()-freeMemorySize.value.toDouble()))
           val  audiosPercentage = (audioSize.toDouble()/(totalMemorySize.value.toDouble()-freeMemorySize.value.toDouble()))
            other.value = (totalMemorySize.value-freeMemorySize.value) -(imagesSize+appsSize+videosSize + audioSize)
            otherPercentage.value = (other.value.toDouble()/(totalMemorySize.value.toDouble()-freeMemorySize.value.toDouble()))
            listOfDataRows.value.apply {
                add(ItemDetail("Images",imagesSize,imagesPercentage, PurpleDark))
                add(ItemDetail("Apps",appsSize,appsPercentage, PureYellow))
                add(ItemDetail("Videos",videosSize,videosPercentage, PureGreen))
                add(ItemDetail("Audios",audioSize,audiosPercentage, LightBlue))
                add(ItemDetail("Others",other.value,otherPercentage.value, Orange))
            }

        }

    }

}