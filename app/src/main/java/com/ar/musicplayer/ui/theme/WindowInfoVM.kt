package com.ar.musicplayer.ui.theme

import androidx.lifecycle.ViewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.ar.musicplayer.data.models.InfoScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WindowInfoVM : ViewModel() {
    private val _windowWidthSizeClass = MutableStateFlow(WindowWidthSizeClass.COMPACT)
    val windowWidthSizeClass: StateFlow<WindowWidthSizeClass> = _windowWidthSizeClass

    private val _showBottomBar = MutableStateFlow(true)
    val showBottomBar: StateFlow<Boolean> = _showBottomBar

    private val _showPreviewScreen = MutableStateFlow(false)
    val showPreviewScreen: StateFlow<Boolean> = _showPreviewScreen

    private val _isTwoPaneLayout = MutableStateFlow(false)
    val isTwoPaneLayout: StateFlow<Boolean> = _isTwoPaneLayout

    // Add more UI states as needed
    private val _isLargeScreen = MutableStateFlow(false)
    val isLargeScreen: StateFlow<Boolean> = _isLargeScreen

    private val _isFullScreenPlayer = MutableStateFlow(false)
    val isFullScreenPlayer: StateFlow<Boolean> = _isFullScreenPlayer


    private val _selectedItem = MutableStateFlow<InfoScreenModel?>(null)
    val selectedItem: StateFlow<InfoScreenModel?> = _selectedItem

    private val _isPreviewVisible = MutableStateFlow(false)
    val isPreviewVisible: StateFlow<Boolean> = _isPreviewVisible

    private val _isMusicDetailsVisible = MutableStateFlow(false)
    val isMusicDetailsVisible: StateFlow<Boolean> = _isMusicDetailsVisible

    fun updateWindowWidthSizeClass(newSizeClass: WindowWidthSizeClass) {
        _windowWidthSizeClass.value = newSizeClass

        when (newSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                _showBottomBar.value = true
                _showPreviewScreen.value = false
                _isTwoPaneLayout.value = false
                _isLargeScreen.value = false
                _isMusicDetailsVisible.value = false
            }
            WindowWidthSizeClass.MEDIUM -> {
                _showBottomBar.value = false
                _showPreviewScreen.value = true
                _isTwoPaneLayout.value = false
                _isLargeScreen.value = false
                _isMusicDetailsVisible.value = true
            }
            WindowWidthSizeClass.EXPANDED -> {
                _showBottomBar.value = false
                _showPreviewScreen.value = true
                _isTwoPaneLayout.value = true
                _isLargeScreen.value = true
                _isMusicDetailsVisible.value = true
            }
        }
    }

    fun onItemSelected(item: InfoScreenModel) {
        _selectedItem.value = item
        _isMusicDetailsVisible.value = false
        _isPreviewVisible.value = true
    }

    fun closePreview() {
        _isPreviewVisible.value = false
    }

    fun closeMusicPreview() {
        _isMusicDetailsVisible.value = false
    }
    fun showMusicPreview(){
        _isPreviewVisible.value = false
        _isMusicDetailsVisible.value = true
    }
    fun toFullScreen(){
        _isFullScreenPlayer.value = true
        _isMusicDetailsVisible.value = false
        _showBottomBar.value = false
        _isPreviewVisible.value = false
    }

    fun closeFullScreen(){
        _isFullScreenPlayer.value = false
    }

    fun toggleBottomBarVisibility() {
        _showBottomBar.value = !_showBottomBar.value
    }

    fun togglePreviewScreenVisibility() {
        _showPreviewScreen.value = !_showPreviewScreen.value
    }

    fun enableTwoPaneLayout(enable: Boolean) {
        _isTwoPaneLayout.value = enable
    }

}