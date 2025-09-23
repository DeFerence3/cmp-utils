package me.diffy.utils.toast

import androidx.compose.ui.awt.ComposeWindow
import java.awt.Color
import javax.swing.*

private var composeWindowProvider: () -> ComposeWindow? = {
    null
}

fun setComposeWindowProvider(provider: () -> ComposeWindow) {
    composeWindowProvider = provider
}
/***
 * <a href="https://proandroiddev.com/how-to-show-toasts-in-compose-multiplatform-android-ios-desktop-with-expect-actual-85c630d46d06">from here</a>
 */
actual open class ToastManager actual constructor() {
    actual fun showToast(message: String, toastDurationType: ToastDurationType) {
        val parent = composeWindowProvider.invoke()
        val durationType = when (toastDurationType) {
            ToastDurationType.SHORT -> 2000
            ToastDurationType.LONG -> 5000
        }
        if (parent != null) {
            val toast = JWindow(parent)
            toast.background = Color(0, 0, 0, 0) // Transparent window

            val panel = RoundedPanel(arcWidth = 28, arcHeight = 28)
            val label = JLabel(message)
            label.foreground = Color.WHITE
            label.background = Color.BLACK
            label.isOpaque = false
            label.border = BorderFactory.createEmptyBorder(10, 24, 10, 24)
            panel.add(label)
            toast.contentPane.add(panel)
            toast.pack()

            // Position at bottom center inside the parent window
            val parentBounds = parent.bounds
            val x = parentBounds.x + (parentBounds.width - toast.width) / 2
            val y = parentBounds.y + parentBounds.height - toast.height - 40
            toast.setLocation(x, y)
            toast.isVisible = true

            Timer(durationType) { toast.dispose() }.start()
        } else {
            // Fallback: show dialog if window reference is missing
            JOptionPane.showMessageDialog(null, message)
        }
    }
}