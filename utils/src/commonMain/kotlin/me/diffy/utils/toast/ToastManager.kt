package me.diffy.utils.toast

/***
 * <a href="https://proandroiddev.com/how-to-show-toasts-in-compose-multiplatform-android-ios-desktop-with-expect-actual-85c630d46d06">from here</a>
 */
expect open class ToastManager() {
    fun showToast(message: String, toastDurationType: ToastDurationType)
}