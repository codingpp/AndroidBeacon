package cn.condingpp.beacon.util

import java.util.*

/**
 * 工具类
 * @author SunPan
 * @date 2018/9/21
 */
class FormatUtil {

    companion object {

        fun hexStringToByteArray(hexString: String): ByteArray? {
            if (hexString.isBlank()) {
                return null
            }

            var tempString = hexString.uppercase(Locale.getDefault())
            if (tempString.length % 2 != 0) {
                tempString = "0$tempString"
            }

            val length = tempString.length / 2
            val hexChars = tempString.toCharArray()
            val resultArray = ByteArray(length)

            for (i in 0 until length) {
                val pos = i * 2
                resultArray[i] =
                        (charToInt(hexChars[pos]) shl 4 or charToInt(hexChars[pos + 1])).toByte()
            }
            return resultArray
        }

        private fun charToInt(c: Char): Int {
            return "0123456789ABCDEF".indexOf(c)
        }

        fun formatStringLength(targetLength: Int, src: String, leftCharacter: Char): String {
            return if (src.length > targetLength) {
                src.substring(src.length - targetLength)
            } else {
                var newSrc = src
                val delta = targetLength - newSrc.length
                for (i in 0 until delta) {
                    newSrc = leftCharacter + newSrc
                }
                newSrc
            }
        }
    }
}